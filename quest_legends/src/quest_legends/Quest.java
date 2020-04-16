package quest_legends;

import java.util.ArrayList;
import java.util.Random;

import quest_legends.GameBoard.QuestBoard;
import quest_legends.Helpers.Color;
import quest_legends.Helpers.InputHandler;
import quest_legends.Helpers.QuestDetails;
import quest_legends.Helpers.Vizualization;
import quest_legends.QuestCharacters.Monster;

/*
 * Rules for the game (flow)
 */
public class Quest extends Game implements Color, Vizualization, QuestDetails {
	public static Random random = new Random();

	public QuestBoard board;
	public Player currentPlayer = null;
	public TeamQuest team = new TeamQuest(1);

	public void startGame() {
		board = new QuestBoard(this, 8, 8);
		System.out.println(BOARD_CELLS_INFO);
		System.out.println(FIGHT_RADIUS_INFO);
		InputHandler.pressAnything();
//		SetupQuestHandler.setupTeam(this);
		SetupQuestHandler.quickSetupTeam(this);
		board.spreadPlayers(team);

		currentPlayer = team.getCurrentTeamPlayer();
		boolean gameStop = false;
		int monster_spawns = MONSTER_SPAWN_FREQUENCY; // number of rounds until next monsters spawn
		board.spawnMonsters(team);
		board.display.showBoard();
		while (!gameStop) {
			
			for (int i = 0; i < team.getTeamSize(); i++) { // finish all players moves
				currentPlayer = team.getNextTeamPlayer();
				makeMove();
				if (questEnd()) {
					gameStop = true;
					board.display.showBoard();
				}
			}
			
			board.moveAllMonsters();
			
			
			
			if (!gameStop) {
				// check monsters nearby. start fight if monsters are in fight radius
				ArrayList<Fight> fights = board.getFights(team);
				if (!fights.isEmpty())
					board.display.showBoard();
				System.out.println("Total fights in this round = " + fights.size());
				for (Fight fight : fights) {
					System.out.println(RED + "Fight " + (fights.indexOf(fight)+1) + " starts..." + RESET);
					fight.startFight();

				}
				for (Monster monster: board.aliveMonsters) {
					board.moveForward(monster);
					if(monster.current_row == board.rows-1) {
						System.out.println(RED+MONSTERS+VICTORY+RESET);
						gameStop = true;
					}
				}
			}

			monster_spawns--;
			if (monster_spawns == 0) {
				board.spawnMonsters(team);
				monster_spawns = MONSTER_SPAWN_FREQUENCY;
			}
		}
	}

	public void makeMove() {

		boolean playerMoved = false;
		while (!playerMoved) {
			System.out.println(GREEN + currentPlayer + RESET + ", choose your move:\n" + AcceptedMoveInputsInfo);
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
			case 'T': // Teleport
				board.teleport(currentPlayer);
				playerMoved = true;
				break;
			case 'B': // Go to Nexus - Market
				Market.enter(currentPlayer);
				System.out.println("\nHow do you want to make next move?\n[Y] - move from your Nexus. "
						+ "\n[N] - move from your most recent board position: (" + currentPlayer.current_row + ","
						+ currentPlayer.current_col + ")");
				if (InputHandler.YesOrNo()) {
					board.getBoard()[currentPlayer.current_row][currentPlayer.current_col].removePiece(HERO_PIECE);
					currentPlayer.updatePosition(board.rows - 1, currentPlayer.getHero().getHomeLane() * 3);
					board.getBoard()[currentPlayer.current_row][currentPlayer.current_col].placePiece(HERO_PIECE);
				}
				break;
			case 'I': // all team heroes information
				printInfo();
				break;
			case 'M': // show map
				board.display.showBoard();
				break;
			case 'Q': // quit
				quitGame();
			}
		}
	}

	public void printInfo() {
		for (Player player : team.getTeam()) {
			System.out.println(player);
			player.getHero().information();
			System.out.println("");
		}
		String s = "-----------TEAM OVERALL-------------\n" + "\tTotal Monsters defeated: " + board.deadMonsters.size()
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
