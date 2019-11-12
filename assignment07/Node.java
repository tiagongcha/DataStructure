package assignment07;

import java.util.ArrayList;
import java.util.List;

/**A generic node class representing the vertices in the graph**
 * 
 * @author gongtia
 *
 * @param <E>
 */
public class Node<E> {
	E data;
	//each node stores a list of neighboring nodes
	ArrayList<Node> neighbors;
	//marks whether the node has been visited during BFS
	boolean visited;
	//store the node that leads to the current node in pathFinding
	Node cameFrom;
	
	/**Constructor**
	 * construct a node with its data, the neighbors list will be empty until we add
	 * in neighboring nodes to this node
	 * @param data
	 */
	public Node(E data){
		this.data = data;
		this.neighbors = new ArrayList<Node>();
		this.visited = false;
		this.cameFrom = null;
	}
	
	/**get neighbor list method**
	 * 
	 * @return neighboring node list in this node
	 */
	public ArrayList<Node> getNeighbors(){
		return neighbors;
	}
}
