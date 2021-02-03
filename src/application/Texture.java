package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

public class Texture {
  private static Image grass0;
  private static Image grass1;
  private static Image grass2;
  private static Image grass3;
  private static Image water;
  private static Image player;
  private static Image playerBack;
  private static Image playerWalk;
  private static Image playerWalkBack;
  private static Image fishingPole;
  private static Image grassWalking;
  private static Image arrow;
  private static Image fishingPoleCasted;
  private static Image bobber;
  private static Image background;
  private static Image playButton;
  private static Image playButtonHover;
  private static Image resume;
  private static Image resumeHover;
  private static Image exit;
  private static Image exitHover;
  private static Image waterEdge;
  private static Image shadow;
  private static Image journalPage1;
  private static Image journalPage2;
  private static Image waterDeep;
  private static Image waterLandingEffect;
  private static Image next;
  private static Image nextHover;
  private static Image prev;
  private static Image prevHover;
  private static Image pathUp;
  private static Image pathDown;
  private static Image pathRight;
  private static Image pathLeft;
  private static Image dirt;
  private static Image cornerTopLeft;
  private static Image cornerBottomLeft;
  private static Image cornerTopRight;
  private static Image cornerBottomRight;
  private static Image wood;
  private static Image pillarLeft;
  private static Image pillarRight;
  private static Image dockShadow;
  private static Image redHouse;
  private static Image blueHouse;
  private static Image greenHouse;
  private static Image baitShop;
  private static Image leftBridge;
  private static Image rightBridge;
  private static Image bridgeShadow;
  private static Image waterEdgeBridgeShadow;
  private static Image ferry;
  private static Image market;
  private static Image tree;
  
  public Texture() {
    try {
//      grass0 =  new Image(new FileInputStream("res/Tiles/grass0.png"));
//      grass1 = new Image(new FileInputStream("res/Tiles/grass1.png"));
//      grass2 = new Image(new FileInputStream("res/Tiles/grass2.png"));
      grass3 = new Image(new FileInputStream("res/Tiles/grass3.png"));
      water = new Image(new FileInputStream("res/Tiles/water.gif"));
      player = new Image(new FileInputStream("res/Objects/player.png"));
      playerBack = new Image(new FileInputStream("res/Objects/player_back.png"));
      playerWalk = new Image(new FileInputStream("res/Objects/player_walk.gif"));
      playerWalkBack = new Image(new FileInputStream("res/Objects/player_walk_back.gif"));
      fishingPole = new Image(new FileInputStream("res/Objects/fishingpole.png"));
      grassWalking = new Image(new FileInputStream("res/Effects/walking_grass.gif"));
      arrow = new Image(new FileInputStream("res/arrow.png"));
      fishingPoleCasted = new Image(new FileInputStream("res/Objects/fishingpole_casted.png"));
      bobber = new Image(new FileInputStream("res/Objects/bobber.png"));
      background = new Image(new FileInputStream("res/Screen/main_back.png"));
      playButton = new Image(new FileInputStream("res/Screen/playButton.png"));
      playButtonHover = new Image(new FileInputStream("res/Screen/playButtonHover.png"));
      resume = new Image(new FileInputStream("res/Screen/resume.png"));
      resumeHover = new Image(new FileInputStream("res/Screen/resumeButtonHover.png"));
      exit = new Image(new FileInputStream("res/Screen/exit.png"));
      exitHover = new Image(new FileInputStream("res/Screen/exitHover.png"));
      waterEdge = new Image(new FileInputStream("res/Tiles/water_edge.gif"));
      shadow = new Image(new FileInputStream("res/Effects/shadow.png"));
      journalPage1 = new Image(new FileInputStream("res/Objects/journal_rainbowtrout.png"));
      journalPage2 = new Image(new FileInputStream("res/Objects/journal_chinook.png"));
      waterDeep = new Image(new FileInputStream("res/Tiles/water_deep.png"));
      waterLandingEffect = new Image(new FileInputStream("res/Effects/bobber_landing_effect.gif"));
      next = new Image(new FileInputStream("res/Screen/nextButton.png"));
      nextHover = new Image(new FileInputStream("res/Screen/nextHover.png"));
      prev = new Image(new FileInputStream("res/Screen/previousButton.png"));
      prevHover = new Image(new FileInputStream("res/Screen/previousHover.png"));
      pathUp = new Image(new FileInputStream("res/Tiles/path_up.png"));
      pathDown = new Image(new FileInputStream("res/Tiles/path_down.png"));
      pathRight = new Image(new FileInputStream("res/Tiles/path_right.png"));
      pathLeft = new Image(new FileInputStream("res/Tiles/path_left.png"));
      dirt = new Image(new FileInputStream("res/Tiles/dirt.png"));
      cornerTopLeft = new Image(new FileInputStream("res/Tiles/corner_top_left.png"));
      cornerBottomLeft = new Image(new FileInputStream("res/Tiles/corner_bottom_left.png"));
      cornerTopRight = new Image(new FileInputStream("res/Tiles/corner_top_right.png"));
      cornerBottomRight = new Image(new FileInputStream("res/Tiles/corner_bottom_right.png"));
      wood = new Image(new FileInputStream("res/Tiles/wood.png"));
      pillarLeft = new Image(new FileInputStream("res/Tiles/pillar_left.png"));
      pillarRight = new Image(new FileInputStream("res/Tiles/pillar_right.png"));
      dockShadow = new Image(new FileInputStream("res/Tiles/wood_shadow.png"));
      redHouse = new Image(new FileInputStream("res/Objects/red_house.png"));
      blueHouse = new Image(new FileInputStream("res/Objects/blue_house.png"));
      greenHouse = new Image(new FileInputStream("res/Objects/green_house.png"));
      baitShop = new Image(new FileInputStream("res/Objects/bait_shop.png"));
      leftBridge = new Image(new FileInputStream("res/Tiles/bridge_left.png"));
      rightBridge = new Image(new FileInputStream("res/Tiles/bridge_right.png"));
      bridgeShadow = new Image(new FileInputStream("res/Tiles/bridge_shadow.gif"));
      waterEdgeBridgeShadow = new Image(new FileInputStream("res/Tiles/water_edge_bridge_shadow.png"));
      ferry = new Image(new FileInputStream("res/Objects/ferry.png"));
      market = new Image(new FileInputStream("res/Objects/market.png"));
      tree = new Image(new FileInputStream("res/Objects/tree.png"));
    }catch(FileNotFoundException e) {System.out.println(e.getMessage());}
  }
  
  public static Image GRASS_0() {
    return grass0;
  }
  
  public static Image GRASS_1() {
    return grass1;
  }
  
  public static Image GRASS_2() {
    return grass2;
  }
  
  public static Image GRASS_3() {
    return grass3;
  }
  
  public static Image WATER() {
    return water;
  }
  
  public static Image PLAYER() {
    return player;
  }
  
  public static Image PLAYER_BACK() {
    return playerBack;
  }
  
  public static Image PLAYER_WALK() {
    return playerWalk;
  }
  
  public static Image PLAYER_WALK_BACK() {
    return playerWalkBack;
  }
  
  public static Image FISHING_POLE() {
    return fishingPole;
  }
  
  public static Image GRASS_WALKING() {
    return grassWalking;
  }
  
  public static Image ARROW() {
    return arrow;
  }
  
  public static Image FISHING_POLE_CASTED() {
    return fishingPoleCasted;
  }
  
  public static Image BOBBER() {
    return bobber;
  }
  
  public static Image BACKGROUND() {
    return background;
  }
  
  public static Image PLAYBUTTON(int i) {
    if(i == 1) {
      return playButton;
    }else {
      return playButtonHover;
    }
  }
  
  public static Image RESUMEBUTTON(int i) {
    if(i == 1) {
      return resume;
    }else {
      return resumeHover;
    }
  }
  
  public static Image EXITBUTTON(int i) {
    if(i == 1) {
      return exit;
    }else {
      return exitHover;
    }
  }
  
  public static Image WATER_EDGE() {
    return waterEdge;
  }
  
  public static Image SHADOW() {
    return shadow;
  }
  
  public static Image WATER_LANDING_EFFECT() {
    return waterLandingEffect;
  }
  
  public static Image JOURNAL_PAGE_1() {
    return journalPage1;
  }
  
  public static Image JOURNAL_PAGE_2() {
    return journalPage2;
  }
  
  public static Image JOURNAL_BUTTON(int i ) {
    try {
      if(i == 0) {
        return new Image(new FileInputStream("res/Screen/journal_button.png"));
      }else if(i == 1) {
        return new Image(new FileInputStream("res/Screen/journal_hover.png"));
      }else {
        return new Image(new FileInputStream("res/Screen/journal_selected.png"));
      }
    }catch(FileNotFoundException e) {return null;}
  }
  
  public static Image FISHING_POLE_BUTTON(int i) {
    try {
      if(i == 0) {
        return new Image(new FileInputStream("res/Screen/fishing_button.png"));
      }else if(i == 1) {
        return new Image(new FileInputStream("res/Screen/fishing_hover.png"));
      }else {
        return new Image(new FileInputStream("res/Screen/fishing_selected.png"));
      }
    }catch(FileNotFoundException e) {return null;}
  }
  
  public static Image NEXTBUTTON(int i) {
    if(i == 1) {
      return next;
    }else {
      return nextHover;
    }
  }
  
  public static Image PREVIOUSBUTTON(int i) {
    if(i == 1) {
      return prev;
    }else {
      return prevHover;
    }
  }
  
  public static Image PATH_UP() {
    return pathUp;
  }
  
  public static Image PATH_DOWN() {
    return pathDown;
  }
  
  public static Image PATH_RIGHT() {
    return pathRight;
  }
  
  public static Image PATH_LEFT() {
    return pathLeft;
  }
  
  public static Image DIRT() {
    return dirt;
  }
  
  public static Image CORNER_TOP_LEFT() {
    return cornerTopLeft;
  }
  
  public static Image CORNER_BOTTOM_LEFT() {
    return cornerBottomLeft;
  }
  
  public static Image CORNER_TOP_RIGHT() {
    return cornerTopRight;
  }
  
  public static Image CORNER_BOTTOM_RIGHT() {
    return cornerBottomRight;
  }
  
  public static Image WOOD() {
    return wood;
  }
  
  public static Image PILLAR_LEFT() {
    return pillarLeft;
  }
  
  public static Image PILLAR_RIGHT() {
    return pillarRight;
  }
  
  public static Image DOCK_SHADOW() {
    return dockShadow;
  }
  
  public static Image RED_HOUSE() {
    return redHouse;
  }
  
  public static Image BLUE_HOUSE() {
    return blueHouse;
  }
  
  public static Image GREEN_HOUSE() {
    return greenHouse;
  }
  
  public static Image BAIT_SHOP() {
    return baitShop;
  }
  
  public static Image LEFT_BRIDGE() {
    return leftBridge;
  }
  
  public static Image RIGHT_BRIDGE() {
    return rightBridge;
  }
  
  public static Image BRIDGE_SHADOW() {
    return bridgeShadow;
  }
  
  public static Image WATER_EDGE_BRIDGE_SHADOW() {
    return waterEdgeBridgeShadow;
  }
  
  public static Image FERRY() {
    return ferry;
  }
  
  public static Image MARKET() {
    return market;
  }
  
  public static Image TREE() {
    return tree;
  }
}
