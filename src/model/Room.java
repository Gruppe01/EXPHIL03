package model;

import persistence.mysql.MySQLQuery;

public class Room {
    private int roomNumber;
    private int capacity;

    public Room(int roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }

    public Room(int capacity) {
        this.roomNumber = new MySQLQuery().getNextID("MeetingRoom");
        this.capacity = capacity;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) throws IllegalArgumentException {
        if(capacity > 0) this.capacity = capacity;
        else throw new IllegalArgumentException("Room capacity must be more than zero");
    }
}
