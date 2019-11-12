package assignment06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class collisionExperiment {
	private static final int ITER_COUNT = 200;
	
	private static ArrayList<String> genListString(int size) throws FileNotFoundException{
		ChainingHashTable table = new ChainingHashTable();
		
		ArrayList<String> list = (ArrayList<String>) table.readFromFile("moderate_word_list.txt");
		Collections.shuffle(list);
		return list;
	}

	  public static void main(String[] args) {
	    // you spin me round baby, right round
	   long startTime = System.nanoTime();
	    while (System.nanoTime() - startTime < 1_000_000_000);

	    try (FileWriter fw = new FileWriter(new File("realHashcollision.tsv"))) { 

	      for (int len = 50; len <= 850; len+= 50) { 
	    	  
	    	 long totalCollision = 0;
	    	 
	        for (int iter = 0; iter < ITER_COUNT; iter++) {
	          // SET UP!
	        	ChainingHashTable myTable = new ChainingHashTable(10000, new GoodHashFunctor());
	        	myTable.addAll(genListString(len).subList(0, len));

	            totalCollision += myTable.getCollisions();
	          
	        }
	        
	        double averageCollision = totalCollision / (double) ITER_COUNT;
	        System.out.println(len + "\t" + averageCollision); // print to console
	        fw.write(len + "\t" + averageCollision + "\n"); // write to file.
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }}
