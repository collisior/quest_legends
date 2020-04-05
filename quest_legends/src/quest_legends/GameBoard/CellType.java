package quest_legends.GameBoard;

import quest_legends.QuestCharacters.Hero;

public interface CellType {
	
	public static String NEXUS = "Nexus";
	public static String PLAIN = "Plain";
	public static String BUSH = "Bush";
	public static String KOULOU = "Koulou";
	public static String CAVE = "Cave";
	public static String BLOCKED = "Blocked";


	
	/*
	 * Generate random cell type, return this type.
	 */
	public static String getRandomCell() {
		double x = Math.random();
		if (x < 0.2) {
			return PLAIN; 
		} else if (x < 0.4) {
			return BUSH; 
		} else if (x < 0.6) {
			return KOULOU; 
		} else {
			return CAVE;
		}
	}
	
	/*
	 * Handle cell type (boost hero's skills)
	 */
	public static void boostSkills(QuestBoard board, Hero hero) {
		
		if (board.getBoard()[hero.current_row][hero.current_col].getType() == BUSH) {
			hero.setDexterity(hero.getDexterity()*1.15);
		} else if (board.getBoard()[hero.current_row][hero.current_col].getType() == KOULOU) {
			hero.setStrength(hero.getStrength()*1.15);
		} else if (board.getBoard()[hero.current_row][hero.current_col].getType() == CAVE) {
			hero.setAgility(hero.getAgility()*1.15);
		} else {
			
		}
	}
}