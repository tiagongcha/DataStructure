package assignment02;
import java.util.GregorianCalendar;

public class LibraryBook extends Book{
	private String holder;
	private  GregorianCalendar duedate;
	
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
	}
	
	public String getHolder() {
		return holder;
	}
	
	public GregorianCalendar getDueDate() {
		return duedate;
	}
	
//	methods for checking a book in and out:
	public void checkIn() {
		holder = null;
		duedate = null;
	}
	
	public void checkOut(String holderName, GregorianCalendar duedate) {
		this.holder = holderName;
		this.duedate = duedate;
	}
	
	
}
