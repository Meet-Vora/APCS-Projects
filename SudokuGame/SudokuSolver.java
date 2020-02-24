import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *	SudokuSolver - Solves an incomplete Sudoku puzzle using recursion and backtracking
 *
 *	@author	
 *	@since	
 *
 */
public class SudokuSolver {

	private int[][] puzzle;		// the Sudoku puzzle
	
	private String PUZZLE_FILE = "puzzle1.txt";	// default puzzle file
	
	/* Constructor */
	public SudokuSolver() {
		puzzle = new int[9][9];
		// fill puzzle with zeros
		for (int row = 0; row < puzzle.length; row++)
			for (int col = 0; col < puzzle[0].length; col++)
				puzzle[row][col] = 0;
	}
	
	public static void main(String[] args) {
		SudokuSolver sm = new SudokuSolver();
		sm.run(args);
	}
	
	public void run(String[] args) {
		// get the name of the puzzle file
		String puzzleFile = PUZZLE_FILE;
		if (args.length > 0) puzzleFile = args[0];
		
		System.out.println("\nSudoku Puzzle Solver");
		// load the puzzle
		System.out.println("Loading puzzle file " + puzzleFile);
		loadPuzzle(puzzleFile);
		printPuzzle();
		// solve the puzzle starting in (0,0) spot (upper left)
		solvePuzzle(0, 0);
		printPuzzle();
	}
	
	/**	Load the puzzle from a file
	 *	@param filename		name of puzzle file
	 */
	public void loadPuzzle(String filename) {
		Scanner infile = FileUtils.openToRead(filename);
		for (int row = 0; row < 9; row++)
			for (int col = 0; col < 9; col++)
				puzzle[row][col] = infile.nextInt();
		infile.close();
	}
	
	/**	Solve the Sudoku puzzle using brute-force method. */
	public boolean solvePuzzle(int row, int column) {
		
		int[] nums = new int[9];
		int rand = 0;
		
		if(puzzle[row][column] == 0) {
			if(row == 9) return true;    // puzzle done! 
			
			// generates random numbers from 1 to 9 for each of the 9 indices in the array
			for(int a = 0; a < 9; a++) {
				nums[a] = (int)(Math.random()*9 + 1);;
				// makes sure the random number generated has not already been saved
				for(int b = 0; b < a; b++) {
					if(nums[a] == nums[b]) a--;
				}
				// nums[a] = rand;
			}
			
			for (int i = 0; i<9; i++){
				System.out.print(nums[i]);
			}
			System.out.println();
			
			/*
			for(int a = 0; a < nums.length; a++) {
				if(nums[a] != puzzle[row][a])
					first = false;
				if(nums[a] != puzzle[a][column])
					second = false;
			}
			*/
			
			for(int a = 0; a < 9; a++) {
				boolean checks = true;
				boolean done = false;
				for(int b = 0; b < 9; b++) {
					if(nums[a] == puzzle[row][b])
						checks = false;
					if(nums[a] == puzzle[b][column])
						checks = false;
				}
				
				for(int boxRow = 3*(row/3); boxRow < 3*(row/3) + 3; boxRow++) {
					for(int boxCol = 3*(column/3); boxCol < 3*(column/3) + 3; boxCol++) {
						if(nums[a] == puzzle[boxRow][boxCol])
							checks = false;
					}
				}
			
				if(checks) {
					puzzle[row][column] = nums[a];
					if(column == 8)
						if (solvePuzzle(row + 1,0))
							return true;
						else puzzle[row][column] = 0;
					else if(solvePuzzle(row,column + 1))
						return true;
					else puzzle[row][column] = 0;
				}
			}
			return false;
		}
		else {
			if(column == 8)
				if (solvePuzzle(row + 1,0))
					return true;
				else if(solvePuzzle(row,column + 1))
					return true;
		}
		return false;
	}
	
		
	/**
	 *	printPuzzle - prints the Sudoku puzzle with borders
	 *	If the value is 0, then print an empty space; otherwise, print the number.
	 */
	public void printPuzzle() {
		System.out.print("  +-----------+-----------+-----------+\n");
		String value = "";
		for (int row = 0; row < puzzle.length; row++) {
			for (int col = 0; col < puzzle[0].length; col++) {
				// if number is 0, print a blank
				if (puzzle[row][col] == 0) value = " ";
				else value = "" + puzzle[row][col];
				if (col % 3 == 0)
					System.out.print("  |  " + value);
				else
					System.out.print("  " + value);
			}
			if ((row + 1) % 3 == 0)
				System.out.print("  |\n  +-----------+-----------+-----------+\n");
			else
				System.out.print("  |\n");
		}
	}
}