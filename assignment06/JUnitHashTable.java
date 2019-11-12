package assignment06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JUnitHashTable {

	private ChainingHashTable table, emptyTable, bigTable;
	private ArrayList<String> wordListSmall, wordListBig;
	
	/****Really Bad Hash Function Class*******/
	private class ReallyBadHashFunctor implements HashFunctor{

		@Override
		public int hash(String item) {
			
			return 0;
		}
		
	}
	
	/****Bad Hash Function Class*****/
	//simply use the length of the string
	private class BadHashFunctor implements HashFunctor{

		@Override
		public int hash(String item) {
			
			return item.length();
		}	
	}
	
	/**Mediocre Hash Function Class***/
	private class MediocreHashFunctor implements HashFunctor{

		@Override
		public int hash(String item) {
			int hashVal = 0;
			
			for(int i = 0; i < item.length(); i++) {
				hashVal += item.charAt(i);
			}
			return hashVal;
		}	
	}
	
	/**Good Hash Function Class***/
	private class GoodHashFunctor implements HashFunctor{

		@Override
		public int hash(String item) {
			int hashVal = 0;
			
			for(int i = 0; i < item.length(); i++) {
				hashVal = 33 * hashVal + item.charAt(i);
			}
			return hashVal;
		}
		
	}
	
	@BeforeEach
	void setUp() throws Exception {
		table = new ChainingHashTable(16, new GoodHashFunctor());
		bigTable = new ChainingHashTable(100000, new GoodHashFunctor());
		
		table.add("hello");
		
		emptyTable = new ChainingHashTable(16, new GoodHashFunctor()); 
		
		wordListSmall = table.readFromFile("sample_word_list.txt");
		wordListBig = table.readFromFile("moderate_word_list.txt");
		System.out.println(wordListBig.size() + " i wonder");
	}

	@Test
	void testAdd() {
		assertTrue(table.add("hoola"));
		assertFalse(table.add("hello"));
		assertEquals(2, table.size());
		assertEquals(0, table.getCollisions());
	}
	
	@Test
	void testAddAll() {
		table.addAll(wordListSmall);
		assertEquals(36, table.size());
		
		//print out this table:
		LinkedList<String>[] myTable = table.getStorage();
		for(int i = 0; i < myTable.length; i++) {
			System.out.println("The " + i + " th cell has the word list: " +"\r\n" );
			if(myTable[i] != null) {
				for(String s: myTable[i]) {
					System.out.println(s);
				}
			}
		}
		
		bigTable.addAll(wordListSmall);
		assertEquals(0, bigTable.getCollisions());
	}
	
	@Test
	void testContains() {
		assertTrue(table.contains("hello"));
		emptyTable.contains("hello");
		
		table.addAll(wordListSmall);
		assertTrue(table.containsAll(wordListSmall));
	}
	
	@Test
	void testPrime() {
		assertEquals(13, table.nextPrime(11));
		assertEquals(37, table.nextPrime(31));
		assertEquals(3, table.nextPrime(2));
	}
	
	@Test
	void testRemove() {
		table.addAll(wordListSmall);
		assertTrue(table.remove("code"));
		assertFalse(table.remove("sdfdsa"));
		assertFalse(emptyTable.remove("code"));
	}
	
	@Test
	void testRemoveAll() {
		bigTable.addAll(wordListBig);
//		assertEquals(0, bigTable.getCollisions());
		assertTrue(bigTable.removeAll(wordListBig));
		assertEquals(0, bigTable.size());
		
	}
	
}
