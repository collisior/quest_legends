package quest_legends;

import java.util.ArrayList;

import quest_legends.Helpers.CSVFilesHandler;
import quest_legends.Helpers.Color;
import quest_legends.Helpers.GenericMethods;
import quest_legends.Helpers.InputHandler;
import quest_legends.Helpers.QuestDetails;
import quest_legends.QuestCharacters.Hero;
import quest_legends.QuestCharacters.Paladin;
import quest_legends.QuestCharacters.QuestCharacter;
import quest_legends.QuestCharacters.Sorcerer;
import quest_legends.QuestCharacters.Warrior;

public class SetupQuestHandler implements QuestDetails, Color {

	static ArrayList<String> list = new ArrayList<String>();

	public static void setupTeam(Quest quest) {
//		System.out.printf(
//				"\nHow many players will be playing in your team? \nMax number of players in one team is %d. \n",
//				Quest.TEAM_CAPACITY);
//		int numPlayers = InputHandler.getInteger(1, Quest.TEAM_CAPACITY);
		int numPlayers = TEAM_CAPACITY;
		for (int j = 0; j < numPlayers; j++) {
			Player player = quest.addPlayer();
			quest.team.addPlayer(player);
			Hero hero = (Hero) chooseQuestHero(player);
			player.setHero(hero);
			player.setPiece(HERO_PIECE);
			player.setTeam(quest.team);
			System.out.println(player + " your Hero is " + player.getHero());
		}
		
		quest.addTeam(quest.team);
		quest.team.shuffleTurns();
	}

	static QuestCharacter chooseQuestHero(Player player) {
		char[] clist = null;
		if (player.getHeroChosen() != null) {
			System.out.println("\n" + player + ": do you want to choose new Hero? "
					+ "\n (if no, your Hero from previous quest will reset.");
			if (InputHandler.YesOrNo()) {
				clist = null;
			} else {
				clist = player.getHeroChosen();
			}
		}
		if (clist == null) {
			CSVFilesHandler.displayMultipleFiles(heroesFilenames);
			System.out.println("\n" + GREEN + player + RESET
					+ ", choose Quest character by entering Hero type (W - warrior, P - paladin, S - sorcerer) \n"
					+ "and index # of specified hero. For example, to choose Gaerdal_Ironhand (Warrior, index = 1) type 'W1'.");
			clist = chooseNewHero(player);
		}

		String filename = QuestDetails.getHeroFilename(clist[0]);
		int num = Integer.parseInt("" + clist[1]);
		String[] heroData = CSVFilesHandler.retrieveDataFromFileByIndex(filename, num);
		player.setHeroChosen(clist);
		return produceHero(heroData, clist[0]);
	}

	static char[] chooseNewHero(Player player) {
		char[] clist = null;
		int num = 0;
		boolean inputIsValid = false;
		char heroType = 0;
		while (inputIsValid == false) {
			String input = InputHandler.getString();
			input = input.replace(" ", "");
			clist = input.toCharArray();
			heroType = clist[0];
			if (input.length() == 2) {
				if (Character.isLetter(heroType) && (!QuestDetails.getHeroFilename(heroType).isEmpty())) {
					String filename = QuestDetails.getHeroFilename(clist[0]);
					if (Character.isDigit(input.charAt(1))) {
						int max = CSVFilesHandler.map.get(filename);
						num = Integer.parseInt("" + clist[1]);
						if ((num >= 1) && (num <= max)) {
							player.setHeroChosen(clist);
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
		return clist;
	}

	public static QuestCharacter produceHero(String[] heroData, char heroType) {
		Hero hero = null;
		int[] heroDataInt = CSVFilesHandler.convertListStringToInteger(heroData);
		if (heroType == 'W') {
			hero = new Warrior(heroData[0], heroDataInt[0], heroDataInt[1], heroDataInt[2], heroDataInt[3],
					heroDataInt[4], heroDataInt[5]);
		}
		if (heroType == 'P') {
			hero = new Paladin(heroData[0], heroDataInt[0], heroDataInt[1], heroDataInt[2], heroDataInt[3],
					heroDataInt[4], heroDataInt[5]);
		}
		if (heroType == 'S') {
			hero = new Sorcerer(heroData[0], heroDataInt[0], heroDataInt[1], heroDataInt[2], heroDataInt[3],
					heroDataInt[4], heroDataInt[5]);
		}
		return hero;
	}

}