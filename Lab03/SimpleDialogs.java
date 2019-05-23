import javax.swing.*;
import java.awt.*;

public class SimpleDialogs
{
   private static SimpleDialogs sd = new SimpleDialogs();

   private SimpleDialogs()
   {
   }

   public static SimpleDialogs getSimpleDialogs()
   {
      return sd;
   }

   /**
    *  Sets up the look and feel for the current system. <br>
    *  Preconditions: None. <br>
    *  Postconditions: The GUI elements will appear consistent with the current system. <br>
    *  Throws: None. <br>
    */
   public void useSystemStyle()
   {
      try
      {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }
      catch (Exception e)
      {
      }
   }

   /**
    *  Displays a standard message box. <br>
    *  Preconditions: The message to be displayed and the title of the window must be specified. <br>
    *  Postconditions: A message box with the specified message and title is displayed. <br>
    *  Throws: None. <br>
    */
   public void normalOutput(String message, String title)
   {
      JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);
   }

   /**
    *  Displays a message box with an image. <br>
    *  Preconditions: The message to be displayed, the title of the window, and the image name must be specified. <br>
    *  Postconditions: A message box with the specified message and title is displayed along with the requested image. <br>
    *  Throws: None. <br>
    */
   public void imageOutput(String message, String title, ImageIcon img)
   {
      JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE,img);
   }

   /**
    *  Displays a standard input box. <br>
    *  Preconditions: The message to be displayed requesting input and the title of the window must be specified. <br>
    *  Postconditions: An input box with the requested message and title is displayed. <br>
    *                  If O.K. is selected by the user, the String in the input box text area is returned (could be the empty string). <br>
    *                  If Cancel is selected by the user, the empty string is returned. <br>
    *  Throws: None. <br>
    */
   public String stringInput(String message, String title)
   {
      String temp="";
     
      temp = JOptionPane.showInputDialog(null,message,title,JOptionPane.QUESTION_MESSAGE);
      if (temp==null)
         return "";

      return temp;
   }

   /**
    *  Displays a standard input box. <br>
    *  Preconditions: The message to be displayed requesting input and the title of the window must be specified.
                      Also, a String array representing the possible choices to be displayed in a drop down menu must be specified. <br>
    *  Postconditions: An input box with the requested message, title, and drop down menu choices is displayed. <br>
    *                  If O.K. is selected by the user, the String in the input box text area is returned (could be the empty string). <br>
    *                  If Cancel is selected by the user, the empty string is returned. <br>
    *  Throws: None. <br>
    */
   public String comboInput(String message, String title, ImageIcon img, String[] choices)
   {
      String temp="";
      if (choices==null || choices[0]==null)
         return "";
     
      temp = (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE, img, choices, choices[0]);
      if (temp==null)
         return "";

      return temp;
   }

   /**
    *  Displays a standard input box with an image. <br>
    *  Preconditions: The message to be displayed requesting input and the title of the window must be specified. <br>
    *  Postconditions: An input box with the requested message and title is displayed. <br>
    *                  If O.K. is selected by the user, the String in the input box text area is returned (could be the empty string). <br>
    *                  If Cancel is selected by the user, the empty string is returned. <br>
    *  Throws: None. <br>
    */
   public String imageInput(String message, String title, String name)
   {
      String temp="";
      ImageIcon image=new ImageIcon(name);
     
      temp = (String) JOptionPane.showInputDialog(null,message,title,JOptionPane.QUESTION_MESSAGE,image,null,null);
      if (temp==null)
         return "";

      return temp;
   }

   /**
    *  Displays a standard input box. <br>
    *  Preconditions: The message to be displayed requesting input and the title of the window must be specified. <br>
    *  Postconditions: An input box with the requested message and title is displayed. <br>
    *                  If O.K. is selected by the user, the String in the input box text area is converted to an int and returned (0 is returned if the conversion fails). <br>
    *                  If Cancel is selected by the user, -1 is returned. <br>
    *  Throws: None. <br>
    */
   public int integerInput(String message, String title)
   {
      String temp="";
      int result;
     
      temp = JOptionPane.showInputDialog(null,message,title,JOptionPane.QUESTION_MESSAGE);
      if (temp==null)
         result=-1;
      else
      {
         try
         {
            result=Integer.parseInt(temp);
         }
         catch(NumberFormatException e)
         {
            result=0;
         }
      }
      return result;
   }

   /**
    *  Displays a standard input box. <br>
    *  Preconditions: The message to be displayed requesting input and the title of the window must be specified. <br>
    *  Postconditions: An input box with the requested message and title is displayed. <br>
    *                  If O.K. is selected by the user, the String in the input box text area is converted to a double and returned (0.0 is returned if the conversion fails). <br>
    *                  If Cancel is selected by the user, -1.0 is returned. <br>
    *  Throws: None. <br>
    */
   public double doubleInput(String message, String title)
   {
      String temp="";
      double result;
     
      temp = JOptionPane.showInputDialog(null,message,title,JOptionPane.QUESTION_MESSAGE);
      if (temp==null)
         result=-1.0;
      else
      {
         try
         {
           result=Double.parseDouble(temp);
         }
         catch(NumberFormatException e)
         {
            result=0.0;
         }
      }
      return result;
   }

}