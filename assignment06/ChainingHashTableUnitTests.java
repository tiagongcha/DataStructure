package assignment06;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

public class ChainingHashTableUnitTests {
    ChainingHashTable table;
    
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
	
    @Test
    public void testConstructor() {
        table = new ChainingHashTable(10, new GoodHashFunctor());
        assertEquals(0, table.size());
        assertEquals(0, table.getCollisions());
    }

    @Test(expected = NullPointerException.class)
    public void testAddNull() {
        table = new ChainingHashTable(10, new GoodHashFunctor());
        table.add(null);
    }

    @Test
    public void testAddElements() {
        table = new ChainingHashTable(52, new GoodHashFunctor());
        String str = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 26; i++) {
            assertTrue(table.add(str.charAt(i) + ""));
        }
        assertEquals(0, table.getCollisions());
        assertEquals(26, table.size());
    }

    @Test
    public void testAddSameElement() {
        table = new ChainingHashTable(52, new GoodHashFunctor());
        assertTrue(table.add("a"));
        assertFalse(table.add("a"));
    }

    @Test
    public void testAddAllNewElements() {
        table = new ChainingHashTable(1000, new GoodHashFunctor());

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }

        assertEquals(0, table.size());
        assertTrue(table.addAll(list));
        assertEquals(100, table.size());
    }

    @Test
    public void testAddAllSameElements() {
        table = new ChainingHashTable(1000, new GoodHashFunctor());

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }

        assertEquals(0, table.size());
        assertTrue(table.addAll(list));
        assertEquals(100, table.size());
        assertFalse(table.addAll(list));
    }

    @Test
    public void testAddAllSameElementsOneDifferent() {
        table = new ChainingHashTable(1000, new GoodHashFunctor());

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }

        assertEquals(0, table.size());
        assertTrue(table.addAll(list));
        assertEquals(100, table.size());
        list.add("a");
        assertTrue(table.addAll(list));
    }

    @Test
    public void testClear() {
        table = new ChainingHashTable(200, new GoodHashFunctor());

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }

        assertTrue(table.addAll(list));
        assertEquals(100, table.size());
        table.clear();
        assertEquals(0, table.size());
    }

    @Test
    public void testContainsPresent() {
        table = new ChainingHashTable(20, new GoodHashFunctor());
        table.add("a");
        assertTrue(table.contains("a"));
    }

    @Test
    public void testContainsEmptyTable() {
        table = new ChainingHashTable(20, new GoodHashFunctor());
        assertFalse(table.contains("a"));
    }

    @Test
    public void testContainsNotPresent() {
        table = new ChainingHashTable(20, new GoodHashFunctor());
        table.add("a");
        assertFalse(table.contains("b"));
    }

    @Test
    public void testContainsAllPresent() {
        table = new ChainingHashTable(200, new GoodHashFunctor());
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }

        table.addAll(list);
        assertTrue(table.containsAll(list));
    }

    @Test
    public void testContainsAllSomeNotPresent() {
        table = new ChainingHashTable(200, new GoodHashFunctor());
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }

        table.addAll(list);
        list.add("table");
        list.add("screen");
        assertFalse(table.containsAll(list));
    }

    @Test
    public void testContainsAllNonePresent() {
        table = new ChainingHashTable(200, new GoodHashFunctor());
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }

        assertFalse(table.containsAll(list));
    }

    @Test
    public void testRemoveElementPresent() {
        table = new ChainingHashTable(10, new GoodHashFunctor());
        table.add("asdf");
        assertTrue(table.remove("asdf"));
    }

    @Test
    public void testRemoveElementNotPresent() {
        table = new ChainingHashTable(10, new GoodHashFunctor());
        table.add("one");
        assertFalse(table.remove("two"));
    }

    @Test
    public void testRemoveElementAlreadyRemoved() {
        table = new ChainingHashTable(10, new GoodHashFunctor());
        table.add("one");
        table.remove("one");
        assertFalse(table.remove("one"));
    }

    @Test
    public void testContainsAfterRemove() {
        table = new ChainingHashTable(10, new GoodHashFunctor());
        table.add("one");
        table.remove("one");
        assertFalse(table.contains("one"));
    }

    @Test
    public void testSizeAfterRemove() {
        table = new ChainingHashTable(200, new GoodHashFunctor());
        for (int i = 0; i < 100; i++) {
            table.add(i + "");
        }

        table.remove("0");
        assertEquals(99, table.size());
    }

    @Test
    public void testRemoveAllElements() {
        table = new ChainingHashTable(200, new GoodHashFunctor());
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }
        table.addAll(list);

        assertTrue(table.removeAll(list));
        assertEquals(0, table.size());
    }

    @Test
    public void testRemoveAllSameElement() {
        table = new ChainingHashTable(200, new GoodHashFunctor());
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }
        table.addAll(list);

        list.clear();
        for (int i = 0; i < 100; i++) {
            list.add("0");
        }
        assertTrue(table.removeAll(list));
        assertEquals(99, table.size());
    }

    @Test
    public void containsAfterRemove() {
        table = new ChainingHashTable(200, new GoodHashFunctor());
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }
        table.addAll(list);

        for (int i = 0; i < 100; i++) {
            table.remove(i + "");
            assertFalse(table.contains(i + ""));
            assertTrue(table.add(i + ""));
        }
    }

    @Test
    public void testIsEmpty() {
        table = new ChainingHashTable(200, new GoodHashFunctor());
        assertTrue(table.isEmpty());
        table.add("String");
        assertFalse(table.isEmpty());
    }

    @Test
    public void testBigFile() throws FileNotFoundException {
        Scanner scn = new Scanner(new File("english3.txt"));
        table = new ChainingHashTable(100_000, new GoodHashFunctor());

        // total words: 194,433

        ArrayList<String> list = new ArrayList<>();
        Random rand = new Random();
        String temp;
        while (scn.hasNext()) {
            temp = scn.next();
            table.add(temp);
            if (rand.nextInt(2) == 1) {
                list.add(temp);
            }
        }

        scn.close();

        assertEquals(194_433, table.size());
        assertFalse(table.addAll(list));
        System.out.println(table.getCollisions());
        assertTrue(table.containsAll(list));

        list.add("flakker");
        assertFalse(table.containsAll(list));
        assertTrue(table.addAll(list));
        assertEquals(194_434, table.size());
        assertTrue(table.containsAll(list));

        table.clear();
        assertEquals(0, table.size());
    }
}
