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



public class TitleMenu extends Application
{
   StackPane root = new StackPane();
   
   Button newGame = new Button("New Game");
   
   Button loadGame = new Button("Load Game");
   
   Font comic = new Font("Comic Sans MS", 50);
   Label title = new Label("Contraption Zack");
      
   VBox vbox = new VBox(100, title, newGame, loadGame);
   
   public void start (Stage stage)
   { 
      
      title.setFont(comic);
      newGame.setFont(comic);
      loadGame.setFont(comic);
      
      vbox.setAlignment(Pos.CENTER); 
      
      newGame.setOnAction(new ButtonListener());
      
      Scene scene = new Scene(root, 525, 525);
      stage.setScene(scene);
      stage.setTitle("BorderPane");
      stage.show();
      root.getChildren().add(vbox);
      root.requestFocus();
      newGame.requestFocus();
   }


   public static void main(String[] args)
   {
      launch(args);
   }

   public class ButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         if (e.getSource() == newGame);
         {
            root.getChildren().remove(vbox);
            //eventually add code to load first level
         }
         if (e.getSource() == loadGame)
         {
            //add code to load the saved file
         }
         
      }
      
   }

}