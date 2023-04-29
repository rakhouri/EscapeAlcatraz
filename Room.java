
import java.util.ArrayList;

public class Room {

    private String name;
    private String description;
    ArrayList<Item> items = new ArrayList<String>();
    ArrayList<String> exits = new ArrayList<String>();

    /**
     * Room constructor that initializes the room
     * @param name- The name of the room
     * @param description- The description that describes the room
     */
    public Room(String name, String description) {
        this.name = name;
        this.description = description
    }

    /**
     * Returns the name of the room
     * @return- The name of the room
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the room
     * @return- The description of the room
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Adds an exit to the room's exit ArrayList
     * @param exit- The name of the exit
     */
    public void addExit(String exit) {
        exits.add(exit);
    }

    /**
     * List's all of the possible exits of the room
     * @return- The string of exits
     */
    public String listExits() {

        String exitString = "Exits:\n";
        for (String s : exits) {
            exitString = exitString + s + "\n";
        }

        return exitString;
    }

    /**
     * Adds an item to the room's items ArrayList
     * @param item- The item object that is being added to the ArrayList
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Creates a string representation of the room, description, and all possible exits
     * @return- A string of the name of the room, description, and list of exits
     */
    public String toString() {

        String roomToString = this.name + ": " + this.description + "\n" + "\n" + listExits();
        return roomToString;
    }


    
    

}