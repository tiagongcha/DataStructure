import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class junitname extends Solution {

	List<String> known_aliases, known_aliases1, known_aliases2, known_aliases3;
	@Before
	public void setUp() throws Exception {
		known_aliases = new ArrayList<>();
		known_aliases1 = new ArrayList<>();
		known_aliases2 = new ArrayList<>();
		known_aliases3 = new ArrayList<>();
		known_aliases3.add("Alphonse Gabriel Capone");
		known_aliases3.add("Alphonse F Capone");
		known_aliases2.add("Alphonse Gabriel Capone");
		known_aliases2.add("Alphonse Francis Capone");
		known_aliases1.add("Alphonse Capone");
		known_aliases.add("Alphonse Gabriel Capone");
		known_aliases.add("Al Capone");
	}

	@Test
	public void testExactName() {
		assertTrue(name_match(known_aliases, "Alphonse Gabriel Capone"));
		assertTrue(name_match(known_aliases, "Al Capone"));
		assertFalse(name_match(known_aliases, "Alphonse francis Capone"));
		
	}
	
	@Test
	public void testMiss() {
		assertTrue(name_match(known_aliases1, "Alphonse Gabriel Capone"));
		assertTrue(name_match(known_aliases1, "Alphonse Francis Capone"));
		assertFalse(name_match(known_aliases1, "Alex Capone"));
		known_aliases.remove(1);
		assertTrue(name_match(known_aliases, "Alphonse Capone"));
		assertFalse(name_match(known_aliases, "Alphonse Francis Capone"));
		assertFalse(name_match(known_aliases, "Alex Capone"));	
	}
	@Test
	public void test() {
		assertTrue(name_match(known_aliases2, "Alphonse Francis Capone"));
		assertFalse(name_match(known_aliases2, "Alphonse Edward Capone"));
	}
	@Test
	public void test1() {
		assertTrue(name_match(known_aliases3, "Alphonse G Capone"));
		assertTrue(name_match(known_aliases3, "Alphonse Francis Capone"));
		assertFalse(name_match(known_aliases3, "Alphonse E Capone"));
		assertFalse(name_match(known_aliases3, "Alphonse Eward Capone"));
		assertFalse(name_match(known_aliases3, "Alphonse Greg Capone"));
	}

}
