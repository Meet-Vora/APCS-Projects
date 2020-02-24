// imports
import java.util.ArrayList;

/**
 *	PegSolitaire game.
 *	<short Peg Solitaire is a one-player strategy game, where the player must 
 			remove all but one peg from the board.>
 *
 *	@author	Meet Vora
 *	@since	October 1st, 2018
 *
 *	<detailed Peg Solitaire is a one-player strategy game, where the player must 
 			remove all but one peg from the board. The board is a 7 unit by 7 
 			unit board with the corners removed. All the grid locations contain
 			a peg, except for the center. To remove a peg, the player must jump 
 			another peg over it either in a horizontal or vertical direction.
 			The game is over when the player decides to quit, has no possible 
 			moves left on the board, or has won the game by removing all but one
 			peg. >
 */
public class PegSolitaire {
	
	// fields
	
	private int userRow, userColumn;	// user-entered row and column
	private String input;				// stores input of user
	private boolean gameOver;			// checks if game if over

	private PegBoard board;				// object of PegBoard class
	private Location loc;				// object of Location class

	// stores locations of available close and far spaces to move to 
	private ArrayList<Location> closePlaces;
	private ArrayList<Location> farPlaces;
		
	/** constructor */
	public PegSolitaire() {
		
		userRow = 0; userColumn = 0;
		input = "";

		board = new PegBoard();
		closePlaces = new ArrayList<Location>();
		farPlaces = new ArrayList<Location>();
	}
	
	
	/** methods */
	
	
	public static void main(String []args) {
		
		PegSolitaire ps = new PegSolitaire();
		ps.run();
	}
	
	/**
	 *	Runs other methods and is called by main
	 */  

	public void run() {

	 	printIntroduction();
	 	getInput();	
	 	printClosingMessage();
	}

	/**
	 *	Obtains the input from the user to move a peg. Makes sure the entered 
	 *	coordinate is a valid location on the board and there is a peg present
	 *	using several try-catch blocks and do-while loops. 
	 */  

	public void getInput() {
		
		// int userRow, userColumn;
		String[] numberInput;
		boolean gameEnd; 
		boolean isValid = true;
		boolean badInput = false;

		board.printBoard();
	
		// Prompts user for input and processes it as long as game is not over
		// or user does not want to quit game
		do {
			
			do { // Prompts user for correctly formatted input
				
				badInput = false;
				
				input = Prompt.getString("Jumper peg - row col (ex. 3 5, q=quit)");
				numberInput = input.split(" +");
				
				// Asks user for input if they do not type "q" for quit
				if(!input.equals("q")) {

					// Makes sure user enters two characters
					while(numberInput.length <= 1) {

						input = Prompt.getString("Jumper peg - row col (ex. 3 5, q=quit)");
						numberInput = input.split(" +");
					}

					try {
						
						userRow = Integer.parseInt(numberInput[0]);
						userColumn = Integer.parseInt(numberInput[1]);

						// checks if coordinates user enters is valid
						isValid = isCoordinateValid();


						if(!isValid) {

							System.out.println("Invalid jumper peg -> " + input);
							badInput = true;
						}
					}
					catch(NumberFormatException e) {
						
						if(!badInput) // Does not print message if printed before
							System.out.println("Invalid Jumper Peg -> " + input);
						badInput = true;
					}
					catch(ArrayIndexOutOfBoundsException e) {

						if(!badInput) // Does not print message if printed before
							System.out.println("Invalid Jumper Peg -> " + input);
						badInput = true;
					}
				}
			} while (badInput && !isValid && !input.equals("q"));
			
			// Executes other methods if user does not quit and if their input 
			// is valid
			if (!input.equals("q") && isValid) {

				board.removePeg(userRow,userColumn);
				checkNearPeg(userRow,userColumn);
				movePeg();
				board.printBoard();
				closePlaces.clear();
				farPlaces.clear();
			}
			
			gameEnd = isGameOver();	// checks if game is over
		} while(!input.equals("q") || !gameEnd);
	}
	
	/**
	 *	Checks if the coordinates entered by the user are valid using methods
	 *	in the PegBoard class. Uses short-circuit evaluation in the conditions 
	 *	to make sure no exceptions are thrown.
	 *	@return 	true if coordinate entered is valid; false otherwise
	 */ 
	public boolean isCoordinateValid() {

		if(!board.isValidLocation(userRow,userColumn) || 
			!board.isPeg(userRow,userColumn))
				return false;

		// checks all directions of peg to see if it is a valid location entered 
		if(board.isValidLocation(userRow - 2,userColumn) && 
			board.isPeg(userRow - 1,userColumn) && 
			!board.isPeg(userRow - 2,userColumn) || 
			board.isValidLocation(userRow + 2,userColumn) && 
			board.isPeg(userRow + 1,userColumn) && 
			!board.isPeg(userRow + 2,userColumn) || 
			board.isValidLocation(userRow,userColumn - 2) && 
			board.isPeg(userRow,userColumn - 1) && 
			!board.isPeg(userRow,userColumn - 2) || 
			board.isValidLocation(userRow,userColumn + 2) && 
			board.isPeg(userRow,userColumn + 1) && 
			!board.isPeg(userRow,userColumn + 2))
				return true;
		
		return false;
	}

	/**
	 *	Checks the coordinates 1 and 2 spaces above, below, to the right, and 
	 *	the left of the chosen peg to make sure they have a peg and are a valid 
	 *	location and they don't have a peg and are a valid location, respectively.
	 *	Stores coordinates of peg using two ArrayLists and the Location 
	 *	wrapper class. 
	 *	@param x	row of chosen peg
	 *	@param y	column of chosen peg
	 */
	public void checkNearPeg(int x, int y) {

		// Saves location of peg in ArrayLists if move to left is possible
		if(board.isValidLocation(x, y - 2) && !board.isPeg(x, y - 2) 
			&& board.isValidLocation(x, y - 1) && board.isPeg(x, y - 1)) {	

			closePlaces.add(new Location(x, y - 1));
			farPlaces.add(new Location(x, y - 2));
		}

		// Saves location of peg in ArrayLists if move to right is possible
		if(board.isValidLocation(x, y + 2) && !board.isPeg(x, y + 2)
			&& board.isValidLocation(x, y + 1) && board.isPeg(x, y + 1)) {	

			closePlaces.add(new Location(x, y + 1));
			farPlaces.add(new Location(x, y + 2));
		}

		// Saves location of peg in ArrayLists if move downward is possible		
		if(board.isValidLocation(x - 2, y) && !board.isPeg(x - 2, y) 
			&& board.isValidLocation(x - 1, y) && board.isPeg(x - 1, y)) {	

			closePlaces.add(new Location(x - 1, y));
			farPlaces.add(new Location(x - 2, y));
		}
		
		// Saves location of peg in ArrayLists if move to upward is possible
		if(board.isValidLocation(x + 2, y) && !board.isPeg(x + 2, y) 
			&& board.isValidLocation(x + 1, y) && board.isPeg(x + 1, y)) {	

			closePlaces.add(new Location(x + 1, y));
			farPlaces.add(new Location(x + 2, y));
		}	
	}

	/**
	 *	"Moves" the selected peg by removing the peg in its chosen location and
	 *	the peg it jumps over, and adds a new peg 2 spaces away from the
	 *	original location in the direction available. If more than two spaces
	 *	are available for the peg to jump to, prompts the user to select which
	 *	location they would like to jump to.
	 */
	public void movePeg() {
		
		int whichPeg = 0; 
		int farArrayMinusOne = farPlaces.size() - 1;

		// checks if entered location is valid
		if(board.isValidLocation(userRow,userColumn)) {

			if(farPlaces.size() == 0) {}	// no possible moves
			
			// Moves peg in one possible direction
			else if(farPlaces.size() == 1) {	 
				
				board.removePeg(closePlaces.get(0).getRow(),closePlaces.get(0).getCol());
				board.putPeg(farPlaces.get(0).getRow(),farPlaces.get(0).getCol());
			}
			
			// Moves peg in direction indicated by user if more than one 
			// direction of movement possible
			else {

				System.out.print("\nPossible peg jump locations:");
				
				for (int a = 0; a < farPlaces.size(); a++) {
					
					loc = new Location(farPlaces.get(a).getRow(),
						farPlaces.get(a).getCol());
					System.out.print("\n  " + a);
					System.out.print(" " + loc);
				}
				
				whichPeg = Prompt.getInt("\nEnter Location (0 - " 
					+ farArrayMinusOne + ")", 0, farArrayMinusOne);
				
				board.removePeg(closePlaces.get(whichPeg).getRow(),closePlaces.get(whichPeg).getCol());
				board.putPeg(farPlaces.get(whichPeg).getRow(),farPlaces.get(whichPeg).getCol());
			}
		}
	}
	/**
	 *	Checks if the game is over by checking every single valid grid space
	 *	on the board to see if any valid moves are available using a for loop
	 *	nested within another for loop
	 *	@return 	false if valid moves still available; true otherwise 
	 */
	public boolean isGameOver() {
	
		int eachRow, eachColumn;

		// Cycles through each row and column value to see if any pegs have 
		// any valid movement
		for(eachRow = 0; eachRow < board.getBoardSize(); eachRow++) {
			for (eachColumn = 0; eachColumn < board.getBoardSize(); eachColumn++) {
				
				if(isValidToJump(eachRow,eachColumn))
					return true;
			}
		}
		return false;
	}
	
	/**
	 *	Contains condition that checks if there is a valid move avaiable for 
	 *	each peg left on the board. 
	 *	@param eachRow 		row that is being checked
	 *	@param eachColumn 	column that is being checked
	 *	@return 			true if valid move available; false otherwise
	 */
	public boolean isValidToJump(int eachRow,int eachColumn) {
		
		// Checks all sides of all pegs left on board to see if any moves
		// available
		if(board.isValidLocation(eachRow,eachColumn) && 
			board.isPeg(eachRow,eachColumn) && 
			(board.isValidLocation(eachRow-2,eachColumn) && 
			board.isPeg(eachRow-1,eachColumn) && 
			!board.isPeg(eachRow-2,eachColumn) 
			|| board.isValidLocation(eachRow+2,eachColumn) 
			&& board.isPeg(eachRow+1,eachColumn) && 
			!board.isPeg(eachRow+2,eachColumn)
			|| board.isValidLocation(eachRow,eachColumn-2) 
			&& board.isPeg(eachRow,eachColumn-1) 
			&& !board.isPeg(eachRow,eachColumn-2)
			|| board.isValidLocation(eachRow,eachColumn+2) 
			&& board.isPeg(eachRow,eachColumn+1) 
			&& !board.isPeg(eachRow,eachColumn+2))) {

			return true;
		}

		return false;		
	}

	/**
	 *	If only one peg present on the board, prints a congratulatory message
	 *	telling the user they won the game and thanks them for playing. If more 
	 *	than one peg present, prints the user's end score and thanks them for
	 *	playing.
	 */
	public void printClosingMessage() {

		if(board.pegCount() == 1) {

			board.printBoard();
			System.out.println("\n\nYou win!\n\nYour score: " + board.pegCount()
				+ " pegs remaining\n\nThanks for playing Peg Solitaire!\n");
		}
		else {

			board.printBoard();
			System.out.println("\nYour Score: " + board.pegCount() 
				+ " pegs remaining\n\n");
			System.out.println("Thanks for playing Peg Solitaire!\n");
		}
	}

	/**
	 *	Print the introduction to the game.
	 */
	public static void printIntroduction() {
	
		System.out.println("  _____              _____       _ _ _        _ ");
		System.out.println(" |  __ \\            / ____|     | (_) |      (_)");
		System.out.println(" | |__) |__  __ _  | (___   ___ | |_| |_ __ _ _ _ __ ___ ");
		System.out.println(" |  ___/ _ \\/ _` |  \\___ \\ / _ \\| | | __/ _` | | '__/ _ \\");
		System.out.println(" | |  |  __/ (_| |  ____) | (_) | | | || (_| | | | |  __/");
		System.out.println(" |_|   \\___|\\__, | |_____/ \\___/|_|_|\\__\\__,_|_|_|  \\___|");
		System.out.println("             __/ |");
		System.out.println("            |___/");
		System.out.println("\nWelcome to Peg Solitaire!!!\n");
		System.out.println("Peg Solitaire is a game for one player. The " +
							"goal is to remove all\n" +
							"but one of the 32 pegs from a special board. " +
							"The board is a 7x7\n" +
							"grid with the corners cut out (shown below)." +
							" Pegs are placed in all");
		System.out.println("grid locations except the center which is " +
							"left empty. Pegs jump\n" +
							"over other pegs either horizontally or " +
							"vertically into empty\n" +
							"locations and the jumped peg is removed. Play " +
							"continues until\n" +
							"there are no more jumps possible, or there " +
							"is one peg remaining.");
		System.out.println("\nLet's play!!!\n");
	}	
}