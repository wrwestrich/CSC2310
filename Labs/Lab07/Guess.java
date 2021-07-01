// Will Westrich and Seth Hunter

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Guess
{
   private List<MasterMindColor> colors;
   private int guess_id;  //the number of this guess (from 1 to 8)

   public Guess(int guess_id)  
   {
      this.guess_id = guess_id;
      colors = new ArrayList<MasterMindColor>();
   }

   //DO THIS
   public int[] reportResult(Guess secret)
   {
      int[] result = new int[2];
      if (!isFull()) return result;  //nothing awarded if the guess is not complete

      List<Integer> secret_guess_ids = secret.getGuessIDs();
      List<Integer> current_guess_ids = getGuessIDs();

      int black_count = 0;   
      int white_count = 0;

    //count up the black buttons to be awarded (for loop)
	//award a black button and remove those elements
	//make sure to account for the shifting of elements after a removal (decrement your counter)
		
      for(int i = 0; i < current_guess_ids.size(); ++i){
            if(current_guess_ids.get(i) == secret_guess_ids.get(i)){
                ++black_count;
                current_guess_ids.remove(i);
                secret_guess_ids.remove(i--);
            }
      }

    //count up the white buttons to be awarded (nested for loop)
	//award a white button and remove those elements
	//make sure to account for the shifting of elements after a removal (decrement your counter)
	//once elements have been removed, break out of the inner loop

      for(int i = 0; i < secret_guess_ids.size(); ++i){
            for(int j = 0; j < current_guess_ids.size(); ++j){
                if(secret_guess_ids.get(i) == current_guess_ids.get(j)){
                    ++white_count;
                    current_guess_ids.remove(j--);
                    secret_guess_ids.remove(i--);
                    break;
                }
            }
      }

      result[0] = black_count;
      result[1] = white_count;

      return result;
   }

   public boolean isFull()
   {
      return (colors.size() == 4);
   }

   public void addGuess(int guess_color_id)  //provide the id of the color guessed
   {
      if (colors.size() < 4)
      {
         int guess_number = colors.size();
         int x_pos = guess_number * 67 + 34;  //the leftmost color is off by one pixel
         if (guess_number >= 2) x_pos--;
         int y_pos = (guess_id - 1) * 67 + 34;

         MasterMindColor gc = new MasterMindColor(guess_color_id, x_pos, y_pos);
         colors.add(gc);
      }
   }

   //get the colors guessed so that buttons can be processed
   public List<Integer> getGuessIDs()
   {
      List<Integer> guess_color_ids = null;

      if (colors.size() == 4)
      {
         guess_color_ids = new ArrayList<Integer>();
         for (int i = 0; i < 4; i++)
         {
            guess_color_ids.add(colors.get(i).getGuessColorID());
         }
      }

      return guess_color_ids;
   }

   public boolean correct(Guess secret)
   {
      if (!isFull()) return false;

      List<Integer> secret_guess_ids = secret.getGuessIDs();
      List<Integer> current_guess_ids = getGuessIDs();
      int num_matches = 0;

      for (int i = 0; i < 4; i++)
      {
         int secret_guess_id = secret_guess_ids.get(i);
         int current_guess_id = current_guess_ids.get(i);

         if (secret_guess_id == current_guess_id)
         {
            num_matches++;
         }
      }

      return (num_matches == 4);
   }

   public void drawResult(Graphics g, Guess secret)
   {
      int[] result = reportResult(secret);
      int num_black = result[0];
      int num_white = result[1];

      List<Button> buttons = new ArrayList<Button>();
      int button_x_count = 0;

      for (int i = 0; i < num_black; i++)
      {
         //award a black button and place a -1 in the current guess id array
         button_x_count++;
         int x_loc = (button_x_count - 1)*39 + 309;
         int y_loc = (guess_id - 1)*67 + 34;

         Button button = new Button(0, x_loc, y_loc);
         buttons.add(button);
      }

      for (int i = 0; i < num_white; i++)
      {
         //award a white button and place a -1 in the current guess id array

         button_x_count++;
         int x_loc = (button_x_count - 1)*39 + 309;
         int y_loc = (guess_id - 1)*67 + 34;

         Button button = new Button(1, x_loc, y_loc);
         buttons.add(button);
      }

      //draw the buttons
      for (Button button : buttons)
      {
         button.draw(g);
      }
   }

   //will draw incomplete guesses
   public void draw(Graphics g, Guess secret)
   {
      for (MasterMindColor color : colors)
      {
         color.draw(g);  
      }
      drawResult(g, secret);
   }

   //called by Win and Lose
   public void draw(Graphics g)
   {
      for (MasterMindColor color : colors)
      {
         color.draw(g);  
      }
   }

   public String toString()
   {
      String str = "";
      for (MasterMindColor c : colors)
      {
         str += c.toString();
      }
      return str;
   }
}
