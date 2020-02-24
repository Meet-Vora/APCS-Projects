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
public class OldPegSolitaire2 {
	
	// fields
	
	private int userRow, userColumn;
	private String input;
	private boolean gameOver;
	private boolean quit;

	private PegBoard board;
	private Location loc;

	private ArrayList<Location> closePlaces;
	private ArrayList<Location> farPlaces;
		
	/** constructor */
	public OldPegSolitaire2() {
		
		userRow = 0; userColumn = 0;
		board = new PegBoard();
		// loc = new Location(row, column);
		input = "";
		closePlaces = new ArrayList<Location>();
		farPlaces = new ArrayList<Location>();
		quit = false;
	}
	
	
	/** methods */
	
	
	public static void main(String []args) {
		
		OldPegSolitaire2 ops2 = new OldPegSolitaire2();
		ops2.run();
	}
	
	/**
	 *	Runs the other methods and is called by main
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
	
		do {
			do {
				
				badInput = false;
				
				input = Prompt.getString("Jumper peg - row col (ex. 3 5, q=quit)");
				numberInput = input.split(" +");
				
				if(!input.equals("q")) {
					
					while(numberInput.length <= 1) {

						input = Prompt.getString("Jumper peg - row col (ex. 3 5, q=quit)");
						numberInput = input.split(" +");
					}

					try {
						
						userRow = Integer.parseInt(numberInput[0]);
						userColumn = Integer.parseInt(numberInput[1]);

						isValid = isCoordinateValid();


						if(!isValid) {

							System.out.println("Invalid jumper peg -> " + input);
							badInput = true;
						}
					}
					catch(NumberFormatException e) {
						
						if(!badInput)
							System.out.println("Invalid Jumper Peg -> " + input);
						badInput = true;
					}
					catch(ArrayIndexOutOfBoundsException e) {

						if(!badInput)
							System.out.println("Invalid Jumper Peg -> " + input);
						badInput = true;
					}
				}
			} while (badInput && !isValid && !input.equals("q"));
			
			if (!input.equals("q") && isValid) {

				board.removePeg(userRow,userColumn);
				checkNearPeg(userRow,userColumn);
				movePeg();
				board.printBoard();
				closePlaces.clear();
				farPlaces.clear();
			}
			
			gameEnd = isGameOver();
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

	public void checkNearPeg(int x, int y) {
	
		if(board.isValidLocation(x, y + 2) && !board.isPeg(x, y + 2)
			&& board.isValidLocation(x, y + 1) && board.isPeg(x, y + 1)) {	// right

			closePlaces.add(new Location(x, y + 1));
			farPlaces.add(new Location(x, y + 2));
		}
		
		if(board.isValidLocation(x, y - 2) && !board.isPeg(x, y - 2) 
			&& board.isValidLocation(x, y - 1) && board.isPeg(x, y - 1)) {	// left

			closePlaces.add(new Location(x, y - 1));
			farPlaces.add(new Location(x, y - 2));
		}
		
		if(board.isValidLocation(x - 2, y) && !board.isPeg(x - 2, y) 
			&& board.isValidLocation(x - 1, y) && board.isPeg(x - 1, y)) {	// bottom

			closePlaces.add(new Location(x - 1, y));
			farPlaces.add(new Location(x - 2, y));
		}

		if(board.isValidLocation(x + 2, y) && !board.isPeg(x + 2, y) 
			&& board.isValidLocation(x + 1, y) && board.isPeg(x + 1, y)) {	// up

			closePlaces.add(new Location(x + 1, y));
			farPlaces.add(new Location(x + 2, y));
		}	
	}
	
	public void movePeg() {
		
		loc = new Location(userRow,userColumn);
		int whichPeg = 0; 
		int farArrayMinusOne = farPlaces.size() - 1;

		if(board.isValidLocation(userRow,userColumn)) {

			if(farPlaces.size() == 0) {}
			else if(farPlaces.size() == 1) {
				
				board.removePeg(closePlaces.get(0).getRow(),closePlaces.get(0).getCol());
				board.putPeg(farPlaces.get(0).getRow(),farPlaces.get(0).getCol());
			}
				
			else {

				System.out.print("\nPossible peg jump locations:");
				
				for (int a = 0; a < farPlaces.size(); a++) {
					
					System.out.print("\n  " + a);
					System.out.print(" " + loc);
				}
				
				whichPeg = Prompt.getInt("\nEnter Location (0 - " + farArrayMinusOne
					+ ")", 0, farArrayMinusOne);
				
				board.removePeg(closePlaces.get(whichPeg).getRow(),closePlaces.get(whichPeg).getCol());
				board.putPeg(farPlaces.get(whichPeg).getRow(),farPlaces.get(whichPeg).getCol());
			}
		}
	}
	
	public boolean isGameOver() {
	
		int eachRow, eachColumn;
		for(eachRow = 0; eachRow < board.getBoardSize(); eachRow++) {
			for (eachColumn = 0; eachColumn < board.getBoardSize(); eachColumn++) {
				
				if(isValidToJump(eachRow,eachColumn))
					return true;
			}
		}
		return false;
	}
	
	public boolean isValidToJump(int eachRow,int eachColumn) {
		
		// if(board.isValidLocation(eachRow,eachColumn))
		// checkNearPeg(eachRow, eachColumn);
		
		// if(farPlaces.size() == 0 && closePlaces.size() == 0)
		// 	return false;
		
		// return true;

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


		// get size of arraylists, but cant access em right now. Make em field variables?
		
	}

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
	 *	Print the introduction to the game
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