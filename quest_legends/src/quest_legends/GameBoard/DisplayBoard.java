package quest_legends.GameBoard;


public abstract class DisplayBoard {
	
	Board board = null;

	DisplayBoard(Board board) {
		this.board = board;
	}

	public abstract void showBoard();
}
