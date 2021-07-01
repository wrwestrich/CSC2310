//Will Westrich and Seth Hunter

public final class AVLTree extends BinarySearchTree
{
   //keep checking balancing rules?
   private boolean avlFlag;

   public AVLTree()  
   {
      super();
   } 

   public void insert(KeyedItem item) throws TreeException
   {
      super.insert(item);
      //super.setRootNode(insertItem(getRootNode(), item));
      avlFlag = false;
      assert isBalanced() : "Tree is not balanced after inserting key: " + item.getKey();
   } 

   public void delete(Comparable sk) throws TreeException
   {
      super.delete(sk);
      //super.setRootNode(deleteItem(getRootNode(), searchKey));
      avlFlag = false;
      assert isBalanced() : "Tree is not balanced after removing key: " + sk;
   }

   protected TreeNode createNode(KeyedItem item){

        TreeNode tNode = new AVLTreeNode(item);  //set to balanced in the constructor
        avlFlag = true; //added a node to the tree, need to check balancing
        return tNode;

   } 

   protected TreeNode insertLeft(TreeNode tNode, KeyedItem item){

        TreeNode subtree = insertItem(tNode.getLeft(), item);
        tNode.setLeft(subtree);

        //check balance factor and rotate if necessary
        if (avlFlag)  //still need to check
        {
            tNode = avlFixAddLeft((AVLTreeNode)tNode);  //1 indicates came from the left
        }

        return tNode;
   }

   protected TreeNode insertRight(TreeNode tNode, KeyedItem item){

        TreeNode subtree = insertItem(tNode.getRight(), item);
        tNode.setRight(subtree);

        //check balance factor and rotate if necessary
        if (avlFlag)
        {
            tNode = avlFixAddRight((AVLTreeNode)tNode);  //2 indicates came from the right
        }

        return tNode;
   }

   protected TreeNode deleteItem(TreeNode tNode, Comparable searchKey) 
   {
      TreeNode subtree;
      if (tNode == null) 
      {
         throw new TreeException("Item not found");
      }

      KeyedItem nodeItem = (KeyedItem) tNode.getItem();
      int comparison = searchKey.compareTo(nodeItem.getKey());

      if (comparison == 0) 
      {
         // item is in the root of some subtree
         //found something to delete so set avlFlag to true
         avlFlag = true;  //will delete a node from the tree, need to check balancing
         tNode = deleteNode(tNode);  // delete the item
      }
      // else search for the item
      else if (comparison < 0) 
      {
         // search the left subtree
         subtree = deleteItem(tNode.getLeft(), searchKey);
         tNode.setLeft(subtree);

         //check balance factors and rotate if necessary
         if (avlFlag)
         {
            tNode = avlFixDeleteLeft((AVLTreeNode)tNode);  //came from left
         }
      }
      else 
      { 
         // search the right subtree
         subtree = deleteItem(tNode.getRight(), searchKey);
         tNode.setRight(subtree);

         //check balance factors and rotate if necessary
         if (avlFlag)
         {
            tNode = avlFixDeleteRight((AVLTreeNode)tNode);  //came from right
         }
      }  

      return tNode;
   } 

   //determine if the delete is an easy case or a hard case
   protected TreeNode deleteNode(TreeNode tNode) 
   {
      KeyedItem replacementItem;

      // test for a leaf --  this test is taken care of by the next two
      if ( (tNode.getLeft() == null) && (tNode.getRight() == null) ) 
      {
         return null;
      }  

      // test for no left child
      else if (tNode.getLeft() == null) 
      {
         return tNode.getRight();
      } 

      // test for no right child
      else if (tNode.getRight() == null) 
      {
         return tNode.getLeft();
      }  

      // there are two children:
      // retrieve and delete the inorder successor
      else 
      {
         replacementItem = findLeftmost(tNode.getRight());
         tNode.setItem(replacementItem);
         tNode.setRight(deleteLeftmost(tNode.getRight()));
         if (avlFlag)
         {
            //inorder successor: take a right, then keep going left
            tNode = avlFixDeleteRight((AVLTreeNode)tNode);  //came from right
         }
         return tNode;
      }  
   } 

   //find the inorder successor
   //this method is overridden to allow checking for balancing
   protected TreeNode deleteLeftmost(TreeNode tNode) 
   {
      if (tNode.getLeft() == null) 
      {
         avlFlag = true;
         return tNode.getRight();
      }
      else 
      {
         tNode.setLeft(deleteLeftmost(tNode.getLeft()));
         if (avlFlag)
         {
            tNode = avlFixDeleteLeft((AVLTreeNode)tNode);  //keep going left
         }
         return tNode;
      }  
   }   

   protected void srRotation(AVLTreeNode tNode, AVLTreeNode temp, AVLTreeNode left){

        tNode.setBalanceFactor(AVL.BALANCED);
        left.setBalanceFactor(AVL.BALANCED);
        temp = (AVLTreeNode) rotateRight(tNode);
        System.out.println("SR");

   }

   protected void dlrRotation(AVLTreeNode tNode, AVLTreeNode temp, AVLTreeNode left){

        AVLTreeNode leftRight = left.getRight();
        AVL bF = leftRight.getBalanceFactor();

        if (bF == AVL.BALANCED)  //can happen
        {
            tNode.setBalanceFactor(AVL.BALANCED);
            left.setBalanceFactor(AVL.BALANCED);
        }
        else if (bF == AVL.RIGHT)
        {
            tNode.setBalanceFactor(AVL.BALANCED);
            left.setBalanceFactor(AVL.LEFT);
        }
        else
        {
            tNode.setBalanceFactor(AVL.RIGHT);  
            left.setBalanceFactor(AVL.BALANCED);  
        }

        leftRight.setBalanceFactor(AVL.BALANCED);

        temp = (AVLTreeNode) rotateLeft(left);
        tNode.setLeft(temp);
        temp = (AVLTreeNode) rotateRight(tNode);  //don't want to set parent links just yet (done in insert method)
        System.out.println("DLR");

   }

   protected void slRotation(AVLTreeNode tNode, AVLTreeNode temp, AVLTreeNode right){

        tNode.setBalanceFactor(AVL.BALANCED);
        right.setBalanceFactor(AVL.BALANCED);
        temp = (AVLTreeNode) rotateLeft(tNode);
        System.out.println("SL");
   }

   protected void drlRotation(AVLTreeNode tNode, AVLTreeNode temp, AVLTreeNode right){

        AVLTreeNode rightLeft = right.getLeft();
        AVL bF = rightLeft.getBalanceFactor();

        if (bF == AVL.BALANCED)  //can happen
        {
            tNode.setBalanceFactor(AVL.BALANCED);
            right.setBalanceFactor(AVL.BALANCED);
        }
        else if (bF == AVL.RIGHT)
        {
            tNode.setBalanceFactor(AVL.LEFT);
            right.setBalanceFactor(AVL.BALANCED);
        }
        else
        {
            tNode.setBalanceFactor(AVL.BALANCED);
            right.setBalanceFactor(AVL.RIGHT);
        }

        rightLeft.setBalanceFactor(AVL.BALANCED);

        temp = (AVLTreeNode) rotateRight(right);
        tNode.setRight(temp);
        temp = (AVLTreeNode) rotateLeft(tNode);
        System.out.println("DRL");
   }

   //check for a possible rotation
   //if a rotation occurs, set avlFlag to false (no more balance checking required)
   protected AVLTreeNode avlFixAddLeft(AVLTreeNode tNode)
   {
      AVL factor;
      AVLTreeNode temp = null;

      //first must update the balance factors
      tNode.insertLeft();
      factor = tNode.getBalanceFactor();
      if (factor == AVL.BALANCED)  //change to balanced, stop
      {
         avlFlag = false; //no more to do this time around
         return tNode;
      }

      //no rotation necessary at this node, but need to keep checking upwards
      if (factor == AVL.LEFT || factor == AVL.RIGHT)
      {
         return tNode;  
      }
      //find the rotation to perform, change balance factors, rotate, stop
      else
      {
         AVLTreeNode left = tNode.getLeft();

         if (factor == AVL.LEFT_HEAVY && tNode.getLeft().getBalanceFactor() == AVL.RIGHT)
         {
            dlrRotation(tNode, temp, left);
         }

         //single rotate right
         else if (factor == AVL.LEFT_HEAVY && left.getBalanceFactor() == AVL.LEFT)
         {
            srRotation(tNode, temp, left);
         }

         avlFlag = false; //basically, stop checking 
         return temp;  //return the replacement node for this position, linked in recursively
      }
   }

   protected AVLTreeNode avlFixAddRight(AVLTreeNode tNode)
   {
      AVL factor;
      AVLTreeNode temp = null;

      //first must update the balance factors
      
      tNode.insertRight();
      factor = tNode.getBalanceFactor();
      if (factor == AVL.BALANCED)  //change to balanced, stop
      {
        avlFlag = false; 
        return tNode;
      }

      //no rotation necessary at this node, but need to keep checking upwards
      if (factor == AVL.LEFT || factor == AVL.RIGHT)
      {
         return tNode;  
      }
      //find the rotation to perform, change balance factors, rotate, stop
      else
      {
         AVLTreeNode right = tNode.getRight();

         //double rotate right then left
         if (factor == AVL.RIGHT_HEAVY && right.getBalanceFactor() == AVL.LEFT)
         {
            drlRotation(tNode, temp, right);
         } 

         //single rotate left
         else
         {
            slRotation(tNode, temp, right);
         }

         avlFlag = false; //basically, stop checking 
         return temp;  //return the replacement node for this position, linked in recursively
      }
   }

   protected AVLTreeNode avlFixDeleteRight(AVLTreeNode tNode)
   {
      AVL factor;
      AVLTreeNode temp;

      //first must update the bance factors
      
      factor = tNode.getBalanceFactor();
      tNode.deleteRight();
      if (factor == AVL.BALANCED) //change from zero--  STOP
      {
          avlFlag = false; //no more to do this time around
          return tNode;
      }
      factor = tNode.getBalanceFactor();

      if (factor == AVL.LEFT || factor == AVL.RIGHT || factor == AVL.BALANCED)
      {
         return tNode;  //need to keep checking, but no rotations necessary as yet
      }
      else
      {
         //rotations necessary for deleting a node
         AVLTreeNode right = tNode.getRight();
         AVLTreeNode left = tNode.getLeft();

         if (factor == AVL.LEFT_HEAVY && left.getBalanceFactor() == AVL.BALANCED)
         {
            tNode.setBalanceFactor(AVL.LEFT);
            left.setBalanceFactor(AVL.RIGHT);
            temp = (AVLTreeNode) rotateRight(tNode);
            avlFlag = false;  //STOP
            System.out.println("SR0");
         }

         else if (factor == AVL.RIGHT_HEAVY && right.getBalanceFactor() == AVL.BALANCED)
         {
            tNode.setBalanceFactor(AVL.RIGHT);
            right.setBalanceFactor(AVL.LEFT);
            temp = (AVLTreeNode) rotateLeft(tNode);
            avlFlag = false;  //STOP
            System.out.println("SL0");
         }

         else if (factor == AVL.LEFT_HEAVY && left.getBalanceFactor() == AVL.LEFT)
         {
            tNode.setBalanceFactor(AVL.BALANCED);
            left.setBalanceFactor(AVL.BALANCED);
            temp = (AVLTreeNode) rotateRight(tNode);
            System.out.println("SR");
         }

         else if (factor == AVL.RIGHT_HEAVY && right.getBalanceFactor() == AVL.RIGHT)
         {
            tNode.setBalanceFactor(AVL.BALANCED);
            right.setBalanceFactor(AVL.BALANCED);
            temp = (AVLTreeNode) rotateLeft(tNode);
            System.out.println("SL");
         }

         else if (factor == AVL.LEFT_HEAVY && left.getBalanceFactor() == AVL.RIGHT)
         {
            AVLTreeNode leftRight = left.getRight();
            AVL bF = leftRight.getBalanceFactor();
        
            if (bF == AVL.BALANCED) 
            {
               tNode.setBalanceFactor(AVL.BALANCED);
               left.setBalanceFactor(AVL.BALANCED);
            }
            else if (bF == AVL.RIGHT)
            {
               tNode.setBalanceFactor(AVL.BALANCED);
               left.setBalanceFactor(AVL.LEFT);
            }
            else
            {
               tNode.setBalanceFactor(AVL.RIGHT);  
               left.setBalanceFactor(AVL.BALANCED);  
            }

            leftRight.setBalanceFactor(AVL.BALANCED);

            temp = (AVLTreeNode) rotateLeft(left);
            tNode.setLeft(temp);
            temp = (AVLTreeNode) rotateRight(tNode);
            System.out.println("DLR");
         }

         else
         {
            AVLTreeNode rightLeft = right.getLeft();
            AVL bF = rightLeft.getBalanceFactor();

            if (bF == AVL.BALANCED)  
            {
               tNode.setBalanceFactor(AVL.BALANCED);
               right.setBalanceFactor(AVL.BALANCED);
            }
            else if (bF == AVL.RIGHT)
            {
               tNode.setBalanceFactor(AVL.LEFT);
               right.setBalanceFactor(AVL.BALANCED);
            }
            else
            {
               tNode.setBalanceFactor(AVL.BALANCED);
               right.setBalanceFactor(AVL.RIGHT);
            }
            rightLeft.setBalanceFactor(AVL.BALANCED);

            temp = (AVLTreeNode) rotateRight(right);
            tNode.setRight(temp);
            temp = (AVLTreeNode) rotateLeft(tNode);
            System.out.println("DRL");
         }

         return temp;
      }
   }

   protected AVLTreeNode avlFixDeleteLeft(AVLTreeNode tNode)
   {
      AVL factor;
      AVLTreeNode temp;

      //first must update the bance factors
      
      factor = tNode.getBalanceFactor();
      tNode.deleteLeft();
      if (factor == AVL.BALANCED)  //change from zero--  STOP
      {
          avlFlag = false; //no more to do this time around
          return tNode;
      }
      factor = tNode.getBalanceFactor();

      if (factor == AVL.LEFT || factor == AVL.RIGHT || factor == AVL.BALANCED)
      {
         return tNode;  //need to keep checking, but no rotations necessary as yet
      }
      else
      {
         //rotations necessary for deleting a node
         AVLTreeNode right = tNode.getRight();
         AVLTreeNode left = tNode.getLeft();

         if (factor == AVL.LEFT_HEAVY && left.getBalanceFactor() == AVL.BALANCED)
         {
            tNode.setBalanceFactor(AVL.LEFT);
            left.setBalanceFactor(AVL.RIGHT);
            temp = (AVLTreeNode) rotateRight(tNode);
            avlFlag = false;  //STOP
            System.out.println("SR0");
         }

         else if (factor == AVL.RIGHT_HEAVY && right.getBalanceFactor() == AVL.BALANCED)
         {
            tNode.setBalanceFactor(AVL.RIGHT);
            right.setBalanceFactor(AVL.LEFT);
            temp = (AVLTreeNode) rotateLeft(tNode);
            avlFlag = false;  //STOP
            System.out.println("SL0");
         }

         else if (factor == AVL.LEFT_HEAVY && left.getBalanceFactor() == AVL.LEFT)
         {
            tNode.setBalanceFactor(AVL.BALANCED);
            left.setBalanceFactor(AVL.BALANCED);
            temp = (AVLTreeNode) rotateRight(tNode);
            System.out.println("SR");
         }

         else if (factor == AVL.RIGHT_HEAVY && right.getBalanceFactor() == AVL.RIGHT)
         {
            tNode.setBalanceFactor(AVL.BALANCED);
            right.setBalanceFactor(AVL.BALANCED);
            temp = (AVLTreeNode) rotateLeft(tNode);
            System.out.println("SL");
         }

         else if (factor == AVL.LEFT_HEAVY && left.getBalanceFactor() == AVL.RIGHT)
         {
            AVLTreeNode leftRight = left.getRight();
            AVL bF = leftRight.getBalanceFactor();
        
            if (bF == AVL.BALANCED) 
            {
               tNode.setBalanceFactor(AVL.BALANCED);
               left.setBalanceFactor(AVL.BALANCED);
            }
            else if (bF == AVL.RIGHT)
            {
               tNode.setBalanceFactor(AVL.BALANCED);
               left.setBalanceFactor(AVL.LEFT);
            }
            else
            {
               tNode.setBalanceFactor(AVL.RIGHT);  
               left.setBalanceFactor(AVL.BALANCED);  
            }

            leftRight.setBalanceFactor(AVL.BALANCED);

            temp = (AVLTreeNode) rotateLeft(left);
            tNode.setLeft(temp);
            temp = (AVLTreeNode) rotateRight(tNode);
            System.out.println("DLR");
         }

         else
         {
            AVLTreeNode rightLeft = right.getLeft();
            AVL bF = rightLeft.getBalanceFactor();

            if (bF == AVL.BALANCED)  
            {
               tNode.setBalanceFactor(AVL.BALANCED);
               right.setBalanceFactor(AVL.BALANCED);
            }
            else if (bF == AVL.RIGHT)
            {
               tNode.setBalanceFactor(AVL.LEFT);
               right.setBalanceFactor(AVL.BALANCED);
            }
            else
            {
               tNode.setBalanceFactor(AVL.BALANCED);
               right.setBalanceFactor(AVL.RIGHT);
            }
            rightLeft.setBalanceFactor(AVL.BALANCED);

            temp = (AVLTreeNode) rotateRight(right);
            tNode.setRight(temp);
            temp = (AVLTreeNode) rotateLeft(tNode);
            System.out.println("DRL");
         }

         return temp;
      }
   }
}  
