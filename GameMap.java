
import java.util.HashMap;

public class GameMap {
    HashMap<String, Room> map;

    public GameMap() {
        map = new HashMap<>();
    }

    public void addRoom(Room room) {

        map.put(room.getName().toLowerCase(), room) 
        
    }


}