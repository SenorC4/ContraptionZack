import java.awt.*;
import javax.swing.*;

public class ContraptionZac extends JFrame
{
   public ContraptionZac()
   {
      super("Contraption Zac");

      Container contents = getContentPane();
      
 

      ContraptionZacCanvas kp = new ContraptionZacCanvas("00.txt"); //its really a panel

      contents.add(kp);

      setSize(450+14,450+32);
      setVisible(true);      
      
      kp.requestFocus(); //sets what panel starts with the focus. In this example this line is not necessary but may be in the future

   
      /*FlowPane fp = new FlowPane();
      fp.setAlignment(Pos.CENTER);
      Canvas c = new Lab7Canvas("00.txt");
      fp.getChildren().add(c);

      
      Scene scene = new Scene(fp, 450, 450);
      stage.setScene(scene);
      stage.setTitle("Lab 7");
      stage.show();
      c.requestFocus();*/
   }
   
   public static void main(String[] args)
   {
      ContraptionZac theFrame = new ContraptionZac();
      theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }  
}