// Will Westrich and Seth Hunter

import java.util.Iterator;

class TableSTBased implements TableInterface{

    private int size;
    private SearchTreeInterface tree;

    public TableSTBased(){
    
        tree = new AdaptableBinarySearchTree();
        size = 0;
    }

    public boolean tableIsEmpty(){
        
        if(size == 0)
            return true;
        else
            return false;
    }

    public int tableSize(){
        
        return size;
    }

    public void tableInsert(KeyedItem item) throws TableException{
        
        try{
            tree.insert(item);
            ++size;
        }catch(TreeException e){
            throw new TableException("Can't insert duplicates.");
        }
    }

    public boolean tableDelete(Comparable searchKey){
        
        try{
            tree.delete(searchKey);
            --size;
            return true;
        }catch(TreeException e){
            return false;
        }
    }

    public KeyedItem tableRetrieve(Comparable searchKey){

        return tree.retrieve(searchKey);
    }

    public Iterator iterator(){

        BinaryTreeIterator it = new BinaryTreeIterator(tree.getRootNode());
        it.setLevelorder();
        return it;
    }

}
