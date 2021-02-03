package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.Game;
import main.GameRunner;
import main.Map;

public class Display extends Application {
  private static double WIDTH;
  private static double HEIGHT;
  private static Pane mapPane;
  private static Scene scene;
  private static BorderPane menuScreen;
  private static Pane gameScreen;
  private Stage primaryStage;
  
  @Override
  public void start(Stage arg0) throws Exception {
    Rectangle2D screen = Screen.getPrimary().getBounds();
    WIDTH = screen.getWidth();
    HEIGHT = screen.getHeight();
    System.out.println("Loading Textures");
    new Texture();
    System.out.println("loading sounds");
    new SoundPlayer();
    System.out.println("loading map");
    new Map();
    System.out.println("map loading finished");
    menuScreen = Screens.menu();
    gameScreen = Screens.gameScreen();
    scene = new Scene(menuScreen, WIDTH, HEIGHT);
    KeyHandler.add(scene);
    primaryStage = new Stage();
    primaryStage.setTitle("Fishing Game");
    primaryStage.setScene(scene);
    primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    primaryStage.setFullScreen(true);
    primaryStage.show();
    primaryStage.setOnCloseRequest(event -> {
      Platform.exit();
      GameRunner.killThread();
    });
  }
  
  public static void runGame() {
    scene.setRoot(gameScreen);
    GameRunner.setRunning(true);
  }
  
  
  public static void draw(Node node) {
    gameScreen.getChildren().add(node);
  }
  
  public static double WIDTH() {
    return WIDTH;
  }
  
  public static double HEIGHT() {
    return HEIGHT;
  }
  
  public static void pause(boolean bool) {
    if(scene.getRoot().equals(gameScreen)) {
      Screens.showPause(bool);
      GameRunner.setRunning(!bool);
    }
  }
  
  public static void main(String[] args) {
    launch(args);
  }

}
