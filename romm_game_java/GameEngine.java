import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
/**
 *  This class is the main class of the "Haunted House" application. 
 *  "Haunted House" is a very simple, text based adventure game.  
 *  This class creates parser, "items" with weight and some 
 *  It also evaluates and executes the commands that 
 *  the parser returns.
 * @author   Lingkai Huang (21318909)
 * @version  8/1/2014
 */
public class GameEngine
{
    private Parser parser;
    private Room currentRoom;
    private UserInterface gui;
    private HashMap<String, Integer> allItem; // store the items with weight a player picks
    private int totalWeight;// calculate the weight a player carry instantly
    private int maxWeight;//  max weight a player can carry
    private int maxNumItem;//  max amount of item a player can carry
    private ArrayList<Room> path;//store the path a player pass
    private Paint paint;
    private ArrayList<String> itemList;
    private ArrayList<Integer> weightList;
    private ArrayList<String> pickedItemInfo;//store picked items for GUI
    private HashMap<String, Integer> pickedItemIndex;//store index of picked items for GUI
    private int pickedIndex;
    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine(Room currentRoom)
    {
        this.currentRoom=currentRoom;
        parser = new Parser();
        allItem = new HashMap<String, Integer>(); 
        maxWeight = 50; //initialise max weight a player can carry
        totalWeight = 0; // initial value of the weight
        maxNumItem = 50;// initial value of the amount
        currentRoom = null;// initial current location
        path = new ArrayList<Room>();//store the path a player pass
        paint = new Paint();
        itemList = new ArrayList<String>(allItem.keySet()); 
            //stores the name of items 
        weightList = new ArrayList<Integer>(allItem.values());
            //stores the weight of items
        pickedItemInfo  = new ArrayList<String>();  
        pickedItemIndex = new HashMap<String, Integer>();
        pickedIndex = 0;
    }
    
    /**
     * @param userInterface 
     */
    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        printWelcome();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        gui.print("\n");
        gui.println("Welcome !!!!");
        gui.println("This is a new, incredibly boring adventure game.");
        gui.println("Click 'play' button in menu to start!!");
        gui.println("Type or enter 'help' if you need help.");
        gui.print("\n");
        gui.println(currentRoom.getLongDescription());
        gui.printItem(currentRoom.getRoomItem());
        
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param commandLine The command to be processed
     */
    public void interpretCommand(String commandLine) 
    {
        gui.println(commandLine);
        Command command = parser.getCommand(commandLine);
        if(command.isUnknown()) {
            gui.println("I don't know what you mean...");
            return;
        }
        
        String commandWord = command.getCommandWord();
        switch (commandWord) {
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "quit":
                endGame();
                break;
            case "use":
                useItem(command);
                break;
            case "pick":
                pickItem(command);
                break;
            case "drop":
                dropItem(command);
                break;
            case "item":    
                printItems();
                break;
            case "look":    
                look();
                break;
            case "return":    
                returnBack();
                break;
        } return; 
    }
    
    /**
     * Print out some help information.
     */
    private void printHelp() 
    {
        gui.println("You are lost. You are alone. You wander");
        gui.println("around in a house.\n");
        gui.print("Your command actions are: \n" + parser.showCommands()+ "\n");
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @param command
     */
    private void goRoom(Command command) 
    {
        path.add(currentRoom);
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null)
            gui.println("There is no door!.\n");
        else {
            currentRoom = nextRoom;
            gui.println(currentRoom.getLongDescription());
        }
        gui.printItem("Items in this room:");
        gui.printItem(currentRoom.getRoomItem());
    }

    /** 
     * finish game
     */
    private void endGame()
    {
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
    }
    
    
    /**
     * This function is based on the created rooms.
     * Go to some specific rooms with given items to achieve specific functions.
     * @param command The command to be processed
     */
    public void useItem(Command command) 
    {
        
        if(!command.hasSecondWord()) 
        {
            // if there is no second word, we don't know where to go...
            gui.println("use what?");
        }
        else
        {
            String item = command.getSecondWord();
            if(allItem.containsKey(item))
            {
                switch (item)
                {
                    case "hat":
                        paint.changeManColor();//call from "Paint"
                        gui.println("You have changed your dress!!");
                        break;
                    case "weapon":
                        if(currentRoom.checkItemInRoom("monster"))
                        {
                            if(paint.isAlive()== 0) //call from "Paint" 
                            {
                                gui.ableButton(false);
                                gui.println("You are killed! Game over");
                            }
                            else
                            {
                                setItemEmpty("monster");
                                if(paint.isAlive()== 1)
                                {
                                    gui.println("You killed it, but you are hurt!");
                                }
                                else
                                {
                                    gui.println("You killed it!");
                                }
                                gui.printItem(currentRoom.getRoomItem());
                            }
                        }
                        else
                        {
                            gui.println("You can't use weapon here!!");
                        }
                        break;
                    case "key":
                        if(currentRoom.getDescription() == "in the hall")
                        {
                            gui.println("The door is open, you are free now!!");
                            paint.winInfo(); //call from "Paint"
                            gui.ableButton(false);
                        }
                        else
                        {
                            gui.println("You can't use key here!!");
                        }
                        break;
                    case "food":
                        paint.drawEnergyBar(); //call from "Paint"
                        gui.println("Ate the food, you are energetic now!!");
                        allItem.remove("food");
                        int a = pickedItemIndex.get("food");
                        pickedItemInfo.remove(a);
                        gui.printBag(pickedItemInfo+"\n");
                        break;
                    default:
                        gui.println("You can't use this item!!!");
                        break;
                }
            }
            else
            {
                gui.println("You don't have that item!!!");
                gui.println(" ");
            }
        }
    }
    
    /**
     * achieve the function of drop item
     * @param command The command to be processed
     */
    public void dropItem(Command command) 
    {
        if(!command.hasSecondWord()) 
        {
            // if there is no second word, we don't know what to drop...
            gui.println("Drop what?");
            gui.println("Type 'all' to drop all items");
            gui.println(" ");
            return;
        }
        String aItem = command.getSecondWord();
        if(command.getSecondWord().equals("all"))
        {
            dropAll();
        }
        else if (allItem.size()>0)
        {
            if(allItem.containsKey(aItem) == false)
            {
                gui.println("You don't have that item.");
                gui.println(" ");
            }
            else
            {
                int w = allItem.get(aItem);
                currentRoom.creatItem(aItem, currentRoom, w);
                getDropWeight(aItem);
                allItem.remove(aItem);
                gui.println("You have drop :" + aItem);
                printWeightInfo();
                int a = pickedItemIndex.get(aItem);
                pickedIndex--;
                pickedItemInfo.remove(a);
                gui.println(" ");
            }
        }
        else 
        {
            gui.println("You have no item now.");
            gui.println(" ");
        }
        gui.printItem(currentRoom.getRoomItem());
        gui.printBag(pickedItemInfo+"\n"); //print items info in different logs in GUI
    }
     
    /**
     * achieve drop all items at once
     */
    public void dropAll()
    {
        ArrayList<String>itemList = new ArrayList<String>(allItem.keySet()); 
            //stores the name of items instantly
        ArrayList<Integer> weightList = new ArrayList<Integer>(allItem.values());
            //stores the weight of items instantly
        for(int i=0; i < allItem.size(); i++)
            {
                currentRoom.creatItem(itemList.get(i), currentRoom, weightList.get(i));
                //print out items with weight
            }
        allItem.clear();// remove all items of the list
        gui.println("You have no item now.");
        totalWeight = 0;
        gui.println(" ");
    }
    
    /** 
     * achieve the function of pick items
     * @param command The command to be processed
    */
    public void pickItem(Command command) 
    {
        if(!command.hasSecondWord()) 
        {
            // if there is no second word, we don't know what to pick...
            gui.println("Pick what?");
            gui.println(" ");
            return;
        }
        String aItem = command.getSecondWord();
        Room theRoom = currentRoom.returnRoom(aItem); // return the room of the item
        if(theRoom == currentRoom && currentRoom.returnRoom(aItem) != null)
        {
            if(currentRoom.getWeightRoom(aItem)<= maxWeight) 
            {//check the weight if it is under maximum
                if(calculateAddingWeight(aItem))  
                {// check if total weight after picking this item is over max weight
                    if (allItem.size()>maxNumItem) 
                    {//check the number of items if it is under maximum
                        gui.println("Reach maximun number, can't pick it.");
                        gui.println(" ");
                    }
                    else
                    {
                        allItem.put(aItem, currentRoom.getWeightRoom(aItem));
                        pickedItemInfo.add(aItem+": "+currentRoom.getWeightRoom(aItem)+"\n");
                        gui.println("You have picked :" + aItem);
                        setItemEmpty(aItem); //after pick it, clean the item in the room now.
                        gui.println(" ");
                        pickedItemIndex.put(aItem, pickedIndex);
                        pickedIndex++;
                    }
                }  
            }
            else
            {   // if the item is too heavy...
                gui.println("Weight of it: " + currentRoom.getWeightRoom(aItem));
                gui.println("Too heavy. You can't pick it.");
                gui.println("");
            }
            printWeightInfo();
        }
        else
        {   // if the item player want to pick does not exist.
            gui.println("You can't pick that!"); 
            gui.println(" ");
        }
        gui.printItem(currentRoom.getRoomItem()); 
        gui.printBag(pickedItemInfo+"\n");  //print items info in different logs in GUI
    }
    
    /**
     * print all the items the player has picked
     */
    public void printItems()
    {
        if(allItem.size()==0)
        {
            gui.println("You have no item now.");
            gui.println(" ");
        }
        else
        {
            gui.println("Your item(s): ");
            for(int i=0; i < allItem.size(); i++)
            {
                gui.println(itemList.get(i) + ": " + weightList.get(i)); 
                //print out items with weight
            }
            gui.println("");
        }
    }
    
    /**
     * @param item The picked item to be cleaned 
     */
    public void setItemEmpty(String item)
    {
        currentRoom.removeRoom(item); 
    }
   
    /** 
     * Try to return back in the previous room. 
     * If the room is where player begined, print an error message.
     */
    public void returnBack()
    {
        if(path.size()>0)
        {
            currentRoom = path.get(path.size()-1);
            path.remove(path.size()-1);
            printLocationInfo();
            
        }
        else
        {
            gui.println("");
            printLocationInfo();
            gui.println("You can't return back any more!");
            gui.println(" ");
        }
        
    }
    
    /**
     * look action command
     */
    public void look()
    {
        printLocationInfo();
    }
 
    /**
     * print location information command
     */
    public void printLocationInfo()
    {
        gui.println(currentRoom.getLongDescription());
    }
     
    /**
     * @param item
     * @return true If total weight after picking this item is not over max weight
     * check the total weight after picking the "item"
     * if it is over max weight, player can't pick it
     */
    public boolean calculateAddingWeight(String item)
    {
        if (totalWeight + currentRoom.getWeightRoom(item)<= maxWeight)
        {
            totalWeight = totalWeight + currentRoom.getWeightRoom(item);
            return true;
        }
        else
        {
            gui.println("Reach maximum weight, can't pick it.");
            gui.println(" ");
            return false;
        }
         
    }
    
    /**
     * @param item
     * calculate the total weight after drop the item
     */
    public void getDropWeight(String item)
    {
        totalWeight = totalWeight - allItem.get(item);
    }
    
    /**
     * @param item
     * calculate the total weight after drop the item
     */
    public void printWeightInfo()
    {
        gui.println("maximum weight: " + maxWeight);  
        gui.println("Total weight of items now is: " + totalWeight + ".\n");
    }
}
