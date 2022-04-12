//written by Alex Leischner
//this is my Panel class
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.*;
import java.text.*;
import java.io.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.Timer;
public class ProjectPanel extends JPanel 
{
int x,y,xpos1,ypos1; //xpos1 and ypos1 are for my player, the 2 other ints for number of rows and colums (when I have my ArrayList of ArrayLists)
int grav=1; //need that for my gravity
int xover,yover; //when the player won this is the last position
boolean itsover=true; //If the player won it becomes false
Scanner scan; //myy Scanner
Player2 player1; //my Player
ArrayList<ArrayList<GameObject>> outsidelist = new ArrayList<ArrayList<GameObject>>(); //here I store the map
public ProjectPanel(String filename)
   {
   super ();
   try //initiate the Scanner
      {
      scan = new Scanner(new File(filename));
      }
   catch(FileNotFoundException fnfe)
      {
      System.out.println("Wrong file!!");
      }
   //read the start position of the player
   xpos1 = scan.nextInt();
   ypos1 = scan.nextInt();
   //read in the size of the 2d arraylist
   x = scan.nextInt();
   y = scan.nextInt();
   for(int i=0; i<x ;i++) //add x times an innerlist
      {
      ArrayList<GameObject> innerList = new ArrayList<GameObject>();
      outsidelist.add(innerList);
      }
      
   //now I read in the map data
   for(int k=0;k<x;k++)
      {
      for(int j=0;j<y;j++) 
         {
         int choice=scan.nextInt();
         if (choice == 1) //if its 1, a normal Block is added to the list
            {
            outsidelist.get(k).add(new Block(j*25+12, k*25+12, Color.BLUE));
            }
         else if (choice == 2) //if it is 2, a victoryBlock is added
            {
            outsidelist.get(k).add(new VictoryBlock(j*25+12, k*25+12, Color.GREEN));
            }
         else //if it is not 1 or 2, null is added
            {
            outsidelist.get(k).add(null);
            }
         }
      }
   player1 = new Player2 (xpos1+12, ypos1+12, Color.RED); //create my player2
   addKeyListener(new KeyEventDemo()); //need the KeyListener 
   setPreferredSize(new Dimension(450,450));
   setFocusable(true);
   Timer t = new Timer (1, new ButtonListener());//I add my timer for the movements
   Timer t2 = new Timer(10, new TimeListener()); //this timer is for gravity and jumping
   //I start both of them
   t.start();
   t2.start();
   }
   
public void paintComponent(Graphics g) //the method that it draws everything
   {
   super.paintComponent(g);
   //here I draw the whole map
   for(int k=0;k<x;k++)
      {
      for(int j=0;j<y;j++)
         {
         //if it is a Block, a Block is drawn, if it is a VictoryBlock, this one is drawn
         if (outsidelist.get(k).get(j) instanceof Block)
            {
            g.setColor(outsidelist.get(k).get(j).getColor());
            outsidelist.get(k).get(j).draw(g);
            }
         else if (outsidelist.get(k).get(j) instanceof VictoryBlock)
            {
            g.setColor(outsidelist.get(k).get(j).getColor());
            outsidelist.get(k).get(j).draw(g);
            }
         }
      //if the player has not won
      if (itsover)
         {
         player1.draw(g);
         }
      //if the player won the player stays at its last position
      else
         {
         g.setColor(player1.getColor());
         g.fillRect(xover-12,yover-12,25,25);

         }
      }
   }
   
//if the keys are released, the corresponding boolean becomes false  
public class KeyEventDemo  implements KeyListener  
   {
   public void keyTyped(KeyEvent e) {}
   public void keyReleased(KeyEvent e) 
      {
      if (e.getKeyCode() == KeyEvent.VK_A)
         {
         left = false; //left becomes false if A is released
         }
      if (e.getKeyCode() == KeyEvent.VK_D)
         {
         right = false; //right becomes false if D is released
         }
      if (e.getKeyCode() == KeyEvent.VK_W)
         {
         up = false; //up becomes false if W is released
         }
      }
    
   public void keyPressed(KeyEvent e)  
      {
      if(e.getKeyCode() == KeyEvent.VK_W) //if W is pressed
         {
          if(player1.j(outsidelist)) //if the player is on the ground (if the method j is true)
           {   
              //up and jump2 become true
              up = true;
              jump = 7; //jump is 7
              jump2 = true;
              minusj = 0; //minusj is 0
           }
         }
      if(e.getKeyCode() == KeyEvent.VK_A)
         {
         left = true; //left is true if A is pressed
         }
      if(e.getKeyCode() == KeyEvent.VK_D)
         {
         right = true; //right is true if D is pressed
         }
      repaint();
      }
   }
   
      
int jump, timejump,minusj; //need all these variables for jumping
boolean right, up, left, jump2; //the first 3 are for moving, the last for jumping
public class ButtonListener implements ActionListener
{
   public void actionPerformed (ActionEvent e)
   {
   if (player1.win(outsidelist)) //if the player won
        {
        if (true)
           {
           itsover=false; //becomes false, the player does not move anymore
           //saves the last position
           xover=player1.getx();
           yover=player1.gety();
           int result = JOptionPane.showConfirmDialog(null,"WINNER","Confirm Quit", JOptionPane.DEFAULT_OPTION); //shows that it is over
           if (result == 0)
              System.exit(0); //exit
           }
        }
   if (left) //if the playerwants to go left
      {
     if (player1.winl(outsidelist,1,0)) //if the player wins with a left move
        {
        if (true)
           {
           itsover=false; //becomes false, the player does not move anymore
           //saves the last position
           xover=player1.getx();
           yover=player1.gety();
           int result = JOptionPane.showConfirmDialog(null,"WINNER","Confirm Quit", JOptionPane.DEFAULT_OPTION); //shows that it is over
           if (result == 0)
              System.exit(0); //exit
           }

        }

      if (player1.collides(outsidelist)) //if the player collides with something
         {
         if(!player1.leftfree(outsidelist)) //checks if the spot left to him is free, c
            {
            xpos1--;
            player1.setx(xpos1);
            }
         }
      else //no collision, moves the player one spot left
         {
         xpos1--;
         player1.setx(xpos1);
         }
      }
   if (right) //if right is true
      {
      if (player1.winl(outsidelist,-1,0)) //if the player wins with a right move
        {
        if (true)
           {
           itsover=false; //becomes false, the player does not move anymore
           //saves the last position
           xover=player1.getx();
           yover=player1.gety();
           int result = JOptionPane.showConfirmDialog(null,"WINNER","Confirm Quit", JOptionPane.DEFAULT_OPTION); //shows that it is over
           if (result == 0)
              System.exit(0); //exit
           }

        }

      if (player1.collides(outsidelist)) //if the player collides with a right move
         {
         if(!player1.rightfree(outsidelist)) //if the spot right to him is free, it makes the move, xpos1 increases by 1
            {
            xpos1++;
            player1.setx(xpos1);
            }
         }
      else //no collision, player moves one spot to the right
         {
         xpos1++;
         player1.setx(xpos1);
         }
      }
   
      if (player1.winl(outsidelist,0,1)) //while going up it checks if the player wins
        {
        if (true)
           {
           itsover=false;//becomes false, the player does not move anymore
           //saves the last position
           xover=player1.getx();
           yover=player1.gety();
           int result = JOptionPane.showConfirmDialog(null,"WINNER","Confirm Quit", JOptionPane.DEFAULT_OPTION); //shows that it is over
           if (result == 0)
              System.exit(0); //exit
           }

        
      }

   repaint();
   }
}
   
    //I need both ints for the gravity
    int count=1; //thats the time increase
    int n=1; //thats how fast it falls
    public class TimeListener implements ActionListener
       {
       public void actionPerformed(ActionEvent e)
          {
             count++; //increases every tick by 1
             if (count%20==0) //if this is true, n increases by 1 (if it is less than 7)
                {
                if (n<7)
                   {
                   n++;
                   }
                }
             for (int i=0; i<n; i++) //how often it happens
                {
                player1.sety(player1.gety()+1); //one downward move
                if (player1.isOnground(outsidelist)) //if the player is on the ground everything is reset, the downmove is taken back
                   {
                   player1.sety(player1.gety()-1);
                   count=1;
                   n=1;
                   }
                repaint(); //reapint after every move
                }
          
          if(jump2) //if the boolean is true
             {
             timejump++; //increases by one every tick
             if(timejump % 10 == 0) //if this is true, minusj increases by 1 (if it is less than 7)
                {
                if(minusj<7)
                   {
                   minusj++;
                   }
                }
            for(int i=0; i<jump; i++)
               { 
               player1.sety(player1.gety()-1); //one up move
               if (player1.head(outsidelist)) //if there is something on top of the player, the upmove is taken back
                  {
                  player1.sety(player1.gety()+1);
                  jump=0; //becomes 0 again
                  break;
                  }
                  repaint(); //reapint after every move
                  jump = 7-minusj; //jump becomes smaller
               }
            if(jump ==0)
               {
               jump2 = false; //jump2 is not true anymore
               }
            up=false; //up becomes false
            }
         }
      }
}
         

