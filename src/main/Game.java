package main;

import Fish.Fish;
import application.KeyHandler;

public class Game {
  private static Player player;
  
  
  public Game() {
    player = new Player();
  }
  
  public void update() {
    KeyHandler.update();
    player.update();
    Map.update(player);
    Fish.update(player.getFishingPole());
  }
  
  public static void UP(boolean bool) {
    player.setUp(bool);
    Map.setUp(bool);
  }
  
  public static void DOWN(boolean bool) {
    player.setDown(bool);
    Map.setDown(bool);
  }
  
  public static void RIGHT(boolean bool) {
    player.setRight(bool);
    Map.setRight(bool);
  }
  
  public static void LEFT(boolean bool) {
    player.setLeft(bool);
    Map.setLeft(bool);
  }
  
  public static void setCasting(boolean bool) {
    player.setCasting(bool);
  }
  
  public static void cast() {
    player.cast();
  }
  
  public static boolean isCasted() {
    return player.isCasted();
  }
  
  public static void reeling() {
    player.reeling();
  }
  
  public static void reelingOff() {
    player.reelingOff();
  }
}
