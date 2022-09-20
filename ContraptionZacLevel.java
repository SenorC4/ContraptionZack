import java.util.*;
import java.io.*;

public class ContraptionZacLevel
{
   int[][] data;
   int x;
   int y;
   int exitX;
   int exitY;
   

   String[] directions = new String[4];
   
   public String name;
   
   public ContraptionZacLevel(String filename)
   {
      name = filename;
   
      try
      {
         Scanner scan = new Scanner(new File(filename));
         
         //remove "Layout" string
         scan.next()
         
         x = scan.nextInt()
         y = scan.nextInt()
         
         //get layout
         data = new int[x][y];
         
         for(int i=0;i<x;i++)
         {
            for(int j=0;j<y;j++)
            {
               data[j][i] = scan.next();
            }
         }
         
         //get walls
         
         //get objects
         
         
               
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