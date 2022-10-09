
public class GameGate extends mechanism{

   double Px;
   double Py;
   int num;
   boolean activated = false;

   public GameGate(double x, double y, String numIn, String state){
      Px = x;
      Py = y;
      num = Integer.parseInt(numIn);
      if (state.matches("down."))
      {
         activated = false;
      }
      else 
      {
         activated = true;
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
   
   public void setActive(boolean a)
   {
      activated = a;
   }
   
   public void reset()
   {
      activated = false;
   }
   
   public int getNum()
   {
      return num;
   }
   
   public boolean getState()
   {
      return activated;
   }
   
   public String getType()
   {
      return "Gate";
   }

}