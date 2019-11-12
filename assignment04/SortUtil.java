package assignment04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class SortUtil {
	private static int mergesortThreshold = 2;
	/**
	 * Driver for mergesort
	 * 
	 * @param list
	 * @param comp
	 */
	
	/**This method performs a mergesort on the generic ArrayList given as input.
	 * 
	 * @param list
	 * @param comp
	 */
	public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> comp) {
		if(list.size() <= 1)
			return;
		
		
		 ArrayList<T> temp = new ArrayList<T>();
		 for(int i = 0; i < list.size(); i++) {
			 temp.add(null);
		 }
		 
		 mergesort(list, temp, comp ,0, list.size()-1);
	}
	
	private static<T> void mergesort(ArrayList<T> list, ArrayList<T> temp, Comparator<? super T> comp, int left, int right) {
		if(left >= right)
			return;
		
		int mid = (left + right)/2;
		mergesort(list, temp, comp ,left, mid);
		mergesort(list, temp, comp ,mid + 1, right);
		merge(list, temp, comp ,left, mid + 1, right);	
	}
	
	private static<T> void merge(ArrayList<T> list, ArrayList<T> temp, Comparator<? super T> comp,int left, int rightstart, int right) {
		int leftEnd = rightstart;
		int tempIndex = left;
		int size = right - left +1;
		
		int i = left;
		int j = rightstart;
		
		while(i < rightstart && j <= right) {
			if(comp.compare(list.get(i), list.get(j))<=0) {
				temp.set(tempIndex, list.get(i));
				tempIndex++;
				i++;
			}else {
				temp.set(tempIndex, list.get(j));
				tempIndex++;
				j++;
			}
		}
		
		while(i < rightstart) {
			temp.set(tempIndex, list.get(i));
			tempIndex++;
			i++;
		}
		
		while(j <= right) {
			temp.set(tempIndex, list.get(j));
			tempIndex++;
			j++;
		}
		
		for(int p = 0; p < size; p++) {
			list.set(left, temp.get(left));
			left++;
		}

	}
		
	/**This method performs a quicksort on the generic ArrayList given as input.
	 * 
	 * @param list
	 * @param comp
	 */
	//helper function for three different pivot selection
	public static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comp) {
		if(list.size() <= 1)
			return;
		
		quicksort(list, comp, 0, list.size() - 1);
	}
	
	private static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comp, int left, int right) {
		
		//base case:
		if(left >= right)
			return;
		int pivotIndex = partition(list, comp,left, right);
		quicksort(list, comp,left, pivotIndex -1);
		quicksort(list, comp, pivotIndex, right);
	}
	
	private static <T> int partition(ArrayList<T> list, Comparator<? super T> comp, int left, int right) {
		int L = left;
		int R = right;
		
		T pivot = list.get(left);
		
		while(L <= R) {
			while(comp.compare(list.get(L), pivot) < 0) {
				L++;
			}
			
			while(comp.compare(list.get(R), pivot) > 0) {
				R--;
			}
			
			if(L <= R) {
				swap(list, L, R);
				L++;
				R--;
			}
			
		}
//		swap(list, left, R);
		
		//return partition point
		return L;
	}
	
	
	
	/**
	 * Swaps the elements
	 * 
	 * @param list
	 * @param index1
	 * @param index2
	 */
	private static <T> void swap(ArrayList<T> list, int index1, int index2) {
		T temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**This method generates and returns an ArrayList of
	 *  integers 1 to size in ascending order
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateBestCase(int size){
		ArrayList<Integer> output = new ArrayList<Integer>();
		
		for(int i = 1; i <= size ; i++) {
			output.add(i);
		}
		return output;
	}
	
	/**This method generates and returns an ArrayList of integers 1 to 
	 * size in permuted order (i,e., randomly ordered).
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateAverageCase(int size){
		Random rand = new Random();
		ArrayList<Integer> output = generateBestCase(size);
		
		for(int i = 0; i < size; i++) {
			Collections.swap(output, i, rand.nextInt(size));
		}
		return output;
	}
	
	/**This method generates and returns an ArrayList of integers 1 to 
	 * size in descending order.
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateWorstCase(int size){
		ArrayList<Integer> output = new ArrayList<Integer>();
		
		for(int i = size; i >=1; i--) {
			output.add(i);
		}
		
		return output;
	}
	
	

	
	
}
