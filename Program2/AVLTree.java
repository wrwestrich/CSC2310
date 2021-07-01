

//should consider not extending BST as a lot of knowledge about parent class implementation 
//is necessary to write this class
public final class AVLTree extends BinarySearchTree implements Execute
{
   private boolean avlFlag;

   public AVLTree()  
   {
      super();
   } 

   public void insert(KeyedItem item) throws TreeException
   {
      super.insert(item);
      avlFlag = false;
      assert isBalanced() : "tree is not balanced after insertion of key: " + item.getKey();
   } 

   public void delete(Comparable sk) throws TreeException
   {
      super.delete(sk);
      avlFlag = false;
      assert isBalanced() : "tree is not balanced after deletion of key: " + sk;
   }

   //overriding these protected methods requires knowledge of the parent class' implementation
   protected TreeNode createNode(KeyedItem item)
   {
      TreeNode tNode = new AVLTreeNode(item);
      avlFlag = true;
      return tNode;
   }

   protected TreeNode insertLeft(TreeNode tNode, KeyedItem item)
   {
      tNode = super.insertLeft(tNode, item);
      AVLTreeNode avltn = (AVLTreeNode) tNode;
      if (avlFlag)
      {
         avltn = avlFixAddLeft(avltn);  
      }
      return avltn;
   }

   protected TreeNode insertRight(TreeNode tNode, KeyedItem item)
   {
      tNode = super.insertRight(tNode, item);
      AVLTreeNode avltn = (AVLTreeNode) tNode;
      if (avlFlag)
      {
         avltn = avlFixAddRight(avltn);  
      }
      return avltn;
   }

   //sets balance factors and calls the inherited rotate right method
   private AVLTreeNode singleRight(AVLTreeNode tNode)
   {
      AVLTreeNode left = tNode.getLeft();
      tNode.setBalanceFactor(AVL.BALANCED);
      left.setBalanceFactor(AVL.BALANCED);
      tNode = (AVLTreeNode) rotateRight(tNode);
      //System.out.println("SR");
      return tNode;
   }

   private AVLTreeNode singleLeft(AVLTreeNode tNode)
   {
      AVLTreeNode right = tNode.getRight();
      tNode.setBalanceFactor(AVL.BALANCED);
      right.setBalanceFactor(AVL.BALANCED);
      tNode = (AVLTreeNode) rotateLeft(tNode);
      //System.out.println("SL");
      return tNode;
   }

   private AVLTreeNode doubleLeftRight(AVLTreeNode tNode)
   {
      AVLTreeNode left = tNode.getLeft();
      AVLTreeNode leftRight = left.getRight();

      //next line determines the rotation
      AVL bF = leftRight.getBalanceFactor();

      leftRight.setBalanceFactor(AVL.BALANCED);
      tNode.setBalanceFactor(AVL.BALANCED);
      left.setBalanceFactor(AVL.BALANCED);

      if (bF == AVL.RIGHT)
      {
         //tNode.setBalanceFactor(AVL.BALANCED);
         left.setBalanceFactor(AVL.LEFT);
      }
      else if (bF == AVL.LEFT)
      {
         tNode.setBalanceFactor(AVL.RIGHT);  
         //left.setBalanceFactor(AVL.BALANCED);  
      }

      //do the rotation
      AVLTreeNode temp = (AVLTreeNode) rotateLeft(left);
      tNode.setLeft(temp);
      tNode = (AVLTreeNode) rotateRight(tNode);  
      //System.out.println("DLR");
      return tNode;
   }

   private AVLTreeNode doubleRightLeft(AVLTreeNode tNode)
   {
      AVLTreeNode right = tNode.getRight();
      AVLTreeNode rightLeft = right.getLeft();

      //next line determines the rotation
      AVL bF = rightLeft.getBalanceFactor();

      rightLeft.setBalanceFactor(AVL.BALANCED);
      tNode.setBalanceFactor(AVL.BALANCED);
      right.setBalanceFactor(AVL.BALANCED);

      if (bF == AVL.RIGHT)
      {
         tNode.setBalanceFactor(AVL.LEFT);
         //right.setBalanceFactor(AVL.BALANCED);
      }
      else if (bF == AVL.LEFT)
      {
         //tNode.setBalanceFactor(AVL.BALANCED);
         right.setBalanceFactor(AVL.RIGHT);
      }

      AVLTreeNode temp = (AVLTreeNode) rotateRight(right);
      tNode.setRight(temp);
      tNode = (AVLTreeNode) rotateLeft(tNode);
      //System.out.println("DRL");
      return tNode;
   }

   private AVLTreeNode avlFixAddLeft(AVLTreeNode tNode)
   {
      //b, l, and lu are the only possible balance factors
      tNode.insertLeft();
      AVL factor = tNode.getBalanceFactor();
      if (factor == AVL.BALANCED)
      {
         avlFlag = false; //no more to do this time around
         return tNode;
      }
      else if (factor == AVL.LEFT)
      {
         return tNode;  //no rotation necessary at this node, but need to keep checking upwards
      }

      //the balance factor must be lu
      AVLTreeNode left = tNode.getLeft();
      if (left.getBalanceFactor() == AVL.RIGHT)
      {
         tNode = doubleLeftRight(tNode);
      }
      else 
      {
         tNode = singleRight(tNode);
      }

      avlFlag = false; //basically, stop checking (return the replacement node for this position)
      return tNode;
   }

   private AVLTreeNode avlFixAddRight(AVLTreeNode tNode)
   {
      tNode.insertRight();
      AVL factor = tNode.getBalanceFactor();
      if (factor == AVL.BALANCED)
      {
         avlFlag = false; //no more to do this time around
         return tNode;
      }
      else if (factor == AVL.RIGHT)
      {
         return tNode;  //no rotation necessary at this node, but need to keep checking upwards
      }

      AVLTreeNode right = tNode.getRight();
      if (right.getBalanceFactor() == AVL.LEFT)
      {
         tNode = doubleRightLeft(tNode);
      }
      else
      {
         tNode = singleLeft(tNode);
      }

      avlFlag = false;
      return tNode;
   }

   protected TreeNode deleteLeft(TreeNode tNode, Comparable sk)
   {
      tNode = super.deleteLeft(tNode, sk);
      AVLTreeNode avltn = (AVLTreeNode) tNode;
      if (avlFlag)
      {
         avltn = avlFixDeleteLeft(avltn); 
      }
      return avltn;
   }

   protected TreeNode deleteRight(TreeNode tNode, Comparable sk)
   {
      tNode = super.deleteRight(tNode, sk);
      AVLTreeNode avltn = (AVLTreeNode) tNode;
      if (avlFlag)
      {
         avltn = avlFixDeleteRight(avltn);  
      }
      return avltn;
   }

   protected TreeNode deleteNode(TreeNode tNode) 
   {
      avlFlag = true;
      tNode = super.deleteNode(tNode);
      return tNode;
   } 

   protected TreeNode deleteInorderSuccessor(TreeNode tNode)
   {
      tNode = super.deleteInorderSuccessor(tNode);
      AVLTreeNode avltn = (AVLTreeNode) tNode;
      if (avlFlag)
      {
         avltn = avlFixDeleteRight(avltn);  //came from right
      }
      return avltn;
   }

   protected TreeNode deleteLeftmostt(TreeNode tNode)
   {
      tNode = super.deleteLeftmostt(tNode);
      AVLTreeNode avltn = (AVLTreeNode) tNode;
      if (avlFlag)
      {
         avltn = avlFixDeleteLeft(avltn);  
      }
      return avltn;
   }

   //will be doing left rotations if coming back from the left on a delete
   private AVLTreeNode avlFixDeleteLeft(AVLTreeNode tNode)
   {
      AVL factor = tNode.getBalanceFactor();
      tNode.deleteLeft();
      if (factor == AVL.BALANCED)  //change from zero--  STOP
      {
         avlFlag = false; //no more to do this time around
         return tNode;
      }
      factor = tNode.getBalanceFactor();

      if (factor == AVL.RIGHT || factor == AVL.BALANCED)
      {
         return tNode;  //need to keep checking, but no rotations necessary as yet
      }

      //rotations necessary for deleting a node
      AVLTreeNode right = tNode.getRight();
      AVL bF = right.getBalanceFactor();

      if (bF == AVL.BALANCED)
      {
         tNode = singleLeft0(tNode);
      }
      else if (bF == AVL.RIGHT)
      {
         tNode = singleLeft(tNode);
      }
      else 
      {
         tNode = doubleRightLeft(tNode);
      }

      return tNode;
   }

   private AVLTreeNode avlFixDeleteRight(AVLTreeNode tNode)
   {
      AVL factor = tNode.getBalanceFactor();
      tNode.deleteRight();
      if (factor == AVL.BALANCED) //change from zero--  STOP
      {
         avlFlag = false; //no more to do this time around
         return tNode;
      }
      factor = tNode.getBalanceFactor();


      if (factor == AVL.LEFT || factor == AVL.BALANCED)
      {
         return tNode;  //need to keep checking, but no rotations necessary as yet
      }

      //rotations necessary for deleting a node
      AVLTreeNode left = tNode.getLeft();
      AVL bF = left.getBalanceFactor();

      if (bF == AVL.BALANCED)
      {
         tNode = singleRight0(tNode);
      }
      else if (bF == AVL.LEFT)
      {
         tNode = singleRight(tNode);
      }
      else 
      {
         tNode = doubleLeftRight(tNode);
      }

      return tNode;
   }

   private AVLTreeNode singleLeft0(AVLTreeNode tNode)
   {
      AVLTreeNode right = tNode.getRight();
      tNode.setBalanceFactor(AVL.RIGHT);
      right.setBalanceFactor(AVL.LEFT);
      tNode = (AVLTreeNode) rotateLeft(tNode);
      avlFlag = false;  //STOP
      System.out.println("SL0");
      return tNode;
   }

   private AVLTreeNode singleRight0(AVLTreeNode tNode)
   {
      AVLTreeNode left = tNode.getLeft();
      tNode.setBalanceFactor(AVL.LEFT);
      left.setBalanceFactor(AVL.RIGHT);
      tNode = (AVLTreeNode) rotateRight(tNode);
      avlFlag = false;  //STOP
      System.out.println("SR0");
      return tNode;
   }

   public void execute(Command foo){
      for(Object node : this)
         foo.execute(node);
   }
}  
