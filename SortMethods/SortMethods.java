/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Meet Vora
 *	@since	November 29th, 2018
 */
public class SortMethods {
	
	private int[] tempArr;

	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(Integer[] arr) {
		// a is range of indices you want to look at. Have to give highest value index of array
		// a > 0 and not a >= 0 cuz don't want to compare last index with itself
		for(int a = arr.length - 1; a > 0; a--)
			for(int b = 0; b < a; b++)
				if(arr[b].compareTo(arr[b+1]) > 0)
					swap(arr,b,b+1);
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(Integer[] arr, int x, int y) {
		Integer temp = arr[x];
		arr[x] = arr[y];
		arr[y]  = temp;	
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(Integer[] arr) {
		
		for(int a = arr.length; a > 1; a--) {
			Integer max = 0;
			for(int b = 1; b < a; b++) {
				if (arr[b].compareTo(arr[max]) > 0)
					max = b;
			}
			swap(arr,max,a-1);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(Integer [] arr) {
		for(int a = 1; a < arr.length; a++) {
			Integer nextElem = arr[a];
			Integer index = a;
			
			while (index > 0 && nextElem < arr[index-1]) {
				arr[index] = arr[index-1];
				index--;
			}
			arr[index] = nextElem; 
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(Integer [] arr) {
		
		int length = arr.length;
		tempArr = new int[length];
		recursiveSort(arr,0,length-1);		
	}
	
	/**
	 *	Recursive helper method that divides the array into individual element
	 *	components. Keeps calling itself until base case is fulfilled.
	 *	@param arr 		array of Integer objects to sort
	 *	@param from 	staring index of array to sort from
	 *	@param to 		ending index of array to sort to
	 */
	public void recursiveSort(Integer[] arr, int from, int to) {
		
		if(to - from < 2) {	// Base case: if array is 1 or 2 elements long
			if(to > from && arr[to] < arr[from])
				swap(arr,to,from);	// swap indices of arr[to] and arr[from]
		}
		else {	// Recursive case
			int middle = (from + to)/2;
			recursiveSort(arr,from,middle);
			recursiveSort(arr,middle + 1,to);
			merge(arr,from,middle,to);
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
	private void merge(Integer[] arr, int from, int middle, int to) {
		
		int i = from, j = middle + 1, k = from;
		
		// while both arrays have elements left unprocessed
		while( i <= middle && j <= to) {
			if(arr[i] < arr[j]) {
				tempArr[k] = arr[i];
				i++;
			}
			else {
				tempArr[k] = arr[j];
				j++;
			}
			k++;
		}

		// copy the tail of the first half of arr into tempArr
		while (i <= middle) {
			tempArr[k] = arr[i];
			i++; k++;
		}
		// copy the tail of the first half of arr into tempArr
		while (j <= to) {
			tempArr[k] = arr[j];
			j++; k++;
		}
		// copy elements of tempArr back into arr
		for (k = from; k <= to; k++)
			arr[k] = tempArr[k];
	}
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
	
		Integer[] arr = new Integer[10];
		// Fill arr with random numbers
	
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

	
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();


		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

	}
}