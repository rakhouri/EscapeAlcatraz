
import java.util.HashMap;

public class GameMap {
    HashMap<String, Room> map;

    public GameMap() {
        map = new HashMap<>();
    }

    /**
     * Adds a room to the room HashMap
     * @param room- The room object
     */
    public void addRoom(Room room) {

        map.put(room.getName().toLowerCase(), room) 

    }

    /**
     * Returns the Room object based on the name that is given
     * @return- The Room object associated with the name acquired from the hashmap.
     */
    public Room getRoom(String roomName) {
        
        return map.get(roomName.toLowerCase());

    }


}