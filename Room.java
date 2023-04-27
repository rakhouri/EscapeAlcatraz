
import java.util.ArrayList;

public class Room {

    private String name;
    private String description;
    ArrayList<String> exits = new ArrayList<String>();

    public Room(String name, String description) {
        this.name = name;
        this.description = description
    }
    
}