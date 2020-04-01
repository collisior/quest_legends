package quest_legends;

import quest_legends.GameBoard.Piece;
import quest_legends.GameBoard.QuestBoard;
import quest_legends.Helpers.CSVFilesHandler;
import quest_legends.Helpers.Vizualization;

public class Main implements Vizualization {
		
	public static void main(String[] args) {
		
		CSVFilesHandler.setData();
		System.out.println(WELCOME);

		Quest game = new Quest();
		
		game.board = new QuestBoard(8,8);
		game.board.getBoard()[0][0].placePiece(new Piece('M'));
		Player p = game.addPlayer();
		p.current_row = 1;
		p.current_col = 1;
		
		game.board.getBoard()[1][1].placePiece(new Piece('@'));
		game.board.displayBoard(game);
		
//		game.startGame();
//		Board board = new QuestBoard(8,8);
//		board.displayBoard();
		
	}
	
}