package assignment05;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeUnitTest {

    private BinarySearchTree<Character> charTree, alphabeticalTree, emptyTree;
    private ArrayList<Character> testList, alphabet;
    private Object[] testArr;

    @Before
    public void setUp() throws Exception {
        charTree = new BinarySearchTree<>();
        emptyTree = new BinarySearchTree<>();
        alphabeticalTree = new BinarySearchTree<>();

        testList = new ArrayList<>();
        alphabet = new ArrayList<>();

        testArr = new Object[26];

        for (int index = 0; index < 26; index++) {
            alphabet.add((char) ('a' + index));
            testArr[index] = (char) ('a' + index);
        }

        alphabeticalTree.addAll(alphabet);

        Collections.shuffle(alphabet);
        charTree.addAll(alphabet);
        charTree.writeDot("charTree.dot");

    }

    @Test
    public void test() throws Exception {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random();

        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(1);
        tree.add(7);
        tree.add(13);
        tree.add(17);
        tree.add(16);
        tree.add(18);
        tree.add(19);

        tree.remove(18);
        // System.out.println(tree.generateDot());
    }

    @Test
    public void test2() throws Exception {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(1);
        tree.remove(1);
        System.out.println(tree.isEmpty());
    }

    @Test
    public void firstReturnsSmallestItem() {
        assertTrue(alphabeticalTree.first().equals('a'));
    }

    @Test(expected = NoSuchElementException.class)
    public void firstThrowsIfEmpty() {
        emptyTree.first();
    }

    @Test
    public void lastReturnsLargestItem() {
        assertTrue(alphabeticalTree.last().equals('z'));
    }

    @Test(expected = NoSuchElementException.class)
    public void lastThrowsIfEmpty() {
        emptyTree.last();
    }

    @Test
    public void addReturnsTrueWhenElementIsAddedToSet() {
        assertTrue(charTree.add('A'));
    }

    @Test
    public void addActuallyAddsElementToSet() {
        charTree.add('Z');
        assertTrue(charTree.contains('Z'));
    }

    @Test
    public void addReturnsFalseIfElementIsAlreadyInSet() {
        assertFalse(charTree.add('a'));
    }

    @Test
    public void addAllReturnsTrueWhenAtLeastOneElementIsAdded() {
        testList.add('A');
        testList.add('(');
        testList.add('a');
        assertTrue(charTree.addAll(testList));
    }

    @Test
    public void addAllReturnsFalseWhenNoElementsAreAdded() {
        testList.add('b');
        testList.add('v');
        assertFalse(charTree.addAll(testList));
    }

    @Test
    public void clearRemovesAllElementsFromList() {
        charTree.clear();
        assertTrue(charTree.isEmpty());
    }

    @Test
    public void containsReturnsTrueWhenElementIsInSet() {
        assertTrue(charTree.contains('a'));
    }

    @Test
    public void containsReturnsFalseWhenElementIsNotInSet() {
        assertFalse(charTree.contains('{'));
    }

    @Test
    public void containsAllReturnsTrueIfAllElementsAreInSet() {
        testList.add('a');
        testList.add('f');
        testList.add('y');
        assertTrue(charTree.containsAll(testList));
    }

    @Test
    public void containsAllReturnsFalseIfAnyElementIsNotInSet() {
        testList.add('a');
        testList.add('f');
        testList.add('y');
        testList.add('$');
        assertFalse(charTree.containsAll(testList));
    }

    @Test
    public void isEmptyReturnsTrueIfSetIsEmpty() {
        assertTrue(emptyTree.isEmpty());
    }

    @Test
    public void isEmptyReturnsFalseIfSetIsNotEmpty() {
        assertFalse(charTree.isEmpty());
    }

    @Test
    public void removeReturnsTrueIfElementIsRemovedFromSet() {
        assertTrue(charTree.remove('f'));
    }

    @Test
    public void removeActuallyRemovesTheElement() {
        charTree.remove('h');
        assertFalse(charTree.contains('h'));
    }

    @Test
    public void removeReturnsFalseIfSetDoesNotContainElement() {
        assertFalse(charTree.remove('%'));
    }

    @Test
    public void removeAllReturnsTrueIfOneElementIsRemoved() {
        testList.add('b');
        testList.add('#');
        testList.add('y');
        assertTrue(charTree.removeAll(testList));
    }

    @Test
    public void removeAllReturnsFalseIfNoElementsAreRemoved() {
        testList.add('G');
        testList.add('#');
        testList.add('(');
        assertFalse(charTree.removeAll(testList));
    }

//    @Test
//    public void sizeReturnsCorrectSize() throws Exception {
//        System.out.println(charTree.generateDot());
//        charTree.remove('h');
//        System.out.println(charTree.generateDot());
//        assertEquals(25, charTree.size());
//    }
//
//    @Test
//    public void toArrayReturnsOrderedArray() throws Exception{
//        ArrayList<Character> test = new ArrayList<>();
//        for(char c = 'a'; c <= 'z'; c++) {
//            test.add(c);
//        }
//
//        System.out.println(charTree.generateDot());
//        assertEquals(test, charTree.toArrayList());
//    }
}