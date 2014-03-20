package persistence.data;

import model.MeetingAdmin;

import java.io.Serializable;
import java.util.ArrayList;

public class MeetingAdmins implements Serializable {
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

    public MeetingAdmin getMeetingAdminByUsernameAndMeetingID(int meetingID, String username){
        for(MeetingAdmin meetingAdmin : meetingAdmins){
            if(meetingID == meetingAdmin.getMeetingID() && username.equals(meetingAdmin.getUsername())) return meetingAdmin;
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

    public void removeMeetingAdminByMeetingIDAndUsername(int meetingID, String username){
        MeetingAdmin meetingAdmin = getMeetingAdminByUsernameAndMeetingID(meetingID, username);

        if(meetingAdmin == null) throw new IllegalArgumentException("User is not an admin");
        else meetingAdmins.remove(meetingAdmin);
    }

    public void updateMeetingAdmin(MeetingAdmin meetingAdmin){
        int i = getMeetingAdminIndexByUsernameAndMeetingID(meetingAdmin.getMeetingID(), meetingAdmin.getUsername());

        meetingAdmins.remove(i);
        meetingAdmins.add(i, meetingAdmin);
    }
}
