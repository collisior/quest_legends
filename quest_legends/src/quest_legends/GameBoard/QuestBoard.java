package quest_legends.GameBoard;

import java.util.ArrayList;

import quest_legends.Game;
import quest_legends.Player;
import quest_legends.Quest;
import quest_legends.Team;
import quest_legends.TeamQuest;
import quest_legends.Helpers.Color;
import quest_legends.Helpers.QuestDetails;
import quest_legends.Helpers.Vizualization;
import quest_legends.QuestCharacters.Hero;
import quest_legends.QuestCharacters.Monster;

public class QuestBoard extends Board implements CellType, Color, Vizualization, QuestDetails {
	
	public ArrayList<Monster> aliveMonsters = new ArrayList<Monster>();
	/*
	 * Constructor to initialize Quest board (dimensions retrieved from user)
	 */
	public QuestBoard() {
		super();
		spreadCells();
	}

	/*
	 * Constructor to initialize fixed ROWxCOL board
	 */
	public QuestBoard(int x, int y) {
		super(x, y);
		spreadCells();
	}

	public boolean isValidMove(Player player, int row, int col) {
		System.out.println("Row, col = " + row+" " + col);
		if (boardPositionExists(row, col)) {
			if (this.getBoard()[row][col].getType() == BLOCKED) {
				System.out.println(BLOCKED_CELL_MESSAGE);
				return false;
			} else if (this.getBoard()[row][col].pieceExists(HERO_PIECE)) {
				System.out.println(ANOTHER_HERO_OCCUPIED_MESSAGE);
				return false;
			}
			else {
				System.out.println("Updating position..." + row+" " + col);
				updateBoard(player, row, col);
				return true;
			}
		}
		return false;
	}
	/*
	 * Move player's mark on targeted cell position on the game-board.
	 */
	public void updateBoard(Player player, int row, int col) {
		this.getBoard()[player.current_row][player.current_col].removePiece(player.getPiece());
		board[row][col].placePiece(player.getPiece());
		player.updatePosition(row, col);
	}

	/*
	 * Move monster's mark one cell further towards Hero's Nexus.
	 */
	public void moveMonster(Monster monster) {
		board[monster.current_row][monster.current_col].removePiece(MONSTER_PIECE);
		board[monster.current_row + 1][monster.current_col].placePiece(MONSTER_PIECE);
		monster.updatePosition(monster.current_row + 1, monster.current_col);
	}

	/*
	 * Current cell further actions.
	 */
	public void cellTypeHandler(Player currentPlayer) {
		Cell cell = board[currentPlayer.current_row][currentPlayer.current_col];
		
		if (cell.pieceExists(MONSTER_PIECE)) { // This cell contains monster -> start fight
			// TODO: fight with monster
		} else {
			CellType.boostSkills(this, (Hero) currentPlayer.getHero());
		}
	}

	private void spreadCells() {
		for (int r = 0; r < rows; r++) {
			if (r == 0 || r == rows-1) {	// Nexus
				for (int c = 0; c < cols; c++) {
					board[r][c].setType(NEXUS);
					if ((c+1)%3 == 0) {
						board[r][c].setType(BLOCKED);
					}
					
				}
			} else {
				for (int c = 0; c < cols; c++) {
					board[r][c].setType(CellType.getRandomCell());
					if ((c+1)%3 == 0) {
						board[r][c].setType(BLOCKED);
					}
					
				}
				
			}
			
		}
		
	}
	/*
	 * Generate random piece, return this piece.
	 */
	public void displayBoard(Game game) {
		if (game instanceof Quest) {
			DisplayBoard.showQuestBoard((Quest) game, this);
		}
	}
	/*
	 * Spread p;ayers on their Nexus area.
	 */
	public void spreadPlayers(Team team) {
		int Nexus_col = 0; int lane = 1;
		for(Player player : team.getTeam()) {
			int random_col = Quest.random.nextInt(10)%2 + Nexus_col;
			updateBoard(player, rows-1, random_col);
			player.setHomeLane(lane);
			Nexus_col += 3; lane++;
		}
	}


}


