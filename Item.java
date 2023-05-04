
import java.util.ArrayList;

public class Item {

    private ArrayList<String> actions = new ArrayList<String>();
    private String name;
    private String description;

    /**
     * Constructor that creates the Item object and gives it a name
     * @param name- The name of the Item object
     */
    public Item (String name, String description) {
        this.name = name;
        this.description = description;
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

    /**
     * Adds an action that the player can do with the item to the actions ArrayList
     * @param action- The name of the action
     */
    public void addAction (String action) {

        actions.add(action);
    }

    /**
     * Checks to see if the item can preform a certain action
     * @param action- The name of the action that wants to be preformed
     * @return- True or false depending on if the action is in the actions ArrayList
     */
    public boolean hasAction (String action) {

        boolean hasAction = false;

        for (String s : actions) {
            if (s.equalsIgnoreCase(action)) {
                hasAction = true;
                break;
            }
        }

        return hasAction;
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