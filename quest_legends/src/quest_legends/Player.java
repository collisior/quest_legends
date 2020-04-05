package quest_legends;

import quest_legends.GameBoard.Piece;
import quest_legends.QuestCharacters.QuestCharacter;

/*
* The Player class models Player.
*/

public class Player {

	private Piece piece; // e.g. can be used in TicTacToe, Checkers
	boolean WinnerStatus;
	private int totalWins;
	public int current_row = 0, current_col = 0;
	private int home_lane = 0;
	private int id = -1;
	private int teamId;
	private QuestCharacter hero;
	public String color;
	private char[] heroChosen = null;


	// Constructor to initialize this Player
	public Player(int id) {
		this.WinnerStatus = false;
		this.totalWins = 0;
		setId(id);
	}
	

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
	public void updatePosition(int row, int col) {
		if(hero!=null) {
			hero.updatePosition(row, col);
		}
		current_row = row;
		current_col = col;
	}

	/*
	 * Retrieve this Player's figure
	 */
	public Piece getPiece() {
		return this.piece;
	}

	/*
	 * Set this Player's piece
	 */
	public void setPiece(Piece p) {
		this.piece = p;
	}

	/*
	 * Set this Player's team id
	 */
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	
	/*
	 * Clear this Player's team id
	 */
	public void clearTeamId() {
		setTeamId(-1);
	}

	/*
	 * Retrieve this Player's team id
	 */
	public int getTeamId() {
		return this.teamId;
	}

	/*
	 * Change this Player's state to Winner
	 */
	public void changeToWinner() {
		if (this.WinnerStatus == false) {
			this.WinnerStatus = true;
		}
	}

	/*
	 * Change this Player's state to Non Winner
	 */
	public void changeToNonWinner() {
		if (this.WinnerStatus == true) {
			this.WinnerStatus = false;
		}
	}

	/*
	 * Update Player's total number of wins
	 */
	public void addWin() {
		this.totalWins++;
	}

	// Check if this Player is a Winner (return true if Winner)
	public boolean isWinner() {
		return this.WinnerStatus;
	}

	public int getTotalWins() {
		return this.totalWins;
	}

	public boolean isHuman() {
//		if ()
		return false;
	}

	public String toString() {
		return "H" + getId();
	}

	public QuestCharacter getHero() {
		return hero;
	}

	public void setHero(QuestCharacter hero) {
		this.hero = hero;
	}

	public char[] getHeroChosen() {
		return heroChosen;
	}

	public void setHeroChosen(char[] heroChosen) {
		this.heroChosen = heroChosen;
	}

	public int getCurrentRow() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getHomeLane() {
		return home_lane;
	}


	public void setHomeLane(int home_lane) {
		this.home_lane = home_lane;
	}
}
