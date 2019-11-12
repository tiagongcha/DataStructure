package lab05;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {
	private E[] array;
	private int size;
	private Comparator<? super E> comp;

	/**
	 * A void (no arguments) constructor, which creates an empty sorted set sorted
	 * according to the natural ordering of its elements.
	 */
	public BinarySearchSet() {
		this.array = (E[]) new Object[10];
		this.size = 0;
		this.comp = null;
	}

	/**
	 * A constructor with a single argument of type Comparator, which creates an
	 * empty sorted set sorted according to the specified comparator.
	 */
	public BinarySearchSet(Comparator<? super E> comparator) {
		this.comp = comparator;
		array = (E[]) new Object[10];
		size = 0;
	}

	// rmb to change the insert function as well:
	private int binarySearch(E[] array, Object target) {
		int low = 0;
		int high = size - 1;
		int mid;

		while (low <= high) {
			mid = low + (high - low) / 2;
			// change to compare from comparator interface:
			if (compare(array[mid], (E) target) < 0)
				low = mid + 1;
			else if (compare(array[mid], (E) target) > 0)
				high = mid - 1;
			else
				return mid;
		}
		// low gives us the right pos to insert:
		return -low - 1;
	}

	/**
	 * a helper function compare for comparing two generic elements in the
	 * collection:
	 * 
	 * @param e1 - two elements to compare
	 * @param e2 - two elements to compare
	 * @return an integer if e1 > e2 return value is positive, equal returns 0, and
	 *         e1 < e2 return negative values
	 */
	private int compare(E e1, E e2) {
		if (comp == null) {
//			System.out.println("compare " + ((Comparable<? super E>) e1).compareTo(e2));
			return ((Comparable<? super E>) e1).compareTo(e2);
		}
		return comp.compare(e1, e2);
	}

	/**
	 * @return The comparator used to order the elements in this set, or null if
	 *         this set uses the natural ordering of its elements (i.e., uses
	 *         Comparable).
	 */
	@Override
	public Comparator<? super E> comparator() {
		return comp;
	}

	/**
	 * @return the first (lowest, smallest) element currently in this set
	 * @throws NoSuchElementException if the set is empty
	 */
	@Override
	public E first() {
		if (size == 0)
			throw new NoSuchElementException();

		return array[0];
	}

	/**
	 * @return the last (highest, largest) element currently in this set
	 * @throws NoSuchElementException if the set is empty
	 */
	@Override
	public E last() {
		if (size == 0)
			throw new NoSuchElementException();
		return array[size - 1];
	}

	/**
	 * Adds the specified element to this set if it is not already present and not
	 * set to null.
	 * 
	 * @param o element to be added to this set
	 * @return true if this set did not already contain the specified element
	 */
	@Override
	public boolean add(E element) {
		int index = binarySearch(array, element);

		if (index >= 0)
			return false;

		else {

			if (size == array.length) {
				grow();
			}

			int insertPos = (index + 1) * -1;
//			System.out.println(insertPos);
			for (int i = size - 1; i >= insertPos; i--) {
				array[i + 1] = array[i];
			}
			array[insertPos] = element;
			size++;
		}
		return true;

//		if (binarySearch(array, element) > -1) {
//			return false;
//		}
//		if (size == array.length) {
//			grow();
//		}
//
//		if (size == 0) {
//			array[size++] = element;
//		} else {
//			int i;
//			// could also make use of binary search return corrent index to insert:
//			for (i = size - 1; i >= 0 && compare(array[i], element) > 0; i--) {
//				array[i + 1] = array[i];
//			}
//
//			array[i + 1] = element;
//			size++;
//		}
//		return true;

	}

	/**
	 * helper function for growing collection when we reach the full compacity:
	 */
	private void grow() {

		E[] newArr = (E[]) new Object[size * 2];
		for (int i = 0; i < size; i++) {
			newArr[i] = this.array[i];
		}
		this.array = newArr;
	}

	/**
	 * Adds all of the elements in the specified collection to this set if they are
	 * not already present and not set to null.
	 * 
	 * @param c collection containing elements to be added to this set
	 * @return true if this set changed as a result of the call
	 */
	@Override
	public boolean addAll(Collection<? extends E> elements) {
		boolean hasChanged = false;

		for (E e : elements) {
			if (this.add(e)) {
				hasChanged = true;
			}
		}
		return hasChanged;
	}

	/**
	 * Removes all of the elements from this set. The set will be empty after this
	 * call returns.
	 */
	@Override
	public void clear() {
		this.size = 0;
	}

	/**
	 * @param o element whose presence in this set is to be tested
	 * @return true if this set contains the specified element
	 */
	@Override
	public boolean contains(Object element) {

		int i = binarySearch(array, element);
		return (i > -1);

	}

	/**
	 * @param c collection to be checked for containment in this set
	 * @return true if this set contains all of the elements of the specified
	 *         collection
	 */
	@Override
	public boolean containsAll(Collection<?> elements) {

		for (Object e : elements) {
			if (!this.contains(e))
				return false;
		}
		return true;
	}

	/**
	 * @return true if this set contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * @return an iterator over the elements in this set, where the elements are
	 *         returned in sorted (ascending) order
	 */
	@Override
	public Iterator<E> iterator() {
		return new MyIterator();
	}

	/**
	 * Removes the specified element from this set if it is present.
	 * 
	 * @param o object to be removed from this set, if present
	 * @return true if this set contained the specified element
	 */
	@Override
	public boolean remove(Object element) {
		int index = binarySearch(array, element);

		if (index > -1) {
			for (int i = index; i < size - 1; i++) {
				array[i] = array[i + 1];
			}

			array[size - 1] = null;
			size--;

			return true;
		}

		else
			return false;
	}

	/**
	 * Removes from this set all of its elements that are contained in the specified
	 * collection.
	 * 
	 * @param c collection containing elements to be removed from this set
	 * @return true if this set changed as a result of the call
	 */
	@Override
	public boolean removeAll(Collection<?> elements) {
		boolean hasChanged = false;

		for (Object e : elements) {
			if (this.contains(e)) {
				this.remove(e);
				hasChanged = true;
			}
		}

		return hasChanged;
	}

	/**
	 * @return the number of elements in this set
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return an array containing all of the elements in this set, in sorted
	 *         (ascending) order.
	 */
	@Override
	public Object[] toArray() {
		return array;
	}

	/**
	 * An inner iterator class
	 */
	private class MyIterator<E> implements Iterator<E> {

		private int iterPos;
		private int lastSeen = -1;

		/**
		 * Returns true if there is another item left in the iterator, returns false
		 * otherwise
		 */
		@Override
		public boolean hasNext() {
			return iterPos < size;
		}

		/**
		 * return the last seen element in the collection
		 * 
		 */
		@Override
		public E next() throws NoSuchElementException {
			if (iterPos >= size)
				throw new NoSuchElementException();
			else {
				lastSeen = iterPos;
				return (E) array[iterPos++];
			}

		}

		@Override
		public void remove() throws IllegalStateException {
			if (lastSeen < 0)
				throw new IllegalStateException();
			else {
				BinarySearchSet.this.remove(array[lastSeen]);
				iterPos = lastSeen;
				lastSeen = -1;
			}
		}
		

	}

}
