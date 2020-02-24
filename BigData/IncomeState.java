/**
 *  IncomeState.java
 * 
 *  Wrapper class for the states array from Income.java.
 *  Has variables to store the stateName and the mean and median household, 
 *  non-household, and total incomes for the state.
 * 
 *  @author Dinesh Thirumavalavan
 *  @since  March 15 2019
 */
public class IncomeState {
	
	// mean and median household, non-household, and total income
	private int meanHouseholdIncome, meanNonHouseholdIncome, meanTotalIncome;
	private int medianHouseholdIncome, medianNonHouseholdIncome, medianTotalIncome;
	private String stateName; // name of the state
	
	/** 
	 *  Constructor - sets the values of the field variables based on the values inputted
	 */
	public IncomeState(int meanhi, int meannhi, int meanti, int medianhi, int mediannhi, int medianti, String stateName) {
		meanHouseholdIncome = meanhi;
		meanNonHouseholdIncome = meannhi;
		meanTotalIncome = meanti;
		medianHouseholdIncome = medianhi;
		medianNonHouseholdIncome = mediannhi;
		medianTotalIncome = medianti;
		this.stateName = stateName;
	}
	
	/** Getters and setters to access and change the field variables */
	public void setMHI(int meanhi) { meanHouseholdIncome = meanhi; }
	public void setMNHI(int meannhi) { meanNonHouseholdIncome = meannhi; }
	public void setMTI(int meanti) { meanTotalIncome = meanti; }
	
	public void setMEHI(int medianti) { medianTotalIncome = medianti; }
	public void setMENHI(int medianti) { medianTotalIncome = medianti; }
	public void setMETI(int medianti) { medianTotalIncome = medianti; }
	
	public void setName(String stateName) { this.stateName = stateName; }
	
	public int getMHI() { return meanHouseholdIncome; }
	public int getMNHI() { return meanNonHouseholdIncome; }
	public int getMTI() { return meanTotalIncome; }
	
	public int getMEHI() { return medianHouseholdIncome; }
	public int getMENHI() { return medianNonHouseholdIncome; }
	public int getMETI() { return medianTotalIncome; }
	
	public String getName() { return stateName; }
	
	/** toString method - prints out the field variables of the IncomeState */
	public String toString() { return "\nState: " + stateName + ", income: " + medianHouseholdIncome; }
}
