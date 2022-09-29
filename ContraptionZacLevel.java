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
   String nextFile;
   String lastFile;
   
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
         
         //get last file if go back
         lastFile = scan.next();

         
         //get next file to load
         nextFile = scan.next();
         
         
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
         
         objects = new String[numObjects][4];
         
         for(int i = 0; i < numObjects; i++){
            objects[i][0] = scan.next(); //name
            objects[i][1] = scan.next(); //x
            objects[i][2] = scan.next(); //y
            objects[i][3] = scan.next(); //relationship
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
   
   
   public String getNext(){
      return nextFile;
   }
   
   public String getLast(){
      return lastFile;
   }

}