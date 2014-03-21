package persistence;

import com.sun.swing.internal.plaf.synth.resources.synth_sv;
import gui.Frame;
import model.*;
import persistence.data.*;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

        for(Room room : rooms().getRooms()){
            if(room.getRoomNumber() == -1) continue;
            if(room.getCapacity() < capacity) continue;

            availableRooms.add(room.getRoomNumber());
        }

        for(Meeting meeting : meetings){
            int room = meeting.getRoom();

            if(room == -1) continue;
            if(!availableRooms.contains(room)) continue;
            if(!(LocalDateTime.parse(meeting.getEndtime()).isBefore(LocalDateTime.parse(startTime)) && LocalDateTime.parse(meeting.getStarttime()).isAfter(LocalDateTime.parse(endTime)))) continue;

            availableRooms.remove(room);
        }

        return availableRooms;
    }

    public ArrayList<MeetingInvite> getMeetingInvitesByUsernameAndDate(String username, LocalDate date){
        ArrayList<MeetingInvite> meetingInvited = new ArrayList<>();
        ArrayList<MeetingInvite> meetingInvites = meetingInvites().getMeetingInvitesByUsername(username);

        for(MeetingInvite meetingInvite : meetingInvites){
            if(!meetings().getMeetingByID(meetingInvite.getMeetingID()).getStartTimeAsLocalDateTime().toLocalDate().equals(date)) continue;

            meetingInvited.add(meetingInvite);
        }

        return meetingInvited;
    }

    public void updateMeetingByMeeting(Meeting meeting){
        Frame.getClient().sendChanges(meeting, "update");
    }

    public void updateMeetingByMeetingID(int meetingID, String starttime, String endtime, String description, String place, int room, int minCapacity, String lastUpdated){
        Meeting meeting = meetings().getMeetingByID(meetingID);

        meeting.setStarttime(starttime);
        meeting.setEndtime(endtime);
        meeting.setDescription(description);
        meeting.setPlace(place);
        meeting.setRoom(room);
        meeting.setMinCapacity(minCapacity);
        meeting.setLastUpdated(lastUpdated);

        Frame.getClient().sendChanges(meeting, "update");
    }

    public void updateMeetingInviteByUsernameAndMeetingID(int meetingID, String username, boolean coming, String alarm){
        MeetingInvite meetingInvite = meetingInvites().getMeetingInviteByUsernameAndMeetingID(meetingID, username);

        if(meetingInvite == null) throw new IllegalArgumentException("Error updating meeting info");
        else{
            meetingInvite.setComing(coming);
            meetingInvite.setAlarm(alarm);
            meetingInvite.setLastSeenAsLocalDateTime(LocalDateTime.now());

            Frame.getClient().sendChanges(meetingInvite, "update");
        }
    }

    public ArrayList<MeetingInvite> getActiveMeetingInvitesByUsernameAndWeek(String username, String stringDate){
        ArrayList<MeetingInvite> meetingInvitesReturn = new ArrayList<>();
        LocalDate date = LocalDate.parse(stringDate);

        switch(date.getDayOfWeek()){
           case TUESDAY:
                date = date.minusDays(1);
                break;
            case WEDNESDAY:
                date = date.minusDays(2);
                break;
            case THURSDAY:
                date = date.minusDays(3);
                break;
            case FRIDAY:
                date = date.minusDays(4);
                break;
            case SATURDAY:
                date = date.minusDays(5);
                break;
            case SUNDAY:
                date = date.minusDays(6);
                break;
            default:
                break;
        }

        while(true){
            for(MeetingInvite meetingInvite : meetingInvites().getMeetingInvitesByUsername(username)){
                if(meetings().getMeetingByID(meetingInvite.getMeetingID()).getStartTimeAsLocalDateTime().toLocalDate().isEqual(date)) meetingInvitesReturn.add(meetingInvite);
            }

            if(date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) break;
            date = date.plusDays(1);
        }

        return meetingInvitesReturn;
    }

    public ArrayList<Meeting> getMeetingNotificationsByUsername(String username){
        ArrayList<Meeting> meetings = new ArrayList<>();
        ArrayList<MeetingInvite> meetingInvites = meetingInvites().getMeetingInvitesByUsername(username);

        for(MeetingInvite meetingInvite : meetingInvites){
            Meeting meeting = meetings().getMeetingByID(meetingInvite.getMeetingID());

            LocalDateTime lastUpdated = meeting.getLastUpdatedAsLocalDateTime();
            LocalDateTime lastSeen = meetingInvite.getLastSeenAsLocalDateTime();

            if(lastUpdated == null) continue;
            if(lastSeen == null){
                meetings.add(meeting);
                continue;
            }

            if(meeting.getLastUpdatedAsLocalDateTime().isAfter(meetingInvite.getLastSeenAsLocalDateTime())) meetings.add(meeting);
        }

        return meetings;
    }

    public ArrayList<String> getUsersInGroup(int groupID){
        ArrayList<GroupMembership> allGroupMemberships = groupMemberships().getGroupMemberships();
        ArrayList<String> usersInGroup = new ArrayList<>();

        if(groupID == -1){
            usersInGroup = new ArrayList<>(users().getAllUsers());

            for(GroupMembership groupMembership : allGroupMemberships){
                usersInGroup.remove(groupMembership.getUsername());
            }
        }else{
            for(GroupMembership groupMembership : groupMemberships().getGroupMembershipsByGroupID(groupID)){
                if(groupMembership.getGroupID() == groupID) usersInGroup.add(groupMembership.getUsername());
            }
        }

        return usersInGroup;
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
        else{
            MeetingAdmin meetingAdmin = new MeetingAdmin(meetingID, username);

            Frame.getClient().sendChanges(meetingAdmin, "insert");
        }
    }

    public void deleteMeetingAdmin(int meetingID, String username){
        if(meetingAdmins().getMeetingAdminByUsernameAndMeetingID(meetingID, username) == null) throw new IllegalArgumentException("The user is not an admin");
        else{
            MeetingAdmin meetingAdmin = meetingAdmins().getMeetingAdminByUsernameAndMeetingID(meetingID, username);

            Frame.getClient().sendChanges(meetingAdmin, "delete");
        }
    }

    public ArrayList<MeetingInvite> getMeetingMembers(int meetingID) {
        return meetingInvites().getMeetingInvitesByMeetingID(meetingID);
    }

    public void addMeetingMember(int meetingID, String username) {
        if(meetingInvites().getMeetingInviteByUsernameAndMeetingID(meetingID, username) != null) throw new IllegalArgumentException("The user is already invited");
        else{
            MeetingInvite meetingInvite = new MeetingInvite(meetingID, username);

            Frame.getClient().sendChanges(meetingInvite, "insert");
        }
    }

    public void deleteMeetingMember(int meetingID, String username){
        if(meetingInvites().getMeetingInviteByUsernameAndMeetingID(meetingID, username) == null) throw new IllegalArgumentException("The user is not invited");
        else{
            MeetingInvite meetingInvite = meetingInvites().getMeetingInviteByUsernameAndMeetingID(meetingID, username);

            Frame.getClient().sendChanges(meetingInvite, "delete");
        }
    }

    public ArrayList<String> getExternalMeetingMembers(int meetingID) {
        return externalUsers().getExternalUsersByMeetingID(meetingID);
    }

    public void addExternaMeetingMember(int meetingID, String email, String name, String phonenumber) throws IllegalArgumentException {
        if(externalUsers().getExternalUsersByMeetingID(meetingID).contains(email)) throw new IllegalArgumentException("The user is already invited");
        else{
            ExternalUser externalUser = new ExternalUser(email, meetingID, name, phonenumber);

            Frame.getClient().sendChanges(externalUser, "insert");
        }
    }

    public void deleteExternalMeetingMember(int meetingID, String email) throws IllegalArgumentException {
        if(!externalUsers().getExternalUsersByMeetingID(meetingID).contains(email)) throw new IllegalArgumentException("The user is not invited");
        else{
            ExternalUser externalUser = externalUsers().getExternalUserByEmailAndMeetingID(meetingID, email);

            Frame.getClient().sendChanges(externalUser, "delete");
        }
    }
}
