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
  
  //assets for pause menu
  boolean gamePaused = false;
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
      
      
      //create squyare
      
      //gc.fillRect(250, 250, 25, 25);
      
      //default javafx stuff
      stage.setScene(scene);      
      stage.setTitle("Square");      
      stage.show();
      
      //Keyboard lisetner
      
      
      //request focus for canvas
      canvas.requestFocus();
      titleBox.requestFocus();
   }
   
   public static void main(String[] args){ 
      launch(args); 
   }
   
   // private EventHandler<KeyEvent> keyReleased = new EventHandler<KeyEvent>() {
// 
//         @Override
//         public void handle(KeyEvent event) {
//             // set movement to 0, if the released key was responsible for the paddle
//             switch (event.getCode()) {
//                 case W:
//                 case S:
//                     leftPaddleDY = 0;
//                     break;
//                 case UP:
//                 case DOWN:
//                     rightPaddleDY = 0;
//                     break;
//             }
//         }
// 
//     };
// 
//     private EventHandler<KeyEvent> keyPressed = new EventHandler<KeyEvent>() {
// 
//         @Override
//         public void handle(KeyEvent event) {
//             // start movement according to key pressed
//             switch (event.getCode()) {
//                 case W:
//                     leftPaddleDY = -6;
//                     break;
//                 case S:
//                     leftPaddleDY = 6;
//                     break;
//                 case UP:
//                     rightPaddleDY = -6;
//                     break;
//                 case DOWN:
//                     rightPaddleDY = 6;
//                     break;
//             }
// 
//         }
public class KeyListenerDown implements EventHandler<KeyEvent>  
   {
   
      public void handle(KeyEvent event) 
      {
      
         if (event.getCode() == KeyCode.ESCAPE && gamePaused == false)
         {
            gamePaused = true;
            root.getChildren().add(vbox);
            resume.requestFocus();
            
         }
         else if (event.getCode() == KeyCode.ESCAPE && gamePaused == true)
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
            root.getChildren().remove(titleBox);
            gc.drawImage(new Image("Assets/Boat1.png", false), 400, 600);
            root.requestFocus();
            //eventually add code to load first level
         }
         if (e.getSource() == loadGame)
         {
            //add code to load the saved file
         }
      }
   }
   
   

}