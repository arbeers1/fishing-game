package application;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
  private static AudioInputStream inputStream;
  private static Clip footstepGrass2;
  private static Clip footstepGrass3;
  private static Clip reelingClip;
  private static Clip bobberLanding;
  private static Clip cast;
  private static Clip fishCatch;
  private static Clip pageFlip;
  private static Clip button;
  private static Clip click;
  
  private static boolean step;
  private static boolean play;
  private static boolean playReel;
  
  public enum SoundEffects {BOBBER_LAND, CAST, FISH_CATCH, PAGE_FLIP, BUTTON, CLICK}
  
  public SoundPlayer() {
    try {
      inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("res/Sounds/footstep_grass_2.wav")));
      footstepGrass2 = AudioSystem.getClip();
      footstepGrass2.open(inputStream);
      
      inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("res/Sounds/footsteo_grass_3.wav")));
      footstepGrass3 = AudioSystem.getClip();
      footstepGrass3.open(inputStream);
      
      inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("res/Sounds/reeling.wav")));
      reelingClip = AudioSystem.getClip();
      reelingClip.open(inputStream);
      
      inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("res/Sounds/bobberLanding.wav")));
      bobberLanding = AudioSystem.getClip();
      bobberLanding.open(inputStream);
      
      inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("res/Sounds/cast.wav")));
      cast = AudioSystem.getClip();
      cast.open(inputStream);
      
      inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("res/Sounds/fishCatch.wav")));
      fishCatch = AudioSystem.getClip();
      fishCatch.open(inputStream);
      
      inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("res/Sounds/pageFlip.wav")));
      pageFlip = AudioSystem.getClip();
      pageFlip.open(inputStream);
      
      inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("res/Sounds/button.wav")));
      button = AudioSystem.getClip();
      button.open(inputStream);
      
      inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("res/Sounds/click.wav")));
      click = AudioSystem.getClip();
      click.open(inputStream);
      
      inputStream.close();
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
    play = true;
    step = true;
    playReel = true;
  }
  
  public static void playGrassSound() {
    if(play) {
      play = false;
      if(step) {
        footstepGrass2.setMicrosecondPosition(0);
        footstepGrass2.start();
      }else {
        footstepGrass3.setMicrosecondPosition(0);
        footstepGrass3.start();
      }
    }
    footstepGrass2.addLineListener(e -> {
      if(e.getType() == LineEvent.Type.STOP) {
        step = !step;
        play = true;
      }
    });
    footstepGrass3.addLineListener(e -> {
      if(e.getType() == LineEvent.Type.STOP) {
        step = !step;
        play = true;
      }
    });
  }
 
  
  public static void stopWalking() {
    if(step) {
      footstepGrass2.stop();
    }else {
      footstepGrass3.stop();
    }
  }
  
  public static void startReelingSound() {
    if(playReel) {
      playReel = false;
      reelingClip.loop(reelingClip.LOOP_CONTINUOUSLY);
    }
  }
  
  public static void stopReelingSound() {
    playReel = true;
    reelingClip.stop();
  }
  
  public static void playSound(SoundEffects sound) {
    if(sound.equals(SoundEffects.BOBBER_LAND)){
      bobberLanding.setMicrosecondPosition(0);
      bobberLanding.start();
    }else if(sound.equals(SoundEffects.CAST)) {
      cast.setMicrosecondPosition(0);
      cast.start();
    }else if(sound.equals(SoundEffects.FISH_CATCH)) {
      fishCatch.setMicrosecondPosition(0);
      fishCatch.start();
    }else if(sound.equals(SoundEffects.PAGE_FLIP)) {
      pageFlip.setMicrosecondPosition(0);
      pageFlip.start();
    }else if(sound.equals(SoundEffects.BUTTON)) {
      button.setMicrosecondPosition(0);
      button.start();
    }else if(sound.equals(SoundEffects.CLICK)) {
      click.setMicrosecondPosition(0);
      click.start();
    }
  }
}