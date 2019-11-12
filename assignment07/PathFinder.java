package assignment07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PathFinder<E> {

	private static Node<Character> nodes[][];
	private static int height;
	private static int width;
	private static Node start;
	private static Node end;

	/**
	 * This method will read a maze from a file with the given input name, and
	 * output the solved maze to a file with the given output name.
	 *
	 * In side this function, the shortest pathway will be found through
	 * Breadth-first-search of a graph
	 * 
	 * @param inputFile
	 * @param outputFile
	 */
	public static void solveMaze(String inputFile, String outputFile) {
		// read in the maze:
		try {
			readIn(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// add neighbors:
		addNeighbor(height, width);

		// find the shortest path:
		// create an empty queue
		Queue<Node<Character>> queue = new LinkedList<Node<Character>>();

		// add the start node into the queue:
		queue.add(start);
		// before poping out, set its visited to true:
		start.visited = true;
		while (!queue.isEmpty()) {
			// pop the first element in the queue
			Node<Character> curr = queue.poll();
			// we have reached the goal:
			if (curr.data == 'G') {
				break;
			}
			// add all the neighboring nodes to the queue:
			for (Node<Character> node : curr.neighbors) {
				// prevent revisiting a node, which might result in infinite loop:
				if (!node.visited) {
					node.visited = true;
					node.cameFrom = curr;
					queue.add(node);
				}
			}
		}

		// mark the path of shortest path, set each node in the shortest path's data to '.'
		//for later visualizing
		Node<Character> temp = end.cameFrom;
		while (temp != null && temp != start) {
			temp.data = '.';
			temp = temp.cameFrom;
		}

		// write to the file:
		PrintWriter output;
		try {
			output = new PrintWriter(new FileWriter(outputFile));
			output.println(height + " " + width);

			for (int i = 0; i < height; i++) {

				for (int j = 0; j < width; j++) {
					if (nodes[i][j] == null) {
						output.print('X');
					} else {
						output.print(nodes[i][j].data);
					}
				}

				output.print("\n");
			}

			output.flush();
			output.close();
		} catch (IOException e) {
			System.out.println("unable to write out");
		}

	}

	/** helper method for read in the input maze, and store the input file as a 
	 * graph implemented as a 2D nodes[][] array
	 * 
	 * @param inputFile
	 * @throws IOException
	 */
	private static void readIn(String inputFile) throws IOException {
		BufferedReader readIn = new BufferedReader(new FileReader(inputFile));

		// read in the height and width of the maze:
		String[] dimensions = readIn.readLine().split(" ");
		height = Integer.parseInt(dimensions[0]);
		width = Integer.parseInt(dimensions[1]);

		// create 2D array to represent what we read in:
		nodes = new Node[height][width];

		// read in the file:
		String line;
		int row = 0;
		while ((line = readIn.readLine()) != null) {
			for (int col = 0; col < line.length(); col++) {
				char curr = line.charAt(col);

				if (curr == 'X')
					nodes[row][col] = null;
				if (curr == ' ')
					nodes[row][col] = new Node<Character>(' ');
				if (curr == 'S') {
					nodes[row][col] = new Node<Character>('S');
					//mark the start node here
					start = nodes[row][col];
				}
				if (curr == 'G') {
					nodes[row][col] = new Node<Character>('G');
					//mark the end node here
					end = nodes[row][col];
				}
			}
			row++;
		}
	}

	/**helper method for adding neighboring nodes for each node
	 * this is just one way of implementing the graph
	 * @param height
	 * @param width
	 */
	private static void addNeighbor(int height, int width) {
		// add neighbors:
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				//'X' in the array technically is not part of the graph data
				//so here we are only taking 'S''G'' ' nodes in the array into 
				//the eventual graph data structure
				if (nodes[i][j] != null) {
					// add up && making sure we are not accessing data out of bound:
					if ((i - 1) >= 0 && nodes[i - 1][j] != null) {
						nodes[i][j].getNeighbors().add(nodes[i - 1][j]);
					}

					// add down:
					if (((i + 1) <= height - 1) && nodes[i + 1][j] != null) {
						nodes[i][j].getNeighbors().add(nodes[i + 1][j]);
					}

					// add left:
					if (((j - 1) >= 0) && nodes[i][j - 1] != null) {
						nodes[i][j].getNeighbors().add(nodes[i][j - 1]);
					}

					// add right:
					if (((j + 1) <= width - 1) && nodes[i][j + 1] != null) {
						nodes[i][j].getNeighbors().add(nodes[i][j + 1]);
					}
				}
			}
		}
	}

}
