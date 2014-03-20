package persistence.data;

import model.Room;

import java.io.Serializable;
import java.util.*;

public class Rooms implements Serializable {
    private ArrayList<Room> rooms;

    public Rooms(ArrayList<Room> rooms){
        this.rooms = rooms;
    }

    public Rooms(){
        this.rooms = new ArrayList<>();
    }


    public ArrayList<Room> getRooms(){
        return rooms;
    }

    public Room getRoomByNumber(int roomID){
        for(Room room : rooms){
            if(roomID == room.getRoomNumber()) return room;
        }

        return null;
    }

    public int getRoomIndexByNumber(int roomNumber){
        for(Room room : rooms){
            if(roomNumber == room.getRoomNumber()) return rooms.indexOf(room);
        }

        return -1;
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public void removeRoom(Room room){
        rooms.remove(room);
    }

    public void updateRoom(Room room){
        int i = getRoomIndexByNumber(room.getRoomNumber());

        rooms.remove(i);
        rooms.add(i, room);
    }

    public int getNextRoomNumber(){
        int largestID = -1;

        for(Room room : rooms){
            if(room.getRoomNumber() > largestID) largestID = room.getRoomNumber();
        }

        return largestID + 1;
    }
}
