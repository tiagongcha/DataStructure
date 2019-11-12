package assignment4Pair;

//package assignment04;
//
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortUtilTest {
	ArrayList<Integer> bestCaseSmall, worstCaseSmall; 
	ArrayList<String> stuff;
	Comparator<Integer> compInt;
	Comparator<String> compStr;

	@Before
	public void setUp() throws Exception {
		bestCaseSmall = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			bestCaseSmall.add(i);
		}

		worstCaseSmall = new ArrayList<>();
		for (int i = 20; i >= 1; i--) {
			worstCaseSmall.add(i);
		}
		stuff = new ArrayList<String>();
		stuff.add("foo");
		stuff.add("car");
		stuff.add("baz");
		stuff.add("boz");
		stuff.add("waz");
		stuff.add("aoz");
		
		compStr = new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		};
		
		compInt = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		};
	}

	@Test
	public void testGenerateBestCase() {
		assertTrue(SortUtil.generateBestCase(20).equals(bestCaseSmall));

	}

	@Test
	public void testGenerateAverageCase() {
		assertFalse(SortUtil.generateAverageCase(20).equals(SortUtil.generateAverageCase(20)));
		assertTrue(SortUtil.generateAverageCase(20).size() == SortUtil.generateAverageCase(20).size());
//		for(Integer i: SortUtil.generateAverageCase(20)) {
//			System.out.println(i);
//		}
	}

	@Test
	public void testGenerateWorseCase() {
		assertTrue(SortUtil.generateWorstCase(20).equals(worstCaseSmall));
	}


	@Test
	public void testQuickSortInt() {

		ArrayList<Integer> worstCase = SortUtil.generateWorstCase(10000000);
		ArrayList<Integer> permuted = SortUtil.generateAverageCase(10000000);
		
		//sorting the list:
		SortUtil.quicksort(worstCase, compInt);
		SortUtil.quicksort(permuted, compInt);
		
		ArrayList<Integer> sorted = SortUtil.generateBestCase(10000000);

		assertTrue(worstCase.equals(sorted));
		assertTrue(permuted.equals(sorted));
	}
	
	@Test
	public void testQuickSortString() {
		SortUtil.quicksort(stuff, compStr);
		assertEquals("aoz", stuff.get(0));
		assertEquals("baz", stuff.get(1));
		assertEquals("boz", stuff.get(2));
		assertEquals("car", stuff.get(3));
		assertEquals("foo", stuff.get(4));
		assertEquals("waz", stuff.get(5));	
	}
	
	@Test
	public void testMergeSortString() {
		SortUtil.mergesort(stuff, compStr);
		assertEquals("aoz", stuff.get(0));
		assertEquals("baz", stuff.get(1));
		assertEquals("boz", stuff.get(2));
		assertEquals("car", stuff.get(3));
		assertEquals("foo", stuff.get(4));
		assertEquals("waz", stuff.get(5));	
	}
	
	@Test
	public void testMergeSortInt() {

		ArrayList<Integer> worstCase = SortUtil.generateWorstCase(10000000);
		ArrayList<Integer> permuted = SortUtil.generateAverageCase(10000000);
		
		//sorting the list:
		SortUtil.mergesort(worstCase, compInt);
		SortUtil.mergesort(permuted, compInt);
		
		ArrayList<Integer> sorted = SortUtil.generateBestCase(10000000);

		assertTrue(worstCase.equals(sorted));
		assertTrue(permuted.equals(sorted));
	}

}