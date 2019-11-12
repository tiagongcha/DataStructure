package assignment05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import assignment04.SortUtil;
import assignment05.BinarySearchTree;

class JUnitBST {
	
	private BinarySearchTree<Integer> tree1, tree2, tree3, tree4, tree5, tree6,tree7,tree8;
	private ArrayList<Integer> list, list1, listTest, list999;
	private BinarySearchTree<Character> charTree;
	private ArrayList<Character> charList;
	
	
	@BeforeEach
	void setUp() throws Exception {
		listTest = new ArrayList<Integer>();
		Random rand = new Random();
		for(int i = 0; i < 26; i++) {
			listTest.add(rand.nextInt(26));
		}
		
		list999 = new ArrayList<Integer>();
		
		tree1 = new BinarySearchTree<Integer>();
		tree2 = new BinarySearchTree<Integer>();
		tree3 = new BinarySearchTree<Integer>();
		tree4 = new BinarySearchTree<Integer>();
		tree5 = new BinarySearchTree<Integer>();
		tree6 = new BinarySearchTree<Integer>();
		tree7 = new BinarySearchTree<Integer>();
		tree8 = new BinarySearchTree<Integer>();
		
		tree8.add(5);
		tree8.add(8);
		tree8.add(6);
		tree8.add(9);
		tree8.writeDot("binaryTree8.dot");
		charTree = new BinarySearchTree<Character>();
		charList = new ArrayList<Character>();
		
		for(int i = 0; i < 26; i ++) {
			charList.add((char) ('a' + i));
		}
		for(int i = 0; i < 26; i ++) {
			charList.add((char) ('A' + i));
		}
		Collections.shuffle(charList);
		charTree.addAll(charList);
		
		tree2.add(2);
		tree2.add(1);
		tree2.add(4);
		tree2.add(5);
		tree2.add(6);
		tree2.add(3);
//		tree2.writeDot("binaryTree2.dot");
		
		list = SortUtil.generateBestCase(10);
		list1 = SortUtil.generateBestCase(6);
		
		tree3.add(20);
		tree3.add(9);
		tree3.add(27);
		tree3.add(5);
		tree3.add(16);
		tree3.add(2);
		tree3.add(6);
		tree3.add(11);
		tree3.add(19);
		tree3.add(10);
		tree3.add(17);
//		tree3.writeDot("binaryTree3.dot");
		
		//tree5 is single element:
		tree5.add(999);
		
		tree7.add(1);
		tree7.add(2);
		tree7.add(3);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAdd() {
		tree1.add(20);
		tree1.add(9);
		tree1.add(27);
		tree1.add(5);
		tree1.add(16);
		tree1.writeDot("binaryTree.dot");
		
		assertFalse(tree1.add(9));
		assertTrue(tree1.add(100));
		
		assertFalse(charTree.add('a'));
	}
	
	@Test
	void testAddAll() {
		assertTrue(tree2.addAll(list));
		assertFalse(tree2.addAll(list1));
	}
	
//	@Test
//	void testClear() {
//		tree2.clear();
//		assertTrue(tree2.isEmpty());
//	}
//	
//	@Test
//	void testContainAll() {
//		assertTrue(tree2.containsAll(list1));
//		assertFalse(tree2.containsAll(list));
//	}
//	
//	@Test
//	void testContain() {
//		assertTrue(tree2.contains(1));
//		assertTrue(tree2.contains(2));
//		assertTrue(tree2.contains(3));
//		assertTrue(tree2.contains(4));
//		assertTrue(tree2.contains(5));
//		assertTrue(tree2.contains(6));
//		assertFalse(tree2.contains(10));
//	}
//	
//	@Test
//	void testFirst() {
//		assertEquals(1, (int)tree2.first());
//		assertEquals(2,(int)tree3.first());
//	}
//	
//	@Test
//	void testLast() {
//		assertEquals(6, (int)tree2.last());
//		assertEquals(27,(int)tree3.last());
//	}
//	
//	@Test
//	void testRemove() {
//		tree2.remove(1);
//		assertFalse(tree2.contains(1));
//		assertEquals(2, (int)tree2.first());
//		assertEquals(5, tree2.size());
//		
//		//remove with one child:
//		tree3.remove(19);
//		tree3.remove(11);
//		tree3.remove(2);
//		assertEquals(8, tree3.size());
//		
//		//remove single element:
//		tree5.remove(999);
//		assertTrue(tree5.isEmpty());
//		
//		//try remove empty tree, shouldnt crash the program
//		tree6.remove(666);
//		
//		//remove with two children:
//		tree2.remove(4);
//		assertFalse(tree2.contains(4));
//		
//		tree3.remove(9);
//		tree3.writeDot("binaryTree3.dot");
//		
//		charTree.remove('h');
//		charTree.remove('a');
//		charTree.remove('f');
//		charTree.remove('d');
//		charTree.remove('A');
//		charTree.remove('W');
//		
//		assertFalse(charTree.contains('h'));
//		assertFalse(charTree.contains('a'));
//		assertFalse(charTree.contains('f'));
//		assertFalse(charTree.contains('d'));
//		assertFalse(charTree.contains('A'));
//		assertFalse(charTree.contains('W'));
//
//		
//	}
//	
//	@Test
//	void testToArray() {
//		List<Integer> list = new ArrayList<Integer>();
//		for(int i = 1; i <=6; i++) {
//			list.add(i);
//		}
//		assertTrue(list.equals(tree2.toArrayList()));
//		
//		assertEquals(52, charTree.toArrayList().size());
//	}
//	
//	@Test
//	void testSearchParent(){		 
//		assertEquals(2, (int)tree2.searchParent(1).getItem());
//		assertEquals(5, (int)tree3.searchParent(6).getItem());
//		assertEquals(16, (int)tree3.searchParent(19).getItem());
//		assertEquals(20, (int)tree3.searchParent(27).getItem());
//		assertEquals(19, (int)tree3.searchParent(17).getItem());	
//	}
//	
//	@Test
//	void testInOrder() {
//		tree2.inorderTraversal(tree2.getRoot());
//	}
//	
//	@Test
//	void minDepth() {
//		tree1.add(1);
//		tree1.add(2);
//		int i = tree2.minDepth(tree2.getRoot());
//	}
//	
//	@Test
//	void testRemoveAll() {
//		
////		ArrayList<Integer> listTest = new ArrayList<Integer>();
////		
////		for(int i = 0; i < 26; i++) {
////			listTest.add(rand.nextInt(26));
////		}
////		assertEquals(26, listTest.size());
//		
//		tree1.addAll(listTest);
////		tree1.add(0);
////		tree1.add(4);
////		tree1.add(3);
////		tree1.add(9);
////		tree1.add(7);
////		tree1.add(8);
////		tree1.writeDot("binaryTree1.dot");
////		
////		list999.add(0);
////		list999.add(4);
////		list999.add(3);
////		list999.add(9);
////		list999.add(7);
////		list999.add(8);
//		
////		assertTrue(tree1.contains(4));
//		tree1.removeAll(listTest);
//		assertEquals(0, tree1.size());
//		
////		ArrayList<Integer> listTest1 = new ArrayList<Integer>();
////		for(int i = 26; i > 0; i--) {
////			listTest1.add(i);
////		}
////		tree1.addAll(listTest1);
////		assertEquals(26, tree1.size());
////		tree1.removeAll(listTest1);
////		assertEquals(0, tree1.size());
////		
////		tree1.add(999);
////		tree1.remove(999);
////		assertEquals(0, tree1.size());
//		
//	}
	@Test
	void testSerial() {
		tree1.deserialize("1 2 n n 3 ");
	}
}
