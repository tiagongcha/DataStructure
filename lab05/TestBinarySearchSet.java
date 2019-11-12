package lab05;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBinarySearchSet {

	private BinarySearchSet<Integer> setInteger1;
	private BinarySearchSet<Integer> setIntegerEven;
	private BinarySearchSet<String> setString1;
	private BinarySearchSet<String> setString2;
	private BinarySearchSet<Integer> setIntegerSingle;
	private BinarySearchSet<Integer> setInteger4;

	private Iterator<Integer> intIterator;
	private Iterator<String> stringIterator;

	@Before
	public void setUp() throws Exception {
		setInteger1 = new BinarySearchSet<Integer>();
		setIntegerEven = new BinarySearchSet<Integer>();
		setIntegerSingle = new BinarySearchSet<Integer>();
		setInteger4 = new BinarySearchSet<Integer>();
		setString1 = new BinarySearchSet<String>();

		for (int i = 100; i < 0; i--) {
			setInteger1.add(i);
		}

		/**
		 * sort string in custom manner --> sort by the length of the array
		 */
		setString2 = new BinarySearchSet<String>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}

		});

		// setting up the iterator objects:
		intIterator = setInteger1.iterator();
		stringIterator = setString1.iterator();
	}

	@Test
	public void testAddInt() {

		for (int i = 100; i > 0; i--) {
			assertTrue(setInteger1.add(i));
		}
		for (int i = 2; i < 200; i += 2) {
			assertTrue(setIntegerEven.add(i));
		}
		// setInteger3 has only one element:
		setIntegerSingle.add(100);
		assertFalse(setIntegerSingle.add(100));

//		test sorted integer set:
		assertEquals(1, setInteger1.toArray()[0]);
		assertEquals(2, setInteger1.toArray()[1]);
		assertEquals(3, setInteger1.toArray()[2]);
		assertEquals(4, setInteger1.toArray()[3]);
	}

	@Test
	public void testAddString() {
		setString1.add("Banana");
		setString1.add("Apple");
		setString1.add("Ada");
		setString1.add("war");
		setString1.add("car");

		setString2.add("Banana");
		setString2.add("Apple");
		setString2.add("Adam");
		setString2.add("war");
		setString2.add("wo");

//		test sorted string set:
		assertEquals("Ada", setString1.first());
		assertEquals("Apple", setString1.toArray()[1]);
		assertEquals("Banana", setString1.toArray()[2]);
		assertEquals("car", setString1.toArray()[3]);
		assertEquals("war", setString1.toArray()[4]);
//		test add method's return boolean value:
		assertFalse(setString1.add("car"));

//		test sorted string set with custom comparator:
		assertEquals("wo", setString2.first());
		assertEquals("war", setString2.toArray()[1]);
		assertEquals("Adam", setString2.toArray()[2]);
		assertEquals("Apple", setString2.toArray()[3]);
		assertEquals("Banana", setString2.toArray()[4]);
//		test add method's return boolean value:
		assertFalse(setString2.add("war"));
	}

	@Test
	public void testFirst() {
		testAddInt();
		assertEquals(1, (int) setInteger1.first());
		assertEquals(2, (int) setIntegerEven.first());
		assertEquals(100, (int) setIntegerSingle.first());

		testAddString();
		assertEquals("Ada", setString1.first());
	}

	@Test
	public void testLast() {
		testAddInt();
		assertEquals(100, (int) setInteger1.last());
		assertEquals(198, (int) setIntegerEven.last());
		assertEquals(100, (int) setIntegerSingle.last());

		testAddString();
		assertEquals("war", setString1.last());
	}

	@Test
	public void testAddAllInt() {
		testAddInt();
		int[] data = new int[] { 1, 2, 3, 4, 5 };
		List<Integer> temp = Arrays.stream(data).boxed().collect(Collectors.toList());

		assertFalse(setInteger1.addAll(temp));
		assertTrue(setIntegerEven.addAll(temp));
		assertTrue(setIntegerEven.contains(3));
		assertTrue(setIntegerEven.contains(5));
		assertTrue(setIntegerSingle.addAll(temp));

		assertEquals(100, setInteger1.size());
		assertEquals((99 + 3), setIntegerEven.size());
		assertEquals(6, setIntegerSingle.size());
	}

	@Test
	public void testAddAllString() {
		testAddString();
		List<String> temp = new ArrayList(Arrays.asList(new String[] { "car", "war", "hi", "hola" }));
		assertTrue(setString1.addAll(temp));

		assertEquals(7, setString1.size());
	}

	@Test
	public void testClear() {
		testAddInt();
		setInteger1.clear();
		assert (setInteger1.size() == 0);
	}

	@Test
	public void testContains() {
		testAddInt();
		assertTrue(setIntegerSingle.contains(100));
		assertFalse(setIntegerEven.contains(999));

		testAddString();
		assertTrue(setString1.contains("Apple"));
		assertTrue(setString1.contains("Ada"));
		assertTrue(setString1.contains("Banana"));
		assertTrue(setString1.contains("car"));
		assertFalse(setString1.contains("carr"));
	}

	@Test
	public void testContainsAll() {
		testAddInt();
		int[] data = new int[] { 1, 2, 3, 4, 5 };
		List<Integer> temp = Arrays.stream(data).boxed().collect(Collectors.toList());
		assertTrue(setInteger1.containsAll(temp));
		assertFalse(setIntegerEven.containsAll(temp));

		testAddString();
		List<String> temp1 = new ArrayList(Arrays.asList(new String[] { "car", "war", "Banana" }));
		List<String> temp2 = new ArrayList(Arrays.asList(new String[] { "car", "war", "hi", "hola" }));
		assertTrue(setString1.containsAll(temp1));
		assertFalse(setString1.containsAll(temp2));
	}

	@Test
	public void testIsEmpty() {
		assertTrue(setString1.isEmpty());
	}

	/** testing for iterator class */

	@Test
	public void TestHasNext() {
		assertFalse(intIterator.hasNext());
		testAddInt();
		testAddString();
		assertTrue(intIterator.hasNext());
		assertTrue(stringIterator.hasNext());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testNextException() {
		testAddInt();
		
		for(int i = 0; i < 101; i++) {
			intIterator.next();
		}
	}
	
	@Test
	public void testNext() {
		testAddInt();
		
		for(int i = 1; i < 101; i++) {
			assertEquals(i, (int)intIterator.next());
		}
		
		testAddString();
		assertEquals("Ada", stringIterator.next());
		assertEquals("Apple", stringIterator.next());
		assertEquals("Banana", stringIterator.next());
		assertEquals("car", stringIterator.next());
		assertEquals("war", stringIterator.next());
	}
	
	@Test(expected = IllegalStateException.class)
	public void removeWithoutCallNext() {
		testAddInt();
		intIterator.remove();
	}
	
	@Test
	public void removeFirst() {
		testAddInt();
		intIterator.next();
		intIterator.remove();
		assertEquals(2, (int)setInteger1.first());
	}
	
	@Test
	public void removeEveryOther() {
		testAddInt();
		
		while(intIterator.hasNext()) {
			intIterator.next();
			intIterator.remove();
			//iterator one more time without calling remove()
			if(intIterator.hasNext()) {
				intIterator.next();
			}
		}
		assertEquals(50, (int)setInteger1.size());
	}
	
	@Test
	public void removeLast() {
		
		testAddInt();
		
		//same idea as walking a linked list:
		while(intIterator.hasNext()) {
			intIterator.next();
		}
		intIterator.remove();
		assertEquals(99, (int)setInteger1.size());
		assertEquals(99, (int) setInteger1.toArray()[98]);
		
	}
	
	@Test
	public void removeEverything() {
		testAddInt();
		
		while(intIterator.hasNext()) {
			intIterator.next();
			intIterator.remove();
		}
		assertTrue(setInteger1.isEmpty());
	}
	
	

}
