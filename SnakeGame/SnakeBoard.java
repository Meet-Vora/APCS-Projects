/**
 *	Contains methods used to print out the game Board to the terminal window.
 *	When given snake and coordinate objects, prints the border, the location of
 *	the snake, and the target on the screen. The height and width of the board
 *	must be provided to the constructor of the class.
 *
 *	@author	Meet Vora
 *	@since	15th November, 2018
 */
public class SnakeBoard {
	
	/*	fields	*/
	private char[][] board;			// The 2D array to hold the board
	private final int BOARD_WIDTH, BOARD_HEIGHT;	// width and height of board
	
	
	/*	Constructor	*/
	public SnakeBoard(int height, int width) {
		
		BOARD_HEIGHT = height;	// number of rows
		BOARD_WIDTH = width;	// number of columns
		board = new char[BOARD_HEIGHT][BOARD_WIDTH];
	}
	
	/**
	 *	Print the board to the screen. The board contain the location of the
	 *	snake's head, denoted by an ampersand (@), the snake's tail, denoted by
	 *	astericks (*), and the target, denoted by a plus sign (+).
	 *	@param snake 	a Snake object that contains the snake's location
	 *	@param target 	a Coordinate object that contains the target's location
	 */
	public void printBoard(Snake snake, Coordinate target) {
		/*	implement here	*/
	
		// target = new Coordinate((int)(Math.random()*25),(int)(Math.random()*20));
		clearBoard();

		// add location of snake head to board
		board[snake.get(0).getRow()][snake.get(0).getCol()] = '@';

		// add location of all sections of snake's tail to board
		for (int a = 1; a < snake.size(); a++)
			board[snake.get(a).getRow()][snake.get(a).getCol()] = '*';
		

		// add target to board
		board[target.getRow()][target.getCol()] = '+';

		// print top bar of board
		System.out.print("+ ");
		for (int a = 0; a < BOARD_WIDTH; a++)
			System.out.print("- ");
		System.out.println("+");
		
		// print left and right bar of board one row at a time
		for (int a = 0; a < BOARD_HEIGHT; a++) {
			
			System.out.print("| ");
			for (int b = 0; b < BOARD_WIDTH; b++)
				System.out.print(board[a][b] + " ");			
			System.out.println("|");
		}
	
		// print bottom bar of board
		System.out.print("+ ");
		for (int a = 0; a < BOARD_WIDTH; a++)
			System.out.print("- ");
		System.out.println("+");
	}
	
	/* Helper methods go here	*/

	/** 
	 *	Reinitializes each element of the board array as a space character
	 */
	public void clearBoard() {

		for (int a = 0; a < BOARD_HEIGHT; a++) {			
			for (int b = 0; b < BOARD_WIDTH; b++)
				board[a][b] = ' ';
		}
	}

	/*	Accessor methods	*/
	
	/** 
	 *	Returns width of board 
	 *	@return 	width of board
	 */
	public int getWidth() { return BOARD_WIDTH; }

	/** 
	 *	Returns height of board 
	 *	@return 	height of board
	 */
	public int getHeight() { return BOARD_HEIGHT; }

	/** 
	 *	Returns board array 
	 *	@return 	board array
	 */
	public char[][] getBoard() { return board; }


	/********************************************************/
	/********************* For Testing **********************/
	/********************************************************/
	
	public static void main(String[] args) {
		// Create the board
		int height = 20, width = 25;
		SnakeBoard sb = new SnakeBoard(height, width);
		// Place the snake
		Snake snake = new Snake(3, 3);
		// Place the target
		Coordinate target = new Coordinate(1, 7);
		// Print the board
		sb.printBoard(snake, target);
	}
}