package quest_legends.Helpers;

import quest_legends.GameBoard.Piece;

public interface QuestDetails {
	
	public static String[] heroesFilenames = { "csv/Warriors.csv", "csv/Paladins.csv", "csv/Sorcerers.csv" };
	public static String[] monstersFilenames = { "csv/Exoskeletons.csv", "csv/Dragons.csv", "csv/Spirits.csv" };
	public static String[] marketFilenames = { "csv/Armory.csv", "csv/Potions.csv", "csv/Weaponry.csv",
			"csv/IceSpells.csv", "csv/FireSpells.csv", "csv/LightningSpells.csv" };
	public static String[] mascotsFilenames = {"csv/Mascots.csv"};
	
	
	public static Piece MONSTER_PIECE = new Piece('M');
	public static Piece HERO_PIECE = new Piece('H');
	
	public final static int TEAM_CAPACITY = 3;
	public final static int END_LEVEL = 10; // max level number to reach
	public final static int MONSTER_SPAWN_FREQUENCY = 2; // the number of rounds after which new set of monsters occur
	

	public static String getHeroFilename(char c) {
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
}
