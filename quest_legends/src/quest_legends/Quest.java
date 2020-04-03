package quest_legends;

import java.util.Random;

import quest_legends.GameBoard.QuestBoard;
import quest_legends.Helpers.Color;
import quest_legends.Helpers.GenericMethods;
import quest_legends.Helpers.InputHandler;
import quest_legends.Helpers.Vizualization;

/*
 * Rules for the game (flow)
 */
public class Quest extends Game implements Color, Vizualization {
	public static Random random = new Random();
	public static double chanceToMeetMonsters = 0.8;
	static int levelEnd = 10; // level number to indicate winners (reach-level quest mode)
	public QuestBoard board;
	public Player currentPlayer = null;
	public TeamQuest team = new TeamQuest(1);

	final static int TEAM_CAPACITY = 3;

	public void startGame() {
		board = new QuestBoard();
		board.displayBoard(this);
		SetupQuestHandler.setupTeam(this);
		setGamersQueue(GenericMethods.shuffle(getPlayers()));
		currentPlayer = (Player) getNextInQueue(mapPlayers);
		boolean gameStop = false;

		board.displayBoard(this);
		while (!gameStop) {
			makeMove();
			if (questEnd()) {
				gameStop = questEnd();
			}
			currentPlayer = (Player) getNextInQueue(mapTeams);
		}
	}


	public void makeMove() {
		char[] acceptedInputs = { 'W', 'w', 'A', 'a', 'S', 's', 'D', 'd', 'Q', 'q', 'I', 'i', 'M', 'm', 'B', 'b', 'T', 't'};
		boolean playerMoved = false;
		while (!playerMoved) {
			System.out.println(currentPlayer
					+ ", enter your move:\n 'W' - up, 'A'- left, 'S' - down, 'D'- right, 'Q' - quit, 'B' - back to Nexus, 'I' - print info, 'M'- show map. \n");
			char input = Character.toUpperCase(InputHandler.getCharacter(acceptedInputs));
			char cellType = 0;
			switch (input) {
			case 'W':
//				System.out.println("Move up >>");
				if (board.isValidMove(currentPlayer, currentPlayer.current_row - 1, currentPlayer.current_col)) {
					playerMoved = true;
				} else {

				}
				break;
			case 'A':
//				System.out.println("Move left >>");
				if (board.isValidMove(currentPlayer, currentPlayer.current_row, currentPlayer.current_col - 1)) {
					playerMoved = true;
				}
				break;
			case 'S':
//				System.out.println("Move down >>");
				if (board.isValidMove(currentPlayer, currentPlayer.current_row + 1, currentPlayer.current_col)) {
					playerMoved = true;
				}
				break;
			case 'D':
//				System.out.println("Move right >>");
				if (board.isValidMove(currentPlayer, currentPlayer.current_row, currentPlayer.current_col + 1)) {
					playerMoved = true;
				}
				break;
			case 'B':
				// TODO: Go to Nexus - Market
			case 'I': // all team heroes information
				cellType = 'i';
				break;
			case 'M': // show map
				board.displayBoard(this);
				break;
			case 'Q': // quit
				quitGame();
			}
			board.cellTypeHandler(currentPlayer);
		}
	}

	public void printInfo() {
		for (Player player : team.getTeam()) {
			System.out.println(player);
			player.getHero().information();
			System.out.println("");
		}
		String s = "-----------TEAM OVERALL-------------\n" + "\tTotal Fights won: " + ((TeamQuest) team).getFightsWon()
				+ "\n\tTotal Fights lost:" + ((TeamQuest) team).getFightsLost()
				+ "\n-------------------------------------\n";
		System.out.println(s);
	}

	
	private boolean questEnd() {
		//TODO: End Quest
		return false;
	}

}
