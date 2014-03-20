package persistence.data;

import model.*;
import persistence.DataStorage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Meetings extends DataStorage implements Serializable {
    private ArrayList<Meeting> meetings;

    public Meetings(ArrayList<Meeting> meetings){
        this.meetings = meetings;
    }

    public Meetings(){
        this.meetings = new ArrayList<>();
    }

    public ArrayList<Meeting> getMeetings(){
        return meetings;
    }

    public Meeting getMeetingByID(int meetingID){
        for(Meeting meeting : meetings){
            if(meetingID == meeting.getMeetingID()) return meeting;
        }

        return null;
    }

    public int getMeetingIndexByID(int meetingID){
        for(Meeting meeting : meetings){
            if(meetingID == meeting.getMeetingID()) return meetings.indexOf(meeting);
        }

        return -1;
    }

    public void addMeeting(Meeting meeting){
        meetings.add(meeting);
    }

    public void removeMeeting(Meeting meeting){
        meetings.remove(meeting);
    }

    public void updateMeeting(Meeting meeting){
        int i = getMeetingIndexByID(meeting.getMeetingID());

        meetings.remove(i);
        meetings.add(i, meeting);
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
