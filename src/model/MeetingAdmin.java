package model;

import java.io.Serializable;

public class MeetingAdmin implements Serializable {
    private int meetingID;
    private String username;

    public MeetingAdmin(int meetingID, String username) {

        this.meetingID = meetingID;
        this.username = username;
    }

    public int getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(int meetingID) {
        this.meetingID = meetingID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
