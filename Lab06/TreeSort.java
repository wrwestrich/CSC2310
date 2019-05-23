// Will Westrich and Seth Hunter

import java.util.Iterator;
import java.lang.reflect.Array;
import bst.*;
import ki.KeyedItem;

public class TreeSort
{
   //convenience method
   public static KeyedItem[] treeSort(KeyedItem[] sort)
   {
        return treeSort(sort, sort.length);
   }

   public static KeyedItem[] treeSort(KeyedItem[] sort, int n)
   {
      //easier to use a KeyedItem array than Comparable
     if(n > sort.length)
        return null;
	  
	  //create a new Binary Search Tree
      //a balanced tree ensures logn inserts for nlogn sort
      BinarySearchTree tree = new BinarySearchTree(true, true);

      Class sortClass = sort.getClass().getComponentType();
	  
      //need to use the Class class as the actual array type may be a subtype of KeyedItem
      KeyedItem[] sorted = (KeyedItem[]) Array.newInstance(sortClass, sort.length);
	  
      // fill up the search tree
      for(int i = 0; i < n; ++i)
            tree.insert(sort[i]);

      TreeIterator iter = tree.iterator();
      iter.setInorder();

      //pull sorted stuff out of the tree
      for(int i = 0; iter.hasNext(); ++i)
            sorted[i] = (KeyedItem) iter.next();

      for(int i = n; i < sort.length; ++i)
            sorted[i] = sort[i];

	  return sorted; 
   }
}
