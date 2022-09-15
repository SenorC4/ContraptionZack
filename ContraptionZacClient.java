import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import java.net.*;
import javafx.geometry.*;

public class ContraptionZacClient extends Application
{
   //initializes different objects needed
   //root flowpane holding all objects
   FlowPane root = new FlowPane();
   //object i created in seperate class to draw the Maze Walls
   MazeGameWalls wall = new MazeGameWalls();
   
   //variables for the 2D array list in order to check if there is a wall there or not
   int[][] posBox = new int[21][21];
   int posX = 0;
   int posY = 0;
   int startPosX = 0;
   int startPosY = 0;
   int precisePosX = 0;
   int precisePosY = 0;
   
   //variables related to drawing the walls and player
   int drawX = 0;
   int drawY = 0;
   int startX = 0;
   int startY = 0;
   int endX = 0;
   int endY = 0;
   
   public void start (Stage stage)
   {  
      //set the size and background and add load object to main flowpane
      root.setPrefSize(525,525);
      //add the wall to the root flowpane
      root.getChildren().add(wall);
      try
      {
         //create scanner to read in the file
         Scanner scan = new Scanner(new File("MazeGame.txt"));
      
         //take in variables while there still are stuff to read
         while (scan.hasNext())
         {
            //draw color of wall based on whether the variable was read in as a 0 or 1
            int box = scan.nextInt();
            if (box == 0)
            {
               wall.drawWalls(drawX, drawY, Color.WHITE);
            }
            else
            {
               wall.drawWalls(drawX, drawY, Color.BLACK);
            }
            //sets up 2D array
            posBox[posX][posY] = box;
            
            //set the starting position
            if ((box == 0) && (drawY == 0))
            {
               startX = drawX;
               startY = drawY;
               startPosX = posX;
               startPosY = posY;
            }
            //set the endng position
            else if ((box == 0) && (drawY == 500))
            {
               endX = drawX;
               endY = drawY;
            }
            
            //increment the variables
            drawX += 25;
            posX += 1;
            if (drawX >= 525)
            {
               drawX = 0;
               drawY += 25;
               posX = 0;
               posY += 1;
            }
         }
      }
      //if file is not found
      catch(FileNotFoundException fnfe)
      {
         System.out.println("File not found!");
      }
      
      wall.drawStart(startX,startY);
      
      //create vbox in order to recieve keyboard inputs and add to main flowpane
      VBox key = new VBox();
      root.getChildren().add(key);
      
      //set the event of the key being pressed down
      key.setOnKeyPressed(new KeyListenerDown());
      
      //initialize the scene with the root flowpane
      Scene scene = new Scene(root, 525, 525);
      stage.setScene(scene);
      stage.setTitle("BorderPane");
      stage.show();
      
      //set the inputs already selected on the vbox
      key.requestFocus();
   }
   
   //start the program
   public static void main(String[] args)
   {
      launch(args);
   } 
   
   //object that checks to see what keyboard input is given when pressed down
   public class KeyListenerDown implements EventHandler<KeyEvent>  
   {
      public void handle(KeyEvent event) 
      {
         //if the user presses Up and there is not a wall, move Up
         if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.Q || event.getCode() == KeyCode.E) 
         {
            if (startPosY-1 == -1)
            {
               
            }
            else
            {
               if ((precisePosX > 0) && ((posBox[startPosX-1][startPosY-1] == 1) || (posBox[startPosX+1][startPosY-1] == 1)))
               {
               
               }
               else if (posBox[startPosX][startPosY-1] == 0)
               {
                  wall.move(0,-1);
                  precisePosY++;
                  if (precisePosY > 24)
                  {
                     startPosY--;
                     precisePosY = 0;
                  }
                  
               }
               //else
               //{
               //   if (precisePosY < 0)
               //   {
               //      wall.move(0,-1);
               //      precisePosY++;
               //  }
              // }
           }
         }
         
         //if the user presses Left and there is not a wall, move Left
         if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.Q || event.getCode() == KeyCode.Z) 
         {
            //make sure dosen't leave screen on left side
            if (startPosX-1 == -1)
            {
               
            }
            else
            {  //precisePosY is y position within tile
               //
               if ((precisePosY > 0) && ((posBox[startPosX-1][startPosY-1] == 1) || (posBox[startPosX-1][startPosY+1] == 1)))
               {
               
               }
               //moves left one pxl if allowed to
               else if (posBox[startPosX-1][startPosY] == 0)
               {
                  wall.move(-1,0);
                  precisePosX++;
                  //if it goes into a new tile, then reset precisePos to 0 in the new tile
                  if (precisePosX > 24)
                  {
                     startPosX--;
                     precisePosX = 0;
                  }
                  
               }
             
            }
            
         }
        
          
         //if the user presses Right and there is not a wall, move Right
         if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.E || event.getCode() == KeyCode.X) 
         {
            if (startPosX+1 == 21)
            {
            
            }
            else
            {
               if ((precisePosY > 0) && ((posBox[startPosX+1][startPosY-1] == 1) || (posBox[startPosX+1][startPosY+1] == 1)))
               {
               
               }
               else if (posBox[startPosX+1][startPosY] == 0)
               {
                  wall.move(1,0);
                  precisePosX--;
                  if (precisePosX < 0)
                  {
                     startPosX++;
                     precisePosX = 24;
                  }
                  
               }
               
            }

         }
          
         //if the user presses Down and there is not a wall, move Down
         if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.Z || event.getCode() == KeyCode.X) 
         {
            if (startPosY+1 == 21)
            {
            
            }
            else
            {
               if ((precisePosX > 0) && ((posBox[startPosX+1][startPosY+1] == 1) || (posBox[startPosX-1][startPosY+1] == 1)))
               {
               
               }
               else if (posBox[startPosX][startPosY+1] == 0)
               {
                  wall.move(0,1);
                  precisePosY--;
                  if (precisePosY < 0)
                  {
                     startPosY++;
                     precisePosY = 24;
                  }
                  
               }
               else
               {
                  if (precisePosY > 0)
                  {
                     wall.move(0,1);
                     precisePosY--;
                  }
               }
            }
            
         }
         
         //if you reach the end, type out win message
         if ((wall.returnX() == endX) && (wall.returnY() == endY))  
            System.out.println("You Win!");
      }
   }
}