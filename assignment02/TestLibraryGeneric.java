package assignment02;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLibraryGeneric {

	private LibraryGeneric<String> libName;
	private LibraryGeneric<PhoneNumber> libPhone;

	@Before
	public void setUp() throws Exception {
		libName = new LibraryGeneric<String>();
		libPhone = new LibraryGeneric<PhoneNumber>();
		libName.addAll("Mushroom_Publishing.txt");
		libPhone.addAll("Mushroom_Publishing.txt");
	}

	// helper functions for checking out books:
	public void checkoutByName() {
		libName.checkout(9781843190004L, "Tia", 1, 1, 2017);
		libName.checkout(9781843192039L, "Tia", 2, 2, 2017);
		libName.checkout(9781843192701L, "Tia", 4, 3, 2017);
		libName.checkout(9781843190875L, "Tia", 7, 4, 2017);
		libName.checkout(9781843190936L, "Tia", 9, 5, 2017);
		libName.checkout(9781843190011L, "Tina", 1, 2, 2017);
		libName.checkout(9781843190516L, "Ben", 1, 3, 2018);
		libName.checkout(9781843192022L, "Alice", 8, 25, 2018);
		// not overdue book:
		libName.checkout(9781843190110L, "Apple", 12, 25, 2019);
	}

	public void checkoutByPhone() {
		PhoneNumber phone1 = new PhoneNumber("801.535.3415");
		PhoneNumber phone2 = new PhoneNumber("901.555.4535");
		PhoneNumber phone3 = new PhoneNumber("304.545.5438");
		PhoneNumber phone4 = new PhoneNumber("443.551.3454");
		PhoneNumber phone5 = new PhoneNumber("222.123.1221");

		libPhone.checkout(9781843190004L, phone1, 1, 1, 2017);
		libPhone.checkout(9781843192039L, phone1, 12, 1, 2017);
		libPhone.checkout(9781843192701L, phone1, 1, 8, 2017);
		libPhone.checkout(9781843190875L, phone2, 4, 9, 2017);
		libPhone.checkout(9781843190936L, phone3, 1, 1, 2017);

		// not overdue books:
		libPhone.checkout(9781843193319L, phone4, 1, 1, 2019);
		libPhone.checkout(9781843192954L, phone5, 1, 1, 2019);
	}

	@Test
	public void testLookup() {
		// test look up by isbn with type String library:
		checkoutByName();
		assertEquals("Tia", libName.lookup(9781843190004L));
		assertEquals("Tia", libName.lookup(9781843190936L));
		assertEquals("Ben", libName.lookup(9781843190516L));
		assertEquals(null, libName.lookup(9791843190516L));

		// test look up by isbn with PhoneNumber library
		checkoutByPhone();
		PhoneNumber phone1 = new PhoneNumber("801.535.3415");
		PhoneNumber phone2 = new PhoneNumber("901.555.4535");

		assertEquals(phone1, libPhone.lookup(9781843190004L));
		assertEquals(phone1, libPhone.lookup(9781843192039L));
	}

	@Test
	public void testLookUpArrayByName() {
		checkoutByName();
		ArrayList<LibraryBookGeneric<String>> booksCheckedOutByName = libName.lookup("Tia");

		// testing using contains:
		boolean result1 = booksCheckedOutByName.contains(
				new LibraryBookGeneric<String>(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"));
		boolean result2 = booksCheckedOutByName.contains(
				new LibraryBookGeneric<String>(9781843192039L, "William Fitzmaurice", "Operation: Sergeant York"));
		assertTrue(result1);
		assertTrue(result2);

		// testing using getHolder() and getDueDate():
		assertEquals("Tia", booksCheckedOutByName.get(0).getHolder());
		assertEquals("Tia", booksCheckedOutByName.get(1).getHolder());
		assertEquals("Tia", booksCheckedOutByName.get(2).getHolder());
		assertEquals("Tia", booksCheckedOutByName.get(3).getHolder());
		assertEquals("Tia", booksCheckedOutByName.get(4).getHolder());
	}

	@Test
	public void testLookUpArrayByPhone() {
		checkoutByPhone();

		PhoneNumber phone1 = new PhoneNumber("801.535.3415");
		ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOutByPhone = libPhone.lookup(phone1);

		// testing using contains:
		boolean result1 = booksCheckedOutByPhone.contains(
				new LibraryBookGeneric<String>(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"));
		boolean result2 = booksCheckedOutByPhone.contains(
				new LibraryBookGeneric<String>(9781843192039L, "William Fitzmaurice", "Operation: Sergeant York"));
		assertTrue(result1);
		assertTrue(result2);

		// testing using getHolder() and getDueDate():
		assertEquals(phone1, booksCheckedOutByPhone.get(0).getHolder());
	}

	@Test
	public void testCheckInByName() {
		checkoutByName();

		assertTrue(libName.checkin("Tia"));
		// after checking in the book, look up under this holder should return null:
		assertEquals(null, libName.lookup(9781843190875L));
		assertEquals(null, libName.lookup(9781843190004L));
		assertEquals(0, libName.lookup("Tia").size());

		assertTrue(libName.checkin("Ben"));
		assertEquals(null, libName.lookup(9781843190516L));
		assertEquals(0, libName.lookup("Ben").size());
	}

	@Test
	public void testCheckInByPhone() {
		checkoutByPhone();

		PhoneNumber phone1 = new PhoneNumber("801.535.3415");

		assertTrue(libPhone.checkin(phone1));
		// after checking in the book, look up under this holder should return null:
		assertEquals(null, libPhone.lookup(9781843190004L));
		assertEquals(null, libPhone.lookup(9781843192039L));
		assertEquals(0, libPhone.lookup(phone1).size());
	}

	@Test
	public void testSortByIsbn() {
		assertEquals(9781843190004L, libName.getInventoryList().get(0).getIsbn());
		assertEquals(9781843190011L, libName.getInventoryList().get(1).getIsbn());
		assertEquals(9781843190028L, libName.getInventoryList().get(2).getIsbn());
		assertEquals(9781843190042L, libName.getInventoryList().get(3).getIsbn());
	}

	@Test
	public void testSortByAuthor() {
		assertEquals("Alan Burt Akers", libName.getOrderedByAuthor().get(0).getAuthor());
		assertEquals("Anthony J D Burns", libName.getOrderedByAuthor().get(1).getAuthor());
		assertEquals("Carol E. Meacham", libName.getOrderedByAuthor().get(2).getAuthor());
		assertEquals("Cheryl Jones", libName.getOrderedByAuthor().get(3).getAuthor());

		// testing if the tie can be broken by title's order:
		Book book3 = new Book(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound");
		Book book1 = new Book(9781843190028L, "Moyra Caldecott", "Crystal Legends");
		Book book2 = new Book(9781843190011L, "Moyra Caldecott", "The Eye of Callanish");

		int i = libName.getOrderedByAuthor().indexOf(book1);
		int j = libName.getOrderedByAuthor().indexOf(book2);
		int k = libName.getOrderedByAuthor().indexOf(book3);
		assertTrue(i < j);
		assertTrue(i < k);
		assertTrue(j < k);
	}

	@Test
	public void testSortByDueDate() {
		
		// using name String type to test the overdue list of book:
		checkoutByName();
		ArrayList<LibraryBookGeneric<String>> libNameCopy = libName.getOverdueList(11, 10, 2018);
		assertEquals(8, libNameCopy.size());

		// using phoneNumber type to test the overdue list of book:
		checkoutByPhone();
		ArrayList<LibraryBookGeneric<PhoneNumber>> libPhoneCopy = libPhone.getOverdueList(11, 10, 2018);
		assertEquals(5, libPhoneCopy.size());
		
		//testing the order by due date:
		assertEquals(9781843190004L, libNameCopy.get(0).getIsbn());
		assertEquals(9781843190011L, libNameCopy.get(1).getIsbn());
		assertEquals(9781843192039L, libNameCopy.get(2).getIsbn());
		assertEquals(9781843192701L, libNameCopy.get(3).getIsbn());

	}
}
