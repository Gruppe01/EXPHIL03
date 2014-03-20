package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.*;

import persistence.mysql.MySQLQuery;

public class Meeting implements Serializable {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final int meetingID;
	private LocalDateTime starttime;
	private LocalDateTime endtime;
	private String description;
    private String place = null;
    private int room = -1;
    private int minCapacity = -1;
    private String creator;
    private LocalDateTime lastUpdated = null;

    public Meeting(int meetingID, String starttime, String endtime, String description, String place, int room, int minCapacity, String creator, String lastUpdated){
        this.meetingID = meetingID;
        setStarttime(starttime);
        setEndtime(endtime);
        setDescription(description);
        setPlace(place);
        this.room = room;
        setMinCapacity(minCapacity);
        this.creator = creator;
        setLastUpdated(lastUpdated);
    }

	public Meeting(String starttime, String endtime, String description, int room, int minCapacity, String creator){
        this.meetingID = new MySQLQuery().getNextID("Meeting");
		setStarttime(starttime);
		setEndtime(endtime);
        setDescription(description);
        setRoom(room);
        setMinCapacity(minCapacity);
        this.creator = creator;
	}
	
	public Meeting(String starttime, String endtime, String description, String place, String creator){
        meetingID = new MySQLQuery().getNextID("Meeting");
		setStarttime(starttime);
		setEndtime(endtime);
        setDescription(description);
        setPlace(place);
        this.creator = creator;
	}
	
	public String getPlace(){
		return place;
	}
	
	public void setPlace(String place){
		this.place = place;
	}
	
	public String getStarttime() {
		return starttime.toString();
	}

	public void setStarttime(String starttime) throws IllegalArgumentException {
        try {
			this.starttime = LocalDateTime.parse(starttime);
		}catch (DateTimeException e){
			throw new IllegalArgumentException("Invalid starttime format.");
		}
	}
	
	public String getEndtime() {
		return endtime.toString();
	}

	public void setEndtime(String endtime) throws IllegalArgumentException {
        try {
			this.endtime = LocalDateTime.parse(endtime);
        }catch (DateTimeException e){
			throw new IllegalArgumentException("Invalid endtime format.");
		}
	}
	
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

    public int getMinCapacity() {
        return minCapacity;
    }

    public void setMinCapacity(int capacity) throws IllegalArgumentException{
        if(capacity < 0 && capacity != -1){
            throw new IllegalArgumentException("Capacity cannot be less than zero.");
        }else{
            this.minCapacity = capacity;
        }
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) throws IllegalArgumentException {
        this.room = room;
    }
	
	public int getMeetingID() {
		return meetingID;
	}

    public String getCreator(){
        return creator;
    }

    public String getLastUpdated() {
        return lastUpdated.toString();
    }

    public void setLastUpdated(String lastUpdated) throws IllegalArgumentException {
        try {
            this.lastUpdated = LocalDateTime.parse(lastUpdated);
        }catch (DateTimeException e){
            throw new IllegalArgumentException("Invalid lastUpdated format.");
        }
    }

	@Override
	public String toString() {
		return "Members: " + /*getMembers().length +*/ "\r\nStarttime: " + starttime
				+  "\r\nEndtime: " + endtime
				+ "\r\nPlace:  " + place;
	}
}
