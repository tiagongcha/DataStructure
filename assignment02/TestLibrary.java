package assignment02;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLibrary {
	
	private Library libBig;
	
	
	@Before
	public void setUp() throws Exception {	    
	    //setting up the big lib:
		libBig = new Library();
	    libBig.addAll("Mushroom_Publishing.txt");
	}
	
	@Test
	public void testCheckOutInvalidISBN() {
		boolean result = libBig.checkout(1781843190004L, "tia", 12, 1, 2018);
		assertFalse(result);
	}
	
	@Test
	public void testCheckoutValidISNB() {
		boolean result = libBig.checkout(9781843190004L, "Tia", 8, 25, 2018);
		assertTrue(result);
	}
	
	@Test
	public void testAlreadyCheckout() {
		libBig.checkout(9781843190011L, "Tia", 8, 25, 2018);
		boolean result = libBig.checkout(9781843190011L, "Tia", 8, 25, 2018);
		assertFalse(result);
	}
	
	
	public void checkoutBooks() {
		libBig.checkout(9781843190004L, "Tia", 1, 1, 2017);
		libBig.checkout(9781843192039L, "Tia", 1, 1, 2017);
		libBig.checkout(9781843192701L, "Tia", 1, 1, 2017);
		libBig.checkout(9781843190875L, "Tia", 1, 1, 2017);
		libBig.checkout(9781843190936L, "Tia", 1, 1, 2017);
		
		libBig.checkout(9781843190011L, "Tina", 1, 2, 2017);
		libBig.checkout(9781843190516L, "Ben", 1, 2, 2018);
		libBig.checkout(9781843192022L, "Alice", 8, 25, 2018);
	}
	
	
	@Test
	public void testLookup() {
		checkoutBooks();
		assertEquals("Tia", libBig.lookup(9781843190004L));
		assertEquals("Tia", libBig.lookup(9781843190936L));
		assertEquals("Ben", libBig.lookup(9781843190516L));
		assertEquals(null, libBig.lookup(9791843190516L));
	}
	
	@Test
	public void testLookUpArray() {
		checkoutBooks();
		assertEquals(5, libBig.lookup("Tia").size());
		assertEquals(0, libBig.lookup("Tiaaaa").size());
	}
	
	@Test
	public void testCheckin() {
		checkoutBooks();
		assertEquals(true, libBig.checkin(9781843190936L));
		assertEquals(true, libBig.checkin(9781843190875L));
		assertEquals(false, libBig.checkin(9781843190875L));
		assertEquals(false, libBig.checkin(9081843190875L));
		assertEquals(false, libBig.checkin(9781843190936L));	
	}
	
	@Test
	public void testCheckinByName() {
		checkoutBooks();
		assertEquals(true, libBig.checkin("Tia"));
		//aftering checkingin, lookup should return empty array
		assertEquals(0, libBig.lookup("Tia").size());
		
		assertEquals(false, libBig.checkin("Tiaaa"));
		assertEquals(true, libBig.checkin("Ben"));
		assertEquals(0, libBig.lookup("Ben").size());
	}

}
