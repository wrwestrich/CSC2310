import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class MazeGUI extends CenterFrame implements ActionListener
{
   private Maze maze;
   private JButton btn;

   public MazeGUI(int width, int height, Maze maze)
   {      
      super (width, height, "Maze");

      setLayout(new BorderLayout());
      setSize(width, height);
      setResizable(false);

      DrawPanel draw = new DrawPanel();
      draw.setDrawable(maze);

      add(draw, BorderLayout.CENTER);

      btn = new JButton("Solve!");
      btn.setBackground(java.awt.Color.white);
      btn.setActionCommand("Solve");
      add(btn, BorderLayout.SOUTH);
      btn.addActionListener(this);

      //repaint periodically (see action performed)
      Timer timer = new Timer(50, this);
      timer.setActionCommand("Timer");
      timer.start();
      this.maze = maze;

      setVisible(true);
   }

   public void actionPerformed(ActionEvent ae)
   {
      if (ae.getActionCommand().equals("Solve"))
      {
         btn.setEnabled(false);
         Thread t = new Thread(maze);
         t.start();
      }

      else
      {
         repaint();
      }
   }
}