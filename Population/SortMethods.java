import java.util.List;
import java.util.ArrayList;

/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Meet Vora
 *	@since	November 29th, 2018
 */
public class SortMethods {
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(List<City> arr) {
		// a is range of indices you want to look at. Have to give highest value index of array
		// a > 0 and not a >= 0 cuz don't want to compare last index with itself
		for(int a = arr.size() - 1; a > 0; a--)
			for(int b = 0; b < a; b++)
				if(arr.get(b).compareTo(arr.get(b+1)) > 0)
					swap(arr,b,b+1);
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
	 *	sorted array ranging from 'from' to 'to'.
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

	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */

	/*
	public void printArray(List<City> arr) {
		if (arr.size() == 0) System.out.print("(");
		else System.out.printf("( %4d", arr.get(0));
		for (int a = 1; a < arr.size(); a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr.get(a));
			else System.out.printf(", %4d", arr.get(a));
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
	
		List<City> arr = new ArrayList<City>();
		// Fill arr with random numbers
	
		for (int a = 0; a < 10; a++)
			arr.add(new City((int)(Math.random() * 100) + 1));
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
		for (int a = 0; a < 10; a++)
			arr.add(new City((int)(Math.random() * 100) + 1));
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

	
		for (int a = 0; a < 10; a++)
			arr.add(new City((int)(Math.random() * 100) + 1));
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();


		for (int a = 0; a < 10; a++)
			arr.add(new City((int)(Math.random() * 100) + 1));
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
	}
		*/
}