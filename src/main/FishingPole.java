package main;

import java.awt.MouseInfo;
import java.awt.Point;
import Fish.Fish;
import application.Display;
import application.SoundPlayer;
import application.Texture;
import application.SoundPlayer.SoundEffects;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class FishingPole {
  private ImageView fishingPole;
  private ImageView aimArrow;
  private ImageView bobber;
  private ImageView shadow;
  private ImageView bobberLandEffect;
  private Player player;
  private Rectangle power;
  private Rectangle powerBack;
  private boolean casting;
  private boolean casted;
  private boolean reeling;
  private Line line;
  private double bobberCenteralY;
  private boolean bobberDirection;
  private double[] resultLine;
  private double destX;
  private double destY;
  private boolean drawingLine;
  private boolean bobberJiggle;
  private int frameCount;
  private double slope;
  private boolean bobberLandingSound;
  
  public FishingPole(Player player) {
    this.player = player;
    casting = false;
    casted = false;
    bobberLandEffect = new ImageView(Texture.WATER_LANDING_EFFECT());
    bobberLandEffect.setVisible(false);
    Display.draw(bobberLandEffect);
    fishingPole = new ImageView(Texture.FISHING_POLE());
    fishingPole.setLayoutX(player.getX() + 70);
    fishingPole.setLayoutY(player.getY() - 20);
    fishingPole.setVisible(false);
    Display.draw(fishingPole);
    powerBack = new Rectangle(86, 16);
    powerBack.setLayoutX(player.getX() - 3);
    powerBack.setLayoutY(player.getY() + 87);
    powerBack.setVisible(false);
    powerBack.setFill(Color.DARKGREY);
    Display.draw(powerBack);
    power = new Rectangle(0, 10);
    power.setLayoutX(player.getX());
    power.setLayoutY(player.getY() + 90);
    power.setFill(Color.INDIANRED);
    Display.draw(power);
    line = new Line(fishingPole.getLayoutX() + 20,fishingPole.getLayoutY(),400,400);
    line.setVisible(false);
    Display.draw(line);
    bobber = new ImageView(Texture.BOBBER());
    bobber.setVisible(false);
    Display.draw(bobber);
    shadow = new ImageView(Texture.SHADOW());
    shadow.setVisible(false);
    Display.draw(shadow);
    //bobberLandEffect = new ImageView(Texture.WATER_LANDING_EFFECT());
    //bobberLandEffect.setVisible(false);
    //Display.draw(bobberLandEffect);
    bobberCenteralY = 0;
    bobberDirection = true;
    reeling = false;
    resultLine = new double[2];
    destX = 0;
    destY = 0;
    drawingLine = false;
    bobberJiggle = true;
    frameCount = 0;
    bobberLandingSound = true;
  }
  
  public void update() {
    fishingPole.setLayoutX(player.getX() + 70);
    fishingPole.setLayoutY(player.getY() - 20);
    power.setLayoutX(player.getX());
    power.setLayoutY(player.getY() + 90);
    powerBack.setLayoutX(player.getX() - 3);
    powerBack.setLayoutY(player.getY() + 87);
    line.setStartX(fishingPole.getLayoutX()+5);
    line.setStartY(fishingPole.getLayoutY());
    if(!casted) {
      line.setEndX(fishingPole.getLayoutX()+5);
      line.setEndY(fishingPole.getLayoutY());
    }
    if(casting) {
      addPower();
    }else {
      powerBack.setVisible(false);
    }
    animate();
    //Makes the bobber bob
    if(!drawingLine) {
      if(bobberDirection) {
        bobber.setLayoutY(bobber.getLayoutY() + .2);
        line.setEndY(line.getEndY() + .2);
        if(bobber.getLayoutY() >= bobberCenteralY + 3) {
          bobberDirection = !bobberDirection;
        }
      }else {
        bobber.setLayoutY(bobber.getLayoutY() - .2);
        line.setEndY(line.getEndY() - .2);
        if(bobber.getLayoutY() <= bobberCenteralY - 3) {
          bobberDirection = !bobberDirection;
        }
      }
    }
    if(reeling) {
      reelIn();
    }
  }

  private void animate() {
    if(drawingLine) {
      if(line.getEndX() < destX-5) {
        line.setEndX(line.getEndX() + 10);
        bobber.setLayoutX(line.getEndX()-5);
      }else if(line.getEndX() > destX+5) {
        line.setEndX(line.getEndX() - 10);
        bobber.setLayoutX(line.getEndX()-5); 
      }
      
      if(line.getEndY() < destY) {
          line.setEndY(line.getEndY() + 10);
          bobber.setLayoutY(line.getEndY());
      }
      if((shadow.getLayoutX() > destX-5 && shadow.getLayoutX() < destX + 5) && (shadow.getLayoutY() > destY - 5 && shadow.getLayoutY() < destY + 5)) {
        shadow.setLayoutX(destX);
        shadow.setLayoutY(destY);
      }else {
        shadow.setLayoutX(bobber.getLayoutX());
        shadow.setLayoutY(bobber.getLayoutY() + 40);
      }
      shadow.setVisible(true);
      line.setVisible(true);
      bobber.setVisible(true);
    }
    if(line.getEndX() >= destX - 5 && line.getEndY() >= destY) {
      if(casted && !reeling) {       
        bobberLandEffect.setVisible(true);
        bobberLandEffect.setLayoutX(bobber.getLayoutX()-35);
        bobberLandEffect.setLayoutY(bobber.getLayoutY()-35);
        if(bobberLandingSound) {
          SoundPlayer.playSound(SoundEffects.BOBBER_LAND);
          bobberLandingSound = false;
        }
      }
      drawingLine = false;
      shadow.setVisible(false);
    }
    if(line.getEndY() < Display.HEIGHT() * .4) {
      reeling();
    }
  }
  
  public void setVisible(boolean bool) {
    fishingPole.setVisible(bool);
  }
  
  private void addPower() {
    if(fishingPole.isVisible() && !casted) {
      powerBack.setVisible(true);
      if(power.getWidth() < 80) {
        power.setWidth(power.getWidth() + 1);
      }
    }
  }
  
  public void setCasting(boolean bool) {
    casting = bool;
    if(!casting) {
      power.setWidth(0);
    }
  }
  
  public void cast() {
    if(fishingPole.isVisible() && !casted && casting) {
      SoundPlayer.playSound(SoundEffects.CAST);
      Point point = MouseInfo.getPointerInfo().getLocation();
      fishingPole.setImage(Texture.FISHING_POLE_CASTED());
      drawLine(point, power.getWidth());
      //System.out.println("cast a distance of: " + power.getWidth());
      casted = true;
    }
  }
  
  private void drawLine(Point point, double power) {
    if(!drawingLine) {
    destX = 0;
    destY = 0;
    double maxDist = 10 * power;
    double currentDist = Math.hypot(point.getX() - line.getStartX(), point.getY() - line.getStartY());
    slope = (point.getY() - line.getStartY())/(point.getX() - line.getStartX());
    System.out.println("max: " + maxDist + " currentDist" + currentDist);
    redrawLine(maxDist, point.getX(), point.getY(), slope);
    destX = resultLine[0];
    destY = resultLine[1];
    //bobber.setLayoutX(destX-5);
    //bobber.setLayoutY(destY);
    bobberCenteralY = destY;
    //bobber.setVisible(true);
    //line.setEndX(destX);
    //line.setEndY(destY);
    //line.setVisible(true);
    if(destY < Display.HEIGHT()*.4) {
      reeling();
    }
    drawingLine = true;
    animate();
    }
  }
  
  private void redrawLine(double maxDist, double x, double y, double slope) {
    if(Math.hypot(x-line.getStartX(), y-line.getStartY()) > maxDist) {
      if(x>line.getStartX()) {
        x--;
        y-=slope;
      }else if(x<line.getStartX()) {
        x++;
        y+=slope;
      }
      redrawLine(maxDist, x, y, slope);
    }else {
      resultLine[0] = x;
      resultLine[1] = y;
    }
    return;
  }
  
  public boolean isCasted() {
    return casted;
  }

  public void reeling() {
    if(!drawingLine && casted) {
      reeling = true;
      bobberJiggle = false;
      bobberLandEffect.setVisible(false);
    }
  }
  
  private void reelIn() {
    SoundPlayer.startReelingSound();
    double targetX = fishingPole.getLayoutX() + 20;
    if(line.getEndX() > targetX + 3) {
      line.setEndX(line.getEndX() - 2);
      bobber.setLayoutX(line.getEndX()-5);
    }else if(line.getEndX() < targetX - 3) {
      line.setEndX(line.getEndX() + 2);
      bobber.setLayoutX(line.getEndX()-5);
    }
    if(line.getEndY() > Display.HEIGHT() * .4 + 20) {
      line.setEndY(line.getEndY() - 2);
      bobber.setLayoutY(line.getEndY());
      bobberCenteralY = line.getEndY();
    }
    if((line.getEndX() < targetX + 10 && line.getEndX() > targetX - 10) && line.getEndY() < Display.HEIGHT() * .4 + 50) {
      reel();
    }
    if(line.getEndY() < Display.HEIGHT() * .4) {
      reel();
    }
  }
  
  public void reel() {
    line.setEndX(line.getStartX());
    line.setEndY(line.getStartY());
    casted = false;
    try {Player.showLabel(Fish.fishToString());} catch(NullPointerException e) {}
    Fish.catchFish();
    bobberJiggle = true;
    bobberLandingSound = true;
    SoundPlayer.stopReelingSound();
    bobber.setVisible(false);
    reeling = false;
    line.setVisible(false);
    fishingPole.setImage(Texture.FISHING_POLE());
    //System.out.println("fishing pole reeled in");
  }

  public void reelingOff() {
    reeling = false;
    SoundPlayer.stopReelingSound();
  }

  public void jiggle() {
    if(bobberJiggle && frameCount == 5) {
      bobber.setVisible(!bobber.isVisible());
      frameCount = 0;
    }else if(!bobberJiggle){
      bobber.setVisible(true);
    }else {
      frameCount++;
    }
  }
}
