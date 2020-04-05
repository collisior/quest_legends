package quest_legends;

import java.util.ArrayList;
import java.util.Random;

import quest_legends.GameBoard.QuestBoard;
import quest_legends.Helpers.Color;
import quest_legends.Helpers.GenericMethods;
import quest_legends.Helpers.InputHandler;
import quest_legends.Helpers.QuestDetails;
import quest_legends.Helpers.Vizualization;

/*
 * Rules for the game (flow)
 */
public class Quest extends Game implements Color, Vizualization, QuestDetails {
	public static Random random = new Random();

	public QuestBoard board;
	public Player currentPlayer = null;
	public TeamQuest team = new TeamQuest(1);

	public void startGame() {
		board = new QuestBoard(8, 8);
		board.displayBoard(this);
		SetupQuestHandler.setupTeam(this);
		
		board.spreadPlayers(team);
		currentPlayer = team.getCurrentTeamPlayer();
		boolean gameStop = false;

		board.displayBoard(this);
		while (!gameStop) {
			
			for(int i = 0; i < team.getTeamSize(); i++) { // finish all players moves
				currentPlayer = team.getNextTeamPlayer();
				makeMove();
				if (questEnd()) {
					gameStop = true;	
				}
			}
			if (!gameStop) {
				//check monsters nearby. start fight if monsters are in fight radius
				ArrayList<Fight> fights = board.getFights(team);
				for(Fight fight : fights ) {
					fight.startFight();
				}
				
			}
		}
	}

	public void makeMove() {

		boolean playerMoved = false;
		while (!playerMoved) {
			System.out.println(currentPlayer + ", choose your move:\n " + AcceptedMoveInputsInfo);
			char input = Character.toUpperCase(InputHandler.getCharacter(AcceptedMoveInputs));
			switch (input) {
			case 'W':
				if (board.isValidMove(currentPlayer, currentPlayer.current_row - 1, currentPlayer.current_col)) {
					playerMoved = true;
				} else {

				}
				break;
			case 'A':
				if (board.isValidMove(currentPlayer, currentPlayer.current_row, currentPlayer.current_col - 1)) {
					playerMoved = true;
				}
				break;
			case 'S':
				if (board.isValidMove(currentPlayer, currentPlayer.current_row + 1, currentPlayer.current_col)) {
					playerMoved = true;
				}
				break;
			case 'D':
				if (board.isValidMove(currentPlayer, currentPlayer.current_row, currentPlayer.current_col + 1)) {
					playerMoved = true;
				}
				break;
			case 'T': //Teleport
				board.teleport(currentPlayer);
				playerMoved = true;
				break;
			case 'B': // Go to Nexus - Market
				Market.enter(currentPlayer);
			case 'I': // all team heroes information
				printInfo();
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
		if (currentPlayer.current_row == 0) { // player reached Monsters Nexus
			System.out.println(VICTORY);
			return true;
		}
		return false;
	}

}