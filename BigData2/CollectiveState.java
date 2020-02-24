import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 *	CollectiveState -  
 *
 *
 *	@author	Meet Vora, Dinesh Thirumavalavan
 *	@since	March 21st, 2019
 */
public class CollectiveState {

	// private List<State> allStates;
	private List<EducationNumbers> educationState;
	private List<IncomeState> incomeState;
	private List<VotingState> votingState;

	private Education edu;
	private Income income;
	private Voting voting;

	public CollectiveState() {
		// allStates = new ArrayList<State>();
		educationState = new ArrayList<EducationNumbers>();
		incomeState = new ArrayList<IncomeState>();
		votingState = new ArrayList<VotingState>();

		edu = new Education();
		income = new Income();
		voting = new Voting();
	}

	public static void main(String[] args) {
		CollectiveState cs = new CollectiveState();
		cs.run();
	}

	public void run() {
		educationState = edu.readFile();
		incomeState = income.parseIncome();
		votingState = voting.parseVoting();

		// complieData() used to match data up and put it in one element of the allStates arrayList
		compileData();
	}



	public void compileData() {
		PrintWriter writer = FileUtils.openToWrite("State.csv");
		for(int i = 0; i < 50; i ++) {

			// state name data
			String stateName = incomeState.get(i).getName();
			String stateAbbr = educationState.get(i).getStateAbbrev();
			
			// education data
			int lessThanHsDipNum = educationState.get(i).getLTHSDNum();
			int hsDiplomaNum = educationState.get(i).getHSDNum();
			int someCollegeNum = educationState.get(i).getSCNum();
			int bachDegreeNum = educationState.get(i).getBDNUm();
			double lessThanHsDipPercent = educationState.get(i).getLTHSDPercent();
			double hsDiplomaPercent = educationState.get(i).getHSDPercent();
			double someCollegePercent = educationState.get(i).getSCPercent();
			double bachDegreePercent = educationState.get(i).getBDPercent();
			
			// income data
			int meanhi = incomeState.get(i).getMHI();
			int meannhi = incomeState.get(i).getMNHI();
			int meanti = incomeState.get(i).getMTI();
			int medianhi = incomeState.get(i).getMEHI();
			int mediannhi = incomeState.get(i).getMENHI();
			int medianti = incomeState.get(i).getMETI();
			
			// voting data
			int votingI = -1;
			boolean goThroughVoting = true;
			while(goThroughVoting) {
				votingI ++;
				if(votingState.get(votingI).getStateName().equals(stateAbbr))
					goThroughVoting = false;

			}
			
			int demVotes = votingState.get(votingI).getDemVotes();
			int gopVotes = votingState.get(votingI).getGopVotes();
			
			writer.println("State abbreviation, State name, Less than a high school diploma (2012-2016), " + 
				"High school diploma only (2012-2016), Some college or associate's degree (2012-2016), " +
				"Bachelor's degree or higher (2012-2016), Percent of adults with less than a high " + 
				"school diploma (2012-2016), Percent of adults with a high school diploma only (2012-2016), " + 
				"Percent of adults completing some college or associate's degree (2012-2016), " + 
				"Percent of adults with a bachelor's degree or higher (2012-2016), Mean Household Income, " + 
				"Mean Non-Household Income, Mean Total Income, Median Household Income, Median Non-Household " + 
				"Income, Median Total Income, Democratic votes, GOP votes");

			writer.println();
			// for(int i = 0; i < allStates.size(); i++) {
			writer.println(stateName + ", " + stateAbbr + ", "  +
						   lessThanHsDipNum + ", " + hsDiplomaNum + ", " +
						   someCollegeNum + ", " + bachDegreeNum + ", " +
						   lessThanHsDipPercent + ", " + hsDiplomaPercent + ", " +
						   someCollegePercent + ", " + bachDegreePercent + ", " +
						   meanhi + ", " + meannhi + ", " +
						   meanti + ", " + medianhi + ", " + 
						   mediannhi + ", " + medianti + ", " +
						   demVotes + ", " + gopVotes);

							  //  incomeState.get(i).getName() + ", " +
							  //  educationState.get(i).getLTHSDNum() + ", " + 
							  //  educationState.get(i).getHSDNum() + ", " +
							  //  educationState.get(i).getSCNum() + ", " +
						   // educationState.get(i).getBDNUm() + ", " +
						   // educationState.get(i).getLTHSDPercent() + ", " +
						   // educationState.get(i).getHSDPercent() + ", " +
						   // educationState.get(i).getSCPercent() + ", " +
						   // educationState.get(i).getBDPercent() + ", " +
						   // incomeState.get(i).getMHI() + ", " +
						   // incomeState.get(i).getMNHI() + ", " +
						   // incomeState.get(i).getMTI() + ", " +
						   // incomeState.get(i).getMEHI() + ", " +
						   // incomeState.get(i).getMENHI() + ", " +
						   // incomeState.get(i).getMETI() + ", " +
						   // votingState.get(votingI).getDemVotes() + ", " +
						   // votingState.get(votingI).getGopVotes());

			// 
			// allStates.add(new State(stateAbbr, stateName, lessThanHsDipNum, hsDiplomaNum, someCollegeNum, 
			// 	bachDegreeNum, lessThanHsDipPercent, hsDiplomaPercent, someCollegePercent,
			// 	bachDegreePercent, meanhi, meannhi, meanti, medianhi, mediannhi,
			// 	medianti, demVotes, gopVotes)); 		
		}
		// System.out.println(allStates);
	}
	// public void writeInfo() {}
}

	
