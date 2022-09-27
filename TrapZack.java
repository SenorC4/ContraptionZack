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

  //Variables holding the offsets of the level
  int levelOffsetX = 100;
  int levelOffsetY = 100;
  
  //Player x and y positions
  int Px = 400;
  int Py = 600;
  //boolean holding when the player is first drawn
  boolean drewPlayer = false;
  //variables holding directional access
  boolean canMoveLeft = true;
  boolean canMoveRight = true;
  boolean canMoveUp = true;
  boolean canMoveDown = true;
  //variable to count the current frame rotation
  int frameCount = 0;
  
  //ContraptionZacLevel L1;
  ContraptionZacLevel L1 = new ContraptionZacLevel("Assets/Level Files/lvl1.txt");
  ContraptionZacLevel L2 = new ContraptionZacLevel("Assets/Level Files/lvl2.txt");
  ContraptionZacLevel currentLevel = L1;
   
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
  Image Player1 = new Image("Assets/Aseprite Sprites/Boat Game/Boat1.png", false);
  Image Player2 = new Image("Assets/Aseprite Sprites/Boat Game/Boat2.png", false);
  Image Arrow = new Image("Assets/Aseprite Sprites/Boat Game/Arrow.png", false);
  Image PlayerImage = Player1;
   
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

      //if not in the title screen
      if(titleMenu == false){
         //load the first level, get the data from the text file
         String[][] data = currentLevel.getData();
         //get the dimensions of the text for the for loops
         int x = currentLevel.getX();
         int y = currentLevel.getY();
         
         gc.setFill(Color.YELLOW);
         gc.fillRect(0,0,800,800);
         gc.setFill(Color.BLACK);
         
         //go through the array
         for (int i = 0; i < x; i++)
         {
            for (int j = 0; j < y; j++)
            {
               //generic water tile
               if (data[i][j].equals("T1"))
               {
                  //gc.drawImage(Water, levelOffsetX + i*64, levelOffsetY + j*64);
                  gc.setFill(Color.BLUE);
                  gc.fillRect(levelOffsetX + i*64, levelOffsetY + j*64, 64, 64);
                  gc.setFill(Color.BLACK);
               }
               //Exit Arrow Tile
               else if (data[i][j].equals("XT1"))
               {
                  gc.drawImage(Water, levelOffsetX + i*64, levelOffsetY + j*64);
                  gc.drawImage(Arrow, levelOffsetX + i*64, levelOffsetY + j*64);
               }
               //Player Tile
               else if (data[i][j].equals("PT1"))
               {
                  gc.drawImage(Water, levelOffsetX + i*64, levelOffsetY + j*64);
                  //set the player position, only once
                  if (!drewPlayer)
                  {
                     
                     
                     Px = levelOffsetX + i*64 + 32;
                     Py = levelOffsetY + j*64 + 32;
                     //boolean to hold if the player's position has been set yet
                     drewPlayer = true;
                  }
               }
            }
         }
         
         //check bounds left
         //if the player is trying to move outside the array
         if ((Px - 33 - levelOffsetX) <= 0)
         {
            canMoveLeft = false;
            //If the current tile is an exit tile and youre trying to leave
            if (data[(Px - levelOffsetX)/64][(Py - levelOffsetY)/64].equals("XT1"))
            {
               //reset the visuals
               drewPlayer = false;
                  
               //Move to the next level
               if (currentLevel == L1)
               {
                  currentLevel = L2;
               }
            }
         }
         else
         {
            //if the players left is NOT a walkable tile
            if (((!data[(Px - 33 - levelOffsetX)/64][(Py - 31 - levelOffsetY)/64].equals("PT1")) && (!data[(Px - 33 - levelOffsetX)/64][(Py - 31 - levelOffsetY)/64].equals("T1")) && (!data[(Px - 33 - levelOffsetX)/64][(Py - 31 - levelOffsetY)/64].equals("XT1"))) || ((!data[(Px - 33 - levelOffsetX)/64][(Py + 31 - levelOffsetY)/64].equals("PT1")) && (!data[(Px - 33 - levelOffsetX)/64][(Py + 31 - levelOffsetY)/64].equals("T1")) && (!data[(Px - 33 - levelOffsetX)/64][(Py + 31 - levelOffsetY)/64].equals("XT1"))))
               canMoveLeft = false;
            //if the players left is a walkable tile
            else 
               canMoveLeft = true;
         }
            
         //check bounds right
         //if the player is trying to move outside the array
         if ((Px + 33  - levelOffsetX)/64 >= x)
         {
            canMoveRight = false;
            //If the current tile is an exit tile and youre trying to leave
            if (data[(Px - levelOffsetX)/64][(Py - levelOffsetY)/64].equals("XT1"))
            {
               //reset the visuals
               drewPlayer = false;
                  
               //Move to the next level
               if (currentLevel == L1)
               {
                  currentLevel = L2;
               }
            }
         }
         else
         {
            //if the players right is NOT a walkable tile
            if (((!data[(Px + 33 - levelOffsetX)/64][(Py - 31 - levelOffsetY)/64].equals("PT1")) && (!data[(Px + 33 - levelOffsetX)/64][(Py - 31 - levelOffsetY)/64].equals("T1")) && (!data[(Px + 33 - levelOffsetX)/64][(Py - 31 - levelOffsetY)/64].equals("XT1"))) || ((!data[(Px + 33 - levelOffsetX)/64][(Py + 31 - levelOffsetY)/64].equals("PT1")) && (!data[(Px + 33 - levelOffsetX)/64][(Py + 31 - levelOffsetY)/64].equals("T1")) && (!data[(Px + 33 - levelOffsetX)/64][(Py + 31 - levelOffsetY)/64].equals("XT1"))))               canMoveRight = false;
            //if the players right is a walkable tile
            else 
               canMoveRight = true;
         }
         
         //check bounds up
         //if the player is trying to move outside the array
         //System.out.println((Py-32-levelOffsetY)/64);
         if ((Py - 33 - levelOffsetY) < 0)
         {
            canMoveUp = false;
            //If the current tile is an exit tile and youre trying to leave
            if (data[(Px - levelOffsetX)/64][(Py - levelOffsetY)/64].equals("XT1"))
            {
               //reset the visuals
               drewPlayer = false;
                  
               //Move to the next level
               if (currentLevel == L1)
               {
                  currentLevel = L2;
               }
            }
         }
         else
         {
            //if the players up is NOT a walkable tile
            if (((!data[(Px - 31 - levelOffsetX)/64][(Py - 33 - levelOffsetY)/64].equals("PT1")) && (!data[(Px - 31 - levelOffsetX)/64][(Py - 33 - levelOffsetY)/64].equals("T1")) && (!data[(Px - 31 - levelOffsetX)/64][(Py - 33 - levelOffsetY)/64].equals("XT1"))) || ((!data[(Px + 31 - levelOffsetX)/64][(Py - 33 - levelOffsetY)/64].equals("PT1")) && (!data[(Px + 31 - levelOffsetX)/64][(Py - 33 - levelOffsetY)/64].equals("T1")) && (!data[(Px + 31 - levelOffsetX)/64][(Py - 33 - levelOffsetY)/64].equals("XT1"))))
               canMoveUp = false;
            //if the players up is a walkable tile
            else 
               canMoveUp = true;
         }
            
         //check bounds Down
         //if the player is trying to move outside the array
         if ((Py + 33 - levelOffsetY)/64 >= y)
         {
            canMoveDown = false;
            //If the current tile is an exit tile and youre trying to leave
            if (data[(Px - levelOffsetX)/64][(Py - levelOffsetY)/64].equals("XT1"))
            {
               //reset the visuals
               drewPlayer = false;
                  
               //Move to the next level
               if (currentLevel == L1)
               {
                  currentLevel = L2;
               }
            }
         }
         else
         {
            //if the players down is NOT a walkable tile
            if (((!data[(Px - 31 - levelOffsetX)/64][(Py + 33 - levelOffsetY)/64].equals("PT1")) && (!data[(Px - 31 - levelOffsetX)/64][(Py + 33 - levelOffsetY)/64].equals("T1")) && (!data[(Px - 31 - levelOffsetX)/64][(Py + 33 - levelOffsetY)/64].equals("XT1"))) || ((!data[(Px + 31 - levelOffsetX)/64][(Py + 33 - levelOffsetY)/64].equals("PT1")) && (!data[(Px + 31 - levelOffsetX)/64][(Py + 33 - levelOffsetY)/64].equals("T1")) && (!data[(Px + 31 - levelOffsetX)/64][(Py + 33 - levelOffsetY)/64].equals("XT1"))))
               canMoveDown = false;
            //if the players down is a walkable tile
            else 
               canMoveDown = true;
         }

         //deal with objects
         L1.getObjects();
      }
      
      //count every five frames, swap image every cycle
      frameCount++;
      if (frameCount > 4)
      {
         frameCount = 0;
         if (PlayerImage == Player1)
            PlayerImage = Player2;
         else if (PlayerImage == Player2)
            PlayerImage = Player1;
      }
      //Draw player at its current position over the background
      //gc.drawImage(PlayerImage, Px, Py);
      gc.fillRect(Px - 32, Py - 32, 64, 64);
      

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
                  Px -= 4;
            }
            //Right
            if (event.getCode() == KeyCode.D)
            {
               if (canMoveRight)
                  Px += 4;
            }
            //Up
            if (event.getCode() == KeyCode.W)
            {
               if (canMoveUp)
                  Py -= 4;
            }
            //Down
            if (event.getCode() == KeyCode.S)
            {
               if (canMoveDown)
                  Py += 4;
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