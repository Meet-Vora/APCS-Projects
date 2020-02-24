/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Meet Vora
 *	@since	December 3rd, 2018
 */
public class City implements Comparable<City> {
	
	// fields
	private String name;		// city name
	private String state;		// state name
	private String designation;	// place designation (ie. "city", village", etc)
	private int population; 	// population of city
	private int element;
	
	// constructor
	public City(String stateName, String cityName, String desigType, int pop) {
		state = stateName;
		name = cityName;
		designation = desigType;
		population = pop; 
		element = 0;	// not sure what to make element equals to or if it needs a parameter
	}
	
	/**	
	 *	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	@Override
	public int compareTo(City other) {
		
		if (this.population != other.population)
			return this.population - other.population;
		if (this.state.compareTo(other.state) != 0)
			return this.state.compareTo(other.state);
		return this.name.compareTo(other.name);
	}

	/**
	 *	Compare names of two cities
	 *	@param other 	the other City to compare
	 *	@return 		this.name - other.name
	 */
	public int compareToCity(City other) { return this.name.compareTo(other.name); }

	/**	
	 *	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof City && compareTo((City)other) == 0)
			return true;
		return false;
	}
	
	/**	Accessor methods */
	
	/**
	 *	Returns state of each City Object
	 *	@return state Name
	 */
	public String getState() { return state; }	
	
	/**
	 *	Returns city of each City Object
	 *	@return city Name
	 */
	public String getCity() { return name; }	


	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}