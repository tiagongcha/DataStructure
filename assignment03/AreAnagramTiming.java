package assignment03;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import assignment03.AnagramUtil.FileReader;

public class AreAnagramTiming {
	private static final int ITER_COUNT = 500;

	public static void main(String[] args) {
		// you spin me round baby, right round
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			;

		try (FileWriter fw = new FileWriter(new File("areAnagrams.tsv"))) { // open up a file writer so we can
																					// write
																					// to file.
			Random random = new Random();
			for (int i = 1; i <= 500; i++) { // This is used as the exponent to calculate the size of the set.
				int size = i; // or ..

				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
					String o1 = FileReader.genString(size);
					String o2 = FileReader.genString(size);

					// TIME IT!
					long start = System.nanoTime();
					AnagramUtil.areAnagrams(o1, o2);
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
