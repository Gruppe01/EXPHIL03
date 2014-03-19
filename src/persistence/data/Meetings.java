package persistence.data;

import model.Meeting;

import java.util.ArrayList;

public class Meetings {
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

    public void populate(ArrayList<Meeting> meetings){
        for(Meeting meeting : meetings){
            meetings.add(meeting);
        }
    }
}
