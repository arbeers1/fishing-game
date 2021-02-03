package Fish;

import java.util.Random;
import application.SoundPlayer;
import application.SoundPlayer.SoundEffects;
import main.FishingPole;
import main.Journal;

public class Fish {
  private String id;
  private int weight;
  private static long time;
  private static boolean fishOnLine;
  private static Fish fish;
  
  public Fish(String id, int weight) {
    this.id = id;
    this.weight = weight;
    fishOnLine = false;
  }
  
  public static void update(FishingPole pole) {
    if(pole.isCasted() && !fishOnLine) {
      Random rand = new Random();
      int chance = rand.nextInt(5000);
      if(chance >4995) {
        fish = new ChinookSalmon();
        System.out.println("catch");
        fishOnLine = true;
      }else if(chance > 4990) {
        fish = new RainbowTrout();
        fishOnLine = true;
        System.out.println("catch");
      }
    }
    if( (fishOnLine)) {
      pole.jiggle();
    }
  }
  
  public static void catchFish() {
    if(fishOnLine) {
      SoundPlayer.playSound(SoundEffects.FISH_CATCH);
    }
    fishOnLine = false;
    Journal.addData(fish);
    fish = null;
  }
  
  public String getId() {
    return id;
  }
  
  public int getWeight() {
    return weight;
  }
  
  public static String fishToString() {
    return "+"+fish.getWeight()+"lb " + fish.getId();
  }
}
