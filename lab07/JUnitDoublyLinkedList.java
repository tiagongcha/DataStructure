package lab07;

import static org.junit.Assert.*;


import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JUnitDoublyLinkedList {

	private DoublyLinkedList<Integer> smallInt, smallIntReverse, empty, singleElement, duplicateAll, someDuplicate;

	@Before
	public void setUp() throws Exception {
		smallInt = new DoublyLinkedList<Integer>();
		for (int i = 1; i <= 10; i++) {
			smallInt.addFirst(i);
		}

		smallIntReverse = new DoublyLinkedList<Integer>();
		for (int i = 1; i <= 10; i++) {
			smallIntReverse.addLast(i);
		}

		empty = new DoublyLinkedList<Integer>();
		singleElement = new DoublyLinkedList<Integer>();
		singleElement.add(0, 666);
		
		duplicateAll = new DoublyLinkedList<Integer>();
		for(int i = 0; i < 10; i++) {
			duplicateAll.add(i, 999);
		}
		
		someDuplicate = new DoublyLinkedList<Integer>();
		someDuplicate.add(0, 4);
		someDuplicate.add(1, 5);
		someDuplicate.add(2, 6);
		someDuplicate.add(3, 7);
		someDuplicate.add(4, 6);
		someDuplicate.add(5, 2);
		someDuplicate.add(6, -1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddFirst() {
		smallInt.addFirst(5);
		smallInt.addFirst(7);
		assertEquals(7, (int) smallInt.getFirst());
		assertEquals(12, (int) smallInt.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetNull() {
		smallInt.get(10);
		smallInt.get(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGet() {
//		System.out.println((int)smallInt.get(0));
		assertEquals(10, (int) smallInt.get(0));
		assertEquals(9, (int) smallInt.get(1));
		assertEquals(8, (int) smallInt.get(2));
		assertEquals(7, (int) smallInt.get(3));
		assertEquals(6, (int) smallInt.get(4));

		// using singleElement:
		assertEquals(666, (int) singleElement.get(0));
		singleElement.get(666);
	}

	@Test
	public void testADDLast() {
		smallInt.addLast(11);
		assertEquals(11, smallInt.size());
		assertEquals(11, (int) smallInt.get(10));
		assertEquals(1, (int) smallInt.get(9));
//		System.out.println((int)smallIntReverse.get(0));
		assertEquals(1, (int) smallIntReverse.get(0));

		// using single element:
		singleElement.addLast(777);
		assertEquals(666, (int) singleElement.get(0));
		assertEquals(777, (int) singleElement.get(1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAdd() {
		// test add at index = 0;
		smallInt.add(0, 999);
		assertEquals(999, (int) smallInt.get(0));
		assertEquals(11, smallInt.size());

		// test add at index = size;
		smallInt.add(11, 888);
		assertEquals(888, (int) smallInt.get(11));
		assertEquals(12, smallInt.size());

		// test insert at the middle:
		smallIntReverse.add(2, 999);
		assertEquals(999, (int) smallIntReverse.get(2));
		assertEquals(2, (int) smallIntReverse.get(1));
		assertEquals(3, (int) smallIntReverse.get(3));
		assertEquals(11, smallIntReverse.size());

		// test insert at the middle:
		smallIntReverse.add(10, 899);
		assertEquals(899, (int) smallIntReverse.get(10));
		assertEquals(10, (int) smallIntReverse.get(11));
		assertEquals(9, (int) smallIntReverse.get(9));
		assertEquals(12, smallIntReverse.size());

		// test add empty list exception testing:
		empty.add(11, 8);
		empty.add(0, 777);
		assertEquals(777, (int) smallInt.get(0));
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveFirst() {
		// test empty:
		empty.removeFirst();

		// testing more than one element"
		assertEquals(10, (int) smallInt.removeFirst());
		assertEquals(9, smallInt.size());

		// test singleElement:
		assertEquals(666, (int)singleElement.removeFirst());
	}
	
	@Test
	public void testRemoveLast() {
		for(int i = 1; i <=10; i++) {
//			System.out.println((int)smallInt.remove(smallInt.size() - 1));
			assertEquals(i, (int)smallInt.remove(smallInt.size() - 1));
		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemove() {
		//test remove in the middle:
		assertEquals(10, (int) smallInt.remove(0));
		assertEquals(7, (int)smallInt.remove(2));
		assertEquals(8, smallInt.size());
		assertEquals(3, (int) smallInt.remove(5));
	
		assertEquals(10, (int)smallIntReverse.remove(9));
		
		//test remove single element:
		assertEquals(666, (int)singleElement.remove(0));
		assertEquals(0, singleElement.size());
		
		//test empty:
		empty.remove(0);
		
	
	}
	
	@Test
	public void testIndexOf() {
		assertEquals(0, smallInt.indexOf(10));
		assertEquals(1, smallInt.indexOf(9));
		assertEquals(2, smallInt.indexOf(8));
		assertEquals(3, smallInt.indexOf(7));
		assertEquals(4, smallInt.indexOf(6));
		assertEquals(-1, smallInt.indexOf(999));
		
		assertEquals(2, someDuplicate.indexOf(6));
		
		//test all duplicates:
		assertEquals(0, duplicateAll.indexOf(999));
		
		//test empty:
		assertEquals(-1, empty.indexOf(0));
		
		//test single:
		assertEquals(0, singleElement.indexOf(666));
		assertEquals(-1, singleElement.indexOf(888));
	}
	
	@Test
	public void testLastIndexOf() {
		//test all duplicates:
		assertEquals(9, duplicateAll.lastIndexOf(999));
		assertEquals(-1, duplicateAll.lastIndexOf(888));
		
		//test empty
		assertEquals(-1, empty.lastIndexOf(0));
		
		//test single:
		assertEquals(0, singleElement.lastIndexOf(666));
		assertEquals(-1, singleElement.lastIndexOf(888));
		
		assertEquals(4, someDuplicate.lastIndexOf(6));
		
		assertEquals(0, smallInt.lastIndexOf(10));
		assertEquals(1, smallInt.lastIndexOf(9));
		assertEquals(2, smallInt.lastIndexOf(8));
		assertEquals(3, smallInt.lastIndexOf(7));
		assertEquals(4, smallInt.lastIndexOf(6));
		assertEquals(-1, smallInt.lastIndexOf(999));
		
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(empty.isEmpty());
		assertFalse(smallInt.isEmpty());
	}
	
	@Test
	public void testClear() {
		smallInt.clear();
		assertTrue(smallInt.isEmpty());
	}
	
	@Test
	public void toArray() {
		
//		test small array:
		Integer[] small1 = new Integer[10];
		for(int i = 0; i < 10; i++) {
			small1[i] = 10 - i;
		}	
		assertTrue(Arrays.equals(small1, smallInt.toArray()));
		
		//test for single element array:
		Integer[] single = new Integer[] {666};
		assertTrue(Arrays.equals(single, singleElement.toArray()));
	}
	
	@Test
	public void testGetHead() {
		smallIntReverse.getHead();
	}

}
