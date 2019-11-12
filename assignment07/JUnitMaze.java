package assignment07;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JUnitMaze {

	@Test
	void testBigMaze() throws IOException {
		PathFinder.solveMaze("mazes/bigMaze.txt", "mazes/mybigMaze.txt");
		BufferedReader readIn = new BufferedReader(new FileReader("mazes/bigMazeSol.txt"));
		BufferedReader readOut = new BufferedReader(new FileReader("mazes/mybigMaze.txt"));
		
		String line;
		while((line = readIn.readLine()) != null) {
			assertEquals(line, readOut.readLine());
		}
		
		readIn.close();
		readOut.close();
	}
	
	@Test
	void testClassicMaze() throws IOException {
		PathFinder.solveMaze("mazes/classic.txt", "mazes/myclassic.txt");
		BufferedReader readIn = new BufferedReader(new FileReader("mazes/classicSol.txt"));
		BufferedReader readOut = new BufferedReader(new FileReader("mazes/myclassic.txt"));
		
		String line;
		while((line = readIn.readLine()) != null) {
			assertEquals(line, readOut.readLine());
		}
		
		readIn.close();
		readOut.close();
	}
	@Test
	void testDemoMaze() throws IOException{
		PathFinder.solveMaze("mazes/demoMaze.txt", "mazes/mydemoMaze.txt");
//		BufferedReader readIn = new BufferedReader(new FileReader("mazes/classicSol.txt"));
//		BufferedReader readOut = new BufferedReader(new FileReader("mazes/myclassic.txt"));
//		
//		String line;
//		while((line = readIn.readLine()) != null) {
//			assertEquals(line, readOut.readLine());
//		}
//		
//		readIn.close();
//		readOut.close();
	}
	
	@Test
	void testMediumMaze() throws IOException {
		PathFinder.solveMaze("mazes/mediumMaze.txt", "mazes/mymediumMaze.txt");
		BufferedReader readIn = new BufferedReader(new FileReader("mazes/mediumMazeSol.txt"));
		BufferedReader readOut = new BufferedReader(new FileReader("mazes/mymediumMaze.txt"));
		
		String line;
		while((line = readIn.readLine()) != null) {
			assertEquals(line, readOut.readLine());
		}
		
		readIn.close();
		readOut.close();
	}
	//test randomMaze
	
	@Test
	void testStraightMaze() throws IOException {
		PathFinder.solveMaze("mazes/straight.txt", "mazes/mystraight.txt");
		BufferedReader readIn = new BufferedReader(new FileReader("mazes/straightSol.txt"));
		BufferedReader readOut = new BufferedReader(new FileReader("mazes/mystraight.txt"));
		
		String line;
		while((line = readIn.readLine()) != null) {
			assertEquals(line, readOut.readLine());
		}
		
		readIn.close();
		readOut.close();
	}
	
	@Test
	void testtinyMaze() throws IOException {
		PathFinder.solveMaze("mazes/tinyMaze.txt", "mazes/mytinyMaze.txt");
		BufferedReader readIn = new BufferedReader(new FileReader("mazes/tinyMazeSol.txt"));
		BufferedReader readOut = new BufferedReader(new FileReader("mazes/mytinyMaze.txt"));
		
		String line;
		while((line = readIn.readLine()) != null) {
			assertEquals(line, readOut.readLine());
		}
		
		readIn.close();
		readOut.close();
	}
	
	//test tiny open:
	
	@Test
	void testTurnMaze() throws IOException {
		PathFinder.solveMaze("mazes/turn.txt", "mazes/myturn.txt");
		BufferedReader readIn = new BufferedReader(new FileReader("mazes/turnSol.txt"));
		BufferedReader readOut = new BufferedReader(new FileReader("mazes/myturn.txt"));
		
		String line;
		while((line = readIn.readLine()) != null) {
			assertEquals(line, readOut.readLine());
		}
		
		readIn.close();
		readOut.close();
	}
	
	@Test
	void testUnsovlableMaze() throws IOException {
		PathFinder.solveMaze("mazes/unsolvable.txt", "mazes/myunsolvable.txt");
		BufferedReader readIn = new BufferedReader(new FileReader("mazes/unsolvableSol.txt"));
		BufferedReader readOut = new BufferedReader(new FileReader("mazes/myunsolvable.txt"));
		
		String line;
		while((line = readIn.readLine()) != null) {
			assertEquals(line, readOut.readLine());
		}
		
		readIn.close();
		readOut.close();
	}
	
	@Test
	void testCircleMaze() throws IOException {
		PathFinder.solveMaze("mazes/circleMaze.txt", "mazes/mycircle.txt");
		BufferedReader readIn = new BufferedReader(new FileReader("mazes/circleMazeSol.txt"));
		BufferedReader readOut = new BufferedReader(new FileReader("mazes/mycircle.txt"));
		
		String line;
		while((line = readIn.readLine()) != null) {
			assertEquals(line, readOut.readLine());
		}
		
		readIn.close();
		readOut.close();
	}
	
	@Test
	void testCombinationMaze() throws IOException {
		PathFinder.solveMaze("mazes/combinationMaze.txt", "mazes/mycombination.txt");
		BufferedReader readIn = new BufferedReader(new FileReader("mazes/combinationMazeSol.txt"));
		BufferedReader readOut = new BufferedReader(new FileReader("mazes/mycombination.txt"));
		
		String line;
		while((line = readIn.readLine()) != null) {
			assertEquals(line, readOut.readLine());
		}
		
		readIn.close();
		readOut.close();
	}
		
}
