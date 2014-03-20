package persistence;

import model.*;
import persistence.data.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class DataStorage implements Serializable{
    private GroupMemberships groupMemberships;
    private MeetingInvites meetingInvites;
    private MeetingAdmins meetingAdmins;
    private Users users;
    private Groups groups;
    private Rooms rooms;
    private Meetings meetings;
    private ExternalUsers externalUsers;

    public DataStorage(Users users, Groups groups, Rooms rooms, Meetings meetings, ExternalUsers externalUsers, GroupMemberships groupMemberships, MeetingInvites meetingInvites, MeetingAdmins meetingAdmins) {
        this.groupMemberships = groupMemberships;
        this.meetingInvites = meetingInvites;
        this.meetingAdmins = meetingAdmins;
        this.users = users;
        this.groups = groups;
        this.rooms = rooms;
        this.meetings = meetings;
        this.externalUsers = externalUsers;
    }

    public DataStorage() {
        this.groupMemberships = new GroupMemberships();
        this.meetingInvites = new MeetingInvites();
        this.meetingAdmins = new MeetingAdmins();
        this.users = new Users();
        this.groups = new Groups();
        this.rooms = new Rooms();
        this.meetings = new Meetings();
        this.externalUsers = new ExternalUsers();
    }

    public Users users() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Groups groups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public Rooms rooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    public Meetings meetings() {
        return meetings;
    }

    public void setMeetings(Meetings meetings) {
        this.meetings = meetings;
    }

    public ExternalUsers externalUsers() {
        return externalUsers;
    }

    public void setExternalUsers(ExternalUsers externalUsers) {
        this.externalUsers = externalUsers;
    }

    public GroupMemberships groupMemberships() {
        return groupMemberships;
    }

    public void setGroupMemberships(GroupMemberships groupMemberships) {
        this.groupMemberships = groupMemberships;
    }

    public MeetingInvites meetingInvites() {
        return meetingInvites;
    }

    public void setMeetingInvites(MeetingInvites meetingInvites) {
        this.meetingInvites = meetingInvites;
    }

    public MeetingAdmins meetingAdmins() {
        return meetingAdmins;
    }

    public void setMeetingAdmins(MeetingAdmins meetingAdmins) {
        this.meetingAdmins = meetingAdmins;
    }

    //================================================================================================

    public ArrayList<Integer> getAvailableRooms(String startTime, String endTime, int capacity){
        ArrayList<Integer> availableRooms = new ArrayList<>();
        ArrayList<Meeting> meetings = meetings().getMeetings();

        for(Meeting meeting : meetings){
            int room = meeting.getRoom();

            if(LocalDateTime.parse(meeting.getEndtime()).isBefore(LocalDateTime.parse(startTime)) || LocalDateTime.parse(meeting.getStarttime()).isAfter(LocalDateTime.parse(endTime)) && !availableRooms.contains(room) && rooms().getRoomByNumber(room).getCapacity() >= capacity) availableRooms.add(room);
        }

        return availableRooms;
    }

    public ArrayList<MeetingInvite> getMeetingInvitesByUsernameAndDate(String username, LocalDate date){
        ArrayList<MeetingInvite> meetingInvited = new ArrayList<>();

        for(MeetingInvite meetingInvite : meetingInvites().getMeetingInvites()){
            if(username.equals(meetingInvite.getUsername()) && meetings().getMeetingByID(meetingInvite.getMeetingID()).getStartTimeAsLocalDateTime().toLocalDate().equals(date)) meetingInvited.add(meetingInvite);
        }

        return meetingInvited;
    }

    public ArrayList<String> getMeetingAdminsByMeetingID(int meetingID) {
        ArrayList<String> meetingAdmins = new ArrayList<>();

        for(MeetingAdmin meetingAdmin : meetingAdmins().getMeetingAdmins()){
            if(meetingAdmin.getMeetingID() == meetingID && !meetingAdmins.contains(meetingAdmin.getUsername())) meetingAdmins.add(meetingAdmin.getUsername());
        }

        return meetingAdmins;
    }

    public void addMeetingAdmin(int meetingID, String username) {
        if(meetingAdmins().getMeetingAdminByUsernameAndMeetingID(meetingID, username) != null) throw new IllegalArgumentException("The user is allready an admin");

        meetingAdmins().addMeetingAdmin(new MeetingAdmin(meetingID, username));
    }

    public void deleteAdmin(int meetingID, String username){
        if(meetingAdmins().getMeetingAdminByUsernameAndMeetingID(meetingID, username) == null) throw new IllegalArgumentException("The user is not an admin");

        meetingAdmins().removeMeetingAdminByMeetingIDAndUsername(meetingID, username);
    }

    public HashMap<String, Boolean> getMembers(int meetingID) {
        return meetingInvites().getMeetingInvitesByMeetingID(meetingID);
    }

    public void addMember(int meetingID, String username) {
        if(meetingInvites().getMeetingInvitesByMeetingID(meetingID).keySet().contains(username)) throw new IllegalArgumentException("The user is already invited");
        else meetingInvites().addMeetingInvite(new MeetingInvite(meetingID, username));
    }

    public void deleteMember(int meetingID, String username){
        if(!meetingInvites().getMeetingInvitesByMeetingID(meetingID).keySet().contains(username)) throw new IllegalArgumentException("The user is not invited");
        else meetingInvites().removeMeetingInviteByMeetingIDAndUsername(meetingID, username);
    }

    public ArrayList<String> getExternalMembers(int meetingID) {
        return externalUsers().getExternalUsersByMeetingID(meetingID);
    }

    public void addExternalMember(int meetingID, String email, String name, String phonenumber) throws IllegalArgumentException {
        if(externalUsers().getExternalUsersByMeetingID(meetingID).contains(email)) throw new IllegalArgumentException("The user is already invited");
        else externalUsers().addExternalUser(new ExternalUser(email, meetingID, name, phonenumber));
    }

    public void deleteExternalMember(int meetingID, String email) throws IllegalArgumentException {
        if(!externalUsers().getExternalUsersByMeetingID(meetingID).contains(email)) throw new IllegalArgumentException("The user is not invited");
        else externalUsers().removeExternalUserByMeetingIDAndEmail(meetingID, email);
    }
}
