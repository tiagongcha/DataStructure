package lab05;

import static org.junit.Assert.*;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import org.junit.Before;
import org.junit.Test;


public class IteratorTester {
	
	private BinarySearchSet<Integer> smallSetOfEvens;
	private BinarySearchSet<Integer> largeSet;
	
	private Iterator<Integer> smallIterator;

	@Before
	public void setup() {
		smallSetOfEvens = new BinarySearchSet<>();
		
		for(int half = 1; half <= 10; half++) {
			smallSetOfEvens.add(half*2); 
			
		}
		smallIterator = smallSetOfEvens.iterator();
		
		largeSet = new BinarySearchSet<>();
		
		//Fills set with numbers 1 - 100 using Java 8 Streams!
		IntStream.rangeClosed(1, 100).forEach(largeSet::add);
	}
	
	@Test
	public void iterateOverEvenSet() {
		int expected = 2;
		while(expected <= 20) {
			assertEquals(expected, (int)smallIterator.next());
			expected+=2;
		}
	}
	
	@Test(expected = NoSuchElementException.class)
	public void expectedNoSuchElementThrown() {
		for(int count = 0; count < 11; count++) {
			smallIterator.next();
		}
	}
	
	@Test
	public void hasNextReturnsTrue() {
		assertTrue(smallIterator.hasNext());
	}
	
	@Test
	public void hasNextReturnsFalseAtEnd() {
		for(int count = 0; count < 10; count++) {
			smallIterator.next();
		}
		assertFalse(smallIterator.hasNext());
	}
	
	@Test
	public void removeFirstElement() {
		smallIterator.next();
//		System.out.println("x "+ smallIterator.next());
		smallIterator.remove();
//		System.out.println("removed");
		System.out.println("fisrt "+ (int)smallSetOfEvens.first());
		assertEquals(4, (int)smallSetOfEvens.first());
	}
	
	@Test(expected = IllegalStateException.class)
	public void removeWithoutCallToNext() {
		smallIterator.remove();
	}
	
	@Test
	public void removeEverything() {
		System.out.println("remove all");
		
		while(smallIterator.hasNext()) {
			smallIterator.next();
//			System.out.println("next is " + smallIterator.next());
			smallIterator.remove();
			System.out.println("removed" );
		System.out.println("current size " + smallSetOfEvens.size());
		}
//		System.out.println("current size " + smallSetOfEvens.size());
		assertEquals(0, smallSetOfEvens.size());
	}
	
	@Test
	public void removeEveryOtherElement() {
		Iterator<Integer> iterator = largeSet.iterator();
		while(iterator.hasNext()) {
			iterator.next();
			iterator.remove();
			if(iterator.hasNext()) {
				iterator.next();
			}
		}
		assertEquals(50, (int)largeSet.size());
		Object[] array = largeSet.toArray();
		for(int half = 0; half < 50; half++) {
			assertEquals((half+1)*2, array[half]);
		}
	}
}
