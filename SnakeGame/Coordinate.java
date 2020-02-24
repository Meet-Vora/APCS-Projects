/**
 *	Coordinate of the snake.
 *	
 *	@author Meet Vora
 *	@since 	November 15th, 2018
 */

public class Coordinate {
	
	private int row, col; // row and column of Coordinate
	
	public Coordinate(int r, int c) {
		
		row = r;
		col = c;
	}
	
	/** Accessor Methods */
	public int getRow() { return row; }
	public int getCol() { return col; }
	
	/**
	 *	Compare coordinates of snake and where the snake is not to spawn snake's food
	 *	equals method for Coordinate
	 *	The rows must be equal and the columns must be equal
	 *	@param other		another Coordinate
	 *	@return 	`		true if equal; false otherwise
	 */
	@Override
	 
	// not passing a Coordinate object because if you passed Coordinate, it wouldn't override the equals method in the object class
	public boolean equals(Object other) { 
		 
		if(other != null && other instanceof Coordinate &&
				row == ((Coordinate)other).row && 
				col == ((Coordinate)other).col)
			return true;
		return false;
	}
	 
	@Override
	/** toString method to print out row and column */
	public String toString() { return "(" + row + ", " + col + ")"; }

}