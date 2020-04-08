package quest_legends;

import quest_legends.Helpers.CSVFilesHandler;
import quest_legends.Helpers.QuestDetails;
import quest_legends.Helpers.Vizualization;

public class Test implements Vizualization, QuestDetails {
	
	public static void main(String[] args) {
		
		CSVFilesHandler.setData();
		System.out.println(WELCOME);
		

		Quest game = new Quest();
		
		game.startGame();
		
		
//		
		
//		Player p = game.addPlayer();
//		p.current_row = 4;
//		p.current_col = 3;
//		
//		Player p2 = game.addPlayer();
//		p2.current_row = 4;
//		p2.current_col = 4;
//		
//		game.board.getBoard()[1][1].placePiece(new Piece('@'));
//		game.board.displayBoard(game);
		

		
	}
	
	

}