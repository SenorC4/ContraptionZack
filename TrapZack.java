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
  
  int xDiff;
  int yDiff;
  
  int test = 0;
  
  boolean reloaded = false;
  
  //Player x and y positions
  int Px = 400;
  int Py = 600;
  //Player state machine holder
  String state = "inControl";
  //which direction is the player being sprung
  String springDir = "R";
  //how fast the player is being sprung
  int launch = 20;
  //boolean holding when the player is first drawn
  boolean drewPlayer = false;
  //variables holding directional access
  boolean canMoveLeft = true;
  boolean canMoveRight = true;
  boolean canMoveUp = true;
  boolean canMoveDown = true;
  //variable to count the current frame rotation
  int frameCount = 0;
  //count frames for gate open/close
  int gateCount = 0;
  int gateNumber = 0;
  int oldGate = 0;
  //boolean keeping track of initializing objects for the level
  boolean initializedObjects = false;
   //boolean to keep track if objects were grabbed
  boolean loaded = false;
  int numObjects = 0;
  String[][] objects;
  
  //ContraptionZacLevel
  ContraptionZacLevel L1 = new ContraptionZacLevel("Assets/Level1.txt");
  //ContraptionZacLevel L2 = new ContraptionZacLevel("Assets/Level2.txt");
  //ContraptionZacLevel L3 = new ContraptionZacLevel("Assets/Level3.txt");
  //ContraptionZacLevel L4 = new ContraptionZacLevel("Assets/Level4.txt");


  ContraptionZacLevel currentLevel = L1;
   
  StackPane root = new StackPane();
  Canvas canvas = new Canvas(800, 800);
  
  GraphicsContext gc = canvas.getGraphicsContext2D();

  
  AnimationTimer ta;
  

  //assets for pause menu
  ArrayList<mechanism> mechanisms = new ArrayList<mechanism>();
  int numberOfSaves = 0;
  TextInputDialog td = new TextInputDialog();
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
  TextInputDialog ld = new TextInputDialog();
  Button newGame = new Button("New Game");
  Button loadGame = new Button("Load Game");
  Font comic = new Font("Comic Sans MS", 50);
  Font font = new Font(20);
  Label title = new Label("Contraption Zack");
  VBox titleBox = new VBox(200, title, newGame, loadGame);
  
  //assets for load menu
  Button save1 = new Button("");
  Button save2 = new Button("");
  Button save3 = new Button("");
  Button save4 = new Button("");
  Button save5 = new Button("");
  Button save6 = new Button("");
  Button save7 = new Button("");
  Button save8 = new Button("");
  Button save9 = new Button("");
  Button save10 = new Button("");
  
  VBox saveBox = new VBox(0, save1, save2, save3, save4, save5, save6, save7, save8, save9, save10);
  
  //game
  Image Water = new Image("Assets/Water.png", false);
  Image Player1 = new Image("Assets/Boat1.png", false);
  Image Player2 = new Image("Assets/Boat2.png", false);
  Image Henny = new Image("Assets/Henessy1.png", false);
  Image Henny1 = new Image("Assets/Henessy2.png", false);
  
  //exit arrows
  Image Arrow = new Image("Assets/Arrow.png", false);
  Image ArrowL = new Image("Assets/ArrowLeft.png", false);
  Image ArrowR = new Image("Assets/ArrowRight.png", false);
  Image ArrowB = new Image("Assets/ArrowBack.png", false);
  
  //spikessssss
  Image BlueSpike = new Image("Assets/SpikesBlue.png", false);
  Image vBlueSpike = new Image("Assets/vSpikesBlue.png", false);
  Image GreenSpike = new Image("Assets/SpikesGreen.png", false);
  Image vGreenSpike = new Image("Assets/vSpikesGreen.png", false);
  Image OrangeSpike = new Image("Assets/SpikesOrange.png", false);
  Image vOrangeSpike = new Image("Assets/vSpikesOrange.png", false);
  Image PurpleSpike = new Image("Assets/SpikesPurple.png", false);
  Image vPurpleSpike = new Image("Assets/vSpikesPurple.png", false);
  Image YellowSpike = new Image("Assets/SpikesYellow.png", false);
  Image vYellowSpike = new Image("Assets/vSpikesYellow.png", false);
  Image GraySpike = new Image("Assets/SpikesGray.png", false);
  Image vGraySpike = new Image("Assets/vSpikesGray.png", false);
  
  Image DownSpike = new Image("Assets/SpikesDown.png", false);
  Image vDownSpike = new Image("Assets/vSpikesDown.png", false);
  
  Image halfWall = new Image("Assets/halfWall.png", false);

  //buttons
  Image BlueButton = new Image("Assets/ButtonBlueUnpressed.png", false);
  Image BlueButtonPressed = new Image("Assets/ButtonBluePressed.png", false);
  Image GreenButtonPressed = new Image("Assets/ButtonGreenPressed.png", false);
  Image GreenButton = new Image("Assets/ButtonGreenUnpressed.png", false);
  Image OrangeButtonPressed = new Image("Assets/ButtonOrangePressed.png", false);
  Image OrangeButton = new Image("Assets/ButtonOrangeUnpressed.png", false);
  Image PurpleButton = new Image("Assets/ButtonPurpleUnpressed.png", false);
  Image PurpleButtonPressed = new Image("Assets/ButtonPurplePressed.png", false);
  Image YellowButton = new Image("Assets/ButtonYellowUnpressed.png", false);
  Image YellowButtonPressed = new Image("Assets/ButtonYellowPressed.png", false);
  Image GrayButton = new Image("Assets/ButtonGrayUnpressed.png", false);
  Image GrayButtonPressed = new Image("Assets/ButtonGrayPressed.png", false);
  
  
   Image PlayerImage = Player1;
   Image Bottle = Henny;
  
  //Objects
  ArrayList<GameSpring> listOfSprings = new ArrayList<GameSpring>();
  ArrayList<GameButton> buttonList = new ArrayList<GameButton>();
  ArrayList<GameSpike> spikeList = new ArrayList<GameSpike>();
  ArrayList<GameGate> gateList = new ArrayList<GameGate>();   
   public void start(Stage stage){
      
      //escape menu
      save1.setOnAction(new ButtonListener());
      save2.setOnAction(new ButtonListener());
      save3.setOnAction(new ButtonListener());
      save4.setOnAction(new ButtonListener());
      save5.setOnAction(new ButtonListener());
      save6.setOnAction(new ButtonListener());
      save7.setOnAction(new ButtonListener());
      save8.setOnAction(new ButtonListener());
      save9.setOnAction(new ButtonListener());
      save10.setOnAction(new ButtonListener());
      
      resume.setMinWidth(100);
      save.setMinWidth(100);
      load.setMinWidth(100);
      restartA.setMinWidth(100);
      restartL.setMinWidth(100);
      end.setMinWidth(100);
      
      save.setOnAction(new ButtonListener());
      load.setOnAction(new ButtonListener());
      resume.setOnAction(new ButtonListener());
      end.setOnAction(new ButtonListener());
      restartA.setOnAction(new ButtonListener());
      restartL.setOnAction(new ButtonListener());
      root.setOnKeyPressed(new KeyListenerDown());
      
      
      
   
      Scene scene = new Scene(root, 800, 800);      
      root.getChildren().add(canvas);
            
      //title
      title.setFont(comic);
      newGame.setFont(comic);
      loadGame.setFont(comic);
      vbox.setAlignment(Pos.CENTER); 
      newGame.setOnAction(new ButtonListener());
      loadGame.setOnAction(new ButtonListener());
      titleBox.setAlignment(Pos.CENTER);
      root.getChildren().add(titleBox);
      
            
      //default javafx stuff
      stage.setScene(scene);      
      stage.setTitle("Contraption Zack");      
      stage.show();
      
      //Keyboard lisetner
      
      
      //request focus for canvas
      canvas.requestFocus();
      //titleBox.requestFocus();
      newGame.requestFocus();
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
      root.setStyle("-fx-background-color: black");
      //System.out.println(currentLevel.getName());
     /* System.out.println(mechanisms.size());
      System.out.println(listOfSprings.size());
      System.out.println(buttonList.size());
      System.out.println(spikeList.size());*/
      //if not in the title screen
      if(titleMenu == false){
         //load the first level, get the data from the text file
         String[][] data = currentLevel.getData();
         //get the dimensions of the text for the for loops
         int x = currentLevel.getX();
         int y = currentLevel.getY();
         
         gc.setFill(Color.BLACK);
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
               //wall tile (Level 1 only)
               if (data[i][j].equals("W1"))
               {
                  //gc.drawImage(Water, levelOffsetX + i*64, levelOffsetY + j*64);
                  gc.setFill(Color.YELLOW);
                  gc.fillRect(levelOffsetX + i*64, levelOffsetY + j*64, 64, 64);
                  gc.setFill(Color.BLACK);
               }
               //Exit Arrow Tile
               else if (data[i][j].equals("XT1"))
               {
                  gc.drawImage(Water, levelOffsetX + i*64, levelOffsetY + j*64);
                  gc.drawImage(Arrow, levelOffsetX + i*64, levelOffsetY + j*64);
               }
               else if (data[i][j].equals("XFT1"))
               {
                  gc.drawImage(Water, levelOffsetX + i*64, levelOffsetY + j*64);
                  gc.drawImage(Arrow, levelOffsetX + i*64, levelOffsetY + j*64);
               }
               else if (data[i][j].equals("XLT1"))
               {
                  gc.drawImage(Water, levelOffsetX + i*64, levelOffsetY + j*64);
                  gc.drawImage(ArrowL, levelOffsetX + i*64, levelOffsetY + j*64);
               }
               else if (data[i][j].equals("XRT1"))
               {
                  gc.drawImage(Water, levelOffsetX + i*64, levelOffsetY + j*64);
                  gc.drawImage(ArrowR, levelOffsetX + i*64, levelOffsetY + j*64);
               }
               //Player Tile
               else if (data[i][j].equals("PT1"))
               {
                  gc.drawImage(Water, levelOffsetX + i*64, levelOffsetY + j*64);
                  //set the player position, only once
                  if (!drewPlayer)
                  {
                     
                     
                     Px = levelOffsetX + currentLevel.getPx()*64 + 32;
                     Py = levelOffsetY + currentLevel.getPy()*64 + 32;
                     //boolean to hold if the player's position has been set yet
                     drewPlayer = true;
                  }
               }
            }
         }
         
         //Draw objects
          if(!loaded){
            objects = currentLevel.getObjects();
            numObjects = currentLevel.getNumObjects();
            loaded = true;
         }
         
         
         if (!initializedObjects)
         {
            //go through the array
            for (int i = 0; i < numObjects; i++)
            {
               //springs
               if (objects[i][0].equals("springLeft") || objects[i][0].equals("springRight"))
               {
                  GameSpring gs = new GameSpring(Integer.parseInt(objects[i][1]), Integer.parseInt(objects[i][2]), objects[i][3], objects[i][4]);
                  System.out.println(objects[i][4]);
                  mechanisms.add(gs);
                  listOfSprings.add(gs);
               }
               
               if (objects[i][0].equals("Button"))
               {  
                               
                  GameButton gb = new GameButton(Double.parseDouble(objects[i][1]), Double.parseDouble(objects[i][2]), objects[i][3], objects[i][4]);
                  mechanisms.add(gb);
                  buttonList.add(gb);
                  
               }
               
               if (objects[i][0].equals("Spike"))
               {
                  GameSpike gsp = new GameSpike(Double.parseDouble(objects[i][1]), Double.parseDouble(objects[i][2]), objects[i][3], objects[i][4]);
                  mechanisms.add(gsp);
                  spikeList.add(gsp);
               }
               
                if (objects[i][0].equals("gate"))
               {
                   GameGate ggate = new GameGate(Double.parseDouble(objects[i][1]), Double.parseDouble(objects[i][2]), objects[i][3], objects[i][4]);
                   mechanisms.add(ggate);
                   gateList.add(ggate);
               }
               
            }
            initializedObjects = true;
            reloaded = false;
         }
                  
         //check bounds left
         //if the player is trying to move outside the array
         if ((Px - 17 - levelOffsetX) <= 0)
         {
            canMoveLeft = false;
            //If the current tile is an exit tile and youre trying to leave
            if (data[(Px - levelOffsetX)/64][(Py - levelOffsetY)/64].matches("X.T1"))
            {
               //reset the visuals
               drewPlayer = false;
               
               //reset the object arrays
               listOfSprings.clear();
               mechanisms.clear();
                 
               if (currentLevel.getLast().equals("Assets/Level2.txt"))
               {
                  currentLevel.setPx(3);//3
                  currentLevel.setPy(1);//1
                  currentLevel.saveLevel("Assets/Level2AutoSave.txt", mechanisms);
               } 
               else if (currentLevel.getCurrent().equals("Assets/Level3.txt"))
               {
                     currentLevel.setPx(1);//3
                     currentLevel.setPy(1);//1
                     currentLevel.saveLevel("Assets/Level2AutoSave.txt", mechanisms);
               } 
               //load next level
               currentLevel = new ContraptionZacLevel(currentLevel.getNext());
               initializedObjects = false;
               loaded = false;
            }
         }
         else
         {
            if (state == "inControl")
            {
               //if the players left is NOT a walkable tile
               if (((!data[(Px - 17 - levelOffsetX)/64][(Py - 15 - levelOffsetY)/64].equals("PT1")) && (!data[(Px - 17 - levelOffsetX)/64][(Py - 15 - levelOffsetY)/64].equals("T1")) && (!data[(Px - 17 - levelOffsetX)/64][(Py - 15 - levelOffsetY)/64].matches("X.T1"))) || ((!data[(Px - 17 - levelOffsetX)/64][(Py + 15 - levelOffsetY)/64].equals("PT1")) && (!data[(Px - 17 - levelOffsetX)/64][(Py + 15 - levelOffsetY)/64].equals("T1")) && (!data[(Px - 17 - levelOffsetX)/64][(Py + 15 - levelOffsetY)/64].matches("X.T1"))))
                  canMoveLeft = false;
               //if the players left is a walkable tile
               else 
                  canMoveLeft = true;
            }
         }
            
         //check bounds right
         //if the player is trying to move outside the array
         if ((Px + 17  - levelOffsetX)/64 >= x)
         {
            canMoveRight = false;
            //If the current tile is an exit tile and youre trying to leave
            if (data[(Px - levelOffsetX)/64][(Py - levelOffsetY)/64].matches("X.T1"))
            {
               //reset the visuals
               drewPlayer = false;
               
               //reset the object arrays
               listOfSprings.clear();
               mechanisms.clear();   
               //Move to the next level
               
               if (currentLevel.getLast().equals("Assets/Level2.txt"))
                  {
                     
                     currentLevel = new ContraptionZacLevel("Assets/Level2AutoSave.txt");
                  }
               else if (currentLevel.getLast().equals("Assets/Level3.txt"))
               {
                  currentLevel = new ContraptionZacLevel("Assets/Level3AutoSave.txt");
               }

               //currentLevel = new ContraptionZacLevel(currentLevel.getLast()+"AutoSave.txt");
               initializedObjects = false;
               loaded = false;

            }
         }
         else
         {
            if (state == "inControl")
            {
               //if the players right is NOT a walkable tile
               if (((!data[(Px + 17 - levelOffsetX)/64][(Py - 15 - levelOffsetY)/64].equals("PT1")) && (!data[(Px + 17 - levelOffsetX)/64][(Py - 16 - levelOffsetY)/64].equals("T1")) && (!data[(Px + 17 - levelOffsetX)/64][(Py - 15 - levelOffsetY)/64].matches("X.T1"))) || ((!data[(Px + 17 - levelOffsetX)/64][(Py + 15 - levelOffsetY)/64].equals("PT1")) && (!data[(Px + 17 - levelOffsetX)/64][(Py + 15 - levelOffsetY)/64].equals("T1")) && (!data[(Px + 17 - levelOffsetX)/64][(Py + 15 - levelOffsetY)/64].matches("X.T1"))))              
                   canMoveRight = false;
               //if the players right is a walkable tile
               else 
                  canMoveRight = true;
            }
         }
         
         //check bounds up
         //if the player is trying to move outside the array
         //System.out.println((Py-32-levelOffsetY)/64);
         if ((Py - 17 - levelOffsetY) <= 0)
         {
            canMoveUp = false;
            //If the current tile is an exit tile and youre trying to leave
            if (data[(Px - levelOffsetX)/64][(Py - levelOffsetY)/64].matches("X.T1"))
            {
               //reset the visuals
               drewPlayer = false;
               
               //reset the object arrays
               listOfSprings.clear();
               mechanisms.clear();   
               
               if (currentLevel.getCurrent().equals("Assets/Level2.txt"))
               {
                     currentLevel.setPx(3);//3
                     currentLevel.setPy(1);//1
                     currentLevel.saveLevel("Assets/Level2AutoSave.txt", mechanisms);
               }
               else if (currentLevel.getCurrent().equals("Assets/Level3.txt"))
               {
                     currentLevel.setPx(1);//3
                     currentLevel.setPy(1);//1
                     currentLevel.saveLevel("Assets/Level2AutoSave.txt", mechanisms);
               }
               //Move to the next level
               currentLevel = new ContraptionZacLevel(currentLevel.getNext());
               initializedObjects = false;
               loaded = false;
            }
         }
         else
         {
            if (state == "inControl")
            {
               //if the players up is NOT a walkable tile
               if (((!data[(Px - 15 - levelOffsetX)/64][(Py - 17 - levelOffsetY)/64].equals("PT1")) && (!data[(Px - 15 - levelOffsetX)/64][(Py - 17 - levelOffsetY)/64].equals("T1")) && (!data[(Px - 15 - levelOffsetX)/64][(Py - 17 - levelOffsetY)/64].matches("X.T1"))) || ((!data[(Px + 15 - levelOffsetX)/64][(Py - 17 - levelOffsetY)/64].equals("PT1")) && (!data[(Px + 15 - levelOffsetX)/64][(Py - 17 - levelOffsetY)/64].equals("T1")) && (!data[(Px + 15 - levelOffsetX)/64][(Py - 17 - levelOffsetY)/64].matches("X.T1"))))
                  canMoveUp = false;
               //if the players up is a walkable tile
               else 
                  canMoveUp = true;
            }
         }
            
         //check bounds Down
         //if the player is trying to move outside the array
         if ((Py + 17 - levelOffsetY)/64 >= y)
         {
            canMoveDown = false;
            //If the current tile is an exit tile and youre trying to leave
            if (data[(Px - levelOffsetX)/64][(Py - levelOffsetY)/64].matches("X.T1") || data[(Px - levelOffsetX)/64][(Py - levelOffsetY)/64].equals("PT1"))
            {
               //reset the visuals
               drewPlayer = false;
               
               //reset the object arrays
               listOfSprings.clear();
               mechanisms.clear();   
               //Move to the next level
               if(currentLevel != L1 && !currentLevel.getCurrent().equals("Assets/Level2.txt")){
               //System.out.println("Test");
                  if (currentLevel.getLast().equals("Assets/Level2.txt"))
                  {
                     
                     currentLevel = new ContraptionZacLevel("Assets/Level2AutoSave.txt");
                  }
                  else if (currentLevel.getLast().equals("Assets/Level3.txt"))
                  {
                  currentLevel = new ContraptionZacLevel("Assets/Level3AutoSave.txt");
                  }
                  //currentLevel = new ContraptionZacLevel(currentLevel.getLast()+"AutoSave.txt");
                  initializedObjects = false;
                  loaded = false;
               }
               initializedObjects = false;
                  loaded = false;
            }
         }
         else
         {
            if (state == "inControl")
            {
               //if the players down is NOT a walkable tile
               if (((!data[(Px - 15 - levelOffsetX)/64][(Py + 17 - levelOffsetY)/64].equals("PT1")) && (!data[(Px - 15 - levelOffsetX)/64][(Py + 17 - levelOffsetY)/64].equals("T1")) && (!data[(Px - 15 - levelOffsetX)/64][(Py + 17 - levelOffsetY)/64].matches("X.T1"))) || ((!data[(Px + 15 - levelOffsetX)/64][(Py + 17 - levelOffsetY)/64].equals("PT1")) && (!data[(Px + 15 - levelOffsetX)/64][(Py + 17 - levelOffsetY)/64].equals("T1")) && (!data[(Px + 15 - levelOffsetX)/64][(Py + 17 - levelOffsetY)/64].matches("X.T1"))))
                  canMoveDown = false;
               //if the players down is a walkable tile
               else 
                  canMoveDown = true;
            }
         }
         
         //draw all objects 
         //go through the array
         for (int i = 0; i < numObjects; i++)
         {
                          
            if (objects[i][0].equals("Button"))
            {  
               if (objects[i][4].equals("up"))
               {
                  switch(objects[i][3])
                  {
                     case "B":
                        gc.drawImage(BlueButtonPressed, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                        break;
                     case "Y":
                        gc.drawImage(YellowButtonPressed, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                        break;
                     case "O":
                        gc.drawImage(OrangeButtonPressed, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                        break;
                     case "P":
                        gc.drawImage(PurpleButtonPressed, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                        break;
                     case "G":
                        gc.drawImage(GreenButtonPressed, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                        break;
                     case "Gr":
                        gc.drawImage(GrayButtonPressed, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                        break;
                  }
               }
               else
               {
                  switch(objects[i][3])
                  {
                     case "B":
                        gc.drawImage(BlueButton, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                        break;
                     case "Y":
                        gc.drawImage(YellowButton, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                        break;
                     case "O":
                        gc.drawImage(OrangeButton, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                        break;
                     case "P":
                        gc.drawImage(PurpleButton, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                        break;
                     case "G":
                        gc.drawImage(GreenButton, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                        break;
                     case "Gr":
                        gc.drawImage(GrayButton, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                        break;

                  }                  
               }                   
            }
            
            if (objects[i][0].equals("Spike"))
            {
                if(objects[i][4].equals("down")){
                    if(objects[i][3].matches("v.")){
                        gc.drawImage(vDownSpike, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);

                     }else{
                        gc.drawImage(DownSpike, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                     }
                }else{
                  switch(objects[i][3]){
                        case "B":
                           gc.drawImage(BlueSpike, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                           break;
                        case "vB":
                           gc.drawImage(vBlueSpike, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                           break;
                        case "Y":
                           gc.drawImage(YellowSpike, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                           break;
                        case "O":
                           gc.drawImage(OrangeSpike, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                           break;
                        case "P":
                           gc.drawImage(PurpleSpike, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                           break;
                        case "G":
                           gc.drawImage(GreenSpike, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                           break;
                         case "vG":
                           gc.drawImage(vGreenSpike, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                           break;
                        case "Gr":
                           gc.drawImage(GraySpike, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
                           break;    
                   }
                 }
             }
             
            if (objects[i][0].equals("halfWall"))
            {
               gc.drawImage(halfWall, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
            }
            
            gateCount++;
            if (objects[i][0].equals("gate"))
            {
                
               if(currentLevel.getCurrent().equals("Assets/Level3.txt")){
                  
                  if(gateCount > 6000){
                     
                     if(Integer.parseInt(objects[i][3]) == oldGate){
                        objects[i][4] = "down";
                        System.out.println(oldGate + " down");
                        oldGate++;
                        gateCount = 0;
                     }
                     
                     if(oldGate > 3){
                        oldGate = 0;
                     }
                     
                     
                    
                  }
               }
               if(objects[i][4].equals("up")){
                  gc.drawImage(vDownSpike, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
               }
            }
                         
            if (objects[i][0].equals("JukeBox"))
            {
               gc.drawImage(Bottle, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
            }
            
            if (objects[i][0].equals("boat"))
            {
               gc.drawImage(Bottle, levelOffsetX + Float.parseFloat(objects[i][1])*64, levelOffsetY + Float.parseFloat(objects[i][2])*64);
            }

         }
         
         
         
         
         //check all springs
         if (reloaded == false)
         {
         //System.out.println("test");
            for (int i = 0; i < listOfSprings.size(); i++)
            {
               GameSpring gs = listOfSprings.get(i);
              // System.out.println(gs.getState());
               //change color based upon whether it is sprung or not
               if (gs.getState())
                  gc.setFill(Color.GRAY);
               else
                  gc.setFill(Color.WHITE);
               gc.fillRect(levelOffsetX + gs.getPx()*64, levelOffsetY + gs.getPy()*64, 64, 64);
               gc.setFill(Color.BLACK);
               //System.out.println(Py - levelOffsetY - gs.getPy() - 16);
               //if playercollides with middle of the spring
               if (gs.getState())
               {
                  if (state != "sprung")
                  {
                     //differences in x and y from the center of the object
                     xDiff = (Px - levelOffsetX - gs.getPx()*64 - 32);
                     yDiff = (Py - levelOffsetY - gs.getPy()*64 - 32);
                     //System.out.println(xDiff + " " + yDiff);
                     //if canMoveRight already false, dont do the check
                     //check right
                     if (canMoveRight)
                     {
                        if (((xDiff >= -48) && (xDiff <= 0)) && ((yDiff < 48) && (yDiff > -48)))
                           canMoveRight = false;
                        else
                           canMoveRight = true;
                     }
                     //check left
                     if (canMoveLeft)
                     {
                        if (((xDiff <= 48) && (xDiff >= 0)) && ((yDiff < 48) && (yDiff > -48)))
                           canMoveLeft = false;
                        else
                           canMoveLeft = true;
                     }
                     //check down
                     if (canMoveDown)
                     {
                        if (((yDiff >= -48) && (yDiff <= 0)) && ((xDiff < 48) && (xDiff > -48)))
                           canMoveDown = false;
                        else
                           canMoveDown = true;
                     }
                     //check up
                     if (canMoveUp)
                     {
                        if (((yDiff <= 48) && (yDiff >= 0)) && ((xDiff < 48) && (xDiff > -48)))
                           canMoveUp = false;
                        else
                           canMoveUp = true;
                     }
                  }
               }
               else
               {
                  if (((Px - levelOffsetX - gs.getPx()*64 - 32 < 32) && (Px - levelOffsetX - gs.getPx()*64 - 32 > -32)) && ((Py - levelOffsetY - gs.getPy()*64 - 32 < 32) && (Py - levelOffsetY - gs.getPy()*64 - 32 > -32)))
                  {
                     gs.setSprung(true);
                     state = "sprung";
                     springDir = gs.getFacing();
                     Px = gs.getPx()*64 + levelOffsetX + 32;
                     Py = gs.getPy()*64 + levelOffsetY + 32;
                     launch = 40;
                  }
               }
            }
         }
         
         
         
         
         
         for (int i = 0; i < numObjects; i++)
         {
            if (objects[i][0].equals("halfWall"))
            {
            //System.out.println("Testiing");
                xDiff = (int)(Px - levelOffsetX - (Double.parseDouble(objects[i][1])*64 + 32));
                yDiff = (int)(Py - levelOffsetY - (Double.parseDouble(objects[i][2])*64 + 16));
                //System.out.println(objects[i][1] + "   "+ Double.parseDouble(objects[i][2]) + "   "+ yDiff);
                //System.out.println(objects[i][0] + " " + ((Double.parseDouble(objects[i][1])*64) + " " + (Double.parseDouble(objects[i][2])*64)));
                     if (canMoveRight)
                     {
                        if (((xDiff >= -48) && (xDiff <= 0)) && ((yDiff < 32) && (yDiff > -32)))
                           canMoveRight = false;
                        else
                           canMoveRight = true;
                     }
                     //check left
                     if (canMoveLeft)
                     {
                        if (((xDiff <= 48) && (xDiff >= 0)) && ((yDiff < 32) && (yDiff > -32))){
                           canMoveLeft = false;
                           //System.out.println("baaad " +xDiff + "   " + yDiff);
                           }
                        else{
                           canMoveLeft = true;
                           //System.out.println("goooood " + xDiff + "   " + yDiff);
                           }
                     }
                     //check down
                     if (canMoveDown)
                     {
                        if (((yDiff >= -32) && (yDiff <= 0)) && ((xDiff <= 48) && (xDiff > -48)))
                           canMoveDown = false;
                        else
                           canMoveDown = true;
                     }
                     //check up
                     if (canMoveUp)
                     {
                        if (((yDiff <= 32) && (yDiff >= 0)) && ((xDiff < 48) && (xDiff > -48))){
                           canMoveUp = false;
                           //System.out.println("baaad " +xDiff + "   " + yDiff);
                           }
                        else
                           canMoveUp = true;
                           //System.out.println("goooood " + xDiff + "   " + yDiff);
                     }
                  
               
               
            }
         
            if (objects[i][0].equals("Button"))
            {
               if (objects[i][4].equals("up"))
               {
                  if (((Px - levelOffsetX - (Double.parseDouble(objects[i][1]) *64) - 32 < 32) && (Px - levelOffsetX - (Double.parseDouble(objects[i][1]) *64) - 32 > -32)) && ((Py - levelOffsetY - (Double.parseDouble(objects[i][2]) *64) - 32 < 32) && (Py - levelOffsetY - (Double.parseDouble(objects[i][2]) *64) - 32 > -32)))
                  {
                     objects[i][4] = "down";
                     for (int j = 0; j < numObjects; j++)
                     {
                        if (objects[j][0].equals("Spike"))
                        {
                           if (objects[i][3].equals(objects[j][3]))
                           {
                              if (objects[j][4].equals("up"))
                              {
                                 objects[j][4] = "down";
                              }
                              else if (objects[j][4].equals("down"))
                              {
                                 objects[j][4] = "up";
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
         
         
         
         
         
         
         //During spring launch   
         if (state == "sprung")
         {
            //Right
            if (springDir.equals("R"))
            {
               if (!canMoveRight)
               {
                  state = "inControl";
               }
               else
               {
                  Px += 4;
                  launch--;
                  if (launch == 0)
                  {
                     state = "inControl";
                  }
               }
            }
            
            if (springDir.equals("L"))
            {
               if (!canMoveLeft)
               {
                  state = "inControl";
               }
               else
               {
                  Px -= 4;
                  launch--;
                  if (launch == 0)
                  {
                     state = "inControl";
                  }
               }
            }

            
            
            
         }
      }
            
      //count every five frames, swap image every cycle
      frameCount++;
      if (frameCount > 20)
      {
         frameCount = 0;
         if (PlayerImage == Player1)
            PlayerImage = Player2;
         else if (PlayerImage == Player2)
            PlayerImage = Player1;
          //animate bottle
         if (Bottle == Henny)
            Bottle = Henny1;
         else{
            Bottle = Henny;
         }
      }
      //Draw player at its current position over the background
      //gc.drawImage(PlayerImage, Px, Py);
      gc.setFill(Color.BROWN);
      gc.fillRect(Px - 16, Py - 16, 32, 32);
      gc.setFill(Color.BLACK);

      

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
         //default state
         if (state == "inControl")
         {
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
            currentLevel.setPx((Px/64)-2);
            currentLevel.setPy((Py/64)-2);
            td.setContentText("Type in the name for your saved game");
            td.showAndWait();
            String name = td.getEditor().getText();
            currentLevel.saveLevel("SaveGames/" + name + ".txt", mechanisms);
            
            
            switch (numberOfSaves)
            {
               case 0:
                  save1.setText(name);
                  numberOfSaves++;
                  break;
               case 1:
                  save2.setText(name);
                  numberOfSaves++;
                  break;
               case 2:
                  save3.setText(name);
                  numberOfSaves++;
                  break;
               case 3:
                  save4.setText(name);
                  numberOfSaves++;
                  break;
               case 4:
                  save5.setText(name);
                  numberOfSaves++;
                  break;
            
            }
            
            gamePaused = false;
            root.getChildren().remove(vbox);
            root.requestFocus();
         }
         else if (e.getSource() == restartA)
         {
            drewPlayer = false;
            gamePaused = false;
            //reloaded = true;
            initializedObjects = false;
            for (int i = 0; i < mechanisms.size(); i++)
            {
               (mechanisms.get(i)).reset();
            }
            mechanisms.clear();
            
            
            
            currentLevel = new ContraptionZacLevel(currentLevel.getCurrent());
            System.out.println(currentLevel.getName());
            root.getChildren().remove(vbox);
            root.requestFocus();
         }
         else if (e.getSource() == restartL)
         {
            drewPlayer = false;
            gamePaused = false;
            reloaded = true;
            //initializedObjects = false;
            currentLevel = new ContraptionZacLevel("Assets/Level1.txt");
            root.getChildren().remove(vbox);
            root.requestFocus();
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
            titleMenu = false;
            reloaded = true;
           
            root.getChildren().remove(titleBox);
            FlowPane saveList = new FlowPane();
            saveList.setAlignment(Pos.CENTER_LEFT);
            
            File directoryPath = new File("SaveGames/");
            //List of all files and directories
            String saves[] = directoryPath.list();
            for (int i=0; i<saves.length; i++) 
            {
               
               Label l = new Label(saves[i]);
               l.setFont(font);
               saveList.getChildren().add(l);
               l = new Label("      ");
               saveList.getChildren().add(l);
               
            }
            root.getChildren().add(saveList);
            ld.setContentText("Type in the saved game you want to load");
            ld.showAndWait();
            String chosenSave = ld.getEditor().getText();
            currentLevel = new ContraptionZacLevel("SaveGames/"+chosenSave);
            root.getChildren().remove(saveList);
            drewPlayer = false;
            //gamePaused = false;
            ta = new AnimationHandler();
            ta.start();
            root.requestFocus();
         }
         
         
         if (e.getSource() == load)
         {
            //resume.setText("Changed");
            root.getChildren().remove(vbox);
            root.getChildren().add(saveBox);
            save1.requestFocus();
            //System.out.println(save1.getText());
            //System.out.println(save1.getText().equals(""));      
         }
         
         
         //loading the saves
         if (e.getSource() == save1 && !save1.getText().equals(""))
         {
                  currentLevel = new ContraptionZacLevel("SaveGames/"+save1.getText()+".txt");
                  System.out.println(save1.getText());
                  root.getChildren().remove(saveBox);
                  drewPlayer = false;
                  gamePaused = false;
                  initializedObjects = false;
                  reloaded = false;
                  root.requestFocus();
         }
         if (e.getSource() == save2 && !save2.getText().equals(""))
         {
                  currentLevel = new ContraptionZacLevel("SaveGames/"+save2.getText()+".txt");
                  root.getChildren().remove(saveBox);
                  drewPlayer = false;
                  gamePaused = false;
                  reloaded = false;
                  root.requestFocus();
         }
         if (e.getSource() == save3 && !save3.getText().equals(""))
         {
                  currentLevel = new ContraptionZacLevel("SaveGames/"+save3.getText()+".txt");
                  root.getChildren().remove(saveBox);
                  drewPlayer = false;
                  gamePaused = false;
                  reloaded = false;
                  root.requestFocus();
         }
         if (e.getSource() == save4 && !save4.getText().equals(""))
         {
                  currentLevel = new ContraptionZacLevel("SaveGames/"+save4.getText()+".txt");
                  root.getChildren().remove(saveBox);
                  drewPlayer = false;
                  gamePaused = false;
                  reloaded = false;
                  root.requestFocus();
         }
         if (e.getSource() == save5 && !save5.getText().equals(""))
         {
                  currentLevel = new ContraptionZacLevel("SaveGames/"+save5.getText()+".txt");
                  root.getChildren().remove(saveBox);
                  drewPlayer = false;
                  gamePaused = false;
                  reloaded = false;
                  root.requestFocus();
         }
         if (e.getSource() == save6 && !save6.getText().equals(""))
         {
                  currentLevel = new ContraptionZacLevel("SaveGames/"+save6.getText()+".txt");
                  root.getChildren().remove(saveBox);
                  drewPlayer = false;
                  gamePaused = false;
                  reloaded = false;
                  root.requestFocus();
         }
         if (e.getSource() == save7 && !save7.getText().equals(""))
         {
                  currentLevel = new ContraptionZacLevel("SaveGames/"+save7.getText()+".txt");
                  root.getChildren().remove(saveBox);
                  drewPlayer = false;
                  gamePaused = false;
                  reloaded = false;
                  root.requestFocus();
         }
         if (e.getSource() == save8 && !save8.getText().equals(""))
         {
                  currentLevel = new ContraptionZacLevel("SaveGames/"+save8.getText()+".txt");
                  root.getChildren().remove(saveBox);
                  drewPlayer = false;
                  gamePaused = false;
                  reloaded = false;
                  root.requestFocus();
         }
         if (e.getSource() == save9 && !save9.getText().equals(""))
         {
                  currentLevel = new ContraptionZacLevel("SaveGames/"+save9.getText()+".txt");
                  root.getChildren().remove(saveBox);
                  drewPlayer = false;
                  gamePaused = false;
                  reloaded = false;
                  root.requestFocus();
         }
         if (e.getSource() == save10 && !save10.getText().equals(""))
         {
                  currentLevel = new ContraptionZacLevel("SaveGames/"+save10.getText()+".txt");
                  root.getChildren().remove(saveBox);
                  drewPlayer = false;
                  gamePaused = false;
                  reloaded = false;
                  root.requestFocus();
         }
         
         
      }
   }
   

}