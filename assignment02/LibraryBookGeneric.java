package assignment02;
import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book{
	private Type holder;
	private  GregorianCalendar duedate;
	
	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
	}
	
	public Type getHolder() {
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
	
	public void checkOut(Type holderName, GregorianCalendar duedate) {
		this.holder = holderName;
		this.duedate = duedate;
	}
	
	
}
