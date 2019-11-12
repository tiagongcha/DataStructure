package assignment03;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import assignment03.AnagramUtil.FileReader;

public class getLargestAnagramTiming {
	private static final int ITER_COUNT = 200;

	public static void main(String[] args) {
		// you spin me round baby, right round
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			;

		try (FileWriter fw = new FileWriter(new File("getLargestAnagramy.tsv"))) { // open up a file writer so we can
																					// write
																					// to file.
			FileReader fr = new FileReader("largeWord.txt");
			String[] input = fr.getWordList();
			System.out.println("checking " + input[3]);
			
			for (int i = 100; i <= 1000; i++) { // This is used as the exponent to calculate the size of the set.
				int size = i; 

				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!			
					String[] wordList = new String[size];
					for(int j = 0; j < size; j++) {
						wordList[j] = input[j];
					}
					
					// TIME IT!
					long start = System.nanoTime();
					
					AnagramUtil.getLargestAnagramGroup(wordList);
					long stop = System.nanoTime();
					totalTime += stop - start;
				}
				
				double averageTime = totalTime / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
