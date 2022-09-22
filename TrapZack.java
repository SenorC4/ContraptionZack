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

public class TrapZack extends Application{
   
   StackPane root = new StackPane();
   Canvas canvas = new Canvas(800, 800);
   GraphicsContext gc = canvas.getGraphicsContext2D();
   
   
   public void start(Stage stage){
      Scene scene = new Scene(root, 800, 800);      
      root.getChildren().add(canvas);
      
      //create squyare
      gc.drawImage(new Image("Assets/Boat1.png", false), 400, 600);
      //gc.fillRect(250, 250, 25, 25);
      
      //default javafx stuff
      stage.setScene(scene);      
      stage.setTitle("Square");      
      stage.show();
      
      //Keyboard lisetner
      
      
      //request focus for canvas
      canvas.requestFocus();
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

}