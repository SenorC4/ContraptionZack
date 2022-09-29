
public class GameSpring extends mechanism{

   int Px;
   int Py;
   String facing;
   boolean sprung = false;
   
   public GameSpring(int x, int y, String face){
      Px = x;
      Py = y;
      facing = face;
   }

   public int getPx()
   {
      return Px;
   }
   
   public int getPy()
   {
      return Py;
   }
   
   public String getFacing()
   {
      return facing;
   }
   
   public void setSprung(boolean s)
   {
      sprung = s;
   }
   
   public boolean getSprung()
   {
      return sprung;
   }
}