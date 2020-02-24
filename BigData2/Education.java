import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *	Education - Designed to parse the Education.csv file and save it into an
 *				ArrayList of a wrapper class. Collects state name, fips code,
 *				state abbreviation, and education numbers and percentages of 
 *				people with 4 different levels of education in all 50 states,
 *				and the U.S. as a whole for the years 2012-2016. Data is then 
 *				accessed by CollectiveState.java and and printed into another 
 *				.csv file.
 *
 *
 *	@author	Meet Vora
 *	@since	March 13th, 2019
 */
public class Education {
	
	// List of states with relevant information
	private List<EducationNumbers> studies;
		int counter = 0;

	// data file to read
	private final String DATA_FILE = "Education.csv";

	// constructor
	public Education() {
		studies = new ArrayList<EducationNumbers>();
	}

	/*** Main Method ***/
	public static void main(String[] args) {
		Education edu = new Education();
		edu.readFile();
	}

	/**
	 *	The main utility of the class. Opens the Education.csv data file and 
	 *	stores the information from the tokens Arraylist to an ArrayList of 
	 *	EducationNumbers.
	 */
	public List<EducationNumbers> readFile() {
		String[] tokens = new String[100];
		String line = "";
		List<String> tokensList = new ArrayList<String>();
		// Opens file to read
		Scanner read = FileUtils.openToRead(DATA_FILE);
		
		// skip 5 lines in file to get to relevant information
		for(int a = 0; a < 5; a++)
			read.nextLine();

		// check if line contains information of the entire state. If so, then
		// tokenize relevant informatin from the line
		while(read.hasNext()) {
			line = read.nextLine();
			if(checkLine(line)) {
				// .split() is from java.lang library and the tokenizing expression
				// is from Bart Kiers on StackOverflow
				// https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
				tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				line = "";
				tokensList = removeInfo(tokens);

				// adding data to ArrayList of wrapper class
				studies.add(new EducationNumbers(Integer.parseInt(tokensList.get(0)),
											 				  tokensList.get(1),
															  tokensList.get(2),
											 Integer.parseInt(tokensList.get(3)),
											 Integer.parseInt(tokensList.get(4)),
											 Integer.parseInt(tokensList.get(5)),
											 Integer.parseInt(tokensList.get(6)),
											 Double.parseDouble(tokensList.get(7)),
											 Double.parseDouble(tokensList.get(8)),
											 Double.parseDouble(tokensList.get(9)),
											 Double.parseDouble(tokensList.get(10))));
			}
		}
		read.close();

		for(int a = studies.size() - 1; a > 0; a--) {
			if(studies.get(a).getStateName().equals("Puerto Rico") || 
			   studies.get(a).getStateName().equals("District of Columbia"))
					studies.remove(a);
		}
		studies.remove(0);
		return studies;
	}

	/**
	 *	Checks if there are 5 commas present after the county name to see if
	 *	the line represents the information of the entire state or country.
	 *	@param line 	line read from the data file
	 *	@return 		true if 5 commas after county name; false otherwise
	 */
	public boolean checkLine(String line) {
		if(line.substring(2,5).equals("000"))
			return true;
		return false;
	}

	/**
	 *	Removes unnecessary information from tokens ArrayList
	 *	@param tokens 	Arraylist of tokenized information of entire line from file
	 *	@return 		Arraylist of relevant tokenized information   
	 */
	public List<String> removeInfo(String[] tokens) {
		List<String> tokensList = new ArrayList<String>();
		// transfer data from array to ArrayList
		for(int a = 0; a < tokens.length; a++) {
			if(!tokens[a].equals("")) {
				tokensList.add(tokens[a]);
			}
		}

		// removes elements that contain irrelevant information 
		for(int a = 34; a > 2; a--){
			tokensList.remove(a);
		}

		// removes the extra set of quotes if String in tokensList has them.
		// also removes the extra commas in numbers with an empty string so they
		// can be parsed
		for(int a = 0; a < tokensList.size(); a++) {
			String word = tokensList.get(a);
			if(word.substring(0,1).equals("\"")) {
				word = word.substring(1,word.length() - 1);
				tokensList.set(a,word);
			}
			
			if(word.indexOf(',') != -1) {
				word = word.replaceAll(",","");
				tokensList.set(a,word);
			}
		}
		return tokensList;
	}
}
