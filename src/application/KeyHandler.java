package application;

import java.util.ArrayList;
import application.SoundPlayer.SoundEffects;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import main.Game;
import main.Player;

public class KeyHandler {
  private Scene scene;
  private static ArrayList activeKeys;
  private enum Directions{up, down, left, right}
  
  public KeyHandler(Scene scene) {
    this.scene = scene;
    activeKeys = new ArrayList<Directions>();
    handleInput();
  }
  
  private void handleInput() {
    try {
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      public void handle(KeyEvent event) {
        switch(event.getCode()) {
          case W: if(!(activeKeys.contains(Directions.up))){activeKeys.add(Directions.up); }; break;
          case A: if(!(activeKeys.contains(Directions.left))){activeKeys.add(Directions.left); }; break;
          case S: if(!(activeKeys.contains(Directions.down))){activeKeys.add(Directions.down); }; break;
          case D: if(!(activeKeys.contains(Directions.right))){activeKeys.add(Directions.right); }; break;
          case F: Player.setStowed(); Screens.selectPoleB(Player.getStowed());SoundPlayer.playSound(SoundEffects.BUTTON);break;
          case B: Screens.showJournal(!Screens.isJournalVisible());SoundPlayer.playSound(SoundEffects.BUTTON); break;
          case ESCAPE: Display.pause(!Screens.isPauseVisible()); break;
        }
      }
    });
    
    scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
      public void handle(KeyEvent event) {
        switch(event.getCode()) {
          case W: activeKeys.remove(Directions.up); break;
          case A: activeKeys.remove(Directions.left); break;
          case S: activeKeys.remove(Directions.down); break;
          case D: activeKeys.remove(Directions.right); break;
        }
      }
    });
    
    scene.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        try {
          if(event.getButton() == MouseButton.SECONDARY) {
            Game.setCasting(true);
          }else if(event.getButton() == MouseButton.PRIMARY) {
            if(!Game.isCasted()) {
              Game.cast();
              Game.setCasting(false);
            }else {
              Game.reeling();
            }
          }
        }catch(NullPointerException e) {}
      }
    });
      
    scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if(event.getButton() == MouseButton.SECONDARY) {
          Game.setCasting(false);
        }
        if(event.getButton() == MouseButton.PRIMARY) {
          Game.reelingOff();
        }
      }
      
    });
  }catch(NullPointerException e) {}
  }
  
  public static void update() {
    if(activeKeys.contains(Directions.up)) {
      Game.UP(true);
    }else {
      Game.UP(false);
    }
    if(activeKeys.contains(Directions.down)) {
      Game.DOWN(true);
    }else {
      Game.DOWN(false);
    }
    if(activeKeys.contains(Directions.left)) {
      Game.LEFT(true);
    }else {
      Game.LEFT(false);
    }
    if(activeKeys.contains(Directions.right)) {
      Game.RIGHT(true);
    }else {
      Game.RIGHT(false);
    }
  }
  
  public static void add(Scene scene) {
    new KeyHandler(scene);
  }
}
