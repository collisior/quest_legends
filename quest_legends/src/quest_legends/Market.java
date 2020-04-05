package quest_legends;

import quest_legends.Ammunitions.Ammunition;
import quest_legends.Ammunitions.Armor;
import quest_legends.Ammunitions.FireSpell;
import quest_legends.Ammunitions.IceSpell;
import quest_legends.Ammunitions.LightningSpell;
import quest_legends.Ammunitions.Potion;
import quest_legends.Ammunitions.Weapon;
import quest_legends.Helpers.CSVFilesHandler;
import quest_legends.Helpers.QuestDetails;
import quest_legends.Helpers.InputHandler;
import quest_legends.QuestCharacters.Hero;
import quest_legends.QuestCharacters.QuestCharacter;

public class Market implements QuestDetails {

	public static void enter(Player player) {

		System.out.println(player + ", do you want sell your items for the half of the price?");
		if (InputHandler.YesOrNo()) {
			sell(player.getHero());
		}
		System.out.println(player + ", do you want to purchase new ammunition from Market?");
		if (InputHandler.YesOrNo()) {
			purchase(player.getHero());
		}

		System.out.println(player + ", do you want exit Market? (Type 'N' if you want to continue shopping/selling)");
		if (InputHandler.YesOrNo()) {
			// player exits market
		} else {
			enter(player);
		}
	}

	public static void showcase() {
		CSVFilesHandler.displayMultipleFiles(marketFilenames);
	}

	public static Ammunition chooseItem() {

		System.out.println("Choose Ammunition type (A - Armor, W - Weapon, P - Potion, I - Ice Spell, etc.) "
				+ "and index # of specified ammunition. \ne.g. to buy Platinum_Shield (Armor, index = 1) type 'A1'."
				+ "\nType '0' to exit Magic Market.");
		boolean inputIsValid = false;
		int num = 0;
		String[] ammunitionData = null;
		char ammunitionType = 0;
		while (inputIsValid == false) {
			String input = InputHandler.getString();
			input = input.replace(" ", "");
			char[] clist = input.toCharArray();
			if (clist[0] == '0') {
				inputIsValid = true;
				return null;
			}
			ammunitionType = clist[0];
			if (input.length() == 2) {
				if (Character.isLetter(ammunitionType) && (!getMarketFilename(ammunitionType).isEmpty())) {
					String filename = getMarketFilename(clist[0]);
					if (Character.isDigit(input.charAt(1))) {
						int max = CSVFilesHandler.map.get(filename);
						num = Integer.parseInt("" + clist[1]);
						if ((num >= 1) && (num <= max)) {
							ammunitionData = CSVFilesHandler.retrieveDataFromFileByIndex(filename, num);
							inputIsValid = true;
						} else {
							System.out.println("Invalid input. Number is out of range.");
						}
					}

				} else {
					System.out.println("Invalid input, must be one letter followed by one integer. Try again. ");
				}
			}
		}
		return retrieveAmmunition(ammunitionData, ammunitionType);
	}

	private static Ammunition retrieveAmmunition(String[] ammunitionData, char ammunitionType) {
		Ammunition amm = null;
		int[] ammunitionDataInt = CSVFilesHandler.convertListStringToInteger(ammunitionData);
		if (ammunitionType == 'A') {
			amm = new Armor(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1], ammunitionDataInt[2]);
		}
		if (ammunitionType == 'W') {
			amm = new Weapon(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1], ammunitionDataInt[2],
					ammunitionDataInt[3]);
		}
		if (ammunitionType == 'P') {
			amm = new Potion(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1], ammunitionDataInt[2]);
		}
		if (ammunitionType == 'I') {
			amm = new IceSpell(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1], ammunitionDataInt[2],
					ammunitionDataInt[3]);
		}
		if (ammunitionType == 'F') {
			amm = new FireSpell(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1], ammunitionDataInt[2],
					ammunitionDataInt[3]);
		}
		if (ammunitionType == 'L') {
			amm = new LightningSpell(ammunitionData[0], ammunitionDataInt[0], ammunitionDataInt[1],
					ammunitionDataInt[2], ammunitionDataInt[3]);
		}
		return amm;
	}

	private static String getMarketFilename(char c) {
		String filename = "";
		if (c == 'A') {
			filename = "csv/Armory.csv";
		}
		if (c == 'W') {
			filename = "csv/Weaponry.csv";
		}
		if (c == 'P') {
			filename = "csv/Potions.csv";
		}
		if (c == 'I') {
			filename = "csv/IceSpells.csv";
		}
		if (c == 'L') {
			filename = "csv/LightningSpells.csv";
		}
		if (c == 'F') {
			filename = "csv/FireSpells.csv";
		}
		return filename;
	}

	public static void purchase(QuestCharacter questCharacter) {
		showcase();
		Hero hero = (Hero) questCharacter;
		double budget = hero.getMoney();
		int max_level = hero.getLevel();
		Ammunition item = chooseItem();
		if (item == null) {
			return;
		}
		if ((item.getCost() <= budget) && (item.getRequired_level() <= max_level)) {
			hero.setMoney(budget - item.getCost());
			hero.getStorage().add(item);
			System.out.println("\n" + item + " is in your storage!");
		} else if ((item.getCost() > budget) && (item.getRequired_level() <= max_level)) {
			System.out.println("You can't afford this Ammuntion: " + item.getName());
		} else if ((item.getRequired_level() > max_level)) {
			System.out.println("You can't purchase this Ammunition, higher level required! Your current level is: "
					+ hero.getLevel());
		}
		System.out.println("Do you want to continue shopping?");
		if (InputHandler.YesOrNo()) {
			purchase(questCharacter);
		}
	}

	public static void sell(QuestCharacter questCharacter) {
		Hero hero = (Hero) questCharacter;
		if (hero.getStorage().size() <= 0) {
			System.out.println("Your storage is empty! No items to sell.");
		} else {
			hero.getStorage().showStorage();
			System.out.println("\nWhich item do you want to sell? Type index # of item to sell.");
			System.out.println("\nType '" + hero.getStorage().size() + "' to exit.");
			int index = InputHandler.getInteger(0, hero.getStorage().size() - 1);
			if (index == hero.getStorage().size())
				return;
			Ammunition item = hero.getStorage().get(index);
			hero.setMoney(hero.getMoney() + item.getCost() / 2);
			hero.getStorage().remove(index);
			System.out.println("You sold " + item + " for $" + item.getCost() / 2);

			System.out.println("\n Do you want to continue selling?");
			if (InputHandler.YesOrNo()) {
				sell(questCharacter);
			}
		}

	}
}
