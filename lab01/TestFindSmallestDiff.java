package lab01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFindSmallestDiff {
	
	private int[] arr1, arr2, arr3,arr4, arr5;

	@Before
	public void setUp() throws Exception {
		  arr1 = new int[0];
		  arr2 = new int[] { 3, 3, 3 };
		  arr3 = new int[] { 52, 4, -8, 0, -17 };
		  arr4 = new int[] {11,2,4,6,10,99,3432,15,22,1};
		  
		  arr5 = new int[10000];
		  for(int i = 0; i < 10000; i++) {
			  arr5[i] = i;
		  }
	}

	@After
	public void tearDown() throws Exception {
//		We could optionally set the arrays to null in tearDown().
	}

	@Test
	public void emptyArray() {
		
		assertEquals(-1, DiffUtil.findSmallestDiff(arr1));
	}
	
	@Test
	public void allArrayElementsEqual() {
		  
		  assertEquals(0, DiffUtil.findSmallestDiff(arr2));
		}
	
	@Test
	public void smallRandomArrayElements() {
//		System.out.println(DiffUtil.findSmallestDiff(arr3));
		  assertEquals(4, DiffUtil.findSmallestDiff(arr3));
		}
	
	@Test
	public void negativeRandomArrayElements() {
		System.out.println("arr4 "+DiffUtil.findSmallestDiff( arr4));
		  assertEquals(1, DiffUtil.findSmallestDiff(arr4));
		}
	
	@Test
	public void bigNegativeArrayElements() {
		System.out.println("arr5 is " + DiffUtil.findSmallestDiff(arr5));
		  assertEquals(1, DiffUtil.findSmallestDiff(arr5));
		}
	
	

}
