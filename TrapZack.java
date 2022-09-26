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

  int levelOffsetX = 100;
  int levelOffsetY = 100;

  int Px = 400;
  int Py = 600;
  boolean drewPlayer = false;
  boolean canMoveLeft = true;
  boolean canMoveRight = true;
  boolean canMoveUp = true;
  boolean canMoveDown = true;
  
  //ContraptionZacLevel L1;
  ContraptionZacLevel L1 = new ContraptionZacLevel("Assets/Level Files/lvl1.txt");
   
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
  
  //game
  Image Water = new Image("Assets/Aseprite Sprites/Boat Game/Water.png", false);
  Image Player = new Image("Assets/Aseprite Sprites/Boat Game/Boat1.png", false);
  Image Arrow = new Image("Assets/Aseprite Sprites/Boat Game/Arrow.png", false);
   
   
   public void start(Stage stage){
   
      //escape menu
      resume.setMinWidth(100);
      save.setMinWidth(100);
      load.setMinWidth(100);
      restartA.setMinWidth(100);
      restartL.setMinWidth(100);
      end.setMinWidth(100);
      
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
      root.setStyle("-fx-background-color: yellow");

      
      if(titleMenu == false){
         String[][] data = L1.getData();
         int x = L1.getX();
         int y = L1.getY();
         
         for (int i = 0; i < x; i++)
         {
            for (int j = 0; j < y; j++)
            {
               if (data[i][j].equals("T1"))
               {
                  gc.drawImage(Water, levelOffsetX + i*64, levelOffsetY + j*64);
               }
               else if (data[i][j].equals("XT1"))
               {
                  gc.drawImage(Water, levelOffsetX + i*64, levelOffsetY + j*64);
                  gc.drawImage(Arrow, levelOffsetX + i*64, levelOffsetY + j*64);
               }
               else if (data[i][j].equals("PT1"))
               {
                  gc.drawImage(Water, levelOffsetX + i*64, levelOffsetY + j*64);
                  if (!drewPlayer)
                  {
                     Px = levelOffsetX + i*64;
                     Py = levelOffsetY + j*64;
                     drewPlayer = true;
                  }
               }
            }
         }
         
         //check bounds left
         if ((Px - 48 - levelOffsetX)/64 <= -1)
            canMoveLeft = false;
         else
         {
            if ((!data[(Px - 48 - levelOffsetX)/64][(Py - levelOffsetY)/64].equals("PT1")) && (!data[(Px - 48 - levelOffsetX)/64][(Py - levelOffsetY)/64].equals("T1")) && (!data[(Px - 48 - levelOffsetX)/64][(Py - levelOffsetY)/64].equals("XT1")))
               canMoveLeft = false;
            else 
               canMoveLeft = true;
         }
            
         //check bounds right
         if ((Px + 48  - levelOffsetX)/64 >= x)
            canMoveRight = false;
         else
         {
            if ((!data[(Px + 48 - levelOffsetX)/64][(Py - levelOffsetY)/64].equals("PT1")) && (!data[(Px + 48 - levelOffsetX)/64][(Py - levelOffsetY)/64].equals("T1")) && (!data[(Px + 48 - levelOffsetX)/64][(Py - levelOffsetY)/64].equals("XT1")))
               canMoveRight = false;
            else 
               canMoveRight = true;
         }
         
         //check bounds up
         if ((Py - 59 - levelOffsetY)/64 <= -1)
            canMoveUp = false;
         else
         {
            if ((!data[(Px - levelOffsetX)/64][(Py - 59 - levelOffsetY)/64].equals("PT1")) && (!data[(Px - levelOffsetX)/64][(Py - 59 - levelOffsetY)/64].equals("T1")) && (!data[(Px - levelOffsetX)/64][(Py - 59 - levelOffsetY)/64].equals("XT1")))
               canMoveUp = false;
            else 
               canMoveUp = true;
         }
            
         //check bounds Down
         if ((Py + 64  - levelOffsetY)/64 >= y)
            canMoveDown = false;
         else
         {
            if ((!data[(Px - levelOffsetX)/64][(Py + 64 - levelOffsetY)/64].equals("PT1")) && (!data[(Px - levelOffsetX)/64][(Py + 64 - levelOffsetY)/64].equals("T1")) && (!data[(Px - levelOffsetX)/64][(Py + 64 - levelOffsetY)/64].equals("XT1")))
               canMoveDown = false;
            else 
               canMoveDown = true;
         }

         
         L1.getObjects();
      }
      
      
      
      //get player position, check for walls
      
         
      
      //gc.drawImage(Water, Px, Py+1);
      gc.drawImage(Player, Px, Py);
      

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
         
         //Player Movement
         if (!gamePaused)
         {
            //Left
            if (event.getCode() == KeyCode.A)
            {
               if (canMoveLeft)
                  Px--;
            }
            //Right
            if (event.getCode() == KeyCode.D)
            {
               if (canMoveRight)
                  Px++;
            }
            //Up
            if (event.getCode() == KeyCode.W)
            {
               if (canMoveUp)
                  Py--;
            }
            //Down
            if (event.getCode() == KeyCode.S)
            {
               if (canMoveDown)
                  Py++;
            }
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
            
            //ContraptionZacLevel L1 = new ContraptionZacLevel("Assets/Level1.txt");
            
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