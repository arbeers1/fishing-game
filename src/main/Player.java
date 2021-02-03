package main;

import javax.sound.sampled.Clip;
import application.Display;
import application.SoundPlayer;
import application.Texture;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Player {
  int x;
  int y;
  private boolean up;
  private boolean down;
  private boolean left;
  private boolean right;
  private static boolean fishingPoleStowed;
  ImageView image;
  ImageView grassWalking;
  private FishingPole fishingPole;
  private static Label fishLabel;
  private static int fishLabelCounter;
  private boolean movingX;
  private boolean movingY;
  
  public Player() {
    x = (int) (Display.WIDTH()/2);
    y = (int) (Display.HEIGHT()/2);
    up = false;
    down = false;
    left = false;
    right = false;
    fishingPoleStowed = true;
    image = new ImageView(Texture.PLAYER());
    image.setFitHeight(80);
    image.setFitWidth(80);
    image.setLayoutX(x);
    image.setLayoutY(y);
    Display.draw(image);
    grassWalking = new ImageView(Texture.GRASS_WALKING());
    grassWalking.setLayoutX(x);
    grassWalking.setLayoutY(y + 60);
    Display.draw(grassWalking);
    fishingPole = new FishingPole(this);
    fishLabel = new Label("+ 12lb Rainbow Trout");
    fishLabel.setMaxHeight(25);
    fishLabel.setTextFill(Color.WHITE);
    fishLabel.setVisible(false);
    Display.draw(fishLabel);
    fishLabelCounter = 0;
    movingX = false;
    movingY = false;
  }
  
  public void update() {
    if(y <= 0 ) {
      up = false;
    }
    if(x <= 0 ) {
      left = false;
    }
    if(y >= Display.HEIGHT() - image.getFitHeight()) {
      down = false;
    }
    if(x >= Display.WIDTH() - image.getFitWidth()) {
      right = false;
    }
    if(Map.getMovingX()) {
      if(x < Display.WIDTH() * .6 && x > Display.WIDTH() * .4) {
        left = false;
        right = false;
        if(x < Display.WIDTH() * .4 + 10) {
          x+=6;
        }else if(x > Display.WIDTH() * .6 - 10) {
          x-=6;
        }
      }
    }
    if(Map.getMovingY()) {
      if(y < Display.HEIGHT() * .6 && y > Display.HEIGHT() * .4) {
        up = false;
        down = false;
        if(y < Display.HEIGHT() * .4 + 10) {
          y +=6;
        }else if(y > Display.HEIGHT() * .6 - 10) {
          y-=6;
        }
      }
    }
    if(up) {
      y-=4;
    }
    if(down) {
      y+=4;
    }
    if(right) {
      x+=4;
      grassWalking.setLayoutX(x);
    }
    if(left) {
      x-=4;
      grassWalking.setLayoutX(x + 64);
    }
    fishingPole.update();
    if((x != image.getLayoutX() || y != image.getLayoutY()) || Map.getMovingX() || Map.getMovingY()) {
      if(!(imageFromVector(1).equals(image.getImage()))) {
        if(Map.getUp() || up) {
          image.setImage(Texture.PLAYER_WALK_BACK()); 
        }else {
          image.setImage(imageFromVector(1));
        }
      }
      if(!fishingPole.isCasted()) {
        fishingPole.setVisible(false);
      }
      SoundPlayer.playGrassSound();
      grassWalking.setVisible(true);
    }else{
      SoundPlayer.stopWalking();
      image.setImage(imageFromVector(0));
      if(!fishingPoleStowed) {
        fishingPole.setVisible(true);
      }else {
        fishingPole.setVisible(false);
      }
      grassWalking.setVisible(false);
    }
    image.setLayoutX(x);
    image.setLayoutY(y);
    grassWalking.setLayoutY(y + 60);
    fishLabel.setLayoutX(x + 40 - fishLabel.getWidth()/2);
    fishLabel.setLayoutY(y - 35);
    
    if(fishLabel.isVisible()) {
      if(fishLabelCounter < 180) {
        fishLabelCounter++;
      }else {
        fishLabel.setVisible(false);
        fishLabelCounter = 0;
      }
    }
    if(x > Display.WIDTH() * .4 && x < Display.WIDTH() * .6) {
      movingX = false;
    }else {
      movingX = true;
    }
    if(y > Display.HEIGHT() * .40 && y < Display.HEIGHT() * .6) {
      movingY = false;
    }else {
      movingY = true;
    }
  }
  
  //@param i - indicates retrieval properly oriented walking image(1) or standing image(0)
  private Image imageFromVector(int i) {
    //CASE FOR PLAYER WALKING
    if(i == 1) {
      if(up) {
        return Texture.PLAYER_WALK_BACK();
      }else {
        return Texture.PLAYER_WALK();
      }
      //CASE FOR PLAYER STANDING
    }else if(i == 0) {
      if(image.getImage().equals(Texture.PLAYER_WALK_BACK()) || image.getImage().equals(Texture.PLAYER_BACK())) {
        return Texture.PLAYER_BACK();
      }else {
        return Texture.PLAYER();
      }
    }
    return Texture.PLAYER_WALK_BACK();
  }
  
  public void setUp(boolean bool) {
    up = bool;
  }
  
  public void setDown(boolean bool) {
    down = bool;
  }
  
  public void setRight(boolean bool) {
    right = bool;
  }
  
  public void setLeft(boolean bool) {
    left = bool;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public static void setStowed() {
    fishingPoleStowed = !fishingPoleStowed;
  }
  
  public static boolean getStowed() {
    return fishingPoleStowed;
  }

  public void setCasting(boolean bool) {
    fishingPole.setCasting(bool);
  }
  
  public void cast() {
    fishingPole.cast();
  }
  
  public boolean isCasted() {
    return fishingPole.isCasted();
  }

  public void reeling() {
    fishingPole.reeling();
  }

  public void reelingOff() {
    fishingPole.reelingOff();
  }
  
  public FishingPole getFishingPole() {
    return fishingPole;
  }
  
  public static void showLabel(String s) {
    fishLabel.setText(s);
    fishLabel.setVisible(true);
  }
  
  public boolean getMovingX() {
    return movingX;
  }
  
  public boolean getMovingY() {
    return movingY;
  }
}
