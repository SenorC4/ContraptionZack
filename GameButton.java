
public class GameButton extends mechanism{

   int Px;
   int Py;
   boolean pushed = false;
   boolean timer = false;
   String color = "Y";

   public GameButton(int x, int y, String c, boolean t, boolean p){
      Px = x;
      Py = y;
      color = c;
      timer = t;
      pushed = p;
   }

   public int getPx()
   {
      return Px;
   }
   
   public int getPy()
   {
      return Py;
   }
   
   public String getColor()
   {
      return color;
   }
   
   public boolean getTimer()
   {
      return timer;
   }
   
   public void setPushed(boolean p)
   {
      pushed = p;
   }
   
   public boolean getPushed()
   {
      return pushed;
   }

}