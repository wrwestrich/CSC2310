//Will Westrich and Seth Hunter

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class BlackJackPlayer
{
   private ArrayList<BlackJackHand> hands;
   private BlackJackStrategy strategy;
   private int index;  //index of 1 is the first hand

   public BlackJackPlayer(String filename)
   {
      hands = new ArrayList<BlackJackHand>();
      strategy = new BlackJackStrategy(filename);
      index = 0;  //keep track of which hand is being operated on by the game
   }

   public void draw(Graphics g, int width, int height)
   {
      int count = 1;
      int num_hands = numHands();
      double offset = (num_hands - 1)/2.0 * 100.0;
      int off = width/2 - (int) offset;

      //DO THIS use a for-each loop to draw all of the player hands

        for(BlackJackHand hand : hands){
            hand.drawPlayerHands(g, off + (count - 1) * 100 - 50, 300);

            if (count == index)  //indicate the current hand with a yellow dot above the hand
            {
                g.setColor(Color.black);
                g.fillOval(off + (count - 1) * 100 - 23, 284, 13, 13);
                g.setColor(Color.yellow);
                g.fillOval(off + (count - 1) * 100 - 21, 285, 10, 10);
                g.setColor(Color.black);
            }

            count++;
        }
   }

   /** 
    *  The player wants to split.  
    *  You need to remove the hand that is being split from the ArrayList.
    *  Create two new hands and insert them into the appropriate place in the ArrayList.
    */
   public void split(Card card1, Card card2)
   {
      boolean can_split = canSplit();

      if (can_split)
      {
         //DO THIS
        BlackJackHand[] split = hands.get(index - 1).split(card1, card2);

        hands.remove(index - 1);
        hands.add(split[0]);
        hands.add(split[1]);
      }
   }

   /** 
    *  The player wants to double down.
    *  Obtain the current hand using the index instance var.
    *  Hit the hand.
    *  Remember that you can only double a hand once (call a method in BlackJackHand to ensure this).
    */
   public void doubleDown(Card dealt)
   {
        BlackJackHand hand = hands.get(index - 1);
        hand.hit(dealt);

      //DO THIS (call a method in BlackJackHand)
        hand.doubleDown();

   }

   public void hit(Card dealt)
   {
      BlackJackHand hand = hands.get(index - 1);
      hand.hit(dealt);
   }

   public int result(BlackJackHand dealer)
   {
      int winnings = 0;
      int dValue = dealer.handValue();

      //DO THIS
      //use a for-each loop to compute the player's winnings over all player hands

        for(BlackJackHand hand : hands){
            int pValue = hand.handValue();

         //check for player bust first
            if (pValue > 21)
            {
                if (hand.isDouble())
                {
                    winnings -= 1;
                }
                winnings -= 1;
            }
         //only if player did not bust do we check for a dealer bust
            else if (dValue > 21)
            { 
                if (hand.isDouble())
                {
                    winnings += 1;
                }
                winnings += 1;
            }
            else if (pValue > dValue)
            {
                if (hand.isDouble())
                {
                    winnings += 1;
                }
                winnings += 1;
            }
            else if (dValue > pValue)
            {
                if (hand.isDouble())
                {
                    winnings -= 1;
                }
                winnings -= 1;
            }
        }

      return winnings;
   }

   //'S' = stand, 'H' = hit, 'D' = double, 'P' = split
   public char optimal(BlackJackHand dealer)
   {
      if (index > numHands())
      {
         return 0;
      }

      BlackJackHand hand = hands.get(index - 1);

      //first, determine if a split is possible
      boolean can_split = canSplit();
      if (can_split)
      {
         //if a split is possible, should we do it?
         boolean should_split = strategy.shouldSplit(hand, dealer);
         if (should_split)
         {
            return 'P';
         }
      }

      char optimal = strategy.getMove(hand, dealer);
      //make sure the player can double if that is the optimal move (else just hit)
      if (optimal == 'D' && hand.numCards() > 2)
      {
         optimal = 'H';
      }
    
      return optimal;
   }

   public int numHands()
   {
      return hands.size();
   }

   public boolean isDone()
   {
      return (index > numHands());
   }

   //starting a new hand
   public void removeAllHands(BlackJackHand playersHand)
   {
      hands.clear();
      index = 1;
      hands.add(index - 1, playersHand);
   }

   public void nextHand()
   {
      index++;
   }

   public boolean canSplit()
   {
      if (index > numHands())
      {
         return false;
      }

      BlackJackHand current = hands.get(index - 1);
      return current.canSplit();
   }

   public int handValue()
   {
      BlackJackHand current = hands.get(index - 1);
      return current.handValue();
   }
}
