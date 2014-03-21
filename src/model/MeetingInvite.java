package model;

import java.io.Serializable;
import java.time.*;

public class MeetingInvite implements Serializable {
    private int meetingID;
    private String username;
    private Boolean coming;
    private LocalDateTime alarm;
    private LocalDateTime lastSeen;

    public MeetingInvite(int meetingID, String username) {
        this.meetingID = meetingID;
        setUsername(username);
        this.coming = null;
    }

    public MeetingInvite(int meetingID, String username, boolean coming, String alarm, String lastSeen) {
        this.meetingID = meetingID;
        setUsername(username);
        setComing(coming);
        setAlarm(alarm);
        setLastSeen(lastSeen);
    }

    public MeetingInvite(int meetingID, String username, boolean coming, LocalDateTime alarm, LocalDateTime lastSeen) {
        this.meetingID = meetingID;
        setUsername(username);
        setComing(coming);
        setAlarm(alarm.toString());
        setLastSeen(lastSeen.toString());
    }

    public MeetingInvite(int meetingID, String username, boolean coming) {
        this.meetingID = meetingID;
        setUsername(username);
        setComing(coming);
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

    public String isComingAsTinyInt(){
        return coming ? "1" : "0";
    }

    public void setComing(boolean coming) {
        this.coming = coming;
    }

    public String getAlarm() {
        return alarm == null ? null : alarm.toString();
    }

    public void setAlarm(String alarm) throws IllegalArgumentException{
        this.alarm = alarm == null ? null : LocalDateTime.parse(alarm);
    }

    public String getLastSeen() {
        return lastSeen == null ? null : lastSeen.toString();
    }

    public LocalDateTime getLastSeenAsLocalDateTime() {
        return lastSeen;
    }

    public void setLastSeen(String lastUpdated) throws IllegalArgumentException{
        this.lastSeen = LocalDateTime.parse(lastUpdated);
    }

    public void setLastSeenAsLocalDateTime(LocalDateTime lastUpdated) throws IllegalArgumentException{
        this.lastSeen = lastUpdated;
    }
}
