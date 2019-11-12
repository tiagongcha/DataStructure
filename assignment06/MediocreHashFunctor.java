package assignment06;

public class MediocreHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		int hashVal = 0;
		
		for(int i = 0; i < item.length(); i++) {
			hashVal += item.charAt(i);
		}
		return hashVal;
	}	
}


