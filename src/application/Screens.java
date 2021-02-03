package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import application.SoundPlayer.SoundEffects;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GameRunner;
import main.Journal;
import main.Map;
import main.Player;

public class Screens {
  private static VBox vbox;
  private static Pane journal;
  private static Button journalB;
  private static Button poleB;
  private static Label weight;
  private static Label numCaught;
  private static Font font;
  private static Label version;
  
  public static BorderPane menu() {
    try {
      font = Font.loadFont(new FileInputStream("res/Font/Pixeled.ttf"), 20);
    } catch (FileNotFoundException e1) { }
    ImageView background = new ImageView(Texture.BACKGROUND());
    background.setPreserveRatio(true);
    background.setFitHeight(Display.HEIGHT());
    background.setFitWidth(Display.WIDTH());
    BorderPane root = new BorderPane();
    System.out.println(Texture.GRASS_0());
    root.setBackground(new Background(new BackgroundImage(Texture.BACKGROUND(),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
    VBox vbox = new VBox(10);
    vbox.setMinHeight(Display.HEIGHT() * .8);
    vbox.setMinWidth(Display.WIDTH());
    vbox.setAlignment(Pos.CENTER);
    Label title = new Label("Sunfish Harbor");
    title.setPrefWidth(Display.WIDTH());
    title.setAlignment(Pos.CENTER);
    title.setFont(new Font("Pixeled", 70));
    title.setStyle("-fx-text-fill: #996515;");
    Button playButton = new Button("", new ImageView(Texture.PLAYBUTTON(1)));
    playButton.setPrefSize(Display.WIDTH() * .1, Display.HEIGHT() * .2);
    playButton.setStyle("-fx-background-color: transparent");
    playButton.setOnMouseEntered(e-> playButton.setGraphic(new ImageView(Texture.PLAYBUTTON(0))));
    playButton.setOnMouseExited(e-> playButton.setGraphic(new ImageView(Texture.PLAYBUTTON(1))));
    playButton.setOnMouseClicked(e-> {Display.runGame();SoundPlayer.playSound(SoundEffects.CLICK);});
    vbox.getChildren().add(title);
    vbox.getChildren().add(playButton);
    //root.setTop(title);
    root.setCenter(vbox);
    return root;
  }
  
  public static void pauseScreen() {
    vbox = new VBox(15);
    vbox.setPrefHeight(Display.HEIGHT()/2.7);
    vbox.setPrefWidth(Display.WIDTH()/6.4);
    vbox.setLayoutX(Display.WIDTH()/2 - Display.WIDTH()/12.8);
    vbox.setLayoutY(Display.HEIGHT()/2 - Display.HEIGHT()/5.4);
    vbox.setAlignment(Pos.CENTER);
    vbox.setStyle("-fx-background-color: #222831");
    vbox.setVisible(false);
    
    Button resume = new Button("", new ImageView(Texture.RESUMEBUTTON(1)));
    resume.setStyle("-fx-background-color: transparent");
    resume.setPrefWidth(vbox.getWidth() - 20);
    resume.setPrefHeight(vbox.getHeight()/4);
    Button exit = new Button("", new ImageView(Texture.EXITBUTTON(1)));
    exit.setStyle("-fx-background-color: transparent");
    exit.setPrefWidth(vbox.getWidth() - 20);
    exit.setPrefHeight(vbox.getHeight()/4);
    
    vbox.getChildren().add(resume);
    vbox.getChildren().add(exit);
    
    resume.setOnMouseEntered(e -> resume.setGraphic(new ImageView(Texture.RESUMEBUTTON(0))));
    resume.setOnMouseExited(e -> resume.setGraphic(new ImageView(Texture.RESUMEBUTTON(1))));
    resume.setOnMouseClicked(e -> {Display.pause(false);SoundPlayer.playSound(SoundEffects.CLICK);});
    exit.setOnMouseEntered(e -> exit.setGraphic(new ImageView(Texture.EXITBUTTON(0))));
    exit.setOnMouseExited(e -> exit.setGraphic(new ImageView(Texture.EXITBUTTON(1))));
    exit.setOnMouseClicked(e -> {Platform.exit(); GameRunner.killThread();});
    Display.draw(vbox);
  }
  
  public static void journal() {
    journal = new Pane();
    journal.setMinWidth(600);
    journal.setMinHeight(800);
    journal.setLayoutX(Display.WIDTH()/2 - 300);
    journal.setLayoutY(Display.HEIGHT()/2 - 400);
    Journal.loadJournal();
    ImageView page = new ImageView(Journal.next());
    page.setFitHeight(800);
    page.setFitWidth(600);
    //page.setLayoutX(Display.WIDTH()/2 - 300);
    //page.setLayoutY(journal.getLayoutY());
    weight = new Label(Journal.getData()[0] + "lbs");
    weight.setLayoutX(page.getLayoutX() + 400);
    weight.setLayoutY(page.getLayoutY() + 315);
    weight.setFont(new Font("Pixeled", 20));
    
    numCaught = new Label(Journal.getData()[1] +"");
    numCaught.setLayoutX(weight.getLayoutX());
    numCaught.setLayoutY(weight.getLayoutY() + 60);
    numCaught.setFont(new Font("Pixeled", 20));
    Button prev = new Button("", new ImageView(Texture.PREVIOUSBUTTON(1)));
    prev.setStyle("-fx-background-color: transparent");
    prev.setPrefSize(100, 50);
    prev.setLayoutX(page.getLayoutX() + 10);
    prev.setLayoutY(page.getLayoutY() + page.getFitHeight() - 60);
    
    Button next = new Button("", new ImageView(Texture.NEXTBUTTON(1)));
    next.setStyle("-fx-background-color: transparent");
    next.setPrefSize(100, 50);
    next.setLayoutX(prev.getLayoutX() + 580 - 100);
    next.setLayoutY(prev.getLayoutY());
    
    next.setOnMouseEntered(e -> next.setGraphic(new ImageView(Texture.NEXTBUTTON(2))));
    next.setOnMouseExited(e -> next.setGraphic(new ImageView(Texture.NEXTBUTTON(1))));
    prev.setOnMouseEntered(e -> prev.setGraphic(new ImageView(Texture.PREVIOUSBUTTON(2))));
    prev.setOnMouseExited(e -> prev.setGraphic(new ImageView(Texture.PREVIOUSBUTTON(1))));
    next.setOnMouseClicked(e -> {page.setImage(Journal.next());updateJournal();SoundPlayer.playSound(SoundEffects.PAGE_FLIP);});
    prev.setOnMouseClicked(e -> {page.setImage(Journal.prev()); updateJournal();SoundPlayer.playSound(SoundEffects.PAGE_FLIP);});
    journal.getChildren().add(page);
    journal.getChildren().add(weight);
    journal.getChildren().add(numCaught);
    journal.getChildren().add(prev);
    journal.getChildren().add(next);
    journal.setVisible(false);
    Display.draw(journal);
  }
  
  public static Pane gameScreen() {
    Pane pane = new Pane();
   
    Pane map = Map.getTestMap();
    map.setLayoutX(0);
    map.setLayoutY(0);

    version = new Label("Alpha Build 0.0.1");
    version.setLayoutX(10);
    version.setLayoutY(10);
    version.setFont(new Font("Pixeled",15));

    journalB = new Button("",new ImageView(Texture.JOURNAL_BUTTON(0)));
    journalB.setStyle("-fx-background-color: transparent");
    journalB.setPrefSize(80,80);
    journalB.setLayoutX(Display.WIDTH() - 100);
    journalB.setLayoutY(Display.HEIGHT() - 100);
    
    poleB = new Button("",new ImageView(Texture.FISHING_POLE_BUTTON(0)));
    poleB.setStyle("-fx-background-color: transparent");
    poleB.setPrefSize(80,80);
    poleB.setLayoutX(Display.WIDTH() - 200);
    poleB.setLayoutY(Display.HEIGHT() - 100);
    
    //
    pane.getChildren().add(map);
    pane.getChildren().add(version);
    pane.getChildren().add(journalB);
    pane.getChildren().add(poleB);
    
    journalB.setOnMouseEntered(e -> journalB.setGraphic(new ImageView(Texture.JOURNAL_BUTTON(1))));
    journalB.setOnMouseExited(e -> {if(journal.isVisible()) {journalB.setGraphic(new ImageView(Texture.JOURNAL_BUTTON(2)));
    }else {
      journalB.setGraphic(new ImageView(Texture.JOURNAL_BUTTON(0)));
    }
    });
    journalB.setOnMouseClicked(e -> {showJournal(!isJournalVisible());SoundPlayer.playSound(SoundEffects.BUTTON);});
    
    poleB.setOnMouseEntered(e -> poleB.setGraphic(new ImageView(Texture.FISHING_POLE_BUTTON(1))));
    poleB.setOnMouseExited(e -> {if(Player.getStowed()) {poleB.setGraphic(new ImageView(Texture.FISHING_POLE_BUTTON(0)));
    }else{
      poleB.setGraphic(new ImageView(Texture.FISHING_POLE_BUTTON(2)));
    }});
    poleB.setOnMouseClicked(e -> {new ImageView(Texture.FISHING_POLE_BUTTON(2)); Player.setStowed();SoundPlayer.playSound(SoundEffects.BUTTON);});

    return pane;
  }
  
  public static void showJournal(Boolean bool) {
    updateJournal();
    journal.setVisible(bool);
    if(bool) {
      GameRunner.setRunning(false);
      journalB.setGraphic(new ImageView(Texture.JOURNAL_BUTTON(2)));
    }else {
      GameRunner.setRunning(true);
      journalB.setGraphic(new ImageView(Texture.JOURNAL_BUTTON(0)));
    }
  }
  
  public static void updateJournal() {
    weight.setText(Journal.getData()[0] + "lbs");
    numCaught.setText(Journal.getData()[1] +"");
  }
  
  public static boolean isJournalVisible() {
    return journal.isVisible();
  }
  
  public static void showPause(Boolean bool) {
    vbox.setVisible(bool);
  }
  
  public static boolean isPauseVisible() {
    try {
      return vbox.isVisible();
    }catch(NullPointerException e) {
      pauseScreen();
      return vbox.isVisible();
    }
  }
  
  public static void selectPoleB(Boolean bool) {
    if(bool) {
      poleB.setGraphic(new ImageView(Texture.FISHING_POLE_BUTTON(0)));
    }else {
      poleB.setGraphic(new ImageView(Texture.FISHING_POLE_BUTTON(2)));
    }
  }
}
