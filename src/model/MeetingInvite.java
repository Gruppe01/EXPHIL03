package model;

import java.util.Date;

public class MeetingInvite {
    private int meetingID;
    private String username;
    private boolean coming;
    private Date alarm;
    private Date lastUpdated;

    public MeetingInvite(int meetingID, String username, boolean coming) {
        this.meetingID = meetingID;
        this.username = username;
        this.coming = coming;
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

    public boolean isComing() {
        return coming;
    }

    public void setComing(boolean coming) {
        this.coming = coming;
    }

    public Date getAlarm() {
        return alarm;
    }

    public void setAlarm(Date alarm) {
        this.alarm = alarm;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
