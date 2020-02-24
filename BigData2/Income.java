import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *  Income.java
 *  Program created for parsing the Income data set. 
 *  Collects the the mean and median household, non-household, and total income,
 *  and stores those value in an ArrayList of the wrapper class IncomeStates.java.
 *  The method parseIncome() is called by CollectiveState.java to create the final
 *  ArrayList.
 * 
 *  @author Dinesh Thirumavalavan
 *  @since  March 15 2019
 */
 
public class Income {
	
	private List<IncomeState> states; // ArrayList of VotingStates
	
	/**
	 *  Constructor - Initializes the ArrayList 
	 */
	public Income() {
		states = new ArrayList<IncomeState>();
	}
	
	/**
	 *  Main method called from CollectiveState.java. 
	 *  Formats the .csv file, and collects the necessary information, and
	 *  stores it into an ArrayList of IncomeStates
	 * 
	 *  @return 	the List containing all the IncomeStates
	 */
	public List<IncomeState> parseIncome() {
		Scanner reader = openToRead("ACS_17.csv");
		reader.nextLine();
		reader.nextLine();
		
		while(reader.hasNext()) {
			String line = reader.nextLine().replaceAll(",,",",*,").replaceAll(",", "  ");
			StringTokenizer st = new StringTokenizer(line);
			skipTokens(2, st);
			String name = getEntireName(st);
			skipTokens(183, st);
			
			// navigates through all the data to collect the relevant data
			int meti = Integer.parseInt(st.nextToken());
			skipTokens(2, st);
			int mti = Integer.parseInt(st.nextToken());
			skipTokens(67, st);
			int mefi = Integer.parseInt(st.nextToken());
			skipTokens(2, st);
			int mfi = Integer.parseInt(st.nextToken());
			skipTokens(9, st);
			int menfi = Integer.parseInt(st.nextToken());
			skipTokens(2, st);
			int mnfi = Integer.parseInt(st.nextToken());
			
			// to keep only the 50 states 
			if(!name.equals("District of Columbia") && !name.equals("Puerto Rico"))
				states.add(new IncomeState(mfi, mnfi, mti, mefi, menfi, meti, name)); 
		}
		reader.close();
		return states;
	}
	
	/**
	 *  Creates a Scanner of the file, and uses a try-catch block to make
	 *  sure that the file exists.
	 * 
	 *  @param fileName File name of the file to read
	 *  @return 		Scanner object for the file
	 */
	private Scanner openToRead(String fileName)
	 {
		Scanner input = null;
		try {
			input = new Scanner(new java.io.File(fileName));
		}
		catch (FileNotFoundException e)
		{
			System.err.println("ERROR: Cannot open " + fileName + " for reading.");
			System.exit(1);
		}
		return input;
	 }
	 
	 /**
	  *  Skips through a certain number of tokens in StringTokenizer
	  * 
	  *  @param skipNum 	number of tokens to skip through
	  *  @param st 			StringTokenizer to skip through
	  */
	 private void skipTokens(int skipNum, StringTokenizer st) {
		for(int i = 0; i < skipNum; i ++)
			st.nextToken();
	 }
	
	 /**
	  *  Gets the entire name of the State, since some of the state names
	  *  have spaces in them
	  *  
	  *  @param st 		StringTokenizer to get the state name from
	  *  @return 		The state name
	  */
	 private String getEntireName(StringTokenizer st){
		String name = "", nextToken = st.nextToken();
		boolean bool = true;
		while(bool) {
			try {
				Integer.parseInt(nextToken);
				bool = false;
			}catch(NumberFormatException e) {
				name += nextToken + " ";
				nextToken = st.nextToken();	
			}	
		}
		return name.substring(0, name.length() - 1);
	 }
	
	/**
	 *  Tester method - to check if the algorithm works properly or not.
	 */
	public static void main(String[] args) {
		Income i = new Income();
		i.parseIncome();
		System.out.println(i.states);
	}
}	
