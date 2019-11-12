package lab07;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
	/**
	 * Member variable: storing the reference of head and tail, and the size of the
	 * linkedlist
	 **/
	private Node<E> head, tail;
	private int size;

	/**
	 * Default constructor
	 * 
	 */
	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	/**
	 * private Node class
	 * 
	 */
	public class Node<E> {
		E val;
		Node next;
		Node prev;

		Node(E val) {
			this.val = val;
			this.next = null;
			this.prev = null;
		}

		Node() {
			this.val = null;
			this.next = null;
			this.prev = null;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new MyIterator();
	}

	/**
	 * Inserts the specified element at the beginning of the list. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public void addFirst(E element) {
		Node first = new Node(element);

		if (size == 0) {
			head = first;
			tail = first;
		} else {
			first.next = head;
			head.prev = first;
			head = first;
		}
		size++;
	}

	/**
	 * Inserts the specified element at the end of the list. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public void addLast(E o) {
		Node last = new Node(o);

		if (size == 0) {
			head = last;
			tail = last;
		} else {
			last.prev = tail;
			tail.next = last;
			tail = last;
		}
		size++;
	}

	/**
	 * Inserts the specified element at the specified position in the list. Throws
	 * IndexOutOfBoundsException if index is out of range (index < 0 || index >
	 * size()) O(N) for a doubly-linked list.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		Node node = new Node(element);

		if (size == 0) {
			head = node;
			tail = node;
		}
		if (index == 0)
			this.addFirst(element);
		else if (index == size)
			this.addLast(element);
		else {
			Node currentPointer;
			Node prevPointer;

			if (index < size / 2) {
				currentPointer = head;
				prevPointer = null;
				for (int i = 0; i < index; i++) {
					prevPointer = currentPointer;
					currentPointer = currentPointer.next;
				}

			} else {
				prevPointer = tail;
				currentPointer = null;
				for (int i = size - 1; i >= index; i--) {
					currentPointer = prevPointer;
					prevPointer = prevPointer.prev;
				}
			}
			node.next = currentPointer;
			currentPointer.prev = node;
			prevPointer.next = node;
			node.prev = prevPointer;
			size++;

		}
	}

	/**
	 * Returns the first element in the list. Throws NoSuchElementException if the
	 * list is empty. O(1) for a doubly-linked list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		return head.val;
	}

	/**
	 * Returns the last element in the list. Throws NoSuchElementException if the
	 * list is empty. O(1) for a doubly-linked list.
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		return tail.val;
	}

	/**
	 * Returns the element at the specified position in the list. Throws
	 * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
	 * size()) O(N) for a doubly-linked list.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		// creating a dummy node:
		Node dummy = new Node();

		if (index < this.size / 2) {
			dummy.next = head;
			for (int j = 0; j <= index; j++) {
				dummy = dummy.next;
			}
		} else {
			dummy.prev = tail;
			for (int j = size - 1; j >= index; j--) {
				dummy = dummy.prev;
			}
		}

		return (E) dummy.val;
	}

	/**
	 * Removes and returns the first element from the list. ThPriorityQueue.javarows
	 * NoSuchElementException if the list is empty. O(1) for a doubly-linked list.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if (this.isEmpty())
			throw new NoSuchElementException();

		Node temp = head;
		// only one element:
		if (size == 1) {
			head = null;
			tail = null;
		} else {
			// more than one element:
			head = head.next;
			head.prev = null;
		}
		size--;
		return (E) temp.val;
	}

	/**
	 * Removes and returns the last element from the list. Throws
	 * NoSuchElementException if the list is empty. O(1) for a doubly-linked list.
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if (this.isEmpty())
			throw new NoSuchElementException();

		Node temp = tail;
		// only one element:
		if (size == 1) {
			head = null;
			tail = null;
		} else {
			tail = tail.prev;
			tail.next.prev = null;
			tail.next = null;
		}
		size--;
		return (E) temp.val;
	}

	/**
	 * Removes and returns the element at the specified position in the list. Throws
	 * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
	 * size()) O(N) for a doubly-linked list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException, NoSuchElementException {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		if (this.isEmpty())
			throw new NoSuchElementException();

		if (size == 1) {
			Node temp = head;
			head = null;
			tail = null;
			size--;
			return (E) temp.val;
		}

		if (index == 0) {
			E val = this.removeFirst();
			return val;
		}

		if (index == size - 1) {
			E val = this.removeLast();
			return val;
		}

		Node currentPointer = head;
		Node prevPointer = null;

		for (int i = 0; i < index; i++) {
			prevPointer = currentPointer;
			currentPointer = currentPointer.next;
		}
		Node temp = currentPointer;
		prevPointer.next = currentPointer.next;
		currentPointer.next.prev = prevPointer;
		size--;

		return (E) temp.val;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * doubly-linked list.
	 */
	@Override
	public int indexOf(E element) {

		Node current = head;
		int index = 0;

		while (current != null) {
			if (current.val.equals(element))
				return index;
			current = current.next;
			index++;
		}

		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * doubly-linked list.
	 */
	@Override
	public int lastIndexOf(E element) {
		Node current = tail;
		int index = size - 1;

		while (current != null) {
			if (current.val.equals(element))
				return index;
			current = current.prev;
			index--;
		}
		return -1;
	}

	/**
	 * Returns the number of elements in this list. O(1) for a doubly-linked list.
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Returns true if this collection contains no elements. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	/**
	 * Removes all of the elements from this list. O(1) for a doubly-linked list.
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element). O(N) for a doubly-linked list.
	 */
	@Override
	public Object[] toArray() {

		Iterator iter = this.iterator();
		E[] array = (E[]) new Object[this.size];
		int i = 0;
		while (iter.hasNext()) {
			array[i] = (E) iter.next();
			i++;
		}
		return array;
	}

	/**
	 * an inner class for iterator:
	 * 
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
			lastSeen = iterPos;
			iterPos++;
			return (E) DoublyLinkedList.this.get(lastSeen);
		}

		/**
		 * Removes from the underlying collection the last element returned by this
		 * iterator (optional operation)
		 */
		@Override
		public void remove() throws IllegalStateException {
			if (lastSeen < 0)
				throw new IllegalStateException();
			DoublyLinkedList.this.remove(lastSeen);
			iterPos = lastSeen;
			lastSeen = -1;
		}

	}
}
