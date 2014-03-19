package persistence.data;

import model.MeetingInvite;

import java.util.ArrayList;

public class MeetingInvites {
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

    public void addMeetingInvite(MeetingInvite meetingInvite){
        meetingInvites.add(meetingInvite);
    }

    public void removeMeetingInvite(MeetingInvite meetingInvite){
        meetingInvites.remove(meetingInvite);
    }

    public void updateMeetingInvite(MeetingInvite meetingInvite){
        int i = getMeetingInviteIndexByUsernameAndMeetingID(meetingInvite.getMeetingID(), meetingInvite.getUsername());

        meetingInvites.remove(i);
        meetingInvites.add(i, meetingInvite);
    }

    public void populate(ArrayList<MeetingInvite> meetingInvites){
        for(MeetingInvite meetingInvite : meetingInvites){
            meetingInvites.add(meetingInvite);
        }
    }
}
