// Will Westrich and Seth Hunter

package ki;

public abstract class KeyedItem
{
	Comparable key;    //create single instance variable of type Comparable
	
  
    public KeyedItem(Comparable key) 
    {
        this.key = key;
    }  

    public Comparable getKey() 
    {
        return key;
    }  

	//Use Comparable toString() method
    public String toString()
    {
	   return key.toString();
    }
}
