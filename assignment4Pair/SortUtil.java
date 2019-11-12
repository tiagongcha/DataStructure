package assignment4Pair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import lab06.BetterRandomNumberGenerator;

public class SortUtil {
	private static int mergeThreshold = 70;
	private static int quickThreshold = 0;
	private static int pivotCaseNumber = 2;

	/**
	 * This driver method performs a mergesort on the generic ArrayList given as
	 * input
	 * 
	 * @param list - generic arraylist to be sorted
	 * @param comp - a comparater object used to compare elements
	 */
	public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> comp) {
		// single/empty list is already sorted
		if (list.size() <= 1)
			return;

		ArrayList<T> temp = new ArrayList<T>();
		for (int i = 0; i < list.size(); i++) {
			temp.add(null);
		}
		// call recursive mergesort method
		mergesort(list, temp, comp, 0, list.size() - 1);
	}

	/**
	 * recursive merge sort method
	 * 
	 * @param list  - list to be sorted
	 * @param temp  - temp list to hold the sorted element
	 * @param comp  - custom order comparator object
	 * @param left  - the start index of the list
	 * @param right - the last index of the list
	 */
	private static <T> void mergesort(ArrayList<T> list, ArrayList<T> temp, Comparator<? super T> comp, int left,
			int right) {

		// base case of recursive method:
		if (right - left <= mergeThreshold) {
			insertionSort(list, comp, left, right);
			return;
		}

		int mid = (left + right) / 2;
		mergesort(list, temp, comp, left, mid);
		mergesort(list, temp, comp, mid + 1, right);
		merge(list, temp, comp, left, mid + 1, right);
	}

	/**
	 * merge two sorted sublist
	 * 
	 * @param list       - arraylist to be sorted
	 * @param temp       - temp list to hold the sorted element
	 * @param comp       - custom order comparator object
	 * @param left       - the start index of the list && the start index of left
	 *                   subarraylist
	 * @param right      - the last index of the list
	 * @param rightstart - the start index of right subarraylist
	 */
	private static <T> void merge(ArrayList<T> list, ArrayList<T> temp, Comparator<? super T> comp, int left,
			int rightstart, int right) {

		int leftEnd = rightstart;
		// the moving pointer of temp arraylist
		int tempIndex = left;
		int size = right - left + 1;

		// the moving pointer of left subarraylist
		int i = left;
		// the moving pointer of right subarraylist
		int j = rightstart;

		while (i < rightstart && j <= right) {
			if (comp.compare(list.get(i), list.get(j)) <= 0) {
				temp.set(tempIndex, list.get(i));
				tempIndex++;
				i++;
			} else {
				temp.set(tempIndex, list.get(j));
				tempIndex++;
				j++;
			}
		}

		// copy the rest of elements left from right subarraylist, if any
		while (i < rightstart) {
			temp.set(tempIndex, list.get(i));
			tempIndex++;
			i++;
		}

		// copy the rest of elements left from left subarraylist, if any
		while (j <= right) {
			temp.set(tempIndex, list.get(j));
			tempIndex++;
			j++;
		}

		// copy sorted elements from temp back to the original list
		for (int p = 0; p < size; p++) {
			list.set(left, temp.get(left));
			left++;
		}

	}

	/**
	 * a helper method using insertion sort for when quick/merge sort hits threshold
	 * switching to insertion sort to increase the run-time performance
	 * 
	 * @param list  - arraylist to be sorted
	 * @param comp  - custom order comparator object
	 * @param left  - the start index of the list
	 * @param right - the last index of the list
	 */
	private static <T> void insertionSort(ArrayList<T> list, Comparator<? super T> comp, int start, int end) {

		for (int i = start + 1; i <= end; i++) {
			T val = list.get(i);
			int j = i;

			while (j > start && comp.compare(list.get(j - 1), val) > 0) {
				list.set(j, list.get(j - 1));
				j--;
			}
			list.set(j, val);
		}
	}

	/**
	 * This driver method performs a quicksort on the generic ArrayList given as input.
	 * 
	 * @param list  - arraylist to be sorted
	 * @param comp  - custom order comparator object
	 */
	public static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comp) {
		//single/empty list is already sorted
		if (list.size() <= 1)
			return;
		//call the recursive quicksort method
		quicksort(list, comp, 0, list.size() - 1);
	}

	/**recursive quicksort method
	 * 
	 * @param list  - arraylist to be sorted
	 * @param comp  - custom order comparator object
	 * @param left  - the start index of the list
	 * @param right - the last index of the list
	 */
	private static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comp, int left, int right) {

		// base case:
		if (right - left <= quickThreshold) {
			insertionSort(list, comp, left, right);
			return;
		}

		//split list based on the selected pivot index, the method used here needs to include pivot itself 
		//in the sorting method
		int pivotIndex = partition(list, comp, left, right);
		quicksort(list, comp, left, pivotIndex - 1);
		quicksort(list, comp, pivotIndex, right);
	}
	
	/**partition the list and returns the pivot index after sorting, so that elements to the left of pivot index are all smaller than
	 * the pivot, and elements to the right of pivot index are all larger than the pivot
	 * @param list
	 * @param comp
	 * @param left
	 * @param right
	 * @return the pivot index -> pivot in its correct position 
	 */
	private static <T> int partition(ArrayList<T> list, Comparator<? super T> comp, int left, int right) {
		int L = left;
		int R = right;

		T pivot = list.get(pivotSelection(list, left, right, pivotCaseNumber, comp));

		while (L <= R) {
			while (comp.compare(list.get(L), pivot) < 0) {
				L++;
			}

			while (comp.compare(list.get(R), pivot) > 0) {
				R--;
			}

			if (L <= R) {
				swap(list, L, R);
				L++;
				R--;
			}

		}
		return L;
	}

	/**
	 * a helper swap method
	 * 
	 * @param list - arraylist to be sorted 
	 * @param index1
	 * @param index2
	 */
	private static <T> void swap(ArrayList<T> list, int index1, int index2) {
		T temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}

	/**a helper method for three different pivot strategy selection
	 *  
	 * @param list
	 * @param start
	 * @param end
	 * @param casenumber - strategy case number
	 * @param comp
	 * @return pivotIndex - returns pivot index
	 */
	private static <T> int pivotSelection(ArrayList<T> list, int start, int end, int casenumber,
			Comparator<? super T> comp) {
		int pivotIndex = -1;

		switch (casenumber) {

		// random number between 0 - size-1;
		case 1:
			pivotIndex = (int) (Math.random() * (end - start + 1)) + start;
			break;

		// middle position of array
		case 2:
			pivotIndex = (end - start) / 2 + start;
			break;

		// rule of median-of-three
		case 3:
			int mid = (end - start + 1) / 2 + start;
			ArrayList<T> tempList = new ArrayList<T>();
			tempList.add(list.get(start));
			tempList.add(list.get(mid));
			tempList.add(list.get(end));

			SortUtil.insertionSort(tempList, comp, 0, 2);
			if (comp.compare(list.get(start), tempList.get(1)) == 0)
				pivotIndex = start;
			if (comp.compare(list.get(mid), tempList.get(1)) == 0)
				pivotIndex = mid;
			if (comp.compare(list.get(end), tempList.get(1)) == 0)
				pivotIndex = end;
			break;

		// use the left most element --> gives stackoverflow:
//		case 4:
//			pivotIndex = end;
//			break;
		}

		return pivotIndex;
	}

	/**This method generates and returns an ArrayList of integers 1 to size in ascending order.
	 * 
	 * @param size 
	 * @return arraylist in ascending order (all sorted)
	 */
	public static ArrayList<Integer> generateBestCase(int size) {
		ArrayList<Integer> bestCase = new ArrayList<Integer>(size);
		
		for (int i = 0; i < size; ++i)
			bestCase.add(i + 1);
		
		return bestCase;
	}

	/**This method generates and returns an ArrayList of integers 1 to size in permuted order
	 *  (i,e., randomly ordered)
	 * @param size
	 * @return arraylist in randomly permuted order
	 */
	public static ArrayList<Integer> generateAverageCase(int size) {
		Random rand = new Random();
		ArrayList<Integer> output = generateBestCase(size);

		for (int i = 0; i < size; i++) {
			Collections.swap(output, i, rand.nextInt(size));
		}
		return output;
	}

	/**This method generates and returns an ArrayList of integers 1 to 
	 * size in descending order.
	 * @param size
	 * @return arraylist in descending order
	 */
	public static ArrayList<Integer> generateWorstCase(int size) {
		ArrayList<Integer> worstCase = new ArrayList<Integer>(size);
		
		for (int i = size - 1; i > -1; --i)
			worstCase.add(i + 1);
		
		return worstCase;
	}
	
	

}
