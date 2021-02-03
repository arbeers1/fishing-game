package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import application.Display;
import application.Texture;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

//Determines where tiles are placed
public class Map {
  private int[][] townCenterGrid;
  private static Pane townCenter;
  private static Pane currentPane;
  private static boolean up;
  private static boolean down;
  private static boolean right;
  private static boolean left;
  private static boolean movingX;
  private static boolean movingY;
  
  public Map() {
    townCenterGrid = initializeMapGrid();
    townCenter = new Pane();
    loadMaps();
    currentPane = townCenter;
    up = false;
    down = false;
    left = false;
    right = false;
    movingX = true;
    movingY = true;
  }
  
  private int[][] initializeMapGrid() {
    int[][] grid = new int[40][80];
    Scanner sc = null;
    try {
       sc = new Scanner(new File("res/Map/test.csv"));
    } catch (FileNotFoundException e) {}
    int line = 0;
    while(sc.hasNextLine()) {
      String[] arr = sc.nextLine().split(",");
      int[] temp = new int[arr.length];
      for(int i = 0; i < arr.length; i++) {
        temp[i] = Integer.parseInt(arr[i]);
      }
      grid[line] = temp;
      line++;
    }
    return grid;
  }
  
  private void loadMaps() {
    for(int i = 0, y = 0; i < townCenterGrid.length; i++, y+=40) {
      for(int j = 0, x = 0; j < townCenterGrid[0].length; j++, x+=40) {
        ImageView image = new ImageView();
        if(townCenterGrid[i][j] == 1) {
          image.setImage(getTexture());
        }else if(townCenterGrid[i][j] == 0) {
          image.setImage(Texture.WATER());
        }else if(townCenterGrid[i][j] == 27) {
          image.setImage(Texture.TREE());
        }else if(townCenterGrid[i][j] == 2) {
          image.setImage(Texture.PATH_UP());
        }else if(townCenterGrid[i][j] == 3) {
          image.setImage(Texture.PATH_DOWN());
        }else if(townCenterGrid[i][j] == 4) {
          image.setImage(Texture.PATH_RIGHT());
        }else if(townCenterGrid[i][j] == 5) {
          image.setImage(Texture.PATH_LEFT());
        }else if(townCenterGrid[i][j] == 12) {
          image.setImage(Texture.WATER_EDGE());
        }else if(townCenterGrid[i][j] == 13) {
          image.setImage(Texture.WOOD());
        }else if(townCenterGrid[i][j] == 99) {
          image.setImage(null);
        }else if(townCenterGrid[i][j] == 16) {
          image.setImage(Texture.DOCK_SHADOW());
        }else if(townCenterGrid[i][j] == 21) {
          image.setImage(Texture.LEFT_BRIDGE());
        }else if(townCenterGrid[i][j] == 22) {
          image.setImage(Texture.RIGHT_BRIDGE());
        }else if(townCenterGrid[i][j] == 23) {
          image.setImage(Texture.BRIDGE_SHADOW());
        }else if(townCenterGrid[i][j] == 6) {
          image.setImage(Texture.DIRT());
        }else if(townCenterGrid[i][j] == 7) {
          image.setImage(Texture.CORNER_TOP_LEFT());
        }else if(townCenterGrid[i][j] == 8) {
          image.setImage(Texture.CORNER_BOTTOM_LEFT());
        }else if(townCenterGrid[i][j] == 9) {
          image.setImage(Texture.CORNER_TOP_RIGHT());
        }else if(townCenterGrid[i][j] == 10) {
          image.setImage(Texture.CORNER_BOTTOM_RIGHT());
        }else if(townCenterGrid[i][j] == 14) {
          image.setImage(Texture.PILLAR_LEFT());
        }else if(townCenterGrid[i][j] == 15) {
          image.setImage(Texture.PILLAR_RIGHT());
        }else if(townCenterGrid[i][j] == 17) {
          image.setImage(Texture.RED_HOUSE());
        }else if(townCenterGrid[i][j] == 18) {
          image.setImage(Texture.BLUE_HOUSE());
        }else if(townCenterGrid[i][j] == 19) {
          image.setImage(Texture.GREEN_HOUSE());
        }else if(townCenterGrid[i][j] == 20) {
          image.setImage(Texture.BAIT_SHOP());
        }else if(townCenterGrid[i][j] == 24) {
          image.setImage(Texture.WATER_EDGE_BRIDGE_SHADOW());
        }else if(townCenterGrid[i][j] == 25) {
          image.setImage(Texture.FERRY());
        }else if(townCenterGrid[i][j] == 26) {
          image.setImage(Texture.MARKET());
        }
        image.setLayoutX(x);
        image.setLayoutY(y);
        townCenter.getChildren().add(image);
      }
    }
  }
  
  public static void setUp(Boolean bool) {
    up = bool;
  }
  
  public static void setDown(Boolean bool) {
    down = bool;
  }
  
  public static void setLeft(Boolean bool) {
    left = bool;
  }
  
  public static void setRight(Boolean bool) {
    right = bool;
  }
  
  public static void update(Player player) {
      if(currentPane.getLayoutY() == 0) {
        up = false;
      }
      if(currentPane.getLayoutX() == 0) {
        left = false;
      }
      if(currentPane.getLayoutY() == -1600 + Display.HEIGHT()) {
        down = false;
      }
      if(currentPane.getLayoutX() == -3200 + Display.WIDTH()) {
        right = false;
      }
      if(player.getMovingX()) {
        right = false;
        left = false;
      }
      if(player.getMovingY()) {
        up = false;
        down = false;
      }
      if(up) {
        currentPane.setLayoutY(currentPane.getLayoutY() + 4);
      }
      if(down) {
        currentPane.setLayoutY(currentPane.getLayoutY() - 4);
      }
      if(right) {
        currentPane.setLayoutX(currentPane.getLayoutX() - 4);
      }
      if(left) {
        currentPane.setLayoutX(currentPane.getLayoutX() + 4);
      }
      if(!right && !left) {
        movingX = false;
      }else {
        movingX = true;
      }
      if(!up && !down) {
        movingY = false;
      }else {
        movingY = true;
      }
  }
  
  public static boolean getMovingX() {
    return movingX;
  }
  
  public static boolean getMovingY() {
    return movingY;
  }
  
  public static boolean getUp() {
    return up;
  }
  
  public static Pane getTestMap() {
    return townCenter;
  }
  
  public static void printCords(Player player) {
    System.out.println("X: " + (Math.abs(currentPane.getLayoutX()) + player.getX()) + "Y: " + (Math.abs(currentPane.getLayoutY())+player.getY()));
  }
  
//  public static Pane getMap() {
//    Pane root = new Pane();
//    Random rand = new Random();
//    
//    
//    for(int i = 0; i < Display.WIDTH(); i+=40) {
//      for(int j = 0, a = 0; j < Display.HEIGHT(); j+=40) {
//        ImageView image;
//        if(j > Display.HEIGHT() * .4) {
//          image = new ImageView(Texture.WATER());
//          if(a == 0) {
//            image = new ImageView(Texture.WATER_EDGE());
//          }
//          a++;
//        }else {
//          image = new ImageView(getTexture());
//        }
//        
//        image.setLayoutX(i);
//        image.setLayoutY(j);
//        root.getChildren().add(image);
//      }
//    }
//    return root;
//  }
  

  private static Image getTexture() {
//    Random rand = new Random();
//    int random = rand.nextInt(100);
//    if(random < 59) {
//      return Texture.GRASS_0();
//    }else if(random < 79) {
//      return Texture.GRASS_1();
//    }else if(random < 99) {
//      return Texture.GRASS_2();
//    }else {
      return Texture.GRASS_3();
//      }
  }
}
