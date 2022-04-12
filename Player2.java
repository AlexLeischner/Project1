//written by Alex Leischner
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.*;
import java.text.*;
import java.io.*;
import java.awt.event.*;
import java.util.Random;
public class Player2 extends GameObject //implements from GameObjects
   {
   public Player2 (int xin, int yin, Color colin) //takes in 3 parameters
      {
      super(xin, yin, colin); //values are assigned like in parent class
      }
      
   //want to see if the player is on the ground
   public boolean isOnground (ArrayList<ArrayList<GameObject>> list)
      {
      //I calculate the pos
      int yp=Math.floorDiv(this.getx()-12,25);
      int xp=Math.floorDiv(this.gety()-12,25);
      //I do not want to check all the Objects, so I just check the objects around the position
      for (int i=-1; i<2; i++)
         {
         for (int k=-1; k<2; k++)
            {
            if (xp-i>-1&&yp-k>-1&&xp-i<list.size()&&yp-k<list.get(0).size()) //I check that I am not out of bounds
            {
            if (list.get(xp-i).get(yp-k) instanceof GameObject) //i check that it is a instance of GameObject
               {
               //I check if the player would collide if it would be 1 pixel more down
               if (Math.abs((this.getx())-list.get(xp-i).get(yp-k).getx())<25&&list.get(xp-i).get(yp-k).gety()-(this.gety()+1)<24&&list.get(xp-i).get(yp-k).gety()-(this.gety()+1)>0)
                  {
                  return true;
                  }
               }
            }
            }
         }
      return false; //if everything before was not the case, return false
      }
      
   //I check is there is something under my player so that he can jump
   public boolean j (ArrayList<ArrayList<GameObject>> list)
      {
      //I calculate the pos
      int yp=Math.floorDiv(this.getx()-12,25);
      int xp=Math.floorDiv(this.gety()-12,25);
      //I do not want to check all the Objects, so I just check the objects around the position
      for (int i=-1; i<2; i++)
         {
         for (int k=-1; k<2; k++)
            {
            if (xp-i>-1&&yp-k>-1&&xp-i<list.size()&&yp-k<list.get(0).size()) //I check that I am not out of bounds
            {
            if (list.get(xp-i).get(yp-k) instanceof GameObject) //i check that it is a instance of GameObject
               {   
               //Almost the same as the method above, but the values are different           
               if (Math.abs((this.getx())-list.get(xp-i).get(yp-k).getx())<25&&list.get(xp-i).get(yp-k).gety()-(this.gety()+1)<26&&list.get(xp-i).get(yp-k).gety()-(this.gety()+1)>-2)
                  {
                  return true;
                  }
               }
            }
            }
         }
      return false; //if everything before was not the case, return false
      }
   
   //I want to see if there is something above my Player
   public boolean head (ArrayList<ArrayList<GameObject>> list)
      {
      //I calculate the pos
      int yp=Math.floorDiv(this.getx()-12,25);
      int xp=Math.floorDiv(this.gety()-12,25);
      //I do not want to check all the Objects, so I just check the objects around the position
      for (int i=-1; i<2; i++)
         {
         for (int k=-1; k<2; k++)
            {
            if (xp-i>-1&&yp-k>-1&&xp-i<list.size()&&yp-k<list.get(0).size()) //I check that I am not out of bounds
            {
            //I check if the player would collide if it would be 1 pixel more up
            if (list.get(xp-i).get(yp-k) instanceof GameObject) //i check that it is a instance of GameObject
               {
               //if (Math.abs((this.getx())-list.get(i).get(k).getx())<26&&Math.abs((this.gety()+1)-list.get(i).get(k).gety())<26)
               if (Math.abs((this.getx())-list.get(xp-i).get(yp-k).getx())<25&&(this.gety()-1)-list.get(xp-i).get(yp-k).gety()<24&&(this.gety()-1)-list.get(xp-i).get(yp-k).gety()>0)
                  {
                  return true;
                  }
               }
            }
            }
         }
      return false; //if everything before was not the case, return false
      }


      
   public void draw (Graphics g) //draws my player as a rectangle with its color at its position
      {
      g.setColor(getColor());
      g.fillRect(getx()-12,gety()-12,25,25);
      }
      
   
   //a method that tells me if the player collides with anything when he moves normally
   public boolean collides (ArrayList<ArrayList<GameObject>> list)
      {
      //I calculate the pos
      int yp=Math.floorDiv(this.getx()-12,25);
      int xp=Math.floorDiv(this.gety()-12,25);
      //I do not want to check all the Objects, so I just check the objects around the position
      for (int i=-1; i<2; i++)
         {
         for (int k=-1; k<2; k++)
            {
            if (xp-i>-1&&yp-k>-1&&xp-i<list.size()&&yp-k<list.get(0).size()) //I check that I am not out of bounds
            {
            //if the two objects are too close it returns true
            if (list.get(xp-i).get(yp-k) instanceof GameObject) //i check that it is a instance of GameObject
               {               
               if (Math.abs((this.getx())-list.get(xp-i).get(yp-k).getx())<26&&Math.abs((this.gety())-list.get(xp-i).get(yp-k).gety())<25)
                  {
                  return true;
                  }
               }
            }
            }
         }
      return false; //if everything before was not the case, return false
      }
      
   //method that tells me if the player won
   public boolean win (ArrayList<ArrayList<GameObject>> list)
      {
      //I calculate the pos
      int yp=Math.floorDiv(this.getx()-12,25);
      int xp=Math.floorDiv(this.gety()-12,25);
      //I do not want to check all the Objects, so I just check the objects around the position
      for (int i=-1; i<2; i++)
         {
         for (int k=-1; k<2; k++)
            {
            if (xp-i>-1&&yp-k>-1&&xp-i<list.size()&&yp-k<list.get(0).size()) //I check that I am not out of bounds
            {
            if (list.get(xp-i).get(yp-k) instanceof VictoryBlock) //i check that it is a instance of VictoryBlock
               {
               //check if it collides with a object of VictoryBlock
               if (Math.abs((this.getx())-list.get(xp-i).get(yp-k).getx())<25&&list.get(xp-i).get(yp-k).gety()-this.gety()==25)
                  {
                  return true;
                  }
               }
            }
            }
         }
      return false; //if everything before was not the case, return false
      }
   
   //tells me if my player would collide on the left side if i move one pixel left
   public boolean leftfree (ArrayList<ArrayList<GameObject>> list)
      {
      //I calculate the pos
      int yp=Math.floorDiv(this.getx()-12,25);
      int xp=Math.floorDiv(this.gety()-12,25);
      //I do not want to check all the Objects, so I just check the objects around the position
      for (int i=-1; i<2; i++)
         {
         for (int k=-1; k<2; k++)
            {
            if (xp-i>-1&&yp-k>-1&&xp-i<list.size()&&yp-k<list.get(0).size()) //I check that I am not out of bounds
            {
            if (list.get(xp-i).get(yp-k) instanceof GameObject) //i check that it is a instance of GameObject
               {
               //i check if it collides if the xpos decreases by 1
               if (Math.abs((this.getx()-1)-list.get(xp-i).get(yp-k).getx())<26&&Math.abs((this.gety())-list.get(xp-i).get(yp-k).gety())<25)
                  {
                  return true;
                  }
               }
            }
            }
         }
      return false; //if everything before was not the case, return false
      }
      
   //tells me if my player would collide on the right side if i move one pixel right
   public boolean rightfree (ArrayList<ArrayList<GameObject>> list)
      {
      //I calculate the pos
      int yp=Math.floorDiv(this.getx()-12,25);
      int xp=Math.floorDiv(this.gety()-12,25);
      //I do not want to check all the Objects, so I just check the objects around the position
      for (int i=-1; i<2; i++)
         {
         for (int k=-1; k<2; k++)
            {
            if (xp-i>-1&&yp-k>-1&&xp-i<list.size()&&yp-k<list.get(0).size()) //I check that I am not out of bounds
            {
            if (list.get(xp-i).get(yp-k) instanceof GameObject) //i check that it is a instance of GameObject
               {
               //i check if it collides if the xpos increases by 1
               if (Math.abs((this.getx()+1)-list.get(xp-i).get(yp-k).getx())<26&&Math.abs((this.gety())-list.get(xp-i).get(yp-k).gety())<25)
                  {
                  return true;
                  }
               }
            }
            }
         }
      return false; //if everything before was not the case, return false
      }
   
   
   public boolean winl (ArrayList<ArrayList<GameObject>> list, int p, int p2)
      {
      //I calculate the pos
      int yp=Math.floorDiv(this.getx()-12,25);
      int xp=Math.floorDiv(this.gety()-12,25);
      //I do not want to check all the Objects, so I just check the objects around the position
      for (int i=-1; i<2; i++)
         {
         for (int k=-1; k<2; k++)
            {
            if (xp-i>-1&&yp-k>-1&&xp-i<list.size()&&yp-k<list.get(0).size()) //I check that I am not out of bounds
            {
            if (list.get(xp-i).get(yp-k) instanceof VictoryBlock) //i check that it is a instance of VictoryBlock
               {
               if (Math.abs((this.getx()-p)-list.get(xp-i).get(yp-k).getx())<25&&Math.abs(list.get(xp-i).get(yp-k).gety()-this.gety()+p2)<25)
                  {
                  return true;
                  }
               }
            }
            }
         }
      return false; //if everything before was not the case, return false
      }

   }
