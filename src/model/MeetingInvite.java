package model;

import java.time.*;

public class MeetingInvite {
    private int meetingID;
    private String username;
    private Boolean coming;
    private LocalDateTime alarm;
    private LocalDateTime lastUpdated;

    public MeetingInvite(int meetingID, String username) {
        this.meetingID = meetingID;
        this.username = username;
        this.coming = null;
    }

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

    public String getAlarm() {
        return alarm.toString();
    }

    public void setAlarm(String alarm) throws IllegalArgumentException{
        this.alarm = LocalDateTime.parse(alarm);
    }

    public String getLastUpdated() {
        return lastUpdated.toString();
    }

    public void setLastUpdated(String lastUpdated) throws IllegalArgumentException{
        this.lastUpdated = LocalDateTime.parse(lastUpdated);
    }
}
