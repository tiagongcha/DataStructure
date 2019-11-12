package assignment06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class ChainingHashTable implements Set<String> {

	/** member variable **/
	private LinkedList<String>[] storage;
	private int capacity;
	private int size;
	private final double loadFactor;
	private HashFunctor functor;
	private int collision;
	
	public ChainingHashTable() {
		this.loadFactor = 1.5;
	}

	/**
	 * constructor*
	 * 
	 * @param capacity --
	 * @param functor  -- the hash functor object we want to use
	 */
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {

		this.storage = (LinkedList<String>[]) new LinkedList[capacity];
		this.capacity = capacity;
		this.size = 0;
		this.loadFactor = 1.5;
		this.functor = functor;
	}

	public int getCollisions() {
		return this.collision;
	}
	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         the input item was actually inserted); otherwise, returns false
	 */
	@Override
	public boolean add(String item) throws NullPointerException {
		if (item == null)
			throw new NullPointerException();

		// firstly get the item's unique key:
		int index = functor.hash(item) % capacity;

		// prevent overflowing:
		if (index < 0)
			index += capacity;

		// check if the set has the string already:
		if (this.contains(item))
			return false;

		// if that cell in the array is empty, simply add
		if (storage[index] == null) {
			storage[index] = new LinkedList<String>();
		}else {
			collision++;
		}
		storage[index].add(item);
		size++;

		if (size / capacity > loadFactor) {
			rehash();
		}

		return true;
	}

	/** a helper method **/
	private void rehash() {
		// create a copy of old array:
		LinkedList<String>[] oldArray = this.storage;
		int oldCapacity = this.capacity;

		// create a new empty table with nextPrime() size
		this.capacity = nextPrime(2 * size());
		this.storage = (LinkedList<String>[]) new LinkedList[capacity];
		this.size = 0;

		// copy table over:
		for (int i = 0; i < oldCapacity; i++) {
//			System.out.println(i);
			LinkedList<String> temp = oldArray[i];
			if (temp != null) {
				Iterator iter = temp.iterator();
				while (iter.hasNext()) {
					this.add((String) iter.next());
				}
			}
		}
	}

	/** a helper method **/
	public int nextPrime(int n) {
		int output = -1;
		for (int i = n + 1; i < Integer.MAX_VALUE; i++) {
			if (isPrime(i)) {
				output = i;
				break;
			}

		}
		return output;
	}

	/** a helper method **/
	public boolean isPrime(int n) {
		if (n == 2)
			return true;

		for (int i = 2; i < n; i++) {
			if (n % i == 0)
				return false;
		}

		return true;
	}

	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items - the collection of items whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         any item in the input collection was actually inserted); otherwise,
	 *         returns false
	 */
	@Override
	public boolean addAll(Collection<? extends String> items) {

		boolean hasChanged = false;

		for (String s : items) {
			if (this.add(s))
				hasChanged = true;
		}
		return hasChanged;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@Override
	public void clear() {
		this.size = 0;
		//reset the underlying array to its original:
		this.storage = (LinkedList<String>[]) new LinkedList[capacity];
	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input item;
	 *         otherwise, returns false
	 */
	@Override
	public boolean contains(String item) {
		// get the index first:
		int index = functor.hash(item) % capacity;
		// prevent overflowing:
		if (index < 0)
			index += capacity;

		if (storage[index] == null)
			return false;

		return storage[index].contains(item);
	}

	/**
	 * Determines if for each item in the specified collection, there is an item in
	 * this set that is equal to it.
	 * 
	 * @param items - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an item
	 *         in this set that is equal to it; otherwise, returns false
	 */
	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for (String s : items) {
			if (!this.contains(s))
				return false;
		}
		return true;
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Ensures that this set does not contain the specified item.
	 * 
	 * @param item - the item whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         the input item was actually removed); otherwise, returns false
	 */
	@Override
	public boolean remove(String item) {
		if (!this.contains(item))
			return false;

		int index = functor.hash(item) % capacity;
		// prevent overflowing:
		if (index < 0)
			index += capacity;
		
		storage[index].remove(item);
		size--;

		return true;
	}

	/**
	 * Ensures that this set does not contain any of the items in the specified
	 * collection.
	 * 
	 * @param items - the collection of items whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         any item in the input collection was actually removed); otherwise,
	 *         returns false
	 */
	@Override
	public boolean removeAll(Collection<? extends String> items) {
		boolean hasChanged = false;

		for (String s : items) {
			if (this.remove(s))
				hasChanged = true;
		}
		return hasChanged;
	}

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {
		return size;
	}

	public ArrayList<String> readFromFile(String filename) throws FileNotFoundException {
		ArrayList<String> output = new ArrayList<String>();

		Scanner scan = new Scanner(new File(filename));
		while (scan.hasNext()) {
			output.add(scan.next());
		}
		return output;
	}

	public LinkedList<String>[] getStorage() {
		return this.storage;
	}

}
