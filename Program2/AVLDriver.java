// Will Westrich

import java.util.ArrayList;
import java.util.Iterator;

public class AVLDriver
{
   public static void main(String[] args)
   {
      //call the height and isBalanced methods and display the results with all items inserted
      CD[] cds;
      String filename;
      Keyboard input = Keyboard.getKeyboard();

      BinarySearchTree tree = new AVLTree();

      while(true){
          try{
              filename = input.readString("Enter Filename: ");

              cds = readMusic(filename);

              break;
          }
          catch(FileIOException e){
              System.out.println("Invalid Filename.");
          }
      }

      for(CD cd : cds){
          tree.insert(cd);
      }

      Command commander = new WorstCDs();
      Execute executor = (Execute)tree;

      System.out.println("\nWorst Rated CD(s):");

      executor.execute(commander);
      ArrayList<CD> worstCDsList = ((WorstCDs)commander).getWorstCDs();
      for(CD cd : worstCDsList)
         System.out.println(cd.toString());


      System.out.println("\nLongest Song:");

      commander = new LongestSong();
      executor.execute(commander);
      Song longestSong = ((LongestSong)commander).getLongestSong();
      System.out.println(longestSong.getTitle() + " - " + (longestSong.getLength()/60) + ":" + (longestSong.getLength()%60) + "\n");
   }

   private static CD[] readMusic(String fileName)
   {
      FileIO file = new FileIO(fileName, FileIO.FOR_READING);
      String str = file.readLine();
      ArrayList<CD> cds = new ArrayList<CD>();
      while (!file.EOF())
      {
         String title = file.readLine();
         int year = Integer.parseInt(file.readLine());
         int rating = Integer.parseInt(file.readLine());
         int numTracks = Integer.parseInt(file.readLine());
         CD cd = new CD(title, str, year, rating, numTracks);

         cds.add(cd);
         int tracks = 1;

         while (tracks <= numTracks)
         {
            String temp = file.readLine();
            String[] line = temp.split(",");
            String len = line[0];
            String songTitle = line[1];
            cd.addSong(songTitle, len);
            tracks++;
         }

         str = file.readLine();
      }

      CD[] cds_array = new CD[cds.size()];
      int i = 0;
      for(CD cd : cds)
      {
         cds_array[i] = cds.get(i);
         i++;
      }
      return cds_array;
   }
}
