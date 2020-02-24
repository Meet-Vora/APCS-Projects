import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *	Population - Sorts and prints US Population data based on the choice of the
 *				 user. Can organize the data by population, ascending or 
 *				 descending, of all cities, city name, alphabetically or
 *				 reverse-alphabetically, or population, ascending or descending,
 *				 of a chosen state or particular city name. Uses various sort
 *				 methods such as insertion, selection, and merge sorts to 
 *				 sort all the data.
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Meet Vora
 *	@since	December 3rd, 2018
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	public Population() {
		cities = new ArrayList<City>();
	}
	
	/** Main Method */
	public static void main(String[] args) {
		Population pop = new Population();
		pop.run();
	}
	
	/**
	 *	The main utility of the class. Gets user input, and calls different 
	 *	methods based on it that indicates the function they'd like to be 
	 *	performed. Ends the program if the user chooses to quit.
	 */
	public void run() {
		int input = 0;
		boolean quit = false;
		
		// prints the introduction to the program
		printIntroduction();
		// keep the program running until the user chooses to quit
		do {
			System.out.println();
			// prints option menu that guides the user to enter a number to
			// choose a function to perform
			printMenu();

			// Keeps prompting user for input until valid command is entered
			do {
				input = Prompt.getInt("Enter Selection");
			} while(input != 1 && input != 2 && input != 3 && 
					input != 4 && input != 5 && input != 6 && 
					input != 9);
			
			// opens the data file and stores data in ArrayList
			readFile();	

			// based on user input, call different methods		
			switch(input) {
				case 1: leastPopCity();
					break;
				case 2: mostPopCity();
					break;
				case 3: cityNameSortA();
					break;
				case 4: cityNameSortZ();
					break;
				case 5: sameState();
					break;
				case 6: sameCity();
					break;
				case 9: quit = true;
					break;	
			}
			// clear the data stored in the ArrayList
			cities.clear();
		} while(!quit);	

		System.out.println("\nThanks for using Population!");
	}
	
	/**
	 *	Sorts the listed data by the first 50 cities with the lowest populations.
	 *	Uses a selection sort already implemented in the SortMethods class to 
	 *	sort the population data. 
	 */
	public void leastPopCity() {
		long startMillisec = 0;
		long endMillisec = 0;

		System.out.println("\nFifty least populous cities");
		// start measuring time taken to sort data
		startMillisec = System.currentTimeMillis();
		// sort the data in alphabetical order of city name using merge sort
		selectionSort(cities);
		// end measuring time taken to sort data
		endMillisec = System.currentTimeMillis();

		System.out.printf("\n     %-22s %-22s %-12s %12s\n", "State", "City", 
				"Type", "Population");
		// Prints first 50 cities with highest population in ascending order
		for(int a = 0; a < 50; a++) {
			System.out.printf(" %2d: ", a + 1);
			System.out.println(cities.get(a));
		}
		// Print the time elapsed in milliseconds to sort and print the data
		System.out.println("\nElapsed time " + (endMillisec - startMillisec) + 
					" milliseconds");
	}

	/**
	 *	Sorts the listed data by the first 50 cities with the highest populations.
	 *	Uses a merge sort already implemented in the SortMethods class to sort 
	 *	the population data. 
	 */
	public void mostPopCity() {
		long startMillisec = 0;
		long endMillisec = 0;
		int counter = 0;

		System.out.println("\nFifty most populous cities");
		// start measuring time taken to sort data
		startMillisec = System.currentTimeMillis();
		// sort the data in alphabetical order of city name using merge sort
		mergeSortPop(cities);
		// end measuring time taken to sort data
		endMillisec = System.currentTimeMillis();
		
		System.out.printf("\n     %-22s %-22s %-12s %12s\n", "State", "City", 
				"Type", "Population");

		// Print first 50 cities with highest population in descending order
		for(int a = cities.size() - 1; a > cities.size() - 51; a--) {
			System.out.printf(" %2d: ", counter + 1);
			System.out.println(cities.get(a));
			counter++;
		}

		// Print the time elapsed, in milliseconds, to sort and print the data
		System.out.println("\nElapsed time " + (endMillisec - startMillisec) + 
						" milliseconds");
	}

	/**
	 *	Sorts the listed data in alphabetical order of city name. Prints the 
	 *	first 50 cities (starting with 'A'). Uses an insertion sort already 
	 *	implemented in the SortMethods class to sort the city name data. 
	 */
	public void cityNameSortA() {
		long startMillisec = 0;
		long endMillisec = 0;

		System.out.println("\nFifty cities sorted by name");
		// start measuring time taken to sort data
		startMillisec = System.currentTimeMillis();
		// sort the data in alphabetical order of city name using insertion sort
		insertionSortCity(cities);
		// end measuring time taken to sort data
		endMillisec = System.currentTimeMillis();
		
		System.out.printf("\n     %-22s %-22s %-12s %12s\n", "State", "City", 
				"Type", "Population");
		// Print first 50 city names in alphabetical order
		for(int a = 0; a < 50; a++) {
			System.out.printf(" %2d: ", a + 1);
			System.out.println(cities.get(a));
		}

		// Print the time elapsed, in milliseconds, to sort and print the data
		System.out.println("\nElapsed time " + (endMillisec - startMillisec) + 
						" milliseconds");
	}

	/**
	 *	Sorts the listed data in alphabetical order of city name. Prints the 
	 *	last 50 cities (starting with 'Z'). Uses a merge sort already 
	 *	implemented in the SortMethods class to sort the city name data. 
	 */
	public void cityNameSortZ() {
		long startMillisec = 0;
		long endMillisec = 0;
		int counter = 0;

		System.out.println("\nFifty cities sorted by name descending");
		// start measuring time taken to sort data
		startMillisec = System.currentTimeMillis();
		// sort the data in alphabetical order of city name using merge sort
		mergeSortCity(cities);
		// end measuring time taken to sort data
		endMillisec = System.currentTimeMillis();

		System.out.printf("     %-22s %-22s %-12s %12s\n", "State", "City", 
				"Type", "Population");
		// Print first 50 city names in reverse-alphabetical order
		for(int a = cities.size() - 1; a > cities.size() - 51; a--) {
			System.out.printf(" %2d: ", counter + 1); 	// maybe need to printf the numbers
			System.out.println(cities.get(a));
			counter++;
		}
		// Print the time elapsed, in milliseconds, to sort and print the data
		System.out.println("\nElapsed time " + (endMillisec - startMillisec) + 
					" milliseconds");// fix. Doesn't sort properly
	}

	/**
	 *	Sorts the population data of all cities in a particular state in 
	 *	descending order, chosen by the user. User is prompted for input until 
	 *	a valid state name is entered. Uses a selection sort to sort the 
	 *	population data. If there are less than 50 designations present in the 
	 *	chosen state, will print out the data for all avaliable designations. 
	 *	If more than 50, however, will print the first 50 cities with the 
	 *	highest population in the state specified.
	 */
	public void sameState() {	
		String input = "";
		int counter = 0;
		// temporary list used to store states with matching names	
		List<City> stateList = new ArrayList<City>();

		System.out.println();
		// Get user input, and check to see if any state names match input
		// User is prompted for input until matching state name found
		do {
			input = Prompt.getString("Enter state name (ie. Alabama)");
			for (int a = 0; a < cities.size(); a++) {
				if(cities.get(a).getState().equals(input))
					stateList.add(cities.get(a));
			}
		} while(stateList.size() == 0);


		System.out.println("\nFifty most populous cities in " + input);
		// Sorts all cities with same name by population
		selectionSort(stateList);

		System.out.printf("     %-22s %-22s %-12s %12s\n", "State", "City", 
				"Type", "Population");
		
		// if less than 50 designations in the state, print all of them
		if(stateList.size() < 50) {
			for(int a = stateList.size() - 1; a >= 0; a--) {
				System.out.printf(" %2d: ", counter + 1);
				System.out.println(stateList.get(a));
				counter++;
			}
		}
		// else, print first 50
		else {
			// Prints the cities in the state provided in descending order of population
			for(int a = stateList.size() - 1; a > stateList.size() - 51; a--) {
				System.out.printf("%2d: ", counter + 1);
				System.out.println(stateList.get(a));
				counter++;
			}
		}
	}

	/**
	 *	Sorts the population data of all cities with the same name in descending
	 *	order, chosen by the user. User is prompted for input until a valid city
	 *	name is entered. Uses a selection sort to sort the population data.
	 */
	public void sameCity() {
		String input = "";
		int counter = 0;
		// temporary list used to store cities with matching names	
		List<City> cityList = new ArrayList<City>();

		System.out.println();
		// Get user input, and check to see if any city names match input
		// User is prompted for input until matching city name found
		do {
			input = Prompt.getString("Enter city name");
			for (int a = 0; a < cities.size(); a++) {
				if(cities.get(a).getCity().equals(input))
					cityList.add(cities.get(a));
			}
		} while(cityList.size() == 0);


		System.out.println("\nCity " + input + " by population");
		// Sorts all cities with same name by population
		selectionSort(cityList);

		System.out.printf("     %-22s %-22s %-12s %12s\n", "State", "City", 
				"Type", "Population");
	
		// Prints the cities in descending order of population
		for(int a = cityList.size() - 1; a >= 0; a--) {
			System.out.printf(" %2d: ", counter + 1);
			System.out.println(cityList.get(a));
			counter++;
		}
	}

	/**
	 *	Opens the US Population data file and stores the state name, city name, 
	 *	designation, and population of each city into an ArrayList of Cities.
	 */
	public void readFile() {
		// Opens file to read
		Scanner read = FileUtils.openToRead(DATA_FILE);
		read.useDelimiter("[\t\n]");

		// adds data into ArrayList of Cities until no more data left in file
		while(read.hasNext())
			cities.add(new City(read.next(),read.next(),read.next(),read.nextInt()));

		// close the file after reading
		read.close();
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}

	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> arr, int x, int y) {
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);	
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(List<City> arr) {
		
		for(int a = arr.size(); a > 1; a--) {
			Integer max = 0;
			for(int b = 1; b < a; b++) {
				if (arr.get(b).compareTo(arr.get(max)) > 0)
					max = b;
			}
			swap(arr,max,a-1);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSortCity(List<City> arr) {
		for(int a = 1; a < arr.size(); a++) {
			City nextElem = arr.get(a);
			int index = a;
			
			while (index > 0 && nextElem.compareToCity(arr.get(index-1)) < 0) {
				arr.set(index,arr.get(index-1));
				index--;
			}
			arr.set(index, nextElem);
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSortPop(List<City> arr) {
		
		int length = arr.size();
		recursiveSortPop(arr,0,length-1);		
	}
	
	/**
	 *	Recursive helper method that divides the array into individual element
	 *	components. Keeps calling itself until base case is fulfilled.
	 *	@param arr 		array of Integer objects to sort
	 *	@param from 	staring index of array to sort from
	 *	@param to 		ending index of array to sort to
	 */
	public void recursiveSortPop(List<City> arr, int from, int to) {
		
		if(to - from < 2) {	// Base case: if array is 1 or 2 elements long
			if(to > from && arr.get(to).compareTo(arr.get(from)) < 0)
				swap(arr,to,from);	// swap indices of arr[to] and arr[from]
		}
		else {	// Recursive case
			int middle = (from + to)/2;
			recursiveSortPop(arr,from,middle);
			recursiveSortPop(arr,middle + 1,to);
			mergePop(arr,from,middle,to);
		}
	}
	
	/**
	 *	Merges arr[from] to arr[middle] and arr[middle + 1] to arr[to] into one
	 *	sorted array ranging from 'from' to 'to'.
	 *	@param arr 		array of Integer objects to sort
	 *	@param from 	staring index of array to sort from
	 *	@param middle 	middle index used to divide array into two
	 *	@param to 		ending index of array to sort to
	 */
	private void mergePop(List<City> arr, int from, int middle, int to) {
		City[] tempArr = new City[arr.size()];

		int i = from, j = middle + 1, k = from;
		// for(int a = 0; a < arr.size(); a++)
		// 	tempArr.add(arr.get(a));
		
		// while both arrays have elements left unprocessed
		while( i <= middle && j <= to) {
			if(arr.get(i).compareTo(arr.get(j)) < 0) {
				tempArr[k] = arr.get(i) ;
				i++;
			}
			else {
				tempArr[k] = arr.get(j);
				j++;
			}
			k++;
		}

		// copy the tail of the first half of arr into tempArr
		while (i <= middle) {
			tempArr[k] = arr.get(i);
			i++; k++;
		}
		// copy the tail of the first half of arr into tempArr
		while (j <= to) {
			tempArr[k] = arr.get(j);
			j++; k++;
		}
		// copy elements of tempArr back into arr
		for (k = from; k <= to; k++)
			arr.set(k, tempArr[k]);
	}

	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSortCity(List<City> arr) {
		
		int length = arr.size();
		recursiveSortCity(arr,0,length-1);		
	}

	/**
	 *	Recursive helper method that divides the array into individual element
	 *	components. Keeps calling itself until base case is fulfilled.
	 *	@param arr 		array of Integer objects to sort
	 *	@param from 	staring index of array to sort from
	 *	@param to 		ending index of array to sort to
	 */
	public void recursiveSortCity(List<City> arr, int from, int to) {
		
		if(to - from < 2) {	// Base case: if array is 1 or 2 elements long
			if(to > from && arr.get(to).compareToCity(arr.get(from)) < 0)
				swap(arr,to,from);	// swap indices of arr[to] and arr[from]
		}
		else {	// Recursive case
			int middle = (from + to)/2;
			recursiveSortCity(arr,from,middle);
			recursiveSortCity(arr,middle + 1,to);
			mergeCity(arr,from,middle,to);
		}
	}
	
	/**
	 *	Merges arr[from] to arr[middle] and arr[middle + 1] to arr[to] into one
	 *	sorted array ranging from 'from' to 'to'. Sorts city names in alphabetical
	 *	order.
	 *	@param arr 		array of Integer objects to sort
	 *	@param from 	staring index of array to sort from
	 *	@param middle 	middle index used to divide array into two
	 *	@param to 		ending index of array to sort to
	 */
	private void mergeCity(List<City> arr, int from, int middle, int to) {
		City[] tempArr = new City[arr.size()];

		int i = from, j = middle + 1, k = from;
		// for(int a = 0; a < arr.size(); a++)
		// 	tempArr.add(arr.get(a));
		
		// while both arrays have elements left unprocessed
		while( i <= middle && j <= to) {
			if(arr.get(i).compareToCity(arr.get(j)) < 0) {
				tempArr[k] = arr.get(i) ;
				i++;
			}
			else {
				tempArr[k] = arr.get(j);
				j++;
			}
			k++;
		}

		// copy the tail of the first half of arr into tempArr
		while (i <= middle) {
			tempArr[k] = arr.get(i);
			i++; k++;
		}
		// copy the tail of the first half of arr into tempArr
		while (j <= to) {
			tempArr[k] = arr.get(j);
			j++; k++;
		}
		// copy elements of tempArr back into arr
		for (k = from; k <= to; k++)
			arr.set(k, tempArr[k]);
	}
	
}