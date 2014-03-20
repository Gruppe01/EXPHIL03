package persistence.data;

import model.MeetingInvite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MeetingInvites implements Serializable {
    private ArrayList<MeetingInvite> meetingInvites;

    public MeetingInvites(ArrayList<MeetingInvite> meetingInvites){
        this.meetingInvites = meetingInvites;
    }

    public MeetingInvites(){
        this.meetingInvites = new ArrayList<>();
    }

    public ArrayList<MeetingInvite> getMeetingInvites(){
        return meetingInvites;
    }

    public MeetingInvite getMeetingInviteByUsernameAndMeetingID(int meetingInviteID, String username){
        for(MeetingInvite meetingInvite : meetingInvites){
            if(meetingInviteID == meetingInvite.getMeetingID() && username.equals(meetingInvite.getUsername())) return meetingInvite;
        }

        return null;
    }

    public int getMeetingInviteIndexByUsernameAndMeetingID(int meetingInviteID, String username){
        for(MeetingInvite meetingInvite : meetingInvites){
            if(meetingInviteID == meetingInvite.getMeetingID() && username.equals(meetingInvite.getUsername())) return meetingInvites.indexOf(meetingInvite);
        }

        return -1;
    }

    public HashMap<String, Boolean> getMeetingInvitesByMeetingID(int meetingID){
        HashMap<String, Boolean> meetingInvited = new HashMap<>();

        for(MeetingInvite meetingInvite : meetingInvites){
            if(meetingInvite.getMeetingID() == meetingID) meetingInvited.put(meetingInvite.getUsername(), meetingInvite.isComing());
        }

        return meetingInvited;
    }


    public void addMeetingInvite(MeetingInvite meetingInvite){
        meetingInvites.add(meetingInvite);
    }

    public void removeMeetingInvite(MeetingInvite meetingInvite){
        meetingInvites.remove(meetingInvite);
    }

    public void removeMeetingInviteByMeetingIDAndUsername(int meetingID, String username){
        MeetingInvite meetingInvite = getMeetingInviteByUsernameAndMeetingID(meetingID, username);

        if(meetingInvite == null) throw new IllegalArgumentException("User is not invited to the meeting");
        else meetingInvites.remove(meetingInvite);
    }

    public void updateMeetingInvite(MeetingInvite meetingInvite){
        int i = getMeetingInviteIndexByUsernameAndMeetingID(meetingInvite.getMeetingID(), meetingInvite.getUsername());

        meetingInvites.remove(i);
        meetingInvites.add(i, meetingInvite);
    }
}
