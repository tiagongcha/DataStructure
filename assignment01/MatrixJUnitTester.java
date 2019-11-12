/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MatrixJUnitTester {

  Matrix threeByTwo, twoByThree, twoByTwoResult,twoByTwo1, twoByTwo2, twoByTwoPlus,twoByTwo3, oneByone1,
  oneByone2;
  /* Initialize some matrices we can play with for every test! */

  @Before
  public void setup() {
	  oneByone1 = new Matrix(new int[][] {{1},{1}}); 
	  oneByone2 = new Matrix(new int[][] {{2},{2}});
	  
    threeByTwo = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
    twoByThree = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });
    // this is the known correct result of multiplying M1 by M2
    twoByTwoResult = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } });
    
    twoByTwo1 = new Matrix(new int[][] { {1, 2, 3}, {4 , 5, 6} });
    twoByTwo3 = new Matrix(new int[][] { {1, 2, 3}, {4 , 5, 6} });
    twoByTwo2 = new Matrix(new int[][] { {7, 8, 9}, {10 , 11, 12} });
    twoByTwoPlus = new Matrix(new int[][] { {8, 10, 12}, {14 , 16, 18} });
  }

  @Test
  public void testEqual() {
	  Assert.assertEquals(true, twoByTwo1.equals(twoByTwo3));
  }
  
  @Test
  public void testUnequal() {
	  Assert.assertEquals(false, twoByTwo1.equals(twoByTwo2));
  }
  
  @Test
  public void twoByTwoToString() {
    String resultString = "13 12 " + "\n" + "29 26 "+"\n";
    Assert.assertEquals(resultString, twoByTwoResult.toString());
  }
  
  @Test
  public void plusWithTwoDimensions() {
	  Matrix matrixProduct = twoByTwo1.plus(twoByTwo2);
	  
	  Assert.assertEquals(twoByTwoPlus, matrixProduct);
  }
  
  
  @Test
  public void plusWithTwoDimensionsTestString() {
	  Matrix matrixProduct = twoByTwo1.plus(twoByTwo2);
	  String resultString = "8 10 12 " + "\n" + "14 16 18 " + "\n";
	  Assert.assertEquals(resultString, matrixProduct.toString());
  }
  
  
  @Test
  public void plusWithUnequalDimensions() {
	  Matrix matrixProduct = twoByThree.plus(threeByTwo);
	  Assert.assertEquals(null, matrixProduct);
  }
  
  @Test
  public void plusWithOneDimensionString() {
	  Matrix matrixProduct = oneByone1.plus(oneByone2);
	  String resultString = "3 " + "\n" + "3 " + "\n";
	  Assert.assertEquals(resultString, matrixProduct.toString());
  }
  
  
  @Test
  public void timesWithBalancedDimensions() {
    Matrix matrixProduct = threeByTwo.times(twoByThree);
    Assert.assertEquals(twoByTwoResult,matrixProduct);
  }

  
  @Test
  public void timesWithUnbalancedDimensions() {
	  Matrix matrixProduct = threeByTwo.times(twoByTwoResult);
	  Assert.assertEquals(null, matrixProduct);
  }



}
