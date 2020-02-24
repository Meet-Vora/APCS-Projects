/**
 *  VotingState.java
 * 
 *  Wrapper class for the states array from Voting.java.
 *  Has variables to store the stateName, the total number of democrat votes, and 
 *  the total number of stateVotes
 * 
 *  @author Dinesh Thirumavalavan
 *  @since  March 13 2019
 */
public class VotingState {
	
	private int demVotes, gopVotes; // total number of the democratic and republican votes
	private String stateName; // name of the state
	
	/** 
	 *  Constructor - sets the values of the field variables based on the values inputted
	 */
	public VotingState(int demVotes, int gopVotes, String name) {
		this.demVotes = demVotes;
		this.gopVotes = gopVotes;
		stateName = name;
	}
	
	/** Getters and setters to access and change the field variables */
	public void setDemVotes(int demVotes) { this.demVotes = demVotes; }
	public void setGopVotes(int gopVotes) { this.gopVotes = gopVotes; }
	public void setStateName(String stateName) { this.stateName = stateName; }
	
	public int getDemVotes() { return demVotes; }
	public int getGopVotes() { return gopVotes; }
	public String getStateName() { return stateName; }
	
	/** toString method - prints out the field variables of the VotingState */
	public String toString() { return "\nState: " + stateName + ", Dem. votes: " + demVotes + ", GOP votes: " + gopVotes; };
}
