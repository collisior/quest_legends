package quest_legends.GameBoard;

import java.util.ArrayList;

/*
 * The Cell class models Cell (that constructs the Board).
 */

public class Cell {

	private int row;
	private int col;
	private String color = "white";
	private String type = null;
	private ArrayList<Piece> pieces = new ArrayList<Piece>();

	/*
	 *  Constructor to initialize this Cell
	 */
	public Cell(int row, int col) {
		setRow(row);
		setCol(col);
	}
	/*
	 *  Set this Cell's row number
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/*
	 *  Set this Cell's row number
	 */
	public void setCol(int col) {
		this.col = col;
	}

	public void setColor(String color) {
		this.color = color;
	}
	public String getColor(String color) {
		return this.color;
	}
	/*
	 *  Retrieve this Cell's row number
	 */
	public int getRow() {
		return this.row;
	}

	/*
	 *  Retrieve this Cell's column number
	 */
	public int getCol() {
		return this.col;
	}

	/*
	 * Put this Cell with given figure
	 */
	public void placePiece(Piece p) {
		this.pieces.add(p);
	}

	/*
	 * Remove given piece from this Cell
	 */
	public Piece removePiece(Piece p) {
		if (pieceExists(p)) {
			for(int i = 0; i<pieces.size(); i++) {
				if(pieces.get(i).getPieceFigure() == p.getPieceFigure()) {
					return pieces.remove(i);
				}
			}
		}
		return null;
	}
	/*
	 *  Remove last piece from this Cell
	 */
	public void removeLastPiece() {
		if (!pieces.isEmpty()) {
			this.pieces.remove(pieces.size()-1);
		}
	}
	/*
	 * Retrieve this Cell's single Piece 
	 */
	public Piece getSinglePiece() {
		if (this.pieces.isEmpty()) {
			return null;
		}
		return this.pieces.get(0);
	}

	/*
	 *  Retrieve all pieces placed in this Cell's 
	 */
	public ArrayList<Piece> getAllPieces() {
		return this.pieces;
	}

	/*
	 *  Clear/reset this Cell's content = empty
	 */
	public void clearCell() {
		pieces.clear();
	}
	/*
	 * Retrieve this Cell's single Piece 
	 */
	public int getTotalPieces() {
		if (this.pieces.isEmpty()) {
			return 0;
		}
		return this.pieces.size();
	}
	/*
	 * Check if given piece exists in this Cell
	 */
	public boolean pieceExists(Piece piece) {		
		for(Piece piece1 : pieces) {
			if(piece1.getPieceFigure() == piece.getPieceFigure()) {
				return true;
			}
		}
		return false;
	}
	/*
	 *  Check if this Cell is empty (available)
	 */
	public boolean isEmptyCell() {
		if (this.pieces.isEmpty()) {
			return true;
		}
		return false;
	}

	/*
	 *  Get String representation of this Cell
	 */
	public String toString() {
		if (this.isEmptyCell()) {
			return " ";
		} else {
			String allPieces = "";
			for (Piece p : pieces) {
				allPieces += p;
			}
			return allPieces;
		}
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLane() {
		int lane = 0;
		for(int c = 0; c <= col; c++) {
			if ((c + 1) % 3 == 0) {
				lane++;
			}
		}
		
		return lane;
	}
	
	
}
