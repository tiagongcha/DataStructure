package assignment08;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Represents a priority queue of generically-typed items. The queue is
 * implemented as a min heap. The min heap is implemented implicitly as an
 * array.
 * 
 * @author
 */
@SuppressWarnings("unchecked")
public class PriorityQueue<AnyType> {

	private int currentSize;

	private AnyType[] array;

	private Comparator<? super AnyType> cmp;

	private int capacity;

	/**
	 * Constructs an empty priority queue. Orders elements according to their
	 * natural ordering (i.e., AnyType is expected to be Comparable) AnyType is not
	 * forced to be Comparable.
	 */

	public PriorityQueue() {
		currentSize = 0;
		cmp = null;
		capacity = 10;
		array = (AnyType[]) new Object[capacity]; // safe to ignore warning
	}

	/**
	 * Construct an empty priority queue with a specified comparator. Orders
	 * elements according to the input Comparator (i.e., AnyType need not be
	 * Comparable).
	 */
	public PriorityQueue(Comparator<? super AnyType> c) {
		currentSize = 0;
		cmp = c;
		array = (AnyType[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * @return the number of items in this priority queue.
	 */
	public int size() {
		return currentSize;
	}

	/**
	 * Makes this priority queue empty.
	 */
	public void clear() {
		currentSize = 0;
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 *                                (Runs in constant time.)
	 */
	public AnyType findMin() throws NoSuchElementException {
		if (currentSize == 0)
			throw new NoSuchElementException();

		// the smallest item in priority queue is the root of the binary heap
		return this.array[0];
	}

	/**
	 * Removes and returns the minimum item in this priority queue.
	 * 
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 *                                (Runs in logarithmic time.)
	 */
	public AnyType deleteMin() throws NoSuchElementException {

		if (currentSize == 0)
			throw new NoSuchElementException();

		if (currentSize == 1) {
			currentSize = 0;
			return array[0];
		}

		// store the minimum item so that it may be returned at the end
		AnyType min = array[0];
		// replace the item at minIndex with the last item in the tree
		array[0] = array[currentSize - 1];
		// update size
		array[currentSize - 1] = null;
		currentSize--;

		if (currentSize == 1) {
			return min;
		}

		if (currentSize == 2) {
			if (compare(array[0], array[1]) < 0)
				return min;
			else {
				AnyType temp = array[0];
				array[0] = array[1];
				array[1] = temp;
				return min;
			}

		}

		else {
			percolateDown();
			return min;
		}
	}

	private void percolateDown() {
		// swap with the smaller of two children until smaller than both of the children
		int parent = 0;
		int child = 0;
		if (compare(array[1], array[2]) <= 0)
			child = 1;
		else
			child = 2;

		// the terminate condition for percDown is when it reaches the leaf() node,
		// so alternatively, the while() condition could be !isLeaf() && comparison
		while ((parent * 2 + 2) < currentSize && compare(array[parent], array[parent * 2 + 1]) > 0
				|| compare(array[parent], array[parent * 2 + 2]) > 0) {
			// swap with smaller child:
			AnyType temp = array[parent];
			array[parent] = array[child];
			array[child] = temp;

			parent = child;

			if ((parent * 2 + 2) >= currentSize)
				break;

			// find which child is smaller:
			if (array[parent * 2 + 1] != null && array[parent * 2 + 2] != null
					&& compare(array[parent * 2 + 1], array[parent * 2 + 2]) <= 0)
				child = parent * 2 + 1;
			else
				child = parent * 2 + 2;
		}
	}

	/**
	 * Adds an item to this priority queue.
	 * 
	 * (Runs in logarithmic time.) Can sometimes terminate early.
	 * 
	 * @param item - the item to be inserted
	 */
	public void add(AnyType item) {
		if (currentSize == 0) {
			array[0] = item;
			currentSize++;
			return;
		}

		// if the array is full, double its capacity
		if (currentSize == capacity) {
			capacity = 2 * capacity;
			AnyType[] temp = this.array;
			this.array = (AnyType[]) new Object[capacity];

			for (int i = 0; i < currentSize; i++) {
				array[i] = temp[i];
			}
		}

		// add the new item to the next available node in the tree, so that
		// complete tree structure is maintained
		array[currentSize] = item;

		// update size
		currentSize++;
		// percolate the new item up the levels of the tree until heap order is restored
		// It is STRONGLY recommended that you write a percolateUp helper method!
		percolateUp();
	}

	private void percolateUp() {
		// the index of item to be per is currentSize
		int child = currentSize - 1;
		int parent = (child - 1) / 2;

		while (child >= 0 && parent >= 0 && compare(array[parent], array[child]) > 0) {
			// swap with its parents
			AnyType temp = array[child];
			array[child] = array[parent];
			array[parent] = temp;
			child = parent;
			parent = (child - 1) / 2;
		}
	}

	/**
	 * Generates a string for visualizing the binary heap.
	 * 
	 * @return DOT format string to enter at http://www.webgraphviz.com
	 */
	public String generateDot() {
		String result = "digraph Heap {\n\tnode [shape=record]\n";

		for (int i = 0; i < currentSize; i++) {
			result += "\tnode" + i + " [label = \"<f0> |<f1> " + array[i] + "|<f2> \"]\n";
			if (((i * 2) + 1) < currentSize)
				result += "\tnode" + i + ":f0 -> node" + ((i * 2) + 1) + ":f1\n";
			if (((i * 2) + 2) < currentSize)
				result += "\tnode" + i + ":f2 -> node" + ((i * 2) + 2) + ":f1\n";
		}
		return result + "}\n";
	}

	/**
	 * Internal method for comparing lhs and rhs using Comparator if provided by the
	 * user at construction time, or Comparable, if no Comparator was provided.
	 * 
	 * @param lhs - the left-hand-side item being compared
	 * @param rhs - the right-hand-side item being compared
	 * @return a negative integer if lhs < rhs, 0 if lhs == rhs, a positive integer
	 *         if lhs > rhs
	 */
	private int compare(AnyType lhs, AnyType rhs) {
		if (cmp == null) {
			return ((Comparable<? super AnyType>) lhs).compareTo(rhs); // safe to ignore warning
		}
		// We won't test your code on non-Comparable types if we didn't supply a
		// Comparator

		return cmp.compare(lhs, rhs);
	}

	// LEAVE IN for grading purposes
	public Object[] toArray() {
		Object[] ret = new Object[currentSize];
		for (int i = 0; i < currentSize; i++) {
			ret[i] = array[i];
		}
		return ret;
	}
}
