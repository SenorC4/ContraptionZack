
public class GameSpike extends mechanism{

   int Px;
   int Py;
   String color;
   boolean activated = false;

   public GameSpike(int x, int y, String inputtedColor){
      Px = x;
      Py = y;
      color = inputtedColor;
   }

   public int getPx()
   {
      return Px;
   }
   
   public int getPy()
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