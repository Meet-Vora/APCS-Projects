import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *  Voting.java
 *  Program created for parsing the 2016 Presidential Results data set. 
 *  Collects the total democratic votes and GOP votes per state, and stores that
 *  value in an ArrayList of the wrapper class VotingStates.java.
 *  The method parseVoting() is called by CollectiveState.java to create the final
 *  ArrayList.
 * 
 *  @author Dinesh Thirumavalavan
 *  @since  March 13 2019
 */

public class Voting {
	
	private List<VotingState> states; // ArrayList of VotingStates
	
	/**
	 *  Constructor - Initializes the ArrayList 
	 */
	public Voting() {
		states = new ArrayList<VotingState>();
	}
	
	/**
	 *  Main method called from CollectiveState.java. 
	 *  Formats the .csv file, and collects the necessary information, and
	 *  stores it into an ArrayList of VotingStates
	 * 
	 *  @return 	the List containing all the VotingStates
	 */
	public List<VotingState> parseVoting() {
		Scanner reader = openToRead("2016_Presidential_Results.csv");
		int index = 0;
		
		// to skip through the repeat counties in Alaska
		for(int i = 0; i < 29; i ++) reader.nextLine();
	
		while(reader.hasNext())
		{
			String line = reader.nextLine();
			
			int numInQuotes = findNumInQuotes(line);
			
			line = line.replaceAll(",", " ");
			
			StringTokenizer st = new StringTokenizer(line);
			st.nextToken();
			int demVotes = (int)(Double.parseDouble(st.nextToken()));
			int gopVotes = (int)(Double.parseDouble(st.nextToken()));
			
			for(int i = 0; i < 5 + numInQuotes; i ++) st.nextToken();			
			
			String name = st.nextToken();
			
			if(states.size() != 0 && !name.equals( states.get( states.size() - 1 ).getStateName())) 
				index = mergeCountyData(index);
			states.add(new VotingState(demVotes, gopVotes, name));
		}
		index = mergeCountyData(index);
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
	 *  Used to find the number of commas in the quotes to properly parse through them.
	 * 
	 *  @param line 		line in the file to check
	 * 	@return 			Number of commas in the line
	 */
	private int findNumInQuotes(String line) {
		int numInQuotes = 0;
		for(int i = line.indexOf("\"") + 1; i < line.lastIndexOf("\""); i ++) {
			if(line.charAt(i) == ',')
				numInQuotes ++;
		}
		return numInQuotes;
	}
	
	/**
	 *  Merges all the county data in the states array into one large state,
	 *  adding up all the democratic and republican votes.
	 * 
	 *  @param index 	index to go to in the array
	 *  @return 		new value of index to go to in the array
	 */
	private int mergeCountyData(int index) {
		int totalDemVotes = 0, totalGopVotes = 0;
		int stateSize = states.size();
		String stateName = states.get(states.size() - 1).getStateName();
		for(int j = index; j < stateSize; j ++) {
			totalDemVotes += states.get(index).getDemVotes();
			totalGopVotes += states.get(index).getGopVotes();
			states.remove(index);
		}
		if(!stateName.equals("DC"))
			states.add(new VotingState(totalDemVotes, totalGopVotes, stateName)); 
		index ++;
		return index;
	}
	
	
	/**
	 *  Tester method - to check if the algorithm works properly or not.
	 */
	public static void main(String[] args) {
		Voting v = new Voting();
		v.parseVoting();
		System.out.println(v.states);
	}
}	
