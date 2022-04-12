//written by Alex Leischner
//GameObject is the parent class for Block and VictoryBlock
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.*;
import java.text.*;
import java.io.*;
import java.awt.event.*;
import java.util.Random;
public class GameObject
   {
   //every object has a xposistion, yposition and a color
   private int xpos;
   private int ypos;
   private Color color;
   public GameObject(int xin, int yin, Color colin) //takes in 3 parameters
      {
      //assigns these 2 values
      xpos = xin;
      ypos = yin;
      color = colin;
      }
    
   //this methods checks if two GameObjects come too close to each other  
   public boolean collides (GameObject go)
      {
      if (this == go) //it checks that it is not the same
         {
         return false;
         }
      else
         {
         if (Math.abs(this.getx()-go.getx())<26&&Math.abs(this.gety()-go.gety())<26) //that is true if they would be too close
            {
            return true;
            }
         return false;
         }
      }
      
   public void draw (Graphics g) //draws the object at the certain position with a side length of 25
      {
      g.setColor(color);
      g.fillRect(getx()-12,gety()-12,25,25);
      }
   public int getx() //returns the xpos
      {
      return xpos;
      }
   public int gety() //returns the ypos
      {
      return ypos;
      }
   public void setx (int xin) //sets the xpos to a new value
      {
      xpos = xin;
      }
   public void sety (int yin)  //sets the ypos to a new value
      {
      ypos = yin;
      }
   public Color getColor() //returns the color
      {
      return color;
      }
   }
