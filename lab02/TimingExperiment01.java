package lab02;

public class TimingExperiment01 {

  public static void main(String[] args) {
    long lastTime = System.currentTimeMillis();
//    System.out.println("last time is " + lastTime);
    int advanceCount = 0;
    
    while (advanceCount < 100) {
      long currentTime = System.currentTimeMillis();
//      System.out.println("current time is " + currentTime);
      if (currentTime == lastTime) {
//    	  System.out.println("equals");
    	  
    	  continue;
      }
        
      System.out.println("Time advanced " + (currentTime - lastTime) + " milliseconds.");
      lastTime = currentTime;
      advanceCount++;
    }
  }
}
