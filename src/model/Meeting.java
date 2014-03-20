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

    public Meeting(int meetingID, String starttime, String endtime, String description, String place, int room, int minCapacity, String creator, LocalDateTime lastUpdated){
        this.meetingID = meetingID;
        setStarttime(starttime);
        setEndtime(endtime);
        setDescription(description);
        setPlace(place);
        this.room = room;
        setMinCapacity(minCapacity);
        this.creator = creator;
        this.lastUpdated = lastUpdated;
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

	/*public ArrayList<User> getAdmins() {
		return admins;
	}


	public void addAdmin(User admin) {
		if (admins.contains(admin)) throw new IllegalArgumentException("The user is allready an admin");

		this.admins.add(admin);
	}

	public void deleteAdmin(User admin){
		if (admins.contains(admin)){
			this.admins.remove(admin);
		}
		else{
			throw new IllegalArgumentException("The user is not an current admin");
		}
	}

	
	public HashMap<User, Boolean> getMembers() {
		return members;
	}
	

	public void addMember(User member) {
        if(members.containsKey(member)){
            throw new IllegalArgumentException("The user is allready invited");
        }else{
            this.members.put(member, null);
        }
	}
	
	public void deleteMember(User member){
		if (members.keySet().contains(member)){
			this.members.remove(member);
		}else{
			throw new IllegalArgumentException("The user is not a current member");
		}
	}

    public ArrayList<String> getExternalMembers() {
        return externalMembers;
    }

    public void addExternalMember(String member) throws IllegalArgumentException {
        if (externalMembers.contains(member)){
            throw new IllegalArgumentException("The email is allready invited");
        }else{
            this.externalMembers.add(member);
        }
    }

    public void deleteExternalMember(String member) throws IllegalArgumentException {
        if (!externalMembers.contains(member)){
            throw new IllegalArgumentException("The email is not a current member");
        }else{
            this.externalMembers.remove(member);
        }
    }*/
	
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
        if(new MySQLQuery().getAvailableRooms(starttime.toString(), endtime.toString(), minCapacity).contains(room)){
            this.room = room;
        }else{
            throw new IllegalArgumentException("Selected room not available.");
        }
    }
	
	public int getMeetingID() {
		return meetingID;
	}

    public String getCreator(){
        return creator;
    }

	@Override
	public String toString() {
		return "Members: " + /*getMembers().length +*/ "\r\nstarttime: " + starttime
				+  "\r\nendtime: " + endtime
				+ "\r\nPlace:  " + place;
	}
}
