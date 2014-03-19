package model;

import java.text.SimpleDateFormat;
import java.util.*;
import java.time.*;

import persistence.mysql.MySQLQuery;

public class Meeting {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final int meetingID;
	private ArrayList<User> admins;
	private HashMap<User, Boolean> members;
    private ArrayList<String> externalMembers;
	private LocalDateTime starttime;
	private LocalDateTime endtime;
	private String description;
    private int minCapacity;
    private String place;
    private Room room;

	public Meeting(User creator, String starttime, String endtime, String description, int capacity, Room room){
		admins = new ArrayList<>();
		members = new HashMap<>();
		setStarttime(starttime);
		setEndtime(endtime);
        setDescription(description);
        setMinCapacity(capacity);
        setRoom(room);
		meetingID = new MySQLQuery().getNextID("Meeting");

        addAdmin(creator);
        members.put(creator, true);
	}
	
	public Meeting(User creator, String starttime, String endtime, String description, String place){
		admins = new ArrayList<>();
		members = new HashMap<>();
		setStarttime(starttime);
		setEndtime(endtime);
        setDescription(description);
        setPlace(place);
		meetingID = new MySQLQuery().getNextID("Meeting");

        addAdmin(creator);
        members.put(creator, true);
	}
	
	public String getPlace(){
		return place;
	}
	
	public void setPlace(String place){
		this.place = place;
	}
	
	public ArrayList<User> getAdmins() {
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
	
	public String getDuration() {
        long minutes;
        long hours;

        try {
            Date startTime = DATE_FORMAT.parse(starttime.toString());
            Date endTime = DATE_FORMAT.parse(endtime.toString());

            long diff = endTime.getTime() - startTime.getTime();

            minutes = diff / (60 * 1000) % 60;
            hours = diff / (60 * 60 * 1000) % 24;
        }catch (Exception e){
            throw new IllegalArgumentException("Error parsing dates.");
        }

        String hourString = (hours > 0 ? hours + (hours > 1 ? " hours" : " hour") : "");
        String minuteString = (minutes > 0 ? minutes + (minutes > 1 ? " minutes" : " minute") : "");

        return (hourString.equals("") ? minuteString : hourString + (minuteString.equals("") ? "" : ", " + minuteString));
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
        if(capacity < 0){
            throw new IllegalArgumentException("Capacity cannot be less than zero.");
        }else{
            this.minCapacity = capacity;
        }
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) throws IllegalArgumentException {
        if(new MySQLQuery().getAvailableRooms(starttime.toString(), endtime.toString(), minCapacity).contains(room.getRoomNumber())){
            this.room = room;
        }else{
            throw new IllegalArgumentException("Selected room not available.");
        }
    }
	
	public int getMeetingID() {
		return meetingID;
	}

	@Override
	public String toString() {
		return "Meeting [members=" + members + ", starttime=" + starttime
				+  ", endtime=" + endtime + ", duration=" + getDuration()
				+ ", description=" + description + "]";
	}

    public static void main(String args[]){
        Meeting meeting = new Meeting(new User("asdasd", "asdasd", "asdasd", "si@df.com", "12345678"), "2014-03-13 11:00:00", "2014-03-13 13:01:00", "Kjempegøy!", 5, new Room(10));

        System.out.println(meeting);
    }
}
