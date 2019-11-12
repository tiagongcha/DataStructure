package assignment05;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SpellCheckerUnitTests {
	
	private SpellChecker checker;
	private ArrayList<String> list;
	private BinarySearchTree<String> tree;

	@Before
	public void setUp() {
		checker = new SpellChecker();
		list = new ArrayList<>();
		tree = new BinarySearchTree<>();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testAddToEmptyDictionary() {
		assertEquals(2, checker.spellCheck(new File("spellTest.txt")).size());
		checker.addToDictionary("one");
		assertEquals(1, checker.spellCheck(new File("spellTest.txt")).size());
		checker.addToDictionary("two");
		assertEquals(0, checker.spellCheck(new File("spellTest.txt")).size());
	}
	
	@Test
	public void testAddToDictionary() {
		checker = new SpellChecker(new File("dictionary.txt"));
		list.add("ahab");
		list.add("flakker");
		list.add("unkempt");
		list.add("oculus");
		list.add("norfleet");
		list.add("pbfg");
		list.add("jakobs");
		assertEquals(7, checker.spellCheck(new File("spellTest2.txt")).size());
		checker.addToDictionary("ahab");
		checker.addToDictionary("flakker");
		assertEquals(5, checker.spellCheck(new File("spellTest2.txt")).size());
		checker.addToDictionary("unkempt");
		assertEquals(4, checker.spellCheck(new File("spellTest2.txt")).size());
		checker.addToDictionary("oculus");
		checker.addToDictionary("norfleet");
		checker.addToDictionary("pbfg");
		checker.addToDictionary("jakobs");
		assertEquals(0, checker.spellCheck(new File("spellTest2.txt")).size());
	}
	
	@Test
	public void testRemoveFromDictionary() {
		checker = new SpellChecker(new File("dictionary.txt"));
		assertEquals(0, checker.spellCheck(new File("spellTest.txt")).size());
		checker.removeFromDictionary("one");
		assertEquals(1, checker.spellCheck(new File("spellTest.txt")).size());
		checker.removeFromDictionary("two");
		assertEquals(2, checker.spellCheck(new File("spellTest.txt")).size());
	}
	
	@Test
	public void testSpellChecker() throws FileNotFoundException {
		checker = new SpellChecker(new File("dictionary.txt"));
		assertEquals(90, checker.spellCheck(new File("badDictionary.txt")).size());

		File file = new File("badDictionary.txt");
		Scanner scn = new Scanner(file);
		
		while(scn.hasNextLine()) {
			checker.addToDictionary(scn.next());
		}
		scn.close();
		assertEquals(0, checker.spellCheck(new File("badDictionary.txt")).size());		
	}

}
