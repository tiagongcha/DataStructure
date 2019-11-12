package lab07;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class OliDoublyLinkedListTests {
    private DoublyLinkedList<Integer> intList;
    private DoublyLinkedList<String> strList;

    @Before
    public void setUp() {
        intList = new DoublyLinkedList<>();
        strList = new DoublyLinkedList<>();
    }

    @After
    public void tearDown() {
    }

    private void addInt100() {
        for (int idx = 0; idx < 100; idx++) {
            intList.add(idx, idx);
        }
    }

    @Test
    public void testAddNull() {
        intList.addFirst(null);
        assertEquals(1, intList.size());
    }

    @Test
    public void testAddFirst() {
        intList.addFirst(5);
        assertEquals(5, (int) intList.getFirst());
        assertEquals(1, intList.size());

        intList.addFirst(100);
        assertEquals(100, (int) intList.getFirst());
        assertEquals(2, intList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddMethodOutOfBoundsExp() {
        intList.add(1, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddMethodOutOfBoundsExp2() {
        intList.addFirst(4);
        intList.addFirst(3);
        intList.add(3, 5);
    }

    @Test
    public void testAddMethodInsertAtIndex() {
        intList.add(0, 2);
        intList.add(1, 3);
        assertTrue(Arrays.equals(new Integer[]{2, 3}, intList.toArray()));

        intList.add(1, 8);
        assertTrue(Arrays.equals(new Integer[]{2, 8, 3}, intList.toArray()));

        intList.add(3, 7);
        assertTrue(Arrays.equals(new Integer[]{2, 8, 3, 7}, intList.toArray()));
    }

    @Test
    public void testAddmethodIndexEqualsSize() {
        String[] test = new String[100];
        for (int idx = 0; idx < 100; idx++) {
            strList.add(idx, "congratulations!");
            test[idx] = "congratulations!";
        }

        assertTrue(Arrays.equals(test, strList.toArray()));
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetFirstNoSuchElementExp() {
        strList.getFirst();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetEmptyNoSuchElementExp() {
        strList.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetMethodNoSuchElementExp() {
        intList.addFirst(1);
        strList.get(1);
    }

    @Test
    public void testGetFirstReverseIntArray() {
        for (int idx = 0; idx < 100; idx++) {
            intList.addFirst(idx);
        }

        assertEquals(99, (int) intList.getFirst());
    }

    @Test
    public void testGetLastItem() {
        for (int idx = 1; idx <= 100; idx++) {
            intList.add(idx - 1, idx);
        }

        assertEquals(100, (int) intList.get(intList.size() - 1));
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirstEmptyList() {
        strList.removeFirst();
    }

    @Test
    public void testRemoveFirst() {
        strList.addFirst("a");
        strList.addFirst("b");
        strList.addFirst("c");
        strList.addFirst("d");

        assertEquals("d", strList.removeFirst());
    }

    @Test
    public void testRemoveFirstSizeOne() {
        intList.addFirst(1);
        assertEquals(1, (int) intList.removeFirst());
    }

    @Test
    public void testRemoveFirstConsecutive() {
        for (int idx = 0; idx < 100; idx++) {
            intList.add(idx, idx + 1);
        }

        for (int idx = 0; idx < 100; idx++) {
            assertEquals(idx + 1, (int) intList.removeFirst());
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveMethodEmptyListExp() {
        strList.remove(0);
    }

    @Test
    public void testRemoveEvenNumbers() {
        Integer[] onlyOdds = new Integer[]{1, 3, 5, 7, 9};
        for (int idx = 0; idx < 10; idx++) {
            intList.add(idx, idx);
        }

        for (int idx = 0; idx < 5; idx++) {
            intList.remove(idx);
        }

        assertTrue(Arrays.equals(onlyOdds, intList.toArray()));
    }

    @Test
    public void testRemoveLastElement() {
        addInt100();

        for (int idx = 0; idx < 100; idx++) {
            assertEquals(99 - idx, (int) intList.remove(intList.size() - 1));
        }
    }

    @Test
    public void testRemoveFirstElement() {
        addInt100();

        for (int idx = 0; idx < 100; idx++) {
            intList.remove(0);
        }
    }

    @Test
    public void testIndexOfPresentInList() {
        strList.addFirst("congratulations!");
        strList.add(1, "zero");
        strList.addFirst("angel");
        strList.add(3, "element");

        assertEquals(0, strList.indexOf("angel"));
        assertEquals(1, strList.indexOf("congratulations!"));
        assertEquals(2, strList.indexOf("zero"));
        assertEquals(3, strList.indexOf("element"));
    }

    @Test
    public void testIndexOfINotPresentInList() {
        for (int i = 0; i < 10; i++) {
            intList.add(i, i);
        }

        for (int i = 10; i < 50; i++) {
            assertEquals(-1, intList.indexOf(i));
        }
    }

    //TODO: addLast, getLast, removeLast, lastIndexOf

    @Test
    public void testClear() {
        for (int i = 0; i < 1000; i++) {
            intList.add(i, i);
        }

        assertEquals(1000, intList.size());
        intList.clear();
        assertEquals(0, intList.size());
    }

    @Test
    public void testIteratorHasNext() {
        addInt100();

        Iterator<Integer> iter = intList.iterator();

        for (int i = 0; i < 99; i++) {
            iter.next();
            assertTrue(iter.hasNext());
        }

        iter.next();
        assertFalse(iter.hasNext());
    }

    @Test
    public void testIterator() {
        addInt100();

        Iterator<Integer> iter = intList.iterator();

        iter.next();
        iter.remove();
        assertEquals(1, (int) intList.getFirst());
        iter.next();
        iter.next();
        iter.next();
        iter.remove();
        assertEquals(2, (int) intList.get(1));
        assertEquals(4, (int) intList.get(2));
        assertEquals(98, intList.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextNoSuchElementExp() {
        Iterator<String> iter = strList.iterator();
        iter.next();
    }

    @Test(expected = IllegalStateException.class)
    public void testIteratorRemoveEmptyList() {
        Iterator<Integer> iter = intList.iterator();
        iter.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testIteratorRemoveSameElement() {
        intList.addFirst(1);
        intList.addFirst(0);

        Iterator<Integer> iter = intList.iterator();

        iter.next();
        iter.remove();
        iter.remove();
    }
}