package main;

import java.util.ArrayList;
import Fish.ChinookSalmon;
import Fish.Fish;
import Fish.RainbowTrout;
import application.Texture;
import javafx.scene.image.Image;

public class Journal {
  private static int index = -1;
  private static ArrayList<Image> pages = new ArrayList<Image>();
  private static int[][] log = {{0,0},
                                {0,0}};
 
  public static void loadJournal() {
    pages.add(Texture.JOURNAL_PAGE_1());
    pages.add(Texture.JOURNAL_PAGE_2());
  }
  
  public static Image next() {
    index++;
    try {
      return pages.get(index);
    }catch(IndexOutOfBoundsException e) {
      index--;
      return pages.get(index);
    }
  }
  
  public static Image prev() {
    index--;
    try {
      return pages.get(index);
    }catch(IndexOutOfBoundsException e) {
      index++;
      return pages.get(index);
    }
  }
  
  public static void addData(Fish fish) {
    if(fish instanceof RainbowTrout) {
      if(fish.getWeight() > log[0][0]) {
        log[0][0] = fish.getWeight();
      }
      log[0][1]++;
    }else if(fish instanceof ChinookSalmon) {
      if(fish.getWeight() > log[1][0]) {
        log[1][0] = fish.getWeight();
      }
      log[1][1]++;
    }
  }
  
  public static int[] getData() {
    return log[index];
  }
}
