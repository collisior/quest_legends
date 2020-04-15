package quest_legends.GameBoard;


public abstract class DisplayBoard {
	
	Board board = null;

	DisplayBoard(Board board) {
		System.out.println("Display Board created");
		this.board = board;
	}

	public abstract void showBoard();
}
