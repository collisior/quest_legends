package quest_legends.GameBoard;

public interface CellType {
	
	public static String NEXUS = "Nexus";
	public static String PLAIN = "Plain";
	public static String BUSH = "Bush";
	public static String KOULOU = "Koulou";
	public static String CAVE = "Cave";
	public static String BLOCKED = "Blocked";
	public static Piece MONSTER_PIECE = new Piece('M');
	
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
}