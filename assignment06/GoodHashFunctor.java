package assignment06;

public class GoodHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		int hashVal = 0;
		
		for(int i = 0; i < item.length(); i++) {
			hashVal = 33 * hashVal + item.charAt(i);
		}
		return hashVal;
	}
	
}


