/*
Richard Khouri
CPSC 1060: RPG
GitHub Repository: https://github.com/rakhouri/EscapeAlcatraz
5/04/2023
*/

import java.util.ArrayList;

public class Item {

    private String name;
    private String description;
    private String itemContent;

    /**
     * Constructor that creates the Item object and gives it a name
     * @param name- The name of the Item object
     */
    public Item (String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Sets the contents of the item
     * @param itemContent- A string that holds the contents of the item if the user wants to read the item.
     */
    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    /**
     * Returns the name of the item
     * @return- The item's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the item
     * @return- The item's description
     */
    public String getDescription() {
        return this.description;
    }

    public String getContent() {
        return this.itemContent;
    }

    /**
     * Checks to see if the user has a specific item in their inventory
     * @param inventory- The item ArrayList that contains the items that the user has.
     * @return- A boolean that depends on if the user has the item or not. 
     */
    public boolean hasItem (ArrayList<Item> inventory) {
        boolean hasItem = false;

        for (Item i : inventory) {
            if (i.getName().equalsIgnoreCase(getName())) {
                hasItem = true;
                break;
            }
        }

        return hasItem;
    }

    /**
     * Creates a string representation of the item by returning it's name
     * @return- A string of the name of the item
     */
    public String toString() {

        String itemToString = this.name;

        return itemToString;
    }
}