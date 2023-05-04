/*
Richard Khouri
CPSC 1060: RPG
GitHub Repository: https://github.com/rakhouri/EscapeAlcatraz
5/04/2023
*/

import java.util.ArrayList;

public class Room {

    private String name;
    private String description;
    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<String> exits = new ArrayList<String>();

    /**
     * Room constructor that initializes the room
     * @param name- The name of the room
     * @param description- The description that describes the room
     */
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
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
     * Returns the ArrayList of items
     * @return- The item arraylist
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Returns the arraylist index of the specified item
     * @param item- The name of the item
     * @return- The arraylist index of the item
     */
    public int getItemIndex(String item) {
        int i;

        for (i = 0; i < items.size(); i++) {
            if (item.equalsIgnoreCase(items.get(i).getName())) {
                return i;
            }
        }
        return i;
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
     * Lists the items that the room has
     * @return- A string of the items that are in the room
     */
    public String listItems() {

        String itemString = "";
        if (items.size() == 0) {
            itemString = itemString + "There are no items around here.";
            return itemString;
        } else if (items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                if (i == items.size() - 1) {
                    itemString = itemString + items.get(i) + ".";
                } else {
                    itemString = itemString + items.get(i) + ", ";
                }
            }
        }

        return itemString;
    }

    /**
     * Adds an item to the room's items ArrayList
     * @param item- The item object that is being added to the ArrayList
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Validates that the room exit that the player wants to move to
     * @param exit- The string of the exit the player wants to go to
     * @return- A boolean based on if the exit exists or not
     */
    public boolean validateRoom(String exit) {
        boolean validRoom = false;

        for (String s : exits) {
            if (exit.equalsIgnoreCase(s)) {
                validRoom = true;
                break;
            }
        }

        return validRoom;
    }

    /**
     * Validates that the player inputted an item that exists in the room
     * @param item- The string of the item the player wants to pick up
     * @return- A boolean based on if the item exists in the room or not
     */
    public boolean validateItem (String item) {
        boolean validItem = false;

        for (Item i : items) {
            if (item.equalsIgnoreCase(i.getName())) {
                validItem = true;
                break;
            }
        }

        return validItem;
    }

    /**
     * Allows the player to look around which gives them the description of the room and any items that are present in the room.
     * @return- A string that lists the descriptions and items that are in the room
     */
    public String lookAround() {
        String lookAround;

        lookAround = getDescription() + "\n" + "You find some items around you: " + listItems();
        return lookAround;
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