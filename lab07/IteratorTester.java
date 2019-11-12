package lab07;

import static org.junit.Assert.*;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import org.junit.Before;
import org.junit.Test;


public class IteratorTester {
	
	private DoublyLinkedList<Integer> smallSetOfEvens;
	private DoublyLinkedList<Integer> largeSet;
	
	private Iterator<Integer> smallIterator;

	@Before
	public void setup() {
		smallSetOfEvens = new DoublyLinkedList<>();
		
		int i = 0;
		for(int half = 1; half <= 10; half++) {
			
			smallSetOfEvens.add(i,half*2); 
			i++;
			
		}
		smallIterator = smallSetOfEvens.iterator();
		
		largeSet = new DoublyLinkedList<>();
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
		System.out.println("fisrt "+ (int)smallSetOfEvens.getFirst());
		assertEquals(4, (int)smallSetOfEvens.getFirst());
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
