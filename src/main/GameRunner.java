package main;

import application.Display;
import application.RenderThread;
import application.Screens;
import javafx.application.Platform;

public class GameRunner {
  private static boolean isRunning;
  private RenderThread renderThread;
  private static Game game;

  public GameRunner() {
    renderThread = new RenderThread();
    renderThread.start();
    isRunning = false;
    runGame();
  }

  public void runGame() {
    while (true) {
      try {
        Thread.sleep(16);
      } catch (InterruptedException e) {
      }
      // Centeral game loop
      while (isRunning) {
        try {
          Thread.sleep(16);
        } catch (InterruptedException e) {}
        Platform.runLater(new Runnable() {

          @Override
          public void run() {
            // TODO Auto-generated method stub
            game.update();
          }
          
        });
      }
    }
  }
  
  public static void killThread() {
    System.exit(0);
  }
  
  public static void setRunning(boolean state) {
    isRunning = state;
    if(game == null) {
      game = new Game();
      Screens.journal(); 
    }
  }
  
  public static boolean isRunning() {
    return isRunning;
  }
  
  public static void main(String[] args) {
    new GameRunner();
  }
}
