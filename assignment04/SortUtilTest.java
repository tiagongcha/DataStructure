//package assignment04;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//public class SortUtilTest {
//	ArrayList<Integer> bestCaseSmall, worstCaseSmall;
//	Comparator compInt;
//
//	@Before
//	public void setUp() throws Exception {
//		bestCaseSmall = new ArrayList<>();
//		for (int i = 1; i <= 20; i++) {
//			bestCaseSmall.add(i);
//		}
//
//		worstCaseSmall = new ArrayList<>();
//		for (int i = 20; i >= 1; i--) {
//			worstCaseSmall.add(i);
//		}
//
//		compInt = new Comparator<Integer>() {
//
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				// TODO Auto-generated method stub
//				return o1.compareTo(o2);
//			}
//		};
//	}
//
//	@Test
//	public void testGenerateBestCase() {
//		assertTrue(SortUtil.generateBestCase(20).equals(bestCaseSmall));
//
//	}
//
//	@Test
//	public void testGenerateAverageCase() {
//		assertFalse(SortUtil.generateAverageCase(20).equals(SortUtil.generateAverageCase(20)));
//		assertTrue(SortUtil.generateAverageCase(20).size() == SortUtil.generateAverageCase(20).size());
//	}
//
//	@Test
//	public void testGenerateWorseCase() {
//		assertTrue(SortUtil.generateWorstCase(20).equals(worstCaseSmall));
//	}
//
//	@SuppressWarnings("unchecked")
//	@Test
//	public void testQuickSortInt() {
//		ArrayList<Integer> small = SortUtil.generateWorstCase(10);
//		for (int i : small) {
//			System.out.println("i " + i);
//		}
//
//		SortUtil.quicksort(small, compInt);
//
//		for (int i = 0; i < 10; i++) {
//			System.out.println(small.get(i));
//		}
//	}
//
//	@Test
//	public void testMergeSortInt() {
//
//		ArrayList<Integer> small = SortUtil.generateWorstCase(10);
//
//		for (int i : small) {
//			System.out.println("i " + i);
//		}
//
//		SortUtil.mergesort(small, compInt);
//
//		for (int i = 0; i < 10; i++) {
//			System.out.println(small.get(i));
//		}
//
//	}
//}