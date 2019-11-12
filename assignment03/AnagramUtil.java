package assignment03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AnagramUtil {

	/**
	 * create an inner class for the purpose of reading file && providing methods of
	 * generating random length string for testing purposes
	 *
	 */
	public static class FileReader {
		private String filename;
		private String[] wordList;
		private int listSize;
		private static Random rand;

		public FileReader(String filename) {
			this.rand = new Random();
			this.filename = filename;
			File file = new File(filename);
			ArrayList<String> input = new ArrayList<String>();
			int num = 0;

			try {
				Scanner scan = new Scanner(file);
				while (scan.hasNext()) {
					input.add(scan.next());
					num++;
				}
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
			}
			this.listSize = num;
			this.wordList = input.toArray(new String[listSize]);
		}

		public String[] getWordList() {
			return wordList;
		}

		public static String genString(int length) {
			String charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			String output = "";
			Random rand = new Random();
			for (int i = 0; i < length; i++) {
				output += charPool.charAt(rand.nextInt(52));
			}
			return output;
		}

		public static void shuffleWord(String[] input) {

			ArrayList<String> temp = new ArrayList<>(Arrays.asList(input));

			for (int i = 0; i < input.length; i++) {
				Collections.swap(temp, i, rand.nextInt(input.length));
			}
		}
	}

	/**
	 * This method returns the sorted version of the input string. The sorting must
	 * be accomplished using an insertion sort.
	 * 
	 * @param a string
	 * @return sorted version of input string based on the character's natural order
	 */
	public static String sort(String word) {
		if (word == null) {
			return null;
		}

		char[] wordChar = word.toCharArray();

		// implementing insertion sort:
		for (int i = 1; i < wordChar.length; i++) {
			char val = wordChar[i];

			int j = i;

			while (j > 0 && Character.toLowerCase(wordChar[j - 1]) > Character.toLowerCase(val)) {
				wordChar[j] = wordChar[j - 1];
				j--;
			}
			wordChar[j] = val;
		}
		return String.valueOf(wordChar);
	}

	/**
	 * This generic method sorts the input array using an insertion sort and the
	 * input Comparator object.
	 * 
	 * @param wordList     - a array of wordList of object T
	 * @param myComparator - a comparator object that compare the array element
	 *                     based on custom order
	 * @return the sorted version of wordList
	 */
	public static <T> void insertionSort(T[] wordList, Comparator<? super T> myComparator) {
		if (wordList.length == 0) {
			return;
		}

		for (int i = 1; i < wordList.length; i++) {

			T val = wordList[i];
			int j = i;
			while (j > 0 && myComparator.compare(wordList[j - 1], val) > 0) {
				wordList[j] = wordList[j - 1];
				j--;
			}
			wordList[j] = val;
		}
	}

	/**
	 * This method returns true if the two input strings are anagrams of each other,
	 * otherwise returns false.
	 * @param wordLhs
	 * @param wordRhs
	 */
	public static boolean areAnagrams(String wordLhs, String wordRhs) {
		if (wordLhs == null || wordRhs == null) {
			return false;
		}

		String lhs = sort(wordLhs);
		String rhs = sort(wordRhs);

		return lhs.equalsIgnoreCase(rhs);
	}

	/**
	 * This method returns the largest group of anagrams in the input array of
	 * words, in no particular order. It returns an empty array if there are no
	 * anagrams in the input array.
	 * @param wordList
	 * @return returns the anagrams with the largest quantity
	 */
	public static String[] getLargestAnagramGroup(String[] wordList) {
		String[] output;

		// if the input is null, return an empty array:
		if (wordList.length == 0) {
			return wordList;
		}
//		Arrays.sort(wordList, new Comparator<String>() {
//			@Override
//			public int compare(String o1, String o2) {
//
//				if (o1 == null && o2 == null) {
//
//					return 0;
//				} else if (o1 == null && o2 != null) {
//					return 1;
//				} else if (o1 != null && o2 == null) {
//					return -1;
//				} else {
//					return AnagramUtil.sort(o1).toLowerCase().compareTo(AnagramUtil.sort(o2).toLowerCase());
//				}
//			}
//		});

		insertionSort(wordList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {

				if (o1 == null && o2 == null) {

					return 0;
				} else if (o1 == null && o2 != null) {
					return 1;
				} else if (o1 != null && o2 == null) {
					return -1;
				} else {
					return AnagramUtil.sort(o1).toLowerCase().compareTo(AnagramUtil.sort(o2).toLowerCase());
				}
			}
		});

		List<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();

		int largeCount = 0;

		int i = 0;
		while (i < wordList.length && wordList[i] != null) {
			int count = 0;
			ArrayList<String> temp1 = new ArrayList<String>();
			int j = i;

			while (j < wordList.length && areAnagrams(wordList[i], wordList[j])) {
				temp1.add(wordList[j]);
				count++;
				j++;
			}

			if (count > largeCount) {
				largeCount = count;
			}
			temp.add(temp1);

			i = i + count;
		}

		// check for no anagrams:
		if (largeCount <= 1) {
			output = new String[0];
			return output;
		}

		ArrayList<String> result = new ArrayList<String>();
		for (int k = 0; k < temp.size(); k++) {
			if (temp.get(k).size() == largeCount) {
				result.addAll(temp.get(k));
				break;
			}
		}

		output = result.toArray(new String[largeCount]);
		return output;

	}

	/**
	 * * Behaves the same as the previous method, but reads the list of words from
	 * the input filename. It is assumed that the file contains one word per line.
	 * If the file does not exist or is empty, the method returns an empty array
	 * because there are no anagrams.
	 * @param filename
	 */
	public static String[] getLargestAnagramGroup(String filename) {
		FileReader fr = new FileReader(filename);

		return getLargestAnagramGroup(fr.getWordList());
	}

}
