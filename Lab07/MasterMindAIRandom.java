// Will Westrich and Seth Hunter

import java.util.ArrayList;
import java.util.List;

public class MasterMindAIRandom implements MasterMindAI
{
   private MasterMind game;

   public MasterMindAIRandom(MasterMind game)  
   {
      this.game = game;
   }


   public Guess nextGuess(int guess_id)
   {
      return makeRandomGuess(guess_id);
   }

   private static Guess makeRandomGuess(int guess_id)
   {
      Guess random_guess = new Guess(guess_id);
      Random random = Random.getRandomNumberGenerator();
      for (int i = 1; i <= 4; i++)
      {
         int random_int = random.randomInt(1,7);
		 
         //add the random int to your guess list
         random_guess.addGuess(random_int);
      }

      return random_guess;
   }
}
