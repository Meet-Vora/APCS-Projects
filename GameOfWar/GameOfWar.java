/**
 *	The Game of War
 *	(Description)
 *
 *	Requires ListNode and Prompt classes
 *	
 *	@author	
 *	@since	
 */
public class GameOfWar
{
	/*	Fields  */
	private DeckOfCards fullDeck;
	private DeckOfCards myDeck;
	private DeckOfCards compDeck;
	
	private DeckOfCards myPile;
	private DeckOfCards compPile;

	/**	Constructor */
	public GameOfWar() {
		fullDeck = new DeckOfCards(); 
		myDeck = new DeckOfCards();
		compDeck = new DeckOfCards();
		
		myPile = new DeckOfCards();
		compPile = new DeckOfCards();
		
	}

	public static void main(String[] args) {
		GameOfWar gow = new GameOfWar();
		gow.run();
	}

	/**
	 *	Takes user input for type of game to play. Based on user input, either
	 *	runs the simulation or the interactive game.
	 */
	public void run() {
		printIntroduction();
		splitDeck();
		String input = "";

		System.out.println();
		// Get user input. Must be either "i" or "s"
		do {
			input = Prompt.getString("Interactive or Simulation (i or s)");
		} while(!input.equalsIgnoreCase("i") && !input.equalsIgnoreCase("s"));

		// if "s" input, run simulation
		if(input.equalsIgnoreCase("s"))
			simulation();
		// otherwise run interactive portion of game
		else
			interactive();
	}

	/** 
	 *	Runs game simulation. Runs as many games as user indicates. Prints number
	 *	of turns in and the winner of each game, and gives average number of turns
	 *	in all the games and number of times user won the game. 
	 */
	public void simulation() {
		// gets number of runs simulation must make from user
		int runs = Prompt.getInt("Number of Games (20 - 50)", 20, 50);
		int turns = 0, totalTurns = 0, wins = 0;

		System.out.println();
		// run as many times as indicated by user
		for(int a = 0; a < runs; a++) {
			turns = 0;
			myDeck.clear(); compDeck.clear();
			splitDeck();
			// while either deck still has cards, keep doing the simulation
			while(myDeck.size() != 0 && compDeck.size() != 0) {
				// get the top card in the deck, add them to the
				// middle piles, and compare
				Card myCard = myDeck.draw();
				Card compCard = compDeck.draw();
				myPile.add(myCard);
				compPile.add(compCard);
				int compare = myCard.getRank().compareTo(compCard.getRank());

				// if player card higher, add all cards from middle pile to
				// player's deck
				if(compare > 0) {
					myDeck.add(myPile.draw());
					myDeck.add(compPile.draw());
				}
				// if computer card's higher, all add cards from middle pile to
				// computer's deck
				else if(compare < 0) {
					compDeck.add(myPile.draw());
					compDeck.add(compPile.draw());
				}

				// if same rank card, play WAR! Draw 4 cards, and compare rank
				// of 4th one
				else {
					int repeat = 0;
					boolean done = false;
					do {
						// checks if both decks have enough cards to play WAR! If
						// not enough cards, then stop playing WAR!
						if(myDeck.size() >= 4 + 4*repeat && compDeck.size() >= 4 + 4*repeat) {
							// Player and computer burn three cards before drawing 4th. Add
							// to middle piles
							myPile.add(myDeck.draw()); 
							myPile.add(myDeck.draw()); 
							myPile.add(myDeck.draw());

							compPile.add(compDeck.draw());
							compPile.add(compDeck.draw());	
							compPile.add(compDeck.draw());

							// save 4th card to compare, then add to middle piles
							myCard = myDeck.draw();
							myPile.add(myCard);
							compCard = compDeck.draw();
							compPile.add(compCard);

							// compare ranks of 4th card
							compare = myCard.getRank().compareTo(compCard.getRank());
							// records how many times WAR! is played
							repeat++;
						}
						else done = true;
					} while(compare == 0 && !done);

					// if not enough cards to play war, all cards from middle
					// and own deck are transferred to opponent
					if(done)
						isOverWithWar();

					// if player card higher, add all cards from middle piles to player deck
					if(compare > 0) {
						while(!myPile.isEmpty() && !compPile.isEmpty()) {
							myDeck.add(myPile.draw());
							myDeck.add(compPile.draw());
						}
					}
					// otherwise, add all cards from middle piles to computer deck
					else if (compare < 0) {
						while(!myPile.isEmpty() && !compPile.isEmpty()) {
							compDeck.add(myPile.draw());
							compDeck.add(compPile.draw());
						}
					}
				}
				// records how many turns have passed per run
				turns++;
				// shuffles player and computer decks every 10 turns
				if(turns%10 == 0) {
					myDeck.shuffle(); compDeck.shuffle();
				}
			}
			// saves total number of turns passed in all runs
			totalTurns += turns;
		 	// prints that player won	
			if(myDeck.size() >= 48 && myDeck.size() <= 52) {
				System.out.println("Game " + (a + 1) + ": " + turns + " turns  Player won");
				wins++;
			}
			// prints that computer won
			else
				System.out.println("Game " + (a + 1) + ": " + turns + " turns  Computer won");
		}
		System.out.printf("\nAverage number of turns = %.2f\n",(totalTurns*1.0)/runs);
		System.out.println("\nPlayer won " + wins + " times.");
	}

	/**
	 *	Runs interactive portion of game. Based on user input, the game is played,
	 *	the deck if shuffled, or the game is quit. Keeps live tally of number of
	 *	cards possessed by the player and computer. Prints congratulatory and
	 *	informative messages for user.
	 */
	public void interactive() {
		boolean quit = false;
		System.out.println("\nDeck sizes: player = " + myDeck.size() + " computer = "
			+ compDeck.size() + "\n");

		// keep playing turn until user wants to quit or game is over
		do {
			// get input from user
			String input = Prompt.getString("Press 'ENTER' to fight another " 
				+ "battle or 'S' to shuffle your deck");
			
			// if user enters "q", set quit to true to quit program
			if(input.equalsIgnoreCase("q"))
				quit = true;
			// shuffle deck if user enters "s" 
			else if(input.equalsIgnoreCase("s")) {
				myDeck.shuffle();
				System.out.println("Your deck has been shuffled.\n");
			}
			// otherwise, continue on with game
			else {
				// get the top card in the deck, add them to the
				// middle piles, and compare
				Card myCard = myDeck.draw();
				Card compCard = compDeck.draw();
				myPile.add(myCard);
				compPile.add(compCard);

				System.out.println("You drew " + myCard.getRank() + " of "
					+ myCard.getSuit());
				System.out.println("The computer drew " + compCard.getRank() + " of "
					+ compCard.getSuit());
				
				int compare = myCard.getRank().compareTo(compCard.getRank());
				// if player card higher, add all cards from middle pile to
				// player's deck
				if(compare > 0) {
					myDeck.add(myPile.draw());
					myDeck.add(compPile.draw());
					System.out.print("You won two cards! ");
				}
				// if computer card's higher, all add cards from middle pile to
				// computer's deck
				else if(compare < 0) {
					compDeck.add(myPile.draw());
					compDeck.add(compPile.draw());
					System.out.print("The computer won two cards! ");
				}
				// if same rank card, play WAR! Draw 4 cards, and compare rank
				// of 4th one
				else {
					int repeat = 0;
					boolean done = false;
					do {
						// checks if both decks have enough cards to play WAR! If
						// not enough cards, then stop playing WAR!
						if(myDeck.size() >= 4 + 4*repeat && compDeck.size() >= 4 + 4*repeat) {
							// Player and computer burn three cards before drawing 4th. Add
							// to middle piles
							System.out.println("It's a tie! Battle again!");
							// Player and computer burn three cards before drawing 4th
							myPile.add(myDeck.draw()); 
							myPile.add(myDeck.draw()); 
							myPile.add(myDeck.draw());

							compPile.add(compDeck.draw());
							compPile.add(compDeck.draw());	
							compPile.add(compDeck.draw());

							// save 4th card to compare, then add to middle piles
							myCard = myDeck.draw();
							myPile.add(myCard);
							compCard = compDeck.draw();
							compPile.add(compCard);

							// compare ranks of 4th card
							compare = myCard.getRank().compareTo(compCard.getRank());
							
							// print what card player and computer drew
							System.out.println("You drew " + myCard.getRank() 
								+ " of " + myCard.getSuit());
							System.out.println("The computer drew " + compCard.getRank()
								+ " of " + compCard.getSuit());
							
							// keeps track of number of times WAR! is played
							repeat++;
						}
						else done = true;
					} while(compare == 0 && done == false);

					// if not enough cards to play war, all cards from middle
					// and own deck are transferred to opponent
					if(done)
						isOverWithWar();

					// if player card higher, add all cards from middle piles to player deck
					if(compare > 0) {
						// myDeck += repeat*4 + 1; compDeck -= repeat*4 + 1;
						while(!myPile.isEmpty() && !compPile.isEmpty()) {
							myDeck.add(myPile.draw());
							myDeck.add(compPile.draw());
						}
						System.out.print("You won " + 2*(repeat*4 + 1) + " cards! ");
					}
					// otherwise, add all cards from middle piles to computer deck
					else if (compare < 0) {
						while(!myPile.isEmpty() && !compPile.isEmpty()) {
							compDeck.add(myPile.draw());
							compDeck.add(compPile.draw());
						}
						System.out.print("The computer won " + 2*(repeat*4 + 1) + " cards! ");
					}
				}
				System.out.println("Deck sizes: player = " + myDeck.size() 
					+ " computer = " + compDeck.size() + "\n");
			}

		} while(!quit && !myDeck.isEmpty() && !compDeck.isEmpty());

		// congratulate player if they won
		if(quit) System.out.println("Thanks for playing!");
		else if(myDeck.size() >= 49 && myDeck.size() <= 52) 
			System.out.println("Congratulations - PLAYER won!!!");
		else if(compDeck.size() >= 49 && compDeck.size() <= 52)
			System.out.println("Sorry - COMPUTER won.");
	}
	
	/**
	 *	Shuffles the full deck and splits the cards halfway between the player's and
	 *	computer's deck
	 */
	public void splitDeck() {
		// fill and shuffle the full deck
		fullDeck.fill();
		fullDeck.shuffle();

		// add every other card in the full deck to each of the separate decks
		for(int a = 0; a < 26; a++) {
			myDeck.add(fullDeck.draw());
			compDeck.add(fullDeck.draw());
		}
	}

	/**
	 *	Transfers cards between player and computer if either one has less than 4
	 *	cards while playing WAR!, since they would not have enough careds to 
	 *	continue playing. 
	 */
	public void isOverWithWar() {
		// if player is winning, then give them all the cards from the computer's
		// deck and pile
		if(myDeck.size() > compDeck.size()){
			while(!compPile.isEmpty())
				myDeck.add(compPile.draw());
			while(!compDeck.isEmpty())
				myDeck.add(compDeck.draw());
		}
		// otherwise give computer all the remaining cards from the player's
		// deck and pile
		if(myDeck.size() < compDeck.size()){
			while(!myPile.isEmpty())
				compDeck.add(myPile.draw());
			while(!myDeck.isEmpty())
				compDeck.add(myDeck.draw());
		}
	}

	/**	Prints the introduction to the game */
	public void printIntroduction()
	{
		System.out.println("   ______                        ____  ____   _       __");
		System.out.println("  / ____/___ _____ ___  ___     / __ \\/ __/  | |     / /___ ______");
		System.out.println(" / / __/ __ `/ __ `__ \\/ _ \\   / / / / /_    | | /| / / __ `/ ___/");
		System.out.println("/ /_/ / /_/ / / / / / /  __/  / /_/ / __/    | |/ |/ / /_/ / /");
		System.out.println("\\____/\\__,_/_/ /_/ /_/\\___/   \\____/_/       |__/|__/\\__,_/_/"); 
		System.out.println("\nWelcome to the Game of War");
	}
}