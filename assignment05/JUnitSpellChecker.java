package assignment05;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JUnitSpellChecker {
	
	private SpellChecker myChecker;
	
	@Before
	public void setUp() throws Exception {
		myChecker = new SpellChecker(new File("sample_word_list.txt"));
		myChecker.addToDictionary("hello");
		myChecker.addToDictionary("hola");
		myChecker.removeFromDictionary("eat");
		myChecker.removeFromDictionary("crates");
	}


	@Test
	public void testBuildDictionary() {
		myChecker.getDictionary().writeDot("diction.dot");
	}
	
	@Test
	public void testSpellCheck() {
		List<String> misSpelled = myChecker.spellCheck(new File("spellCheck.txt"));

		misSpelled.contains("eat");
		assertTrue(misSpelled.contains("eat"));
		assertTrue(misSpelled.contains("crates"));
	}
}
