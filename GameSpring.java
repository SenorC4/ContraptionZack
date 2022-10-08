
public class GameSpring extends mechanism{

   int Px;
   int Py;
   String facing;
   boolean sprung;
   
   public GameSpring(int x, int y, String face, String state){
      Px = x;
      Py = y;
      facing = face;
      if (state.equals("down"))
      {
         sprung= false;
      }
      else if (state.equals("down"))
      {
         sprung = true;
      }
      
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
   
   public void reset()
   {
      sprung = false;
   }
   
   public void setSprung(boolean s)
   {
      sprung = s;
   }
   
   public boolean getState()
   {
      return sprung;
   }
   
   public String getType()
   {
      return "Spring";
   }
}