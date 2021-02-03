package Fish;

import java.util.Random;

public class ChinookSalmon extends Fish{
  private static final String id = "Chinook Salmon";
  private static Random rand = new Random();
  
  public ChinookSalmon() {
    super(id, rand.nextInt(45) + 10);
  }
}
