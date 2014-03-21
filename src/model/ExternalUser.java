package model;

import java.io.Serializable;

public class ExternalUser implements Serializable {
    private String email;
    private int meetingID;
    private String name;
    private String phoneNumber;

    public ExternalUser(String email, String name, String phoneNumber) throws IllegalArgumentException {
        setEmail(email);
        setName(name);
        setPhoneNumber(phoneNumber);
    }
    
    public ExternalUser(String email, int meetingID, String name, String phoneNumber) throws IllegalArgumentException {
        setEmail(email);
        this.meetingID = meetingID;
        setName(name);
        setPhoneNumber(phoneNumber);
    }

    public String getEmail() {
        return email;
    }
    
    public void setMeetingID(int meetingid){
    	meetingID = meetingid;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        if(User.validEmail(email)) this.email = email;
        else throw new IllegalArgumentException("Invalid email");
    }

    public int getMeetingID() {
        return meetingID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if(!name.matches(User.NAME_PATTERN)) throw new IllegalArgumentException("Invalid name");

        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if(!phoneNumber.matches(User.PHONE_PATTERN)) throw new IllegalArgumentException("Invalid phone number");

        this.phoneNumber = phoneNumber;
    }

    public String toString(){
        return name;
    }
}
