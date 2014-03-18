package persistence.data;

import model.ExternalUser;

import java.util.ArrayList;

public class ExternalUsers {
    private ArrayList<ExternalUser> externalUsers;

    public ExternalUsers(ArrayList<ExternalUser> externalUsers){
        populate(externalUsers);
    }

    public ExternalUsers(){
        this.externalUsers = new ArrayList<>();
    }

    public ArrayList<ExternalUser> getExternalUsers(){
        return externalUsers;
    }

    public ExternalUser getExternalUserByEmailAndMeeting(int meetingID, String email){
        for(ExternalUser externalUser : externalUsers){
            if(meetingID == externalUser.getMeetingID() && email.equals(externalUser.getEmail())) return externalUser;
        }

        return null;
    }

    public int getExternalUserIndex(int meetingID, String email){
        for(ExternalUser externalUser : externalUsers){
            if(meetingID == externalUser.getMeetingID() && email.equals(externalUser.getEmail())) return externalUsers.indexOf(externalUser);
        }

        return -1;
    }

    public void addExternalUser(ExternalUser externalUser){
        externalUsers.add(externalUser);
    }

    public void removeExternalUser(ExternalUser externalUser){
        externalUsers.remove(externalUser);
    }

    public void updateExternalUser(ExternalUser externalUser){
        int i = getExternalUserIndex(externalUser.getMeetingID(), externalUser.getEmail());

        externalUsers.remove(i);
        externalUsers.add(i, externalUser);
    }

    public void populate(ArrayList<ExternalUser> externalUsers){
        for(ExternalUser externalUser : externalUsers){
            externalUsers.add(externalUser);
        }
    }
}
