import java.util.HashMap;
import java.util.ArrayList;
/**
 * This class is part of the "Haunted House" application. 
 * "Haunted House" is a very simple, text based adventure game.  
 * 
 * item class is for defining the items in rooms,
 * so that we can pick and drop
 * 
 * @author   Lingkai Huang (21318909)
 * @version  12/11/2013
 */
public class Item
{
    // instance variables 
    private HashMap<String, Integer> theItem; //"Integer" is for weight.
    /**
     * @param itemDescription Description for a item
     */
    public Item()
    {
        // initialise instance variables
        theItem = new HashMap<String, Integer>(); 
    }

    /**
     *  @param namge
     *  @param weight 
     *  enter in hashmap of theItem
     */
    public void creatItem(String name, int weight)
    {
        theItem.put(name, weight);
    }
    
    /**
     *  @return the weight of item
     */
    public int getWeight(String name)
    {
        return theItem.get(name);
    }
    
    /**
     *  print out the name of all items in this arraylist
     */
    public String itemName()
    {
        if (theItem.size()==0)
        {
            return "There is no item here!!";
        }
        ArrayList<String>itemList = new ArrayList<String>(theItem.keySet()); 
        ArrayList<String>storeItem = new ArrayList<String>();
        
        for(int i=0; i < theItem.size(); i++)
            {
                int a = i +1;
                String b = itemList.get(i);
                storeItem.add("Item " + a + ": " + b + " " + theItem.get(b)+"\n"); 
                
                //print out namge of items
            }
            return "" + storeItem ;
    }
    
    /**
     *  @param item Remove this item
     */
    public void removeItem(String item)
    {
        theItem.remove(item);
    }
}
