
public class GameSpike extends mechanism{

   int Px;
   int Py;
   boolean activated = false;

   public GameSpike(int x, int y){
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
   
   public void setActive(boolean a)
   {
      activated = a;
   }
   
   public void reset()
   {
      activated = false;
   }
   
   public boolean getActivated()
   {
      return activated;
   }

}