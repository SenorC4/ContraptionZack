
public class GameButton extends mechanism{

   int Px;
   int Py;
   String color;
   boolean pushed = false;

   public GameButton(int x, int y, String inputtedColor){
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