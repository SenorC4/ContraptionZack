import java.util.*;
import java.io.*;

public class ContraptionZacLevel
{
   String[][] data;
   String[][] objects;
   int x;
   int y;
   int Px;
   int Py;
   int nextFile;
   
   public String name;
   
   public ContraptionZacLevel(String filename)
   {
      name = filename;
   
      try
      {
         Scanner scan = new Scanner(new File(filename));
         
         //remove "Layout" string
         scan.next();
         
         Px = scan.nextInt();
         Py = scan.nextInt();
         
         //remove "Layout" string
         scan.next();
         
         //get next file to load
         nextFile = scan.nextInt();
         
         
         //get width and length of array
         y = scan.nextInt();
         x = scan.nextInt();
         
         //get layout
         data = new String[x][y];
         
         for(int i=0;i<y;i++)
         {
            for(int j=0;j<x;j++)
            {
               data[j][i] = scan.next();
            }
         }
         
                  
         //get objects
         scan.next();
                  
         int numObjects = scan.nextInt();
         
         objects = new String[numObjects][2];
         
         for(int i = 0; i < numObjects; i++){
            objects[i][0] = scan.next();
            objects[i][1] = scan.next();
         }
      }
      catch(Exception e)
      {
      
      }
   }
   
   public String[][] getData()
   {
      return data;
   }
   
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }
   
   public String[][] getObjects()
   {
      return objects;
   }

   public int getPy()
   {
      return Py;
   }
   
   public int getPx()
   {
      return Px;
   }
   
   
   public int getNext(){
      return nextFile;
   }
}