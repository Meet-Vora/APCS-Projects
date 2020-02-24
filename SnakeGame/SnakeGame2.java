/**
 *	Snake Game - <Description goes here>
 *	
 *	@author	Meet Vora
 *	@since	November 15th, 2018
 */
public class SnakeGame2 {
	
	private Snake snake;		// the snake in the game
	private SnakeBoard board;	// the game board
	private Coordinate target;	// the target for the snake
	private int score;			// the score of the game
	private char[][] newBoard;
	// private int 

	/*	Constructor	*/
	public SnakeGame2() {

		snake = new Snake((int)(Math.random() * (board.getWidth() - 5)) + 1,
				  (int)(Math.random() * (board.getHeight() - 5)) + 1);
		target = new Coordinate((int)(Math.random()*25),(int)(Math.random()*20));
		board = new SnakeBoard(board.getHeight(), board.getWidth());
		newBoard = board.getBoard();
	}
	
	/*	Main method	*/
	public static void main(String[] args) {
		
		SnakeGame2 game2 = new SnakeGame2();
		game2.run();
		// game.printIntroduction();
		// sg.helpMenu(); only use if they press help
	}

	public void runGame() {	
	}

	/**
	 *	Gets input from user and performs different functions based on their 
	 *	entered values. 
	 */
	public void processInput() {

		int movement = 0;
		String input = "";
		boolean quit = false;

		board.printBoard();
		// get input from user and convert to lower case
			
		do {
			do {
				input = Prompt.getString("Score: 0 (w - North, s - South, d - East, " + 
						"a - West, h - Help");
				input = input.toLowerCase();

			} while (!input.equals("w") && !input.equals("s") && !input.equals("d") && 
					 !input.equals("a") && !input.equals("h") && !input.equals("f") && 
					 !input.equals("r") && !input.equals("q"));
			
			// based on what user enters, either move snake, save or restore file,
			// print the help menu, or quit
			switch(input) {

				case "w": movement = 1;	moveSnake(movement);	// move up
					break;
				case "s": movement = 2;	moveSnake(movement); 	// move down
					break;
				case "d": movement = 3;	moveSnake(movement); 	// move right
					break;
				case "a": movement = 4;	moveSnake(movement); 	// move left
					break;
				case "h": helpMenu(); board.printBoard(snake, target); processInput();
					break;
				// case "f": saveGame();
				// 	break;
				//  case "r": restoreGame());
				// 	break;
				case "q": quit = false; quit();
					break;
				// default: movement = - 1;
			}
		} while (!quit && snake.size() <= board.getWidth()*board.getHeight());

		// if (input == "w")
		// 	moveUp();
		// else if (input == "s")
		// 	moveDown();
		// else if (input == "a")
		// 	moveLeft();
		// else if (input == "d")
		// 	moveRight();

	}

	/**
	 *	Assigns the position of each index to the position before it. Based on
	 *	which direction the user chooses to move the snake, the head will either
	 *	move up, left, down, or right.
	 *	@param movement 	indicates direction of movement based on assigned
	 							value
	 */
	public void moveSnake(int movement) {

		checkForCollision();

		for (int a = snake.size() - 1; a > 0; a--)
			snake.set(a, snake.get(a-1));
			// snake.get(a) = snake.get(a - 1);

		switch (movement) {
			case 1: snake.set(0, new Coordinate(snake.get(0).getRow()+1),
								snake.get(0).getCol());
					// snake.remove(1);
				break;
			case 2: snake.set(0, new Coordinate(snake.get(0).getRow()-1,
						snake.get(0).getCol()));
					// snake.remove(1);
				break;
			case 3: snake.set(0, new Coordinate(snake.get(0).getRow(),
						snake.get(0).getCol()+1));
					// snake.remove(1);
				break;
			case 4: snake.set(0, new Coordinate(snake.get(0).getRow()-1,
						snake.get(0).getCol()-1));
					// snake.remove(1);
				break;

		}
		board.printBoard(snake,target);
		processInput();

		// board.setBoard(newBoard[snake.get(0).getCol()][snake.get(0)]) = '@';
		// for (int a = 1; a < snake.size(); a++)
		// 	board.setBoard(newBoard[snake.get(a).getCol()][snake.get(a).getRow()])
	}

	public void checkForCollision() {

		boolean collision = false;

		for (int a = 1; a < snake.size(); a++) {

			// if snake runs into itself
			if (snake.get(0).getRow() == snake.get(a).getRow() && 
					snake.get(0).getRow() == snake.get(a).getRow())
				collision = true;
			
			// if snake runs into border
			else if ((snake.get(0).getRow() > 20 || snake.get(0).getRow() < 0) &&
					(snake.get(0).getCol() > 25 || snake.get(0).getCol() < 0)) {
				
				// try () {	// finish!!
				// 	// newBoard[board.getWidth() + 1][board.getHeight() + 1] = '@'
				// 	moveSnake();	
				// }

			}
		}

		if(collision) {
			printAtEnd();
		}
	}

	/**
	 *	Checks if the location of the target is the same as any coordinate of 
	 *	the snake.
	 */
	public void checkTarget() {

		while (newBoard[target.getCol()][target.getRow()] != ' ')
			target = new Coordinate((int)(Math.random()*25),
						(int)(Math.random()*20));
		// board.setBoard(newBoard[target.getCol()]
	}

	public void quit() {

		String answer = Prompt.getString("\nDo you really want to quit? (y or n)");
		
		if(answer.equalsIgnoreCase("y"))
			printAtEnd();
		else
			board.printBoard(snake, target);
	}

	public void printAtEnd() {

		System.out.println("\nGame is over\nScore = " + score);
		System.out.println("\nThanks for playing SnakeGame!!");
	}
	/**	Print the game introduction	*/
	public void printIntroduction() {
		System.out.println("  _________              __            ________");
		System.out.println(" /   _____/ ____ _____  |  | __ ____  /  _____/_____    _____   ____");
		System.out.println(" \\_____  \\ /    \\\\__  \\ |  |/ // __ \\/   \\  ___\\__  \\  /     \\_/ __ \\");
		System.out.println(" /        \\   |  \\/ __ \\|    <\\  ___/\\    \\_\\  \\/ __ \\|  Y Y  \\  ___/");
		System.out.println("/_______  /___|  (____  /__|_ \\\\___  >\\______  (____  /__|_|  /\\___  >");
		System.out.println("        \\/     \\/     \\/     \\/    \\/        \\/     \\/      \\/     \\/");
		System.out.println("\nWelcome to SnakeGame!");
		System.out.println("\nA snake @****** moves around a board " +
							"eating targets \"+\".");
		System.out.println("Each time the snake eats the target it grows " +
							"another * longer.");
		System.out.println("The objective is to grow the longest it can " +
							"without moving into");
		System.out.println("itself or the wall.");
		System.out.println("\n");
	}
	
	/**	Print help menu	*/
	public void helpMenu() {
		System.out.println("\nCommands:\n" +
							"  w - move north\n" +
							"  s - move south\n" +
							"  d - move east\n" +
							"  a - move west\n" +
							"  h - help\n" +
							"  f - save game to file\n" +
							"  r - restore game from file\n" +
							"  q - quit");
		Prompt.getString("Press enter to continue");
	}
	
}