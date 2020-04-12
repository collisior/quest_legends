package quest_legends.Helpers;

import java.util.ArrayList;

import quest_legends.Player;
import quest_legends.Quest;
import quest_legends.Team;
import quest_legends.QuestCharacters.Dragon;
import quest_legends.QuestCharacters.Exoskeleton;
import quest_legends.QuestCharacters.Monster;
import quest_legends.QuestCharacters.Spirit;

public class Generator implements QuestDetails {
	
	public static String getRandomMonsterFilename() {
		int randomNum = Quest.random.nextInt(monstersFilenames.length-1);
		return monstersFilenames[randomNum];
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
			monster.id = Quest.getAvailableId();
			Quest.updateAvailableId();
			System.out.println("Monster: " + monster.id);
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
}
