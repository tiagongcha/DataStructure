package lab00;

public class CoinFlipExperiment {
	
	public static void main(String[] args) {
		
		int[] counts = new int[201];
		
		for(int i = 0; i < 10000; i++) {
			int amount = coinFlipExperiment();
			
			counts[amount+100] = counts[amount+100] + 1;
			
			System.out.println ("Likelihood of win/loss amount after 100 flips:");
			System.out.println ("Win/loss amount: " + amount);
			System.out.print ("\t"); // A tab character
			System.out.print ("Probability");
			System.out.println ();
		}
		
		double attempts = 10000.0;
		  for (int j = 0; j <= 200; j++)
		  {
		    System.out.print (j - 100);
		    System.out.print ("\t");
		    System.out.print (counts[j] / attempts);
		    System.out.println ();
		  }
	}
	
	/** Returns the amount of money you'd win or lose
	  * by flipping an unbalanced coin 100 times.
	  *
	  * @return the amount of money won/lost
	  */
	  static public int coinFlipExperiment () {
		  
		  int winCount = 0;
		  for(int i = 0; i < 100; i++) {
			  double flip = Math.random();
			  if (flip < 0.505) {
//			    System.out.println ("Heads");
			    winCount++;
			  } else {
//			    System.out.println ("Tails");
			    winCount--;
			  }
			  
		  }
		  return winCount;
	  }

}
