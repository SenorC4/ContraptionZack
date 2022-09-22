import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.scene.image.Image; 
import javafx.stage.Stage; 
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import java.util.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.*;
import java.io.*;
import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.animation.*;
import javafx.scene.input.*;
import javafx.scene.text.Font;

public class TrapZack extends Application{
   
  StackPane root = new StackPane();
  Canvas canvas = new Canvas(800, 800);
  GraphicsContext gc = canvas.getGraphicsContext2D();
  
  AnimationTimer ta;
  
  //assets for pause menu
  boolean gamePaused = false;
  boolean titleMenu = true;
  Button resume = new Button("Resume");
  Button save = new Button("Save");
  Button load = new Button("Load");
  Button restartA = new Button("Restart Area");
  Button restartL = new Button("Restart Level");
  Button end = new Button("Exit");
  VBox vbox = new VBox (0, resume, save, load, restartA, restartL, end);
  
  //title menu
  Button newGame = new Button("New Game");
  Button loadGame = new Button("Load Game");
  Font comic = new Font("Comic Sans MS", 50);
  Label title = new Label("Contraption Zack");
  VBox titleBox = new VBox(200, title, newGame, loadGame);
   
   
   public void start(Stage stage){
   
      //escape menu
      resume.setOnAction(new ButtonListener());
      end.setOnAction(new ButtonListener());
      root.setOnKeyPressed(new KeyListenerDown());
      
      
      
   
      Scene scene = new Scene(root, 800, 800);      
      root.getChildren().add(canvas);
      
      //title
      title.setFont(comic);
      newGame.setFont(comic);
      loadGame.setFont(comic);
      vbox.setAlignment(Pos.CENTER); 
      newGame.setOnAction(new ButtonListener());
      titleBox.setAlignment(Pos.CENTER);
      root.getChildren().add(titleBox);
      
            
      //default javafx stuff
      stage.setScene(scene);      
      stage.setTitle("Square");      
      stage.show();
      
      //Keyboard lisetner
      
      
      //request focus for canvas
      canvas.requestFocus();
      titleBox.requestFocus();
   }
   
   //main
   public static void main(String[] args){ 
      launch(args); 
   }
   
   
   //animation
   public class AnimationHandler extends AnimationTimer{
      public void handle(long currentTimeInNanoSeconds){
         draw(gc);
      }   
   }
   
   //draw player movement and stuff
   public void draw(GraphicsContext gc){
      gc.drawImage(new Image("Assets/Boat1.png", false), 400, 600);

   }


   
   
public class KeyListenerDown implements EventHandler<KeyEvent>  
   {
   
      public void handle(KeyEvent event) 
      {
         //Escape menu
         if (event.getCode() == KeyCode.ESCAPE && gamePaused == false && titleMenu == false)
         {
            gamePaused = true;
            root.getChildren().add(vbox);
            resume.requestFocus();
            
         }
         else if (event.getCode() == KeyCode.ESCAPE && gamePaused)
         {
            gamePaused = false;
            root.getChildren().remove(vbox);
            root.requestFocus();
         }
         
      }
   }
   
   public class ButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {  
         if (e.getSource() == end)
         {
            System.exit(0);
         }
         else if (e.getSource() == resume)
         {
            gamePaused = false;
            root.getChildren().remove(vbox);
            root.requestFocus();
         }
         else if (e.getSource() == save)
         {
            
         }
         else if (e.getSource() == load)
         {
            
         }
         else if (e.getSource() == restartA)
         {
            
         }
         else if (e.getSource() == restartL)
         {
            
         }
         
         if (e.getSource() == newGame);
         {
            titleMenu = false;
            root.getChildren().remove(titleBox);
            vbox.setAlignment(Pos.TOP_LEFT);
            
            //create and start animation handler
            ta = new AnimationHandler();
            ta.start();


            root.requestFocus();
         }
         if (e.getSource() == loadGame)
         {
            //add code to load the saved file
         }
      }
   }
   
   

}