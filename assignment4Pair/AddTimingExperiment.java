package assignment4Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class AddTimingExperiment {
	private static final int ITER_COUNT = 100;
	

	  public static void main(String[] args) {
	    // you spin me round baby, right round
	   long startTime = System.nanoTime();
	    while (System.nanoTime() - startTime < 1_000_000_000)
	      ;

	    try (FileWriter fw = new FileWriter(new File("quicksortrand.tsv"))) { // open up a file writer so we can write
	                                                                                // to file.

	      for (int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
	        int size = (int) Math.pow(2, exp); // or ..

	        // Do the experiment multiple times, and average out the results
	        long totalTime = 0;

	        ArrayList<Integer> set = SortUtil.generateAverageCase(size);
	        ArrayList<Integer> temp;
	        
	        for (int iter = 0; iter < ITER_COUNT; iter++) {
	          // SET UP!
	        	temp = set;
	          // TIME IT!
	          long start = System.nanoTime();
	          SortUtil.mergesort(temp, new Comparator<Integer>() {

	      		@Override
	      		public int compare(Integer o1, Integer o2) {
	      			// TODO Auto-generated method stub
	      			return o1.compareTo(o2);
	      		}
	      		
	      	});
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
//	    Charter charter = new Charter();
//	    charter.createChart("contains_experiment.tsv", "binary_contains.png");
	  }
}
