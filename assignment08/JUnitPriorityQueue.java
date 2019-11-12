package assignment08;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JUnitPriorityQueue {

	private PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
	private PriorityQueue<Integer> queueSingle = new PriorityQueue<Integer>();
	private PriorityQueue<Integer> queueMed = new PriorityQueue<Integer>();
	private PriorityQueue<Integer> queueRand = new PriorityQueue<Integer>();
	@BeforeEach
	void setUp() throws Exception {
		queue.add(1);
		queue.add(21);
		queue.add(16);
		queue.add(24);
		queue.add(31);
		queue.add(19);
		queue.add(68);
		queue.add(65);
		queue.add(26);
		queue.add(32);
		
		queueSingle.add(999);
		
		for(int i = 0; i <=100; i++) {
			queueMed.add(i);
		}
		
		queueRand.add(9);
		queueRand.add(4);
		queueRand.add(6);
		queueRand.add(2);
		queueRand.add(5);
		queueRand.add(1);
		queueRand.add(3);
		queueRand.add(7);
		queueRand.add(8);
		
	}

	@Test
	void testAdd() {
		queue.add(14);
//		System.out.println(queue.generateDot());
		assertEquals(11, queue.size());
		
		queueSingle.add(999);
		queueSingle.add(-1);
		assertEquals(-1, queueSingle.toArray()[0]);
		assertEquals(999, queueSingle.toArray()[1]);
	}
	
	@Test
	void testRemove() {
		assertEquals(999,(int) queueSingle.deleteMin());
		
		//create two element queue:
		queueSingle.add(999);
		queueSingle.add(1);
		assertEquals(1,(int) queueSingle.deleteMin());
		assertEquals(1, queueSingle.size());
		
		//test remove a 100 size priority queue:
		for(int i = 0; i <= 100; i++) {
			assertEquals(i, (int)queueMed.deleteMin());
		}
		
		System.out.println(queueRand.generateDot());
		for(int i = 1; i <= 9; i++) {
			assertEquals(i, (int)queueRand.deleteMin());
		}
		
		//test using deleteMin() to sort array:
		assertEquals(1, (int) queue.deleteMin());
		assertEquals(16, (int) queue.deleteMin());
		assertEquals(19, (int) queue.deleteMin());
		assertEquals(21, (int) queue.deleteMin());
		assertEquals(24, (int) queue.deleteMin());
	}

}
