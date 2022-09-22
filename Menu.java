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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;



public class Menu extends Application
{

StackPane root = new StackPane();
boolean gamePaused = false;

            Button resume = new Button("Resume");
      
            Button save = new Button("Save");
            
            
            Button load = new Button("Load");
            
            
            Button restartA = new Button("Restart Area");
            
            Button restartL = new Button("Restart Level");
            
            Button end = new Button("Exit");
           
            
            VBox vbox = new VBox (0, resume, save, load, restartA, restartL, end);
            ;



public void start (Stage stage)
   { 

//Rectangle rect = new Rectangle(10, 15);
      //Font font = Font.font(30);
      
      
   
   //}
      
      resume.setOnAction(new ButtonListener());
      end.setOnAction(new ButtonListener());
      root.setOnKeyPressed(new KeyListenerDown());
      
      
      Scene scene = new Scene(root, 525, 525);
      stage.setScene(scene);
      stage.setTitle("BorderPane");
      stage.show();
      root.requestFocus();
   }   
      
      public static void main(String[] args)
   {
      launch(args);
   }
   
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
      }
   }

   
   
   
   

}