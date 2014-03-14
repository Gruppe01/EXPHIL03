package persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SpecialQueries extends MySQLConnection {
    public SpecialQueries(){
        super();
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Integer> getAvailableRooms(String startTime, String endTime, int capacity){
        String query = "SELECT * FROM MeetingRoom WHERE roomNumber NOT IN (SELECT room FROM Meeting WHERE NOT (endTime <= ? OR startTime >= ?)) AND capacity >= ?";

        ArrayList<HashMap<String, String>> roomsHashMap = execute(query, new ArrayList(Arrays.asList(startTime, endTime, Integer.toString(capacity))), true);
        ArrayList<Integer> rooms = new ArrayList<>();

        for(HashMap<String, String> room : roomsHashMap){
            rooms.add(Integer.parseInt(room.get("roomNumber")));
        }

        return rooms;
    }
}
