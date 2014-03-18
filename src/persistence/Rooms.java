package persistence;

import model.Room;

import java.util.ArrayList;

//MeetingRoom
public class Rooms {
    private ArrayList<Room> rooms;

    public Rooms(ArrayList<Room> rooms){
        populate(rooms);
    }

    public Rooms(){
        this.rooms = new ArrayList<>();
    }

    public ArrayList<Room> getRooms(){
        return rooms;
    }

    public Room getRoomByID(int roomID){
        for(Room room : rooms){
            if(roomID == room.getRoomNumber()) return room;
        }

        return null;
    }

    public int getRoomIndex(int roomID){
        for(Room room : rooms){
            if(roomID == room.getRoomNumber()) return rooms.indexOf(room);
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
        int i = getRoomIndex(room.getRoomNumber());

        rooms.remove(i);
        rooms.add(i, room);
    }

    public void populate(ArrayList<Room> rooms){
        for(Room room : rooms){
            rooms.add(room);
        }
    }
}
