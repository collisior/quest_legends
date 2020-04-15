package quest_legends.GameBoard;

import java.util.ArrayList;

import quest_legends.Player;
import quest_legends.Quest;
import quest_legends.Helpers.Color;
import quest_legends.Helpers.QuestDetails;
import quest_legends.QuestCharacters.Monster;

public class DisplayBoardQuest extends DisplayBoard implements Color, CellType, QuestDetails {
	Quest quest = null;
	

	DisplayBoardQuest(QuestBoard board, Quest quest) {
		
		super(board);
		this.quest = quest;
		board = quest.board;
		System.out.println("Display Board created");
	}

	/*
	 * Display this quest game-board at current state.
	 */
	String ll = " | | ";

	@Override
	public void showBoard() {
		int cols = board.cols;
		int rows = board.rows;
		String frame = "";
		for (int n = 0; n < cols; n++) {
			frame += "+-+--------";
		}
		String indexes = "   ";
		for (int n = 0; n < cols; n++) {
			indexes += "     " + n + "     ";
		}
		indexes += "\n";
		frame += "+-+\n";
		String b = "";
		String tmp = "";

		for (int i = 0; i < rows; i++) {
			b += "  ";
			for (int j = 0; j < cols; j++) {

				tmp = ll + BLACK + " ` ` ," + RESET; // PLAIN
				if (board.board[i][j].getType() == NEXUS) { // Magic MArket
					tmp = ll + CYAN + "NNNNNN" + RESET;
				} else if (board.board[i][j].getType() == BUSH) { // Bush
					tmp = ll + GREEN + "BBBBBB" + RESET;
				} else if (board.board[i][j].getType() == KOULOU) { // Koulou
					tmp = ll + PURPLE + "KKKKKK" + RESET;
				} else if (board.board[i][j].getType() == CAVE) { // Cave
					tmp = ll + YELLOW + "CCCCCC" + RESET;
				} else if (board.board[i][j].getType() == BLOCKED) { // Blocked
					tmp = ll + BLACK + "\\\\\\\\\\\\" + RESET;
				}
				b += tmp;
				tmp = "";

			}
			b += ll + "\n " + i;
			for (int j = 0; j < cols; j++) { // monster side

				Monster monster = monsterIsHere(i, j);
				if (monster != null) {
					tmp = getMonsterLine(i, j, monster);
				} else {
					tmp = getMiddleLine(i, j);
				}
				b += tmp;
				tmp = "";
			}
			b += ll + i + "\n  ";
			for (int j = 0; j < cols; j++) { // hero side

				Player player = playerIsHere(i, j);
				if (player != null) {
					tmp = getPlayerLine(i, j, player);
				} else {
					tmp = getMiddleLine(i, j);
				}
				b += tmp;
				tmp = "";
			}
			b += ll + "\n  ";
			for (int j = 0; j < cols; j++) {

				tmp = ll + BLACK + " ` ` ," + RESET; // PLAIN
				if (board.board[i][j].getType() == NEXUS) { // Magic MArket
					tmp = ll + CYAN + "NNNNNN" + RESET;
				} else if (board.board[i][j].getType() == BUSH) { // Bush
					tmp = ll + GREEN + "BBBBBB" + RESET;
				} else if (board.board[i][j].getType() == KOULOU) { // Koulou
					tmp = ll + PURPLE + "KKKKKK" + RESET;
				} else if (board.board[i][j].getType() == CAVE) { // Cave
					tmp = ll + YELLOW + "CCCCCC" + RESET;
				} else if (board.board[i][j].getType() == BLOCKED) { // Blocked
					tmp = ll + BLACK + "\\\\\\\\\\\\" + RESET;
				}

				b += tmp;
				tmp = "";

			}
			b += ll + "\n   " + frame + "   " + frame;
			System.out.print("\n ");
		}
		System.out.println(indexes + "   " + frame + "   " + frame + b + " " + indexes);
	}

	/*
	 * Return Player if in given cell player exists, otherwise return null
	 */
	private Player playerIsHere(int row, int col) {
		ArrayList<Integer> listPlayers = quest.getPlayers();
		for (int id : listPlayers) {
			if ((quest.getPlayer(id).current_col == col) && (quest.getPlayer(id).current_row == row)) {
				return quest.getPlayer(id);
			}
		}
		return null;
	}

	/*
	 * Return null if no Monster in this cell, return Monster if it is here
	 */
	private Monster monsterIsHere(int row, int col) {
		if (quest.board.getBoard()[row][col].pieceExists(MONSTER_PIECE)) {
			for (Monster monster : quest.board.aliveMonsters) {
				if ((monster.current_col == col) && (monster.current_row == row)) {
					System.out.println( "Monster is here : " + row + " " + col);
					return monster;
				}
			}
		}
		return null;
	}

	private String getMonsterLine(int i, int j, Monster monster) {
		String tmp = "";
		String monsterID = RED + WHITE_ON_BLACK_BACKGROUND + " M" + monster.id + " " + RESET;
		if (monster.id >= 10) {
			monsterID = RED + WHITE_ON_BLACK_BACKGROUND + "M" + monster.id + " " + RESET;
		} else if (monster.id >= 100) {
			monsterID = RED + WHITE_ON_BLACK_BACKGROUND + "M" + monster.id + "" + RESET;
		}
		tmp = ll + BLACK + " " + monsterID + BLACK + "`" + RESET; // Plain
		if (board.board[i][j].getType() == NEXUS) { // Magic MArket
			tmp = ll + CYAN + "N" + monsterID + CYAN + "N" + RESET;
		} else if (board.board[i][j].getType() == BUSH) { // Bush
			tmp = ll + GREEN + "B" + monsterID + GREEN + "B" + RESET;
		} else if (board.board[i][j].getType() == KOULOU) { // Koulou
			tmp = ll + PURPLE + "K" + monsterID + PURPLE + "K" + RESET;
		} else if (board.board[i][j].getType() == CAVE) { // Cave
			tmp = ll + YELLOW + "C" + monsterID + YELLOW + "C" + RESET;
		} else if (board.board[i][j].getType() == BLOCKED) { // Blocked
			tmp = ll + BLACK + "/" + monsterID + BLACK + "/" + RESET;
		}
		return tmp;
	}

	private String getPlayerLine(int i, int j, Player player) {
		String tmp = "";
		String playerID = WHITE + BACKGROUND_BLACK + " H" + player.getId() + " " + RESET;

		tmp = ll + BLACK + "'" + playerID + BLACK + "`" + RESET; // Plain
		if (board.board[i][j].getType() == NEXUS) { // Magic MArket
			tmp = ll + CYAN + "N" + playerID + CYAN + "N" + RESET;
		} else if (board.board[i][j].getType() == BUSH) { // Bush
			tmp = ll + GREEN + "B" + playerID + GREEN + "B" + RESET;
		} else if (board.board[i][j].getType() == KOULOU) { // Koulou
			tmp = ll + PURPLE + "K" + playerID + PURPLE + "K" + RESET;
		} else if (board.board[i][j].getType() == CAVE) { // Cave
			tmp = ll + YELLOW + "C" + playerID + YELLOW + "C" + RESET;
		} else if (board.board[i][j].getType() == BLOCKED) { // Blocked
			tmp = ll + BLACK + "/" + playerID + BLACK + "/" + RESET;
		}
		return tmp;
	}

	private String getMiddleLine(int i, int j) {
		String tmp = "";
		tmp = ll + BLACK + " ,`  `" + RESET; // Plain
		if (board.board[i][j].getType() == NEXUS) { // Magic MArket
			tmp = ll + CYAN + "N    N" + RESET;
		} else if (board.board[i][j].getType() == BUSH) { // Bush
			tmp = ll + GREEN + "B    B" + RESET;
		} else if (board.board[i][j].getType() == KOULOU) { // Koulou
			tmp = ll + PURPLE + "K    K" + RESET;
		} else if (board.board[i][j].getType() == CAVE) { // Cave
			tmp = ll + YELLOW + "C    C" + RESET;
		} else if (board.board[i][j].getType() == BLOCKED) { // Blocked
			tmp = ll + BLACK + "//////" + RESET;
		}
		return tmp;
	}

}
