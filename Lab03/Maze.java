import java.awt.Graphics;
import java.awt.Color;

public class Maze implements Drawable, Runnable
{
   private final int WALL = 0;
   private final int SPACE = 1;
   private final int TRIED = 3;
   private final int BACKTRACK = 5;
   private final int PATH = 7;

   //the maze is defined as 1s and 0s
   //1s indicate an open pathway and 0s indicate walls
   //of course, moving outside the grid boundaries is also a wall
   //if you make it to the bottom right corner of the maze, starting from the upper left, the maze is solved
   private int[][] grid;

   public Maze(int[][] grid)
   {
      this.grid = grid; //the 2D matrix that you will be updating
      MazeGUI mazegui = new MazeGUI(875, 445, this);
      mazegui.setVisible(true);
   }

   public boolean solve()
   {
      //DO THIS
      //make the initial recursive call (start in the upper left)
      //return true if a solution is found, false otherwise (return the result of the traverse call)





   }

   private boolean traverse(int row, int column) 
   { 
      boolean done =               //assume we are not done until proven otherwise

      //DO THIS
      //initially test that we are still within the bounds of the grid (four tests necessary)
      //if we are not in the bounds of the grid, skip everything and return the appropriate value
      if (                                                             )
      {
         //test that the trial grid is not a wall nor has been already tried (is the grid set to SPACE?)
         //if the current row and col is a wall or has already been tried, skip everything and return the appropriate value
         if (                           )
         {
            //now this location has been tried so mark it as tried
            



            slowDown();  //slow down the animation




            //DO THIS
            //check to see if we have arrived at the bottom right corner of the maze
            if (                             )
            {
               

            }
            else
            {

               //make the below recursive calls in the following order: (unless we are done)
               //up, right, down, left
               //basically, we will try all possible paths until a solution is found
               //it may not be the fastest solution, but we are not concerned with that

               //IMPORTANT!!
               //don't use row++ or column++ use row + 1 or column + 1, etc.
               
               //IMPORTANT!!
               //you will need to make use of the return value (update done)


            	done = 			//down

            	if (!done)
               {
                  done = 		//right
               }
               if (!done)
               {
                  done = 		 //up
               }
               if (!done)
               {
                  done = 		//left
               }
















            }

            //if we are done, on the way back recursively we must mark the path that we took as the solution path
            //at this point, only those methods that participated in the solution are still on the recursion stack
            if (
            {
            	//mark the path taken as the solution path



            }
            else  //back up!
            {
               //slow down the solution to the maze so that it can be seen in the GUI
               //put this thread to sleep so that the maze view can be updated
               //to do this, mark the path as back track


               slowDown();
            }

         }
      }

      return done;
   }  

   //no work below here
   public void run()
   {
      boolean done = solve();
      if (!done)
      {
         SimpleDialogs.getSimpleDialogs().normalOutput("No Solution.", "Maze");
      }
   }

   public void slowDown()
   {
      //slow down the solution to the maze so that it can be seen in the GUI
      //put this thread to sleep so that the maze view can be updated
      try
      {
         Thread.sleep(60);
      }
      catch(InterruptedException e)
      {}
   }

   public void draw(Graphics g, int width, int height)
   {
      // draw squares to represent the maze
      // black for a wall
      // white for an open space
      // blue for tried
      // green for path

      g.setColor(Color.black);
      int xincr = width/grid[0].length;
      int yincr = height/grid.length;
      int x = 0;
      int y = 0;

      for (int row = 0; row < grid.length; row++)
      {
         x = 0;
         for (int column = 0; column < grid[row].length; column++)
         {
            if (grid[row][column] == WALL)
            {
               g.setColor(Color.black);
            }
            else if (grid[row][column] == SPACE)
            {
               g.setColor(Color.white);
            }
            else if (grid[row][column] == TRIED)
            {
               g.setColor(Color.blue);
            }
            else if (grid[row][column] == BACKTRACK)
            {
               g.setColor(Color.RED);
            }
            else
            {
               g.setColor(Color.green);
            }

            //draw a filled square
            g.fillRect(x, y, x + xincr, y + yincr);
            x = x + xincr; //work across a row
         }
         y = y + yincr; 
      }

      g.setColor(Color.black);
   }

   public void mouseClicked(int x, int y) 
   {}
}