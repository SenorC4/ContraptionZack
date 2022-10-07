
public class GameButton extends mechanism{

   double Px;
   double Py;
   String color;
   boolean pushed = false;

   public GameButton(double x, double y, String inputtedColor){
      Px = x;
      Py = y;
      color = inputtedColor;
   }

   public double getPx()
   {
      return Px;
   }
   
   public double getPy()
   {
      return Py;
   }
   
   public void reset()
   {
      pushed = false;
   }
   
   public void setPushed(boolean p)
   {
      pushed = p;
   }
   
   public String getColor()
   {
      return color;
   }
   
   public boolean getPushed()
   {
      return pushed;
   }

}