package lab06;

public class BetterRandomNumberGenerator implements RandomNumberGenerator{
	private long const1 ;
	private long const2 ;
	private long seed;
	private int rand;
	
	BetterRandomNumberGenerator(long seed){
		this.rand = (int) seed;
	}
	
	@Override
	public int nextInt(int max) {
		
		rand = (int) ((const1 * rand + const2) % 99);
//		System.out.println(Math.abs((int)rand % max));
		return Math.abs((int)rand % max);
	}

	@Override
	public void setSeed(long seed) {
		this.seed = seed;		
	}

	@Override
	public void setConstants(long const1, long const2) {
		this.const1 = const1;
		this.const2 = const2;		
	}

}
