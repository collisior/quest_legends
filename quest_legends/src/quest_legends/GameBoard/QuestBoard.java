package quest_legends.GameBoard;

import java.util.ArrayList;
import quest_legends.Fight;
import quest_legends.Game;
import quest_legends.Player;
import quest_legends.Quest;
import quest_legends.Team;
import quest_legends.Helpers.Color;
import quest_legends.Helpers.Generator;
import quest_legends.Helpers.QuestDetails;
import quest_legends.Helpers.Vizualization;
import quest_legends.QuestCharacters.Dragon;
import quest_legends.QuestCharacters.Hero;
import quest_legends.QuestCharacters.Monster;
import quest_legends.QuestCharacters.Spirit;
import quest_legends.QuestCharacters.Exoskeleton;

public class QuestBoard extends Board implements CellType, Color, Vizualization, QuestDetails {

	public ArrayList<Monster> aliveMonsters = new ArrayList<Monster>();
	public ArrayList<Monster> deadMonsters = new ArrayList<Monster>();

	/*
	 * Constructor to initialize Quest board (dimensions retrieved from user)
	 */
	public QuestBoard(Quest quest) {
		super(quest);
		setDisplay(quest);
		spreadCells();
		putMonstersTest(); // TODO: change to random generating monsters
	}

	@Override
	public void setDisplay(Game game) {
		this.display = new DisplayBoardQuest(this, (Quest) game);
	}

	/*
	 * Constructor to initialize fixed ROWxCOL board
	 */
	public QuestBoard(Quest quest, int x, int y) {
		super(quest, x, y);
		setDisplay(quest);
		spreadCells();
	}

	public void putMonstersTest() {
		ArrayList<Monster> newMonsters = new ArrayList<Monster>();
		Monster monster1 = new Dragon("Dragon", 3, 300, 400, 35);
		Monster monster2 = new Spirit("Spirit", 3, 300, 400, 35);
		Monster monster3 = new Exoskeleton("Exoskeleton", 3, 300, 400, 35);
		newMonsters.add(monster1);
		newMonsters.add(monster2);
		newMonsters.add(monster3);
		int Nexus_col = 0;
		for (Monster monster : newMonsters) {
			int random_col = Quest.random.nextInt(10) % 2 + Nexus_col;
			monster.current_row = 0;
			monster.current_col = random_col;
			board[monster.current_row][monster.current_col].placePiece(MONSTER_PIECE);
			Nexus_col += 3;
		}
	}

	public boolean isValidMove(Player player, int row, int col) {

		if (boardPositionExists(row, col)) {
			if (this.getBoard()[row][col].getType() == BLOCKED) {
				System.out.println(BLOCKED_CELL_MESSAGE);
				return false;
			} else if (this.getBoard()[row][col].pieceExists(HERO_PIECE)) {
				System.out.println(ANOTHER_HERO_OCCUPIED_MESSAGE);
				return false;
			} else if (isBehindMonster(row, col)) {
				System.out.println(BEHIND_MONSTER_MESSAGE);
				return false;
			} else {
				if (row == rows - 1 && this.getBoard()[row][col].getType() == NEXUS) {
					updateBoard(player, row, col);
					System.out.println(RED + "You are still in your Nexus!" + RESET);
					return false;
				}
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
		boostSkills((Hero) player.getHero());
		if (comingFight(player)) {
			System.out.println(CYAN + player + ", get ready for the fight!" + RESET);
		}
	}

	/*
	 * Move monster's mark one cell further towards Hero's Nexus.
	 */
	public void moveForward(Monster monster) {
		if (isBehindHero(monster.current_row + 1, monster.current_col)) {
			// stay at the same position. Can't pass behind alive hero.
		} else {
			board[monster.current_row][monster.current_col].removePiece(MONSTER_PIECE);
			board[monster.current_row + 1][monster.current_col].placePiece(MONSTER_PIECE);
			monster.updatePosition(monster.current_row + 1, monster.current_col);
		}
	}

	public void moveAllMonsters() {
		for (Monster monster : aliveMonsters) {
//			if()
			moveForward(monster);
		}
	}

	/*
	 * Handle player's teleport move.
	 */
	public void teleport(Player player) {
		System.out.println(player + ", type board position to Teleport.");

		boolean playerInputIsValid = false;

		while (playerInputIsValid == false) {
			int[] row_col = getBoardPositionFromUser(); // get teleport position

			if (row_col[0] == 0) {
				System.out.println(RED + "Can't teleport to Monster Nexus!" + RESET);
			} else if (row_col[0] == rows - 1) {
				System.out.println(RED + "Can't teleport to your Nexus!" + RESET);
			} else if (board[player.current_row][player.current_col].getLane() != player.getHero().getHomeLane()) {
				// if player is NOT in his own home lane, teleport to his own lane is OK
				if (isValidMove(player, row_col[0], row_col[1])) {
					playerInputIsValid = true;
				}
			} else { // if player is in his home lane
				// teleport to his own lane is NOT allowed
				if (board[row_col[0]][row_col[1]].getLane() == player.getHero().getHomeLane()) {
					System.out.println(RED + "Can't teleport at your home lane!" + RESET);
				} else {
					// teleport to fellows lane is OK
					if (isValidMove(player, row_col[0], row_col[1])) {
						playerInputIsValid = true;
					}
				}
			}
		}
	}

	/*
	 * Return list of players that are in the radius to fight this monster
	 */
	public ArrayList<Player> fightRadius(Monster monster, ArrayList<Player> tmpTeam) {
		ArrayList<Player> players = new ArrayList<Player>();
		for (Player player : tmpTeam) {
			if ((player.current_row == monster.current_row) || (player.current_row == monster.current_row + 1)
			|| (player.current_row == monster.current_row + 0)) { 
				if (player.current_col == monster.current_col || player.current_col - 1 == monster.current_col
						|| player.current_col + 1 == monster.current_col) {
					players.add(player);
				}
			}
		}
		return players;
	}

	/*
	 * 
	 */
	public boolean comingFight(Player player) {
		for (Monster monster : aliveMonsters) {
			if ((player.current_row == monster.current_row) || (player.current_row == monster.current_row + 1)
					|| (player.current_row == monster.current_row - 1)) { // if on the same row
				if (player.current_col == monster.current_col || player.current_col - 1 == monster.current_col
						|| player.current_col + 1 == monster.current_col) {
					return true;
				}
			}
		}
		return false;

	}

	/*
	 * Assign monster-player fights.
	 */
	public ArrayList<Fight> getFights(Team team) {
		ArrayList<Fight> fights = new ArrayList<Fight>();
		ArrayList<Player> tmpTeam = new ArrayList<Player>();
		for (Player player : team.getTeam()) {
			tmpTeam.add(player); // deep copy of team
		}
		for (Monster monster : aliveMonsters) {
			ArrayList<Player> players = fightRadius(monster, tmpTeam);
			for (Player player : players)
				tmpTeam.remove(player);
			if (!players.isEmpty()) {
				fights.add(new Fight(this, monster, players));
			}
		}
		return fights;
	}

	/*
	 * Returns true if row, col position is behind Monster of current lane.
	 * Otherwise, false.
	 */
	private boolean isBehindMonster(int row, int col) {
		for (int r = rows - 1; r > row; r--) {
			if (this.getBoard()[r][col].pieceExists(MONSTER_PIECE)) {
				return true;
			} else if ((col < cols - 1) && this.getBoard()[r][col + 1].pieceExists(MONSTER_PIECE)) {
				return true;
			} else if ((col > 0) && this.getBoard()[r][col - 1].pieceExists(MONSTER_PIECE)) {
				return true;
			}
		}
		return false;
	}

	private boolean isBehindHero(int row, int col) {
		if (row - 1 < 0 || col - 1 < 0 || col + 1 == cols || row == rows) {
			return false;
		}
		if (this.getBoard()[row - 1][col].pieceExists(HERO_PIECE)) {
			return true;
		} else if (this.getBoard()[row - 1][col + 1].pieceExists(HERO_PIECE)) {
			return true;
		} else if (this.getBoard()[row - 1][col - 1].pieceExists(HERO_PIECE)) {
			return true;
		} else {
			return false;
		}
	}

	private void spreadCells() {
		for (int r = 0; r < rows; r++) {
			if (r == 0 || r == rows - 1) { // Nexus
				for (int c = 0; c < cols; c++) {
					board[r][c].setType(NEXUS);
					if ((c + 1) % 3 == 0) {
						board[r][c].setType(BLOCKED);
					}
				}
			} else {
				for (int c = 0; c < cols; c++) {
					board[r][c].setType(getRandomCell());
					if ((c + 1) % 3 == 0) {
						board[r][c].setType(BLOCKED);
					}
				}
			}
		}
	}

	/*
	 * Spread p;ayers on their Nexus area.
	 */
	public void spreadPlayers(Team team) {
		int Nexus_col = 0;
		int lane = 0;
		for (Player player : team.getTeam()) {
			int random_col = Quest.random.nextInt(10) % 2 + Nexus_col;
			updateBoard(player, rows - 1, random_col);
			player.getHero().setHomeLane(lane);
			Nexus_col += 3;
			lane++;
		}
	}

	public void spawnHero(Player player) {

	}

	/*
	 * Generates new set monsters with team's maximum level. Spread these new
	 * monsters on their Nexus lanes.
	 */
	public void spawnMonsters(Team team) {
		ArrayList<Monster> newMonsters = Generator.generateMonsters(team);

		int laneNum = 0;
		for (Monster monster : newMonsters) {
			int random_col = Quest.random.nextInt(10) % 2 + laneNum;
			monster.current_row = 0;
			monster.current_col = random_col;
			board[monster.current_row][monster.current_col].placePiece(MONSTER_PIECE);
			laneNum += 3;
			aliveMonsters.add(monster);
		}
	}

	/*
	 * Generate random cell type, return this type.
	 */
	public String getRandomCell() {
		double x = Math.random();
		if (x < 0.4) {
			return PLAIN;
		} else if (x < 0.6) {
			return BUSH;
		} else if (x < 0.8) {
			return KOULOU;
		} else {
			return CAVE;
		}
	}

	/*
	 * Handle cell type (boost hero's skills)
	 */
	public void boostSkills(Hero hero) {

		if (getBoard()[hero.current_row][hero.current_col].getType() == BUSH) {
			hero.setDexterity(hero.getDexterity() * 1.15);
		} else if (getBoard()[hero.current_row][hero.current_col].getType() == KOULOU) {
			hero.setStrength(hero.getStrength() * 1.15);
		} else if (getBoard()[hero.current_row][hero.current_col].getType() == CAVE) {
			hero.setAgility(hero.getAgility() * 1.15);
		} else {

		}
	}

}
