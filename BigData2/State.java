/**
 *	EducationNumbers - fips code, state name, area name, and number and percent 
 *					 of people with:
 *					 	1. less than a high school diploma
 *					 	2. high school diploma
 *					 	3. some college
 *					 	4. bachelor's degree
 *					 
 *					 in 2012-2016.
 *
 *	@author	Meet Vora
 *	@since	March 21st, 2019
 */
public class State {

	// voting fields
	private int demVotes, gopVotes;

	// income fields
	private int meanHouseholdIncome, meanNonHouseholdIncome, meanTotalIncome;
	private int medianHouseholdIncome, medianNonHouseholdIncome, medianTotalIncome;

	// Education fields
	private int lessThanHsDipNum, hsDiplomaNum, someCollegeNum, bachDegreeNum;		
	private double lessThanHsDipPercent, hsDiplomaPercent, someCollegePercent, 
						bachDegreePercent;

	// stateName fields
	private String abbreviation, state;

	public State(String stateAbbrev, String stateName, int lessHsDipN, int hsDipN, int someColN, 
				int bachDegN, double lessHsDipPer, double hsDipPer, double someColPer,
				double bachDegPer, int meanhi, int meannhi, int meanti, int medianhi, int mediannhi,
				int medianti, int demVotes, int gopVotes) {

		abbreviation = stateAbbrev;
		state = stateName;

		lessThanHsDipNum = lessHsDipN;
		hsDiplomaNum = hsDipN;
		someCollegeNum = someColN;
		bachDegreeNum = bachDegN;
		lessThanHsDipPercent = lessHsDipPer;
		hsDiplomaPercent = hsDipPer;
		someCollegePercent = someColPer;
		bachDegreePercent = bachDegPer;

		this.demVotes = demVotes;
		this.gopVotes = gopVotes;

		meanHouseholdIncome = meanhi;
		meanNonHouseholdIncome = meannhi;
		meanTotalIncome = meanti;
		medianHouseholdIncome = medianhi;
		medianNonHouseholdIncome = mediannhi;
		medianTotalIncome = medianti;
	}
	
	public String toString() { return "\nState name: " + state + ", abbr: " + abbreviation + 
		", Dem. Votes: " + demVotes + ", GOP Votes: " + gopVotes + ", medianHI: " + medianHouseholdIncome + ", Bach Degree: " + bachDegreeNum; }
}
