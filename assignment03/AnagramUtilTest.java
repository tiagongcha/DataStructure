package assignment03;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import assignment03.AnagramUtil.FileReader;

public class AnagramUtilTest {
	private String[] smallStringList, smallNoAnagramList,smallWithNullList,
	smallWithoutNullList,smallWithNullList2;
	private Integer[] smallInt;
	private MyComp comp;
	private CompInt compInt;
	
	@Before
	public void setUp() throws Exception {
		smallStringList = new String[]{"Laert", "ada", "canana","banana", "Alert","alert"};
		smallNoAnagramList = new String[] {"hi", "hola","cat","pig","later"};
		smallWithNullList = new String[] {"banana", null, "anew", "car", null, "wane", "glean", "wean",null};
		smallWithoutNullList = new String[] {"banana", "anew", "car", "wane", "glean", "wean"};
		smallWithNullList2 = new String[] {null, "alert", null, "later",null};
		smallInt = new Integer[] {5,2,10,1,6};
		//setting up the comparator:
		 comp = new MyComp();
		 compInt = new CompInt();
		 	}
	
	protected class CompInt implements Comparator<Integer>{

		@Override
		public int compare(Integer o1, Integer o2) {
			
			return o1.compareTo(o2);
		}
		
	}
	
	protected class MyComp implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			if(o1 == null && o2 == null) {
				
				return 0;
			}
			else if (o1 ==null && o2 !=null) {
				return 1;
			}
			else if (o1 != null && o2 == null) {
				return -1;
			}
			else {
				return AnagramUtil.sort(o1).toLowerCase().
						compareTo(AnagramUtil.sort(o2).toLowerCase());
			}
		}
		
	}
	
	
	
	@Test
	public void testSortSmall() {
		assertEquals("aelrt", AnagramUtil.sort("later"));
		assertEquals("aelrt", AnagramUtil.sort("alert"));
		assertEquals("aelRt", AnagramUtil.sort("aleRt"));
		assertEquals("aeLRt", AnagramUtil.sort("LateR"));
	}
	
	@Test
	public void testAreAnagramSmall() {
		assertTrue(AnagramUtil.areAnagrams("begin", "BeGIN"));
	}
	
	@Test
	public void testInsertionSortString() {
		
		AnagramUtil.insertionSort(smallStringList, comp);
		assertEquals("banana", smallStringList[0]);
		assertEquals("canana", smallStringList[1]);
		assertEquals("ada", smallStringList[2]);
		assertEquals("Laert", smallStringList[3]);
		assertEquals("Alert", smallStringList[4]);
		assertEquals("alert", smallStringList[5]);
	}
	
	@Test
	public void testInsertionSortInt() {
		AnagramUtil.insertionSort(smallInt, compInt);
	}
	
	@Test
	public void testSortListWithNull() {
		
		AnagramUtil.insertionSort(smallWithNullList, comp);
		assertEquals("anew", smallWithNullList[3]);
		assertEquals("wane", smallWithNullList[4]);
		assertEquals("wean", smallWithNullList[5]);	
		
		AnagramUtil.insertionSort(smallWithNullList2, comp);
		assertEquals("alert", smallWithNullList2[0]);
		assertEquals("later", smallWithNullList2[1]);	
	}
	

	@Test
	public void testGetLargestAnagramGroupSmall() {

	assertTrue(Arrays.equals(new String[] {"Laert","Alert","alert"}, AnagramUtil.getLargestAnagramGroup(smallStringList)));
	assertTrue(Arrays.equals(new String[] {"anew","wane","wean"}, AnagramUtil.getLargestAnagramGroup(smallWithoutNullList)));	
	assertTrue(Arrays.equals(new String[] {"anew","wane","wean"}, AnagramUtil.getLargestAnagramGroup(smallWithNullList)));
	assertTrue(Arrays.equals(new String[] {"alert", "later"}, AnagramUtil.getLargestAnagramGroup(smallWithNullList2)));
	}
	
	
	@Test
	public void testNoLargestAnagramGroup() {
		
		assertEquals(0, AnagramUtil.getLargestAnagramGroup(smallNoAnagramList).length);
		assertTrue(Arrays.equals(new String[0], AnagramUtil.getLargestAnagramGroup(smallNoAnagramList)));
	}
	
	@Test
	public void testGetLargestAnagramGroupSampleFile() {
		assertTrue(Arrays.equals(new String[] {"carets", "Caters", "caster", "crates", "Reacts", "recast", "traces"}, 
				AnagramUtil.getLargestAnagramGroup("sample_word_list.txt")));
	}
	
	@Test
	public void testGetLargestAnagramGroupModerateFile() {
		 assertTrue(Arrays.equals(new String[] {"act", "cat"}, AnagramUtil.getLargestAnagramGroup("moderate_word_list.txt")));
	}
	
	
	
	
}
