package assignment05;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;
import java.util.StringJoiner;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

	/**** Member variable *****/
	private Node root;
	private int size;

	public Node getRoot() {
		return root;
	}
	/***** Inner Node class ***/
	public class Node {
		private T item;
		private Node left;
		private Node right;

		public Node() {
			this.left = null;
			this.right = null;
			this.item = null;
		}

		public Node(T item) {
			this.left = null;
			this.right = null;
			this.item = item;
		}

		public Node(int parseInt) {
			// TODO Auto-generated constructor stub
		}

		public T getItem() {
			return item;
		}
		
		//post-order traversal:
		public void postOrder() {
			if(this == null)
				return;
			this.left.postOrder();
			this.right.postOrder();
			System.out.println(this.item);
		}
	}

	/**
	 * a helper method for Contains**
	 * 
	 * @param node - takes in the root of a tree or subtree during recursive call
	 * @param item - the value of that node to be searched
	 * @return
	 */
	private boolean binarySearch(Node node, T item) {

		// Two base cases:
		if (node == null)
			return false;

		if (item.equals(node.item))
			return true;

		if (item.compareTo(node.item) < 0)
			return binarySearch(node.left, item);
		else
			return binarySearch(node.right, item);
	}

	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         the input item was actually inserted); otherwise, returns false
	 * @throws NullPointerException if the item is null
	 */
	@Override
	public boolean add(T item) throws NullPointerException {
		if (item == null)
			throw new NullPointerException();

		// return false if item exist in the tree:
		if (binarySearch(root, item))
			return false;

		root = add(root, item);
		size ++;
		return true;
	}

	/**
	 * a helper function for add(T item) recursively
	 * 
	 * @param node - the root of the tree or subtree during recursive calls
	 * @param item - items to be added
	 * @return node -- return the value of root, after updating it inside the add
	 *         function
	 */
	private Node add(Node node, T item) {
		Node newNode = new Node(item);

		if (node == null) {
			node = newNode;
			return node;
		}

		if (item.compareTo(node.item) < 0) {
			if (node.left == null)
				node.left = newNode;
			else
				add(node.left, item);

		} else {
			if (node.right == null)
				node.right = newNode;
			else
				add(node.right, item);
		}
		return node;
	}

	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items - the collection of items whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         any item in the input collection was actually inserted); otherwise,
	 *         returns false
	 * @throws NullPointerException if any of the items is null
	 */
	@Override
	public boolean addAll(Collection<? extends T> items) throws NullPointerException {
		if (items == null)
			throw new NullPointerException();

		boolean hasChanged = false;
		for (T item : items) {
			if (this.add(item)) {
				
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
		root = null;
		size = 0;
	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input item;
	 *         otherwise, returns false
	 * @throws NullPointerException if the item is null
	 */
	@Override
	public boolean contains(T item) throws NullPointerException {
		if (item == null)
			throw new NullPointerException();
		return binarySearch(root, item);
	}

	/**
	 * Determines if for each item in the specified collection, there is an item in
	 * this set that is equal to it.
	 * 
	 * @param items - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an item
	 *         in this set that is equal to it; otherwise, returns false
	 * @throws NullPointerException if any of the items is null
	 */
	@Override
	public boolean containsAll(Collection<? extends T> items) throws NullPointerException {
		if (items == null)
			throw new NullPointerException();

		for (T item : items) {
			if (!this.contains(item))
				return false;
		}
		return true;
	}

	/**
	 * Returns the first (i.e., smallest) item in this set.
	 * 
	 * @throws NoSuchElementException if the set is empty
	 */
	@Override
	public T first() throws NoSuchElementException {
		if (this.isEmpty())
			throw new NoSuchElementException();

		Node temp = new Node();
		temp = this.root;

		while (temp.left != null) {
			temp = temp.left;
		}
		return temp.item;
	}

	/**
	 * @return true if this set contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Returns the last (i.e., largest) item in this set.
	 * 
	 * @throws NoSuchElementException if the set is empty
	 */
	@Override
	public T last() throws NoSuchElementException {
		if (this.isEmpty())
			throw new NoSuchElementException();

		Node temp = new Node();
		temp = this.root;

		while (temp.right != null) {
			temp = temp.right;
		}
		return temp.item;
	}

	/** a helper method that returns the parent of the node to be deleted **/
	public Node searchParent(T item) {
		return searchParent(root, item);
	}

	/**
	 * same functionality as searching a node, but instead of returning the node,
	 * here it returns the parent of that node to be searched
	 * 
	 * @param node - root of the tree or the subtree during recursive calls
	 * @param item - the item which we want to know its parent
	 * @return
	 */
	private Node searchParent(Node node, T item) {

		if (node.left != null && item.equals(node.left.item) || node.right != null && item.equals(node.right.item))
			return node;

		if (item.compareTo(node.item) < 0)
			return searchParent(node.left, item);
		else
			return searchParent(node.right, item);
	}

	/**
	 * Ensures that this set does not contain the specified item.
	 * 
	 * @param item - the item whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         the input item was actually removed); otherwise, returns false
	 * @throws NullPointerException if the item is null
	 */
	@Override
	public boolean remove(T item) throws NullPointerException {
		if (item == null)
			throw new NullPointerException();

		// return false if this element to be removed doesnt exist
		if (!this.contains(item))
			return false;

		root = remove(root, item);
		size--;
		return true;
	}

	/**
	 * a helper function that deletes the item**
	 * 
	 * @param node - root of the tree or the subtree during recursive calls
	 * @param item - the item which we want to know its parent
	 * @return
	 */
	private Node remove(Node node, T item) {

		if (item.compareTo(node.item) < 0)
			remove(node.left, item);

		else if (item.compareTo(node.item) > 0)
			remove(node.right, item);

		// here we found the node we want to delete:
		else {

			// case1: that node is a leaf:
			if (node.left == null && node.right == null) {
				if(node.equals(this.root)) {
					//there is no parent:
//					this.size = 0;
					return null;
					
				}else {
					// finding the parent of the node to be deleted:
					Node parent = searchParent(item);
					if (parent.left == node)
						parent.left = null;
					else
						parent.right = null;
				}
				return node;
			}

			// case2: that node has one child:
			if (node.left == null && node.right != null || node.right == null && node.left != null) {
				if(node.equals(this.root)) {
					//there is not parent:
					if(node.left != null) {
						//!!! remember to store the orignal to a temp Node
						Node temp = node.left;
						node.item = node.left.item;
						node.left = temp.left;
						node.right = temp.right;
						
					}
					else if(node.right != null) {
						Node temp = node.right;
						node.item = node.right.item;
						node.left = temp.left;
						node.right = temp.right;						
					}
						
				}else {
					// finding the parent of the node to be deleted:
					Node parent = searchParent(item);

					if (parent.left == node) {
						if (node.left != null)
							parent.left = parent.left.left;
						else
							parent.left = parent.left.right;

					} else {
						if (node.left != null)
							parent.right = parent.right.left;
						else
							parent.right = parent.right.right;
					}
				}
				
				return node;
			
			}

			// case 3: that node has two children:
			if (node.left != null && node.right != null) {

				// step 1: search the successor from the right subtree
				Node successor = node.right;
				Node successorParent = node;

				while (successor.left != null) {
					successorParent = successor;
					successor = successor.left;
				}

				// step 2: now we have the correct, smallest successor
				// replace the node with successor
				node.item = successor.item;

				// step 3: delete the successor:
				// case 1 deleting successor:
				if (successor.right == null) {
					if (successorParent.left == successor)
						successorParent.left = null;
					else
						successorParent.right = null;
				}

				// case 2 deleting successor:
				if (successor.right != null) {
					if (successorParent.left == successor)
						successorParent.left = successorParent.left.right;

					else if (successorParent.right == successor)
						successorParent.right = successorParent.right.right;
				}
				return node;
			}
		}
		return node;
		
	}

	/**
	 * Ensures that this set does not contain any of the items in the specified
	 * collection.
	 * 
	 * @param items - the collection of items whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         any item in the input collection was actually removed); otherwise,
	 *         returns false
	 * @throws NullPointerException if any of the items is null
	 */
	@Override
	public boolean removeAll(Collection<? extends T> items) throws NullPointerException {
		if (items == null)
			throw new NullPointerException();

		boolean hasChanged = false;
		for (T item : items) {
			if (remove(item)) {
				hasChanged = true;
				
			}
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

	/**
	 * Returns an ArrayList containing all of the items in this set, in sorted
	 * order.
	 */
	@Override
	public ArrayList<T> toArrayList() {

		ArrayList<T> output = new ArrayList<T>();

		toArrayList(output, root);

		for (T item : output) {
			System.out.println("output " + item);
		}
		return output;
	}

	private void toArrayList(ArrayList<T> output, Node node) {
		// in-order traversal base case:
		if (node == null)
			return;
		
		toArrayList(output, node.left);
		output.add(node.item);
		toArrayList(output, node.right);
	}

	/** Driver method **/
	// Generates a .dot file representing this tree.
	// Use each node's hashCode to uniquely identify it
	public void writeDot(String filename) {
		try {
			PrintWriter output = new PrintWriter(new FileWriter(filename));
			output.println("graph g {");
			if (root != null)
				output.println(root.hashCode() + "[label=\"" + root.item + "\"]");
			writeDotRecursive(root, output);
			output.println("}");
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Recursively traverse the tree, outputting each node to the .dot file
	private void writeDotRecursive(Node n, PrintWriter output) throws Exception {
		if (n == null)
			return;
		if (n.left != null) {
			output.println(n.left.hashCode() + "[label=\"" + n.left.item + "\"]");
			output.println(n.hashCode() + " -- " + n.left.hashCode());
		}
		if (n.right != null) {
			output.println(n.right.hashCode() + "[label=\"" + n.right.item + "\"]");
			output.println(n.hashCode() + " -- " + n.right.hashCode());
		}

		writeDotRecursive(n.left, output);
		writeDotRecursive(n.right, output);
	}
	
	 public List<Integer> inorderTraversal(Node root){
	        Stack<Node> stack = new Stack<Node>();
	        List<Integer> output = new ArrayList<Integer>();
	        
	        if(root == null)
	            return output;
	      Node curr = root;
	      
	      while(curr != null || !stack.isEmpty()) {
	    	  
	    	  while(curr != null) {
	    		  stack.push(curr);
	    		  curr = curr.left;
	    	  }
	    	  curr = stack.pop();
	    	  output.add((int)curr.item);
	    	  curr = curr.right;
	      }
	        
	        return output;    
	    }
	 
	 public int minDepth(Node root) {
	        //firstly recursive, then iterative:
	        if(root == null)
	            return 0;
//	        if(root.right == null && root.left == null)
//	            return 1;
	        
	        int min = 99999;
	        
	        if(root.left != null) {
	        	min = Math.min(min, minDepth(root.left));
	        }
	        
	        if(root.right != null) {
	        	min = Math.min(min, minDepth(root.right));
	        }
	        
	        return min + 1;
	        
	    }
	 
	
	 public String serialize(Node root) {
	        StringBuilder sb = new StringBuilder();
	        Queue<Node> queue = new LinkedList<Node>();
	        queue.add(root);
	       
	        while(!queue.isEmpty()){
	            Node curr = queue.poll();
	            if(curr == null){
	                sb.append("NULL,");
	            }else{
	                sb.append(curr.item).append(",");
	                queue.offer(curr.left);
	                queue.offer(curr.right);
	            }
	                
	        }
	        System.out.println(sb.toString());
	        return sb.toString();
	    }
	 
	 public Node deserialize(String data) {
	        String[] list = data.split(" ");
	        System.out.println(list.length);
	        return deserialize(0,list);
	    }
	    
	    public Node deserialize(int i, String[] list){
	        if(list[i].equals("n") || i == list.length)
	            return null;
	        else{  
	        	Node root = new Node(Integer.parseInt(list[i]));
	            root.left = deserialize(++i, list);
	            System.out.println("i " + i);
	            root.right = deserialize(++i, list); 
	            return root;
	        }    
	    }
	    
	    
	           
	    }


	 
	 

