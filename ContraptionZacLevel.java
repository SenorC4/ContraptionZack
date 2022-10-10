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
   int numObjects;
   String nextFile;
   String lastFile;
   String currentFile;
   
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
         
         //get the curretn file for restart
         currentFile = scan.next();
         
         
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
                  
         numObjects = scan.nextInt();
         
         objects = new String[numObjects][5];
         
         for(int i = 0; i < numObjects; i++){
            objects[i][0] = scan.next(); //name
            objects[i][1] = scan.next(); //x
            objects[i][2] = scan.next(); //y
            objects[i][3] = scan.next(); //relationship
            objects[i][4] = scan.next(); //save state

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
   
   public String getName()
   {
      return name;
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
   
   public void setPy(int posY)
   {
      Py = posY;
   }
   
   public void setPx(int posX)
   {
      Px = posX;
   }

   
   public int getNumObjects()
   {
      return numObjects;
   }
   
   public void saveLevel(String saveName, ArrayList<mechanism> mechanisms)
   {
      try
         {
         //creates a file writer and rewrites every data member to a save file named by the user
            int listSize = mechanisms.size();
            int nonObjects = numObjects - listSize;
            int counter = 0;
            Scanner read = new Scanner(new File(name));
            FileWriter myWriter = new FileWriter(saveName);
            myWriter.write("PlayerPosition\n");
            myWriter.write(Px + " " + Py + "\n");
            myWriter.write("Layout\n");
            myWriter.write(lastFile + "\n");
            myWriter.write(nextFile + "\n");
            myWriter.write(currentFile + "\n");
            myWriter.write(y + " " + x + "\n");
            
            for(int i=0;i<y;i++)
            {
               for(int j=0;j<x;j++)
               {
                  myWriter.write(data[j][i] + " ");
               }
                myWriter.write("\n");
            }
            myWriter.write("\n");
            myWriter.write("Objects\n");
            myWriter.write(numObjects + "\n");
            
            for(int i = 0; i < numObjects; i++)
            {
               myWriter.write(objects[i][0]+ " ");
               myWriter.write(objects[i][1]+ " ");
               myWriter.write(objects[i][2]+ " ");
               myWriter.write(objects[i][3]+ " ");
               //myWriter.write(objects[i][4]+ " ");
               
               if (i >= nonObjects && i < numObjects)
               {
                 // System.out.println(mechanisms.get(counter).getType());
                  //System.out.println(objects[+nonObjects][2]);
                  //System.out.println((mechanisms.get(counter)).getState());
                  if ((mechanisms.get(counter)).getState() == false && (mechanisms.get(counter)).getType().equals("Button") )
                  {
                     myWriter.write("up ");
                  }
                  else if ((mechanisms.get(counter)).getState() == true && (mechanisms.get(counter)).getType().equals("Button"))
                  {
                     myWriter.write("down ");
                  }
                  else if ((mechanisms.get(counter)).getState() == false && ((mechanisms.get(counter)).getType().equals("Spike") || (mechanisms.get(counter)).getType().equals("Spring")))
                  {
                     myWriter.write("down ");
                  }
                  else if ((mechanisms.get(counter)).getState() == true && ((mechanisms.get(counter)).getType().equals("Spike") || (mechanisms.get(counter)).getType().equals("Spring")))
                  {
                     myWriter.write("up ");
                  }
                  else if ((mechanisms.get(counter)).getState() == false && ((mechanisms.get(counter)).getType().equals("Gate")))
                  {
                     myWriter.write("down ");
                  }
                  else if ((mechanisms.get(counter)).getState() == true && ((mechanisms.get(counter)).getType().equals("Gate")))
                  {
                     myWriter.write("up ");
                  }

                  counter++;
               }
               else
               {
                  myWriter.write(objects[i][4]+ " ");
               }
               
               
               myWriter.write("\n");
            }
            myWriter.close(); 
         }
     catch (IOException e) 
         {
            System.out.println("An error occurred.");
         }
   }

   
   public String getNext(){
      return nextFile;
   }
   
   public String getLast(){
      return lastFile;
   }
   
   public String getCurrent(){
      return currentFile;
   }

}