// Will Westrich and Seth Hunter

//DO THIS
//take a look at MasterMindAIRandom and decide what ONE method should be placed in this interface
//this method will provide the interface between the game and any AIs that are written for the game
public interface MasterMindAI
{
   public Guess nextGuess(int guess_id);
}
