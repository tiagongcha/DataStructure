package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Comparator;

/**
 * Class representation of a library (a collection of library books).
 * 
 */
public class LibraryGeneric<Type> {

	private ArrayList<LibraryBookGeneric<Type>> library;

	public LibraryGeneric() {
		library = new ArrayList<LibraryBookGeneric<Type>>();
	}

	/**
	 * Add the specified book to the library, assume no duplicates.
	 * 
	 * @param isbn   -- ISBN of the book to be added
	 * @param author -- author of the book to be added
	 * @param title  -- title of the book to be added
	 */
	public void add(long isbn, String author, String title) {
		library.add(new LibraryBookGeneric<Type>(isbn, author, title));
	}

	/**
	 * Add the list of library books to the library, assume no duplicates.
	 * 
	 * @param list -- list of library books to be added
	 */
	public void addAll(ArrayList<LibraryBookGeneric<Type>> list) {
		library.addAll(list);
	}

	/**
	 * Add books specified by the input file. One book per line with ISBN, author,
	 * and title separated by tabs.
	 * 
	 * If file does not exist or format is violated, do nothing.
	 * 
	 * @param filename
	 */
	public void addAll(String filename) {
		ArrayList<LibraryBookGeneric<Type>> toBeAdded = new ArrayList<>();

		try (Scanner fileIn = new Scanner(new File(filename))) {

			int lineNum = 1;

			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();

				try (Scanner lineIn = new Scanner(line)) {
					lineIn.useDelimiter("\\t");

					if (!lineIn.hasNextLong()) {
						throw new ParseException("ISBN", lineNum);
					}
					long isbn = lineIn.nextLong();

					if (!lineIn.hasNext()) {
						throw new ParseException("Author", lineNum);
					}
					String author = lineIn.next();

					if (!lineIn.hasNext()) {
						throw new ParseException("Title", lineNum);
					}
					String title = lineIn.next();
					toBeAdded.add(new LibraryBookGeneric<Type>(isbn, author, title));
				}
				lineNum++;
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage() + " Nothing added to the library.");
			return;
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
					+ ". Nothing added to the library.");
			return;
		}

		library.addAll(toBeAdded);
	}

	/**
	 * Returns the holder of the library book with the specified ISBN.
	 * 
	 * If no book with the specified ISBN is in the library, returns null.
	 * 
	 * @param isbn -- ISBN of the book to be looked up
	 */
	public Type lookup(long isbn) {
		// FILL IN -- do not return null unless appropriate
		for (LibraryBookGeneric<Type> book : library) {
			if (book.getIsbn() == isbn)
				return book.getHolder();
		}
		return null;
	}

	/**
	 * Returns the list of library books checked out to the specified holder.
	 * 
	 * If the specified holder has no books checked out, returns an empty list.
	 * 
	 * @param holder -- holder whose checked out books are returned
	 */
	public ArrayList<LibraryBookGeneric<Type>> lookup(Type holder) {

		ArrayList<LibraryBookGeneric<Type>> holderList = new ArrayList<>();

		for (LibraryBookGeneric<Type> book : library) {

			if (book.getHolder() != null) {
				if (book.getHolder().equals(holder))
					holderList.add(book);
			}
		}

		return holderList;
	}

	/**
	 * Sets the holder and due date of the library book with the specified ISBN.
	 * 
	 * If no book with the specified ISBN is in the library, returns false.
	 * 
	 * If the book with the specified ISBN is already checked out, returns false.
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param isbn   -- ISBN of the library book to be checked out
	 * @param holder -- new holder of the library book
	 * @param month  -- month of the new due date of the library book
	 * @param day    -- day of the new due date of the library book
	 * @param year   -- year of the new due date of the library book
	 * 
	 */
	public boolean checkout(long isbn, Type holder, int month, int day, int year) {

		// ensure the format of date:
		if (!((month <= 12) && (day <= 31) && (year <= 2020))) {
			System.out.println("invalid date input");
		}

		for (LibraryBookGeneric<Type> book : library) {

			// look for book with specified isbn:
			if (book.getIsbn() == isbn) {

				// check if this book already checkout:
				if (book.getHolder() != null) {
					return false;
				} else {
					book.checkOut(holder, new GregorianCalendar(year, month, day));
					return true;
				}
			}

		}
		return false;
	}

	/**
	 * Unsets the holder and due date of the library book.
	 * 
	 * If no book with the specified ISBN is in the library, returns false.
	 * 
	 * If the book with the specified ISBN is already checked in, returns false.
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param isbn -- ISBN of the library book to be checked in
	 */
	public boolean checkin(long isbn) {

		for (LibraryBookGeneric<Type> book : library) {

			// look for book with isbn:
			if (book.getIsbn() == isbn) {

				// check if its checked in:
				if (book.getHolder() == null && book.getDueDate() == null) {
					return false;
				} else {
					book.checkIn();
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Unsets the holder and due date for all library books checked out be the
	 * specified holder.
	 * 
	 * If no books with the specified holder are in the library, returns false;
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param holder -- holder of the library books to be checked in
	 */
	public boolean checkin(Type holder) {

		int numBook = 0;
		for (LibraryBookGeneric<Type> book : library) {

			if (book.getHolder() != null) {
				if (book.getHolder().equals(holder)) {
					book.checkIn();
					numBook++;
				}
			}
		}
		return numBook > 0;
	}

	/**
	 * @return Returns the list of library books, sorted by ISBN (smallest ISBN
	 *         first).
	 */
	public ArrayList<LibraryBookGeneric<Type>> getInventoryList() {
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
		libraryCopy.addAll(library);

		OrderByIsbn comparator = new OrderByIsbn();

		sort(libraryCopy, comparator);

		return libraryCopy;
	}

	/**
	 * @return Returns the list of library books, sorted by author
	 */
	public ArrayList<LibraryBookGeneric<Type>> getOrderedByAuthor() {
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<>();
		libraryCopy.addAll(library);
		sort(libraryCopy, new OrderByAuthor());

		return libraryCopy;
	}

	/**
	 * Returns the list of library books whose due date is older than the input
	 * date. The list is sorted by date (oldest first).
	 *
	 * If no library books are overdue, returns an empty list.
	 * 
	 * @param month -- month of the new due date of the library book
	 * @param day   -- day of the new due date of the library book
	 * @param year  -- year of the new due date of the library book
	 * @return the sorted copy of library books (only contains overdue ones)
	 */
	public ArrayList<LibraryBookGeneric<Type>> getOverdueList(int month, int day, int year) {
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<>();

		// getting today to compare with overdue book:
		GregorianCalendar today = new GregorianCalendar(year, month, day);
		
		// every iteration checks if this book is overdue, and only add the overdue
		// ones:
		for (LibraryBookGeneric<Type> book : library) {
			
			//making sure we wont have nullpointerexception:
			if (book.getHolder() != null && book.getDueDate() != null) {
				if (book.getDueDate().compareTo(today) < 0) {
					libraryCopy.add(book);
					System.out.println("add ");
				}
			}
		}
		
		System.out.println("array is " + libraryCopy.size());
		sort(libraryCopy, new OrderByDueDate());

		return libraryCopy;
	}

	/**
	 * Performs a SELECTION SORT on the input ArrayList. 1. Find the smallest item
	 * in the list. 2. Swap the smallest item with the first item in the list. 3.
	 * Now let the list be the remaining unsorted portion (second item to Nth item)
	 * and repeat steps 1, 2, and 3.
	 */
	private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
		for (int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size(); j++)
				if (c.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			ListType temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the ISBN.
	 */
	protected class OrderByIsbn implements Comparator<LibraryBookGeneric<Type>> {

		/**
		 * Returns a negative value if lhs is smaller than rhs. Returns a positive value
		 * if lhs is larger than rhs. Returns 0 if lhs and rhs are equal.
		 */
		public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
			return (int) (lhs.getIsbn() - rhs.getIsbn());
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the author, and
	 * book title as a tie-breaker.
	 */
	protected class OrderByAuthor implements Comparator<LibraryBookGeneric<Type>> {

		@Override
		public int compare(LibraryBookGeneric<Type> o1, LibraryBookGeneric<Type> o2) {
			int nameDiff = o1.getAuthor().compareTo(o2.getAuthor());

			if (nameDiff == 0) {
				return o1.getTitle().compareTo(o2.getTitle());
			}
			return nameDiff;
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the due date.
	 *
	 */
	protected class OrderByDueDate implements Comparator<LibraryBookGeneric<Type>> {

		@Override
		public int compare(LibraryBookGeneric<Type> o1, LibraryBookGeneric<Type> o2) {

			return o1.getDueDate().compareTo(o2.getDueDate());
		}

	}

}
