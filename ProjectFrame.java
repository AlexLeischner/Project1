//written by Alex Leischner
//just my frame class
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class ProjectFrame extends JFrame
{
   public ProjectFrame()
      {
      super("");
      Container contents = getContentPane();
      ProjectPanel s = new ProjectPanel("Project.txt"); //takes in a txt for the map
      contents.add(s);
      getRootPane().setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.BLACK)); //makes the black surrounding
      setSize(833,656); //sets a good size
      setVisible(true);      
      }
   public static void main(String[] args)
      {
      ProjectFrame theFrame = new ProjectFrame();
      theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }
}
      
      
