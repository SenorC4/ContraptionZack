
public class GameSpike extends mechanism{

   double Px;
   double Py;
   String color;
   boolean activated = false;

   public GameSpike(double x, double y, String inputtedColor){
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
   
   public void setActive(boolean a)
   {
      activated = a;
   }
   
   public void reset()
   {
      activated = false;
   }
   
   public String getColor()
   {
      return color;
   }
   
   public boolean getActivated()
   {
      return activated;
   }

}