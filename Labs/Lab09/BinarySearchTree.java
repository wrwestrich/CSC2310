// Will Westrich and Seth Hunter

public class BinarySearchTree extends BinaryTreeBasis implements SearchTreeInterface
{ 

   private int size;

   /*
    *   Class Contstructor; Calls inherited constuctor and initializes size variable.
    */ 
   public BinarySearchTree()
   {
      super();
      size = 0;
   }

    /*
    * Retrieves an item with a given search key from the tree.
    * Precondition: searchKey is the search key of the item to be retrieved.
    * Postcondition: If the retrieval was successful, the tree item with the matching search key is returned.
    *                If no such item exists, the method returns a null reference.
    */
   public KeyedItem retrieve(Comparable searchKey) 
   {
      assert validateBSTProperty() : " Not a BST";
      return retrieveItem(getRootNode(), searchKey);
   }  

    /*
    * Inserts an item into a tree in its proper sorted order according to the item's search key.
    * Precondition: The item to be inserted into the tree has a
    *               search key that differs from all search keys presently in the table.
    * Postcondition: If the insertion was successful,
    *                item is in its proper order in the tree.  Otherwise, the tree is unchanged.
    * Throws: TreeException if the search key is already present
    */
   public void insert(KeyedItem item) throws TreeException
   {
      setRootNode(insertItem(getRootNode(), item));
      ++size;
      assert validateSize() : " Size is incorrect";
      assert validateBSTProperty() : " Not a BST";
      assert isBalanced(getRootNode()) : " Tree is not balanced";
   }

    /*
    * Deletes an item with a given search key from the tree.
    * Precondition: searchKey is the search key of the item to be deleted.
    * Postcondition: If the item whose search key equals searchKey existed in the tree, the item was deleted.
    *                Otherwise, the tree is unchanged.
    * Throws: TreeException if the search key is not in the tree.
    */
   public void delete(Comparable searchKey) throws TreeException 
   {
      setRootNode(deleteItem(getRootNode(), searchKey));
      --size;
      assert validateSize() : " Size is incorrect";
      assert validateBSTProperty() : " Not a BST";
      assert isBalanced(getRootNode()) : " Tree is not balanced";
   }  

    /*
    *   Serches for leaf location to return leaf's item.
    *   Precondition: Item is unique.
    *   Postcondition: Returns current node's item.
    *   Calls: retrieveLeft, retrieveRight depending on item's key.
    */
   protected KeyedItem retrieveItem(TreeNode tNode, Comparable searchKey)
   {
      //disallow duplicates so that you always know which object to retrieve (or delete)
      //you could return a list with all duplicate search keys (but delete is still a problem)
      KeyedItem treeItem;

      if (tNode == null) 
      {
         treeItem = null;
      }
      else 
      {
         KeyedItem nodeItem = (KeyedItem) tNode.getItem();
         int comparison = searchKey.compareTo(nodeItem.getKey());

         if (comparison == 0) 
         {
            // item is in the root of some subtree
            treeItem = nodeItem;
         }
         else if (comparison < 0) 
         {
            // search the left subtree
            treeItem = retrieveLeft(tNode.getLeft(), searchKey);
         }
         else  
         { 
            // search the right subtree
            treeItem = retrieveRight(tNode.getRight(), searchKey);
         }  
      }  
      return treeItem;
   }  

   /*
    *   Searches for the leaf insertion location for item.
    *   Precondition: Item is not null.
    *   Postcondition: Returns the current node so it can be linked with its parent depending on whether
    *                  an right or a left was taken.
    *   Throws: TreeException if inserting duplicate is attempted.
    *   Calls: insertLeft, insertRight, insertDup depending on item's key.
    */
   protected TreeNode insertItem(TreeNode tNode, KeyedItem item) throws TreeException
   {

      if (tNode == null) 
      {
         // position of insertion found; insert after leaf
         // create a new node
         tNode = new TreeNode(item);
         return tNode;
      }  

      TreeNode subtree;
      KeyedItem nodeItem = (KeyedItem)tNode.getItem();
      int comparison = item.getKey().compareTo(nodeItem.getKey());

      // search for the insertion position
      if (comparison == 0)
      {
         tNode = insertDuplicate();
      }
      else if (comparison < 0) 
      {
         // search the left subtree
         tNode = insertLeft(tNode.getLeft(), item);
      }
      else 
      { 
         // search the right subtree
         tNode = insertRight(tNode.getRight(), item);
      }  

      return tNode;
   }

    /*
    *   Searches for leaf  item location for leaf deletion.
    *   Precondition: Item is not null.
    *   Postcondition: Returns current node to update the tree's new state.
    *   Throws: TreeException if item is not found.
    *   Calls: deleteLeft, deleteRight depending on item's key.
    */
   protected TreeNode deleteItem(TreeNode tNode, Comparable searchKey) throws TreeException
   {

      if (tNode == null) 
      {
         throw new TreeException("Item not found");
      }

      TreeNode subtree;
      KeyedItem nodeItem = (KeyedItem)tNode.getItem();
      int comparison = searchKey.compareTo(nodeItem.getKey());

      if (comparison == 0) 
      {
         // item is in the root of some subtree
         tNode = deleteNode(tNode);  // delete the item
      }
      // else search for the item
      else if (comparison < 0) 
      {
         // search the left subtree
         tNode = deleteLeft(tNode.getLeft(), searchKey);
      }
      else 
      { 
         // search the right subtree
         tNode = deleteRight(tNode.getRight(), searchKey);
      } 

      return tNode;
   }  

    /*
    *   Deletes node passed to method from tree.
    *   Precondition: Item is not null.
    *   Postcondition: Returns node to replace deleted one for linking.
    *   Calls: findLeftMost, deleteLeftMost if node has two children.
    */
   protected TreeNode deleteNode(TreeNode tNode) 
   {
      // Algorithm note: There are four cases to consider:
      //   1. The tNode is a leaf.
      //   2. The tNode has no left child.
      //   3. The tNode has no right child.
      //   4. The tNode has two children.
      // Calls: findLeftmost and deleteLeftmost

      // test for a leaf --  this test is taken care of by the next two
      if ((tNode.getLeft() == null) && (tNode.getRight() == null)) 
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
         KeyedItem replacementItem = findLeftmost(tNode.getRight());
         tNode.setItem(replacementItem);
         TreeNode subtree = deleteLeftmost(tNode.getRight());
         tNode.setRight(subtree);
         return tNode;
      } 
   }  

    /*
    *   Finds farthest left node.
    *   Precondition: Item is not null.
    *   Postcondition: Returns left most node.
    */
   protected KeyedItem findLeftmost(TreeNode tNode)  
   {
      if (tNode.getLeft() == null) 
      {
         return (KeyedItem)tNode.getItem();
      }
      else 
      {
         return findLeftmost(tNode.getLeft());
      }  
   } 

    /*
    *   Deletes left most node.
    *   Precondition: Item is not null.
    *   Postcondition: Returns node to replace deleted node for linking.
    */
   protected TreeNode deleteLeftmost(TreeNode tNode) 
   {
      if (tNode.getLeft() == null) 
      {
         return tNode.getRight();
      }
      else 
      {
         TreeNode subtree = deleteLeftmost(tNode.getLeft());
         tNode.setLeft(subtree);
         return tNode;
      }  
   } 

    /*
    *   Rotates subtree left.
    *   Precondition: Item is not null.
    *   Postcondition: Returns rotated subtree.
    */
   protected TreeNode rotateLeft(TreeNode tNode)
   {
      TreeNode right = tNode.getRight();
      TreeNode rightleft = right.getLeft();

      tNode.setRight(rightleft);
      right.setLeft(tNode);

      return right;
   }

    /*
    *   Rotates subtree right.
    *   Precondition: Item is not null.
    *   Postcondition: Returns rotated subtree.
    */
   protected TreeNode rotateRight(TreeNode tNode)
   {
      TreeNode left = tNode.getLeft();
      TreeNode leftright = left.getRight();

      tNode.setLeft(leftright);
      left.setRight(tNode);

      return left;
   }

    /*
    *   Validates if tree follows BST conventions.
    *   Precondition: Tree is not null.
    *   Postcondition: Returns true if tree follows conventions, false if not.
    */
   protected boolean validateBSTProperty(){
        
        BinaryTreeIterator iterator = (BinaryTreeIterator) iterator();
        iterator.setInorder();
        TreeNode node1;
        TreeNode node2;

        if(iterator.hasNext()){
            node1 = (TreeNode) iterator.next();
        }
        if(iterator.hasNext()){
            node2 = (TreeNode) iterator.next();
        }

        do{
            KeyedItem key1 = (KeyedItem) node1.getItem();
            KeyedItem key2 = (KeyedItem) node2.getItem();
            if(key1.getKey().compareTo(key2.getKey()) > 0)
                return false;

            node1 = node2;
            if(iterator.hasNext())
                node2 = (TreeNode) iterator.next();
        } while(iterator.hasNext());
        
        return true;
   }

    /*
    *   Validates if size is recorded correctly.
    *   Precondition: Tree is not null.
    *   Postcondition: Returns true if size is propery recorded, false if not.
    *   Calls: size
    */
   protected boolean validateSize(){

        if(size == size())
            return true;
        else
            return false;
   }

    /*
    *   Validates if tree is balanced.
    *   Precondition: Tree is not null.
    *   Postcondition: Returns true if tree is balanced, false if not.
    *   Calls: getHeight
    */
   protected boolean isBalanced(TreeNode node){

        if(getRootNode() == null)
            return true;

        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());

        if(Math.abs(leftHeight - rightHeight) >= 1 && isBalanced(node.getLeft()) && isBalanced(node.getRight()))
            return true;

        return false;
   }

    /*
    *   Returns height of tree.
    *   Precondition: Tree is not null.
    *   Postcondition: Returns total height of tree.
    *   Calls: getHeight
    */
   public int height(){
        return getHeight(getRootNode());
   }

    /*
    *   Returns height of specified subtree.
    *   Precondition: Subtree is not null.
    *   Postcondition: Returns height of subtree.
    */
   protected int getHeight(TreeNode tNode){
        
        if(tNode == null)
            return -1;

        int height;
        int leftHeight = getHeight(tNode.getLeft());
        int rightHeight = getHeight(tNode.getRight());

        if(leftHeight >= rightHeight)
            height = leftHeight + 1;
        else
            height = rightHeight + 1;

        return height;
   }

    /*
    * Returns the number of keyed items in the tree.
    * Preconditions: None.
    * Postcondition: Returns the number of keyed items in the tree.
    */
   public int size(){

        BinaryTreeIterator iterator = (BinaryTreeIterator) iterator();
        iterator.setInorder();

        int size = 0;

        while(iterator.hasNext()){
            ++size;
            //TreeNode foo = iterator.next();
            iterator.next();
        }

        return size;
   }

    /*
    *   Searches for the leaf insertion location for item.
    *   Precondition: Item is not null.
    *   Postcondition: Returns the current node so it can be linked with its parent depending on whether
    *                  an right or a left was taken.
    *   Throws: TreeException if inserting duplicate is attempted.
    *   Calls: insertLeft, insertRight, insertDup depending on item's key.
    */
   protected TreeNode insertLeft(TreeNode tNode, KeyedItem item) throws TreeException{

      if (tNode == null) 
      {
         // position of insertion found; insert after leaf
         // create a new node
         tNode = new TreeNode(item);
         return tNode;
      }  

      TreeNode subtree;
      KeyedItem nodeItem = (KeyedItem)tNode.getItem();
      int comparison = item.getKey().compareTo(nodeItem.getKey());

      // search for the insertion position
      if (comparison == 0)
      {
         tNode = insertDuplicate();
      }
      else if (comparison < 0) 
      {
         // search the left subtree
         tNode = insertLeft(tNode.getLeft(), item);
      }
      else 
      { 
         // search the right subtree
         tNode = insertRight(tNode.getRight(), item);
      }  

      return tNode;
   }

    /*
    *   Searches for the leaf insertion location for item.
    *   Precondition: Item is not null.
    *   Postcondition: Returns the current node so it can be linked with its parent depending on whether
    *                  an right or a left was taken.
    *   Throws: TreeException if inserting duplicate is attempted.
    *   Calls: insertLeft, insertRight, insertDup depending on item's key.
    */
   protected TreeNode insertRight(TreeNode tNode, KeyedItem item) throws TreeException{

      if (tNode == null) 
      {
         // position of insertion found; insert after leaf
         // create a new node
         tNode = new TreeNode(item);
         return tNode;
      }  

      TreeNode subtree;
      KeyedItem nodeItem = (KeyedItem)tNode.getItem();
      int comparison = item.getKey().compareTo(nodeItem.getKey());

      // search for the insertion position
      if (comparison == 0)
      {
         tNode = insertDuplicate();
      }
      else if (comparison < 0) 
      {
         // search the left subtree
         tNode = insertLeft(tNode.getLeft(), item);
      }
      else 
      { 
         // search the right subtree
         tNode = insertRight(tNode.getRight(), item);
      }  

      return tNode;
   }

   /*
    *   Disallows dulpicates to be inserted
    *   Precondition: Item already exists.
    *   Postcondition: Throws TreeException and returns null reference.
    *   Throws: TreeException
    */
   protected TreeNode insertDuplicate() throws TreeException{
        throw new TreeException ("Cannot add duplicate.");

        return null;
   }

    /*
    *   Searches for leaf location to return leaf's item.
    *   Precondition: Item is unique.
    *   Postcondition: Returns current node's item.
    *   Calls: retrieveLeft, retrieveRight depending on item's key.
    */
   protected KeyedItem retrieveLeft(TreeNode tNode, Comparable searchKey){

      KeyedItem treeItem;

      if (tNode == null) 
      {
         treeItem = null;
      }
      else 
      {
         KeyedItem nodeItem = (KeyedItem) tNode.getItem();
         int comparison = searchKey.compareTo(nodeItem.getKey());

         if (comparison == 0) 
         {
            // item is in the root of some subtree
            treeItem = nodeItem;
         }
         else if (comparison < 0) 
         {
            // search the left subtree
            treeItem = retrieveLeft(tNode.getLeft(), searchKey);
         }
         else  
         { 
            // search the right subtree
            treeItem = retrieveRight(tNode.getRight(), searchKey);
         }  
      }  
      return treeItem;
   }

    /*
    *   Searches for leaf location to return leaf's item.
    *   Precondition: Item is unique.
    *   Postcondition: Returns current node's item.
    *   Calls: retrieveLeft, retrieveRight depending on item's key.
    */
   protected KeyedItem retrieveRight(TreeNode tNode, Comparable searchKey){

      KeyedItem treeItem;

      if (tNode == null) 
      {
         treeItem = null;
      }
      else 
      {
         KeyedItem nodeItem = (KeyedItem) tNode.getItem();
         int comparison = searchKey.compareTo(nodeItem.getKey());

         if (comparison == 0) 
         {
            // item is in the root of some subtree
            treeItem = nodeItem;
         }
         else if (comparison < 0) 
         {
            // search the left subtree
            treeItem = retrieveLeft(tNode.getLeft(), searchKey);
         }
         else  
         { 
            // search the right subtree
            treeItem = retrieveRight(tNode.getRight(), searchKey);
         }  
      }  
      return treeItem;
   }

    /*
    *   Searches for leaf  item location for leaf deletion.
    *   Precondition: Item is not null.
    *   Postcondition: Returns current node to update the tree's new state.
    *   Throws: TreeException if item is not found.
    *   Calls: deleteLeft, deleteRight depending on item's key.
    */
   protected TreeNode deleteLeft(TreeNode tNode, Comparable searchKey) throws TreeException{

      if (tNode == null) 
      {
         throw new TreeException("Item not found");
      }

      TreeNode subtree;
      KeyedItem nodeItem = (KeyedItem)tNode.getItem();
      int comparison = searchKey.compareTo(nodeItem.getKey());

      if (comparison == 0) 
      {
         // item is in the root of some subtree
         tNode = deleteNode(tNode);  // delete the item
      }
      // else search for the item
      else if (comparison < 0) 
      {
         // search the left subtree
         tNode = deleteLeft(tNode.getLeft(), searchKey);
      }
      else 
      { 
         // search the right subtree
         tNode = deleteRight(tNode.getRight(), searchKey);
      } 

      return tNode;
   }

    /*
    *   Searches for leaf  item location for leaf deletion.
    *   Precondition: Item is not null.
    *   Postcondition: Returns current node to update the tree's new state.
    *   Throws: TreeException if item is not found.
    *   Calls: deleteLeft, deleteRight depending on item's key.
    */
   protected TreeNode deleteRight(TreeNode tNode, Comparable searchKey) throws TreeException{

      if (tNode == null) 
      {
         throw new TreeException("Item not found");
      }

      TreeNode subtree;
      KeyedItem nodeItem = (KeyedItem)tNode.getItem();
      int comparison = searchKey.compareTo(nodeItem.getKey());

      if (comparison == 0) 
      {
         // item is in the root of some subtree
         tNode = deleteNode(tNode);  // delete the item
      }
      // else search for the item
      else if (comparison < 0) 
      {
         // search the left subtree
         tNode = deleteLeft(tNode.getLeft(), searchKey);
      }
      else 
      { 
         // search the right subtree
         tNode = deleteRight(tNode.getRight(), searchKey);
      } 

      return tNode;
   }

} 
