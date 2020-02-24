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
 *	@since	March 12th, 2019
 */
public class EducationNumbers {
	// field variables
	private int fips;				// fips code
	private String abbreviation;	// state abbreviation
	private String state;  			// state name

	private int lessThanHsDipNum;	// number of people with less than a HS diploma
	private int hsDiplomaNum;		// number of people with a HS diploma
	private int someCollegeNum;		// number of people with some college experience
	private int bachDegreeNum;		// number of people with a bachelor's degree

	private double lessThanHsDipPercent;	// percent of people with less than a HS diploma
	private double hsDiplomaPercent;		// percent of people with a HS diploma
	private double someCollegePercent;		// percent of people with some college experience
	private double bachDegreePercent;		// percent of people with a bachelor's degree	

	// constructor
	public EducationNumbers(int fipsCode, String stateAbbrev, String stateName, int lessHsDipN,
					 int hsDipN, int someColN, int bachDegN, double lessHsDipPer,
					 double hsDipPer, double someColPer, double bachDegPer) {
		fips = fipsCode;
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
	}

	/*** Getter methods used to access data ***/

	public int getFipsCode() { return fips; }
	public String getStateAbbrev() { return abbreviation; }
	public String getStateName() { return state; }
	
	public int getLTHSDNum() { return lessThanHsDipNum; }
	public int getHSDNum() { return hsDiplomaNum; }
	public int getSCNum() { return someCollegeNum; }
	public int getBDNUm() { return bachDegreeNum; }

	public double getLTHSDPercent() { return lessThanHsDipPercent; }
	public double getHSDPercent() { return hsDiplomaPercent; }
	public double getSCPercent() { return someCollegePercent; }
	public double getBDPercent() { return bachDegreePercent; }
	
	public String toString() { return "\nAbbr: " + abbreviation + ", State Name: " + state + ", BachDegreeNum: " + bachDegreeNum; }
}
