package model;

import persistence.MySQLQuery;

import java.text.SimpleDateFormat;
import java.util.*;

public class Meeting {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final int meetingID;
	private ArrayList<User> admins;
	private ArrayList<User> members;
    private ArrayList<String> externalMembers;
	private String starttime;
	private String endtime;
	private String description;
    private int minCapacity;
    private Room room;

	public Meeting(User creator, String starttime, String endtime, String description, int capacity, Room room){
		admins = new ArrayList<>(); admins.add(creator);
		members = new ArrayList<>(); members.add(creator);
		setStarttime(starttime);
		setEndtime(endtime);
        setDescription(description);
        setMinCapacity(capacity);
        setRoom(room);
		meetingID = 0;

        System.out.println(DATE_FORMAT.format(new Date()));
	}
	
	public ArrayList<User> getAdmins() {
		return admins;
	}
	
	public void addAdmin(User admin) throws IllegalArgumentException {
		if (admins.contains(admin)){
            throw new IllegalArgumentException("The user is already an admin");
        }else{
            this.admins.add(admin);
        }
	}
	
	public void deleteAdmin(User admin) throws IllegalArgumentException {
		if (!admins.contains(admin)){
            throw new IllegalArgumentException("The user is not an current admin");
        }else{
            this.admins.remove(admin);
        }
	}
	
	public ArrayList<User> getMembers() {
		return members;
	}
	
	public void addMember(User member) throws IllegalArgumentException {
		if (members.contains(member)){
            throw new IllegalArgumentException("The user is allready invited");
        }else{
            this.members.add(member);
        }
	}
	
	public void deleteMember(User member) throws IllegalArgumentException {
		if (!members.contains(member)){
            throw new IllegalArgumentException("The user is not a current member");
        }else{
            this.members.remove(member);
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
		return starttime;
	}

	public void setStarttime(String starttime) throws IllegalArgumentException {
		if (true) {
			this.starttime = starttime;
		}else{
			throw new IllegalArgumentException("Invalid starttime format.");
		}
	}
	
	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) throws IllegalArgumentException {
		if (true) {
			this.endtime = endtime;
		}else{
			throw new IllegalArgumentException("Invalid endtime format.");
		}
	}
	
	public String getDuration() {
        long minutes;
        long hours;

        try {
            Date startTime = DATE_FORMAT.parse(starttime);
            Date endTime = DATE_FORMAT.parse(endtime);

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
        if(new MySQLQuery().getAvailableRooms(starttime, endtime, minCapacity).contains(room.getRoomNumber())){
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
				+ ", endtime=" + endtime + ", duration=" + getDuration()
				+ ", description=" + description + "]";
	}

    public static void main(String args[]){
        Meeting meeting = new Meeting(new User("asdasd", "asdasd", "asdasd", "si@df.com", "12345678"), "2014-03-13 11:00:00", "2014-03-13 13:01:00", "Kjempegøy!", 5, new Room(new MySQLQuery().getNextID("MeetingRoom"), 10));

        System.out.println(meeting);
    }
}
