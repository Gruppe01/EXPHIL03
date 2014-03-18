package persistence;

import model.Meeting;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

//Meeting, ExternalUser, MeetingAdmin, MeetingInvite
public class Meetings {
    private ArrayList<MeetingObject> meetingObjects;

    public Meetings(ArrayList<MeetingObject> meetingObjects){
        this.meetingObjects = meetingObjects;
    }

    public Meetings(){
        this.meetingObjects = null;
    }

    public ArrayList<MeetingObject> getMeetingObjects(){
        return meetingObjects;
    }

    public void addMeetingObject(MeetingObject meetingObject){
        meetingObjects.add(meetingObject);
    }

    public void deleteMeetingObject(MeetingObject meetingObject){
        meetingObjects.remove(meetingObject);
    }

    public ArrayList<Meeting> getMeetings(){
        ArrayList<Meeting> meetings = new ArrayList<>();

        for(MeetingObject meetingObject : meetingObjects){
            meetings.add(meetingObject.getMeeting());
        }

        return meetings;
    }

    class MeetingObject{
        private Meeting meeting;
        private HashMap<User, Boolean> invitations;

        public MeetingObject(Meeting meeting, HashMap<User, Boolean> invitations){
            this.meeting = meeting;
            this.invitations = invitations;
        }

        public MeetingObject(Meeting meeting){
            this.meeting = meeting;
        }

        public Meeting getMeeting(){
            return meeting;
        }

        public HashMap<User, Boolean> getInvitations(){
            return invitations;
        }

        public void addInvitation(User user){
            invitations.put(user, null);
        }

        public void deleteInvitation(User user){
            invitations.remove(user);
        }

        public void updateInvitation(User user, boolean value){
            invitations.put(user, value);
        }
    }
}
