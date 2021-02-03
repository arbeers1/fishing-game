package Fish;

import java.util.Random;

public class RainbowTrout extends Fish{
  private static final String id = "Rainbow Trout";
  private static Random rand = new Random();
  
  public RainbowTrout() {
    super(id, rand.nextInt(10)+1);
  }
}
