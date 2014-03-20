package persistence.data;

import model.ExternalUser;

import java.io.Serializable;
import java.util.ArrayList;

public class ExternalUsers implements Serializable {
    private ArrayList<ExternalUser> externalUsers;

    public ExternalUsers(ArrayList<ExternalUser> externalUsers){
        this.externalUsers = externalUsers;
    }

    public ExternalUsers(){
        this.externalUsers = new ArrayList<>();
    }

    public ArrayList<ExternalUser> getExternalUsers(){
        return externalUsers;
    }

    public ExternalUser getExternalUserByEmailAndMeetingID(int meetingID, String email){
        for(ExternalUser externalUser : externalUsers){
            if(meetingID == externalUser.getMeetingID() && email.equals(externalUser.getEmail())) return externalUser;
        }

        return null;
    }

    public int getExternalUserIndexByEmailAndMeetingID(int meetingID, String email){
        for(ExternalUser externalUser : externalUsers){
            if(meetingID == externalUser.getMeetingID() && email.equals(externalUser.getEmail())) return externalUsers.indexOf(externalUser);
        }

        return -1;
    }

    public ArrayList<String> getExternalUsersByMeetingID(int meetingID){
        ArrayList<String> externalUsersReturn = new ArrayList<>();

        for(ExternalUser externalUser : externalUsers){
            if(externalUser.getMeetingID() == meetingID && !externalUsersReturn.contains(externalUser.getEmail())) externalUsersReturn.add(externalUser.getEmail());
        }

        return externalUsersReturn;
    }

    public void addExternalUser(ExternalUser externalUser){
        externalUsers.add(externalUser);
    }

    public void removeExternalUser(ExternalUser externalUser){
        externalUsers.remove(externalUser);
    }

    public void removeExternalUserByMeetingIDAndEmail(int meetingID, String email){
        ExternalUser externalUser = getExternalUserByEmailAndMeetingID(meetingID, email);

        if(externalUser == null) throw new IllegalArgumentException("User is not invited to the meeting");
        else externalUsers.remove(externalUser);
    }

    public void updateExternalUser(ExternalUser externalUser){
        int i = getExternalUserIndexByEmailAndMeetingID(externalUser.getMeetingID(), externalUser.getEmail());

        externalUsers.remove(i);
        externalUsers.add(i, externalUser);
    }
}
