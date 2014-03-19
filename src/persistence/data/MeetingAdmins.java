package persistence.data;

import model.MeetingAdmin;

import java.util.ArrayList;

public class MeetingAdmins {
    private ArrayList<MeetingAdmin> meetingAdmins;

    public MeetingAdmins(ArrayList<MeetingAdmin> meetingAdmins){
        this.meetingAdmins = meetingAdmins;
    }

    public MeetingAdmins(){
        this.meetingAdmins = new ArrayList<>();
    }

    public ArrayList<MeetingAdmin> getMeetingAdmins(){
        return meetingAdmins;
    }

    public MeetingAdmin getMeetingAdminByUsernameAndMeetingID(int meetingAdminID, String username){
        for(MeetingAdmin meetingAdmin : meetingAdmins){
            if(meetingAdminID == meetingAdmin.getMeetingID() && username.equals(meetingAdmin.getUsername())) return meetingAdmin;
        }

        return null;
    }

    public int getMeetingAdminIndexByUsernameAndMeetingID(int meetingAdminID, String username){
        for(MeetingAdmin meetingAdmin : meetingAdmins){
            if(meetingAdminID == meetingAdmin.getMeetingID() && username.equals(meetingAdmin.getUsername())) return meetingAdmins.indexOf(meetingAdmin);
        }

        return -1;
    }

    public void addMeetingAdmin(MeetingAdmin meetingAdmin){
        meetingAdmins.add(meetingAdmin);
    }

    public void removeMeetingAdmin(MeetingAdmin meetingAdmin){
        meetingAdmins.remove(meetingAdmin);
    }

    public void updateMeetingAdmin(MeetingAdmin meetingAdmin){
        int i = getMeetingAdminIndexByUsernameAndMeetingID(meetingAdmin.getMeetingID(), meetingAdmin.getUsername());

        meetingAdmins.remove(i);
        meetingAdmins.add(i, meetingAdmin);
    }

    public void populate(ArrayList<MeetingAdmin> meetingAdmins){
        for(MeetingAdmin meetingAdmin : meetingAdmins){
            meetingAdmins.add(meetingAdmin);
        }
    }
}
