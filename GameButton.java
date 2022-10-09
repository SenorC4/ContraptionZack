
public class GameButton extends mechanism{

   double Px;
   double Py;
   String color;
   boolean pushed;

   public GameButton(double x, double y, String inputtedColor, String state){
      Px = x;
      Py = y;
      color = inputtedColor;
      
      if (state.equals("up"))
      {
         pushed = false;
      }
      else 
      {
         pushed = true;
      }
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
   
   public boolean getState()
   {
      return pushed;
   }
   
   public String getType()
   {
      return "Button";
   }

}