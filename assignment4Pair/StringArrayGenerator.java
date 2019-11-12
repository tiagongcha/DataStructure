package assignment4Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StringArrayGenerator {
	/**
	 * This method is used to get word list from file. if file not exist or file is
	 * empty. empty string array will be return. other wise string array of words
	 * will be return
	 * 
	 * @param filename : file to be inputed
	 * @return string array of words inside file
	 */
	public static String[] getStringArrayFromFile(String filename) {
		String[] empty = new String[0];
		ArrayList<String> wordList = new ArrayList<String>();
		Scanner fileScanner;
		String currentFolder = System.getProperty("user.dir");
		System.out.println(currentFolder);
		String fileLocation = currentFolder + "/" + filename;
		try {
			fileScanner = new Scanner(new FileInputStream(fileLocation));

			while (fileScanner.hasNext()) {
				wordList.add(fileScanner.next());
			}

		} catch (FileNotFoundException e) {
			return empty;
		}

		if (wordList.size() == 0)
			return empty;

		String[] wordArray = new String[wordList.size()];
		for (int i = 0; i < wordList.size(); ++i)
			wordArray[i] = wordList.get(i);
		return wordArray;
	}

	public static String[] randomStringArray(int size) {
		String[] wordArray = new String[size];
		String[] chars = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
				"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

		for (int i = 0; i < size; ++i) {
			String sum = "";
			// word length 5-15
			int wordlength = ((int) (Math.random() * 11)) + 5;
			for (int j = 0; j < wordlength; ++j) {
				int temp = (int) (Math.random() * 52);
				sum = sum + chars[temp];
			}
			wordArray[i] = sum;
		}
		return wordArray;
	}

	public static String[] fixSizeRandomStringArray(int size, int wordLength) {
		String[] wordArray = new String[size];
		// word length 3-20
		String[] chars = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
				"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

		for (int i = 0; i < size; ++i) {
			String sum = "";

			for (int j = 0; j < wordLength; ++j) {
				int temp = (int) (Math.random() * 52);
				sum = sum + chars[temp];
			}
			wordArray[i] = sum;
		}
		return wordArray;
	}

}
