package model;

public class ExternalUser {
    private String email;
    private int meetingID;
    private String name;

    public ExternalUser(String email, int meetingID, String name) {
        this.email = email;
        this.meetingID = meetingID;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(int meetingID) {
        this.meetingID = meetingID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
