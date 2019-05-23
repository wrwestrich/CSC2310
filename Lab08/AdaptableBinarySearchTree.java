// Will Westrich and Seth Hunter

class AdaptableBinarySearchTree extends BinarySearchTree{

    public AdaptableBinarySearchTree(){
        super();
    }

    public KeyedItem retrieve(Comparable searchKey){
        try{
            return (KeyedItem)retrieveItemAdapt(getRootNode(), searchKey);
        }catch(TreeException e){
            return null;
        }
    }

    private TreeNode retrieveItemAdapt(TreeNode tNode, Comparable searchKey) throws TreeException{

        KeyedItem treeItem;

        if (tNode == null) 
        {
            throw new TreeException("Item not found.");
        }
        else 
        {
            KeyedItem nodeItem = (KeyedItem) tNode.getItem();
            int comparison = searchKey.compareTo(nodeItem.getKey());
            getRootNode() = tNode;

            if (comparison == 0) 
            {
                // item is in the root of some subtree
                treeItem = nodeItem;
            }
            else if (comparison < 0) 
            {
                // search the left subtree
                treeItem = retrieveItem(tNode.getLeft(), searchKey);
                rotateRight(tNode);
            }
            else  
            { 
                // search the right subtree
                treeItem = retrieveItem(tNode.getRight(), searchKey);
                rotateLeft(tNode);
            }  
        }  
        return tNode;
    }
}
