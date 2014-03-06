package model;

import java.util.ArrayList;

public class Meeting {
	private final int meetingID;
	private ArrayList<User> admins;
	private ArrayList<User> members;
	private String starttime;
	private String endtime;
	private int duration;
	private String description;
	
	
	public Meeting(User creator, String starttime, String endtime, String description){
		admins = new ArrayList<User>();
		members = new ArrayList<User>();
		admins.add(creator);
		members.add(creator);
		this.starttime = starttime;
		this.endtime = endtime;
		this.duration = Integer.parseInt(endtime) - Integer.parseInt(starttime);
		meetingID = 0;
	}
	
	public ArrayList<User> getAdmin() {
		return admins;
	}
	
	public void addAdmin(User admin) {
		this.admins.add(admin);
	}
	
	public void deleteAdmin(User admin){
		this.admins.remove(admin);
	}
	
	public ArrayList<User> getMember() {
		return members;
	}
	
	public void addMember(User member) {
		this.members.add(member);
	}
	
	public void deleteMember(User member){
		this.members.remove(member);
	}
	
	public String getStarttime() {
		return starttime;
	}
	
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	
	public String getEndtime() {
		return endtime;
	}
	
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
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
}

