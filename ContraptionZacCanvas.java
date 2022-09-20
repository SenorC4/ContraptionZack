import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class ContraptionZacCanvas extends JPanel
{
   ContraptionZacLevel level;

   boolean up,down,left,right;
   
   int px=100,py=100;

   public ContraptionZacCanvas(String firstLevel)
   {
      setPreferredSize(new Dimension(9*50,9*50));
      
      level = new ContraptionZacLevel(firstLevel);
      
      /*GraphicsContext gc = getGraphicsContext2D();
      draw(gc);
      
      setOnKeyPressed(new KeyListenerDown());
      setOnKeyReleased(new KeyListenerUp());*/
      
      addKeyListener(new  KeyListenerB());
      setFocusable(true); //allows the panel to have "focus" of where the keyinputs go
      
      
      
      Timer ah = new Timer(10, new AnimationHandler());
      ah.start();
   }
   
   public void paintComponent(Graphics gc)
   {
      gc.clearRect(0,0,450,450);
      gc.setColor(Color.BLACK);
      gc.fillRect(0,0,450,450);
      
      //System.out.println("start "+level.name);
      for(int i=0;i<9;i++)
      {
         for(int j=0;j<9;j++)
         {
            int k = level.getData(j,i);
            if(k == 1)
            {
               gc.setColor(Color.BLACK);
               gc.fillRect(j*50,i*50,50,50);
            }
            else if(k == 0)
            {
               gc.setColor(Color.CYAN);
               gc.fillRect(j*50,i*50,50,50);
            }
         }
      }
      
      gc.setColor(Color.GREEN);
      gc.fillRect(px-25,py-25,50,50);
   }
   
   public class AnimationHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent e) 
      {
          if(up)
          {
            py--;
          }
          if(down)
          {
            py++;
          }
          if(left)
          {
            px--;
          }
          if(right)
          {
            px++;
          }
          
          //my algorithm for detecting walls
          
          if (level.getData(((px + 25)/50),py/50) == 1)
          {
            px--;
          }
          else if (level.getData(((px - 25)/50),py/50) == 1)
          {
            px++;
          }
          
          if (level.getData(px/50,((py + 25)/50)) == 1)
          {
            py--;
          }
          else if (level.getData(px/50,((py - 25)/50)) == 1)
          {
            py++;
          }
          
          //end my algorithm
                    
          if(px < 50)
          {
            String s = level.getNextFileName(1);
          
            if(s.equals("NONE"))
            {
               level = new ContraptionZacLevel(s);
               px = 400;
               System.out.println(s+" "+1);
            }
            else
            {
               px = 50;
            }
          
            
          }
          else if(px > 400)
          {
            String s = level.getNextFileName(3);
          
            if(!s.equals("NONE"))
            {
               level = new ContraptionZacLevel(s);
               System.out.println(s+" "+3);
               px = 50;
            }
            else
            {
               px = 400;
            }
          
          }
          if(py < 50)
          {
            String s = level.getNextFileName(0);
          
            if(!s.equals("NONE"))
            {
               level = new ContraptionZacLevel(s);
               System.out.println(s+" "+0);
               py = 400;
            }
            else
            {
               py = 50;
            }
          
          }
          else if(py > 400)
          {
            String s = level.getNextFileName(2);
          
            if(!s.equals("NONE"))
            {
               level = new ContraptionZacLevel(s);
               System.out.println(s+" "+2);
               py = 50;
            }
            else
            {
               py = 400;
            }
          }          
          repaint();
      }
   }
   
   public class KeyListenerB implements  KeyListener
   {
    public void keyTyped(KeyEvent e) {}
     public void keyPressed(KeyEvent event) 
    {
          if (event.getKeyCode() == KeyEvent.VK_W) 
          {
              up = true;
          }
          if (event.getKeyCode() == KeyEvent.VK_S) 
          {
              down = true;
          }
          if (event.getKeyCode() == KeyEvent.VK_A) 
          {
              left = true;
          }
          if (event.getKeyCode() == KeyEvent.VK_D) 
          {
              right = true;
          }
   }
   
       public void keyReleased(KeyEvent event) 
    {
      
          if (event.getKeyCode() == KeyEvent.VK_W) 
          {
              up = false;
          }
          if (event.getKeyCode() == KeyEvent.VK_S)
          {
              down = false;
          }
          if (event.getKeyCode() == KeyEvent.VK_A) 
          {
              left = false;
          }
          if (event.getKeyCode() == KeyEvent.VK_D) 
          {
              right = false;
          }
      }
   }

}