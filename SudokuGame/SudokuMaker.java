/**
 *	SudokuMaker - Creates a Sudoku puzzle using recursion and backtracking
 *
 *	@author		Meet Vora
 *	@version	 
 *
 */
public class SudokuMaker {

	private int[][] puzzle = new int[9][9];
	private int counter = 0;

	public static void main(String[] args) {
		SudokuMaker sm = new SudokuMaker();
		System.out.println(2/0);
		sm.createPuzzle(0,0);
		sm.printPuzzle();
	}
	
	public boolean createPuzzle(int row, int column) { 
		int[] nums = new int[9];
		int rand = 0;
		// boolean first = true;
		// boolean second = true;
		// boolean third = true;
		
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
				//System.out.println(counter);
				counter++;
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
					if (createPuzzle(row + 1,0))
						return true;
					else puzzle[row][column] = 0;
				else if(createPuzzle(row,column + 1))
					return true;
				else puzzle[row][column] = 0;
			}
		}
		return false;
		/*
		// for(int a = 0; a < nums.length; a++) {
		//if(!first && !second && !third)
			// puzzle[row][column] = nums[a];
			//return createPuzzle(row,column+1);				
		//}
		
		
		if(row < 8 && column == 8)
			return createPuzzle(row+1,0);
		else if(createPuzzle(row,column+1))
			return true;
		else
			puzzle[row][column] = 0;
		return false;
		*/
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