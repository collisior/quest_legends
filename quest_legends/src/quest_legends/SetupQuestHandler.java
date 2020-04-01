package quest_legends;

import java.util.ArrayList;

import quest_legends.GameBoard.Piece;
import quest_legends.Helpers.CSVFilesHandler;
import quest_legends.Helpers.Color;
import quest_legends.Helpers.FilesInfo;
import quest_legends.Helpers.InputHandler;
import quest_legends.QuestCharacters.Dragon;
import quest_legends.QuestCharacters.Exoskeleton;
import quest_legends.QuestCharacters.Hero;
import quest_legends.QuestCharacters.Monster;
import quest_legends.QuestCharacters.Paladin;
import quest_legends.QuestCharacters.QuestCharacter;
import quest_legends.QuestCharacters.Sorcerer;
import quest_legends.QuestCharacters.Spirit;
import quest_legends.QuestCharacters.Warrior;

public class SetupQuestHandler implements FilesInfo, Color {

	static ArrayList<String> list = new ArrayList<String>();
	public static void setupTeam(Quest quest) {
		String[] colors = { RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE };
		for (String t : colors) {
			list.add(t);
		}
		
		System.out.printf(
				"\nHow many players will be playing in your team? \nMax number of players in one team is %d. \n",
				Quest.TEAM_CAPACITY);
		int numPlayers = InputHandler.getInteger(1, Quest.TEAM_CAPACITY);
		for (int j = 0; j < numPlayers; j++) {
			Player player = quest.addPlayer();
			quest.team.addPlayer(player);
			choosePieceFigure(player);
			Hero hero = (Hero) chooseQuestHero(player);
			player.setHero(hero);
			player.setTeamId(quest.team.getId());
			System.out.println(player.color + player + " your Hero: " + player.getHero() + RESET);
		}
		
		quest.addTeam(quest.team);
//		board.putInRandomCell(team.getPiece());
	}
	
	private static void choosePieceFigure(Player player) {
		System.out.println(player + ", choose playing figure: ");
		char figure = '@';

		String l1 = "";
		String l2 = "";
		for (int i = 0; i < list.size(); i++) {
			l1 += "" + i + "  ";
			l2 += "" + list.get(i) + figure + RESET + "  ";
		}
		System.out.println(l1 + "\n" + l2);
		int index = InputHandler.getInteger(0, list.size());
		Piece piece = new Piece(figure);
		piece.setColor(list.get(index));
		player.color = list.get(index);
		player.setPiece(piece);
		list.remove(index);
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

		String filename = getHeroFilename(clist[0]);
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
				if (Character.isLetter(heroType) && (!getHeroFilename(heroType).isEmpty())) {
					String filename = getHeroFilename(clist[0]);
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


	private static String getHeroFilename(char c) {
		String filename = "";
		if (c == 'W') {
			filename = "csv/Warriors.csv";
		}
		if (c == 'P') {
			filename = "csv/Paladins.csv";
		}
		if (c == 'S') {
			filename = "csv/Sorcerers.csv";
		}
		return filename;
	}

	public static ArrayList<Monster> generateMonsters(Team team) {
		ArrayList<Monster> listMonsters = new ArrayList<Monster>();
		int highestHeroLevel = 0;
		for (Player player : team.getTeam()) {
			if (highestHeroLevel < player.getHero().getLevel()) {
				highestHeroLevel = player.getHero().getLevel();
			}
		}
		for (Player player : team.getTeam()) {
			Monster monster = null;
			while (monster == null) {
				monster = generateMonster((int) Math.round(highestHeroLevel));
			}
			listMonsters.add(monster);
		}
		return listMonsters;
	}

	private static Monster generateMonster(int highestHeroLevel) {
		String filename = getRandomMonsterFilename();
		String[] monsterData = getRandomMonsterFrom(filename, highestHeroLevel);
		if (monsterData == null) {
			return null;
		}
		int[] monsterDataInt = CSVFilesHandler.convertListStringToInteger(monsterData);
		Monster monster = null;
		if (filename.equals("csv/Dragons.csv")) {
			monster = new Dragon(monsterData[0], monsterDataInt[0], monsterDataInt[1], monsterDataInt[2],
					monsterDataInt[3]);
		}
		if (filename.equals("csv/Exoskele.csv")) {
			monster = new Exoskeleton(monsterData[0], monsterDataInt[0], monsterDataInt[1], monsterDataInt[2],
					monsterDataInt[3]);
		}
		if (filename.equals("csv/Spirits.csv")) {
			monster = new Spirit(monsterData[0], monsterDataInt[0], monsterDataInt[1], monsterDataInt[2],
					monsterDataInt[3]);
		}
		return monster;
	}

	private static String[] getRandomMonsterFrom(String filename, int highestHeroLevel) {
		ArrayList<Integer> listIndexes = getValidLevelMonstersFrom(filename, highestHeroLevel);
		if (listIndexes.isEmpty()) {
			return null;
		}
		int r = listIndexes.size() - 1;
		int randomIndex = listIndexes.get(r);
		String[] monsterData = CSVFilesHandler.retrieveDataFromFileByIndex(filename, randomIndex);

		return monsterData;
	}

	private static ArrayList<Integer> getValidLevelMonstersFrom(String filename, int highestHeroLevel) {
		ArrayList<Integer> listIndexes = new ArrayList<Integer>();

		for (int i = 1; i < CSVFilesHandler.map.get(filename); i++) {
			String[] s = CSVFilesHandler.retrieveDataFromFileByIndex(filename, i);
			int level = Integer.parseInt(s[1]);
			if (level == highestHeroLevel) {
				listIndexes.add(i);
			}
		}
		return listIndexes;
	}

	private static String getRandomMonsterFilename() {
		int randomNum = Quest.random.nextInt(3);
		String filename = null;
		if (randomNum == 0) {
			filename = "csv/Dragons.csv";
		}
		if (randomNum == 1) {
			filename = "csv/Exoskeletons.csv";
		}
		if (randomNum == 2) {
			filename = "csv/Spirits.csv";
		}
		return filename;
	}

}