import java.util.*;
import java.io.*;

public class ContraptionZacLevel
{
   int[][] data = new int[9][9];

   String[] directions = new String[4];
   
   public String name;
   
   public ContraptionZacLevel(String filename)
   {
      name = filename;
   
      try
      {
         Scanner scan = new Scanner(new File(filename));
         for(int i=0;i<9;i++)
         {
            for(int j=0;j<9;j++)
            {
               data[j][i] = scan.nextInt();
            }
         }
         directions[0] = scan.next();
         directions[1] = scan.next();
         directions[2] = scan.next();
         directions[3] = scan.next();
      
      }
      catch(Exception e)
      {
      
      }
   }
   
   public int getData(int i, int j)
   {
      return data[i][j];
   }
   
   public String getNextFileName(int direction)
   {
      return directions[direction];
   }
}