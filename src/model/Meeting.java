package model;

import java.util.ArrayList;

public class Meeting {
	private final int meetingID;
	private ArrayList<User> admins;
	private ArrayList<User> members;
	private String starttime;
	private String endtime;
	private String description;

	public Meeting(User creator, String starttime, String endtime, String description){
		admins = new ArrayList<User>();
		members = new ArrayList<User>();
		admins.add(creator);
		members.add(creator);
		setStarttime(starttime);
		setEndtime(endtime);
        setDescription(description);
		meetingID = 0;
	}
	
	public ArrayList<User> getAdmins() {
		return admins;
	}
	
	public void addAdmin(User admin) {
		if (admins.contains(admin)){
            throw new IllegalArgumentException("The user is already an admin");
        }else{
            this.admins.add(admin);
        }
	}
	
	public void deleteAdmin(User admin){
		if (!admins.contains(admin)){
            throw new IllegalArgumentException("The user is not an current admin");
        }else{
            this.admins.remove(admin);
        }
	}
	
	public ArrayList<User> getMembers() {
		return members;
	}
	
	public void addMember(User member) {
		if (members.contains(member)){
            throw new IllegalArgumentException("The user is allready invited");
        }else{
            this.members.add(member);
        }
	}
	
	public void deleteMember(User member){
		if (!members.contains(member)){
            throw new IllegalArgumentException("The user is not a current member");
        }else{
            this.members.remove(member);
        }
	}
	
	public String getStarttime() {
		return starttime;
	}
	
	public void setStarttime(String starttime) {
		if (starttime.matches("[0-9]+") && starttime.length() < 3 && Integer.parseInt(starttime) < 25 && Integer.parseInt(starttime) > 0) {
			this.starttime = starttime;
		}else{
			throw new IllegalArgumentException("Invalid number.");
		}
	}
	
	public String getEndtime() {
		return endtime;
	}
	
	public void setEndtime(String endtime) {
		if (endtime.matches("[0-9]+") && endtime.length() < 3 && Integer.parseInt(endtime) < 25 && Integer.parseInt(endtime) > 0) {
			this.endtime = endtime;
		}else{
			throw new IllegalArgumentException("Invalid number.");
		}
	}
	
	public int getDuration() {
		return Integer.parseInt(endtime) - Integer.parseInt(starttime);
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
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
}
