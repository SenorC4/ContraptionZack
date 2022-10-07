
public class GameButton extends mechanism{

   int Px;
   int Py;
   boolean pushed = false;

   public GameButton(int x, int y){
      Px = x;
      Py = y;
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
   
   public boolean getPushed()
   {
      return pushed;
   }

}