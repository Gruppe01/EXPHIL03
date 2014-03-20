package persistence;

import model.*;

import java.util.*;

public class DataHandler extends Thread{
    protected DataStorage dataStorage;

    public DataHandler(DataStorage dataStorage){
        this.dataStorage = dataStorage;
    }

    public DataHandler(){
        this.dataStorage = new DataStorage();
    }

    @SuppressWarnings("unchecked")
    public void receiveChanges(Object changedObject, String type){
        if(changedObject.getClass().getSimpleName().equals("ArrayList")) receive((ArrayList<Object>) changedObject, type);
        else receive(changedObject, type);
    }

    @SuppressWarnings("unchecked")
    public void receive(ArrayList<Object> changedObjects, String type){
        switch (type){
            case "insert":
                for(Object changedObject : changedObjects){
                    insertObject(changedObject);
                }
                break;
            case "update":
                for(Object changedObject : changedObjects){
                    updateObject(changedObject);
                }
                break;
            case "delete":
                for(Object changedObject : changedObjects){
                    deleteObject(changedObject);
                }
                break;
            case "populate":
                setDataStorage((DataStorage) changedObjects.get(0));
                break;
            default:
                break;
        }
    }

    public void receive(Object changedObject, String type){
        switch (type){
            case "insert":
                insertObject(changedObject);
                break;
            case "update":
                updateObject(changedObject);
                break;
            case "delete":
                deleteObject(changedObject);
                break;
            case "populate":
                setDataStorage((DataStorage) changedObject);
                break;
            default:
                break;
        }
    }

    private void insertObject(Object changedObject){
        String table = changedObject.getClass().getSimpleName();

        switch(table){
            case "User":
                User user = (User) changedObject;

                dataStorage.users().addUser(user);
                break;
            case "Group":
                Group group = (Group) changedObject;

                dataStorage.groups().addGroup(group);
                break;
            case "Room":
                Room room = (Room) changedObject;

                dataStorage.rooms().addRoom(room);
                break;
            case "Meeting":
                Meeting meeting = (Meeting) changedObject;

                dataStorage.meetings().addMeeting(meeting);
                break;
            case "ExternalUser":
                ExternalUser externalUser = (ExternalUser) changedObject;

                dataStorage.externalUsers().addExternalUser(externalUser);
                break;
            case "GroupMembership":
                GroupMembership groupMembership = (GroupMembership) changedObject;

                dataStorage.groupMemberships().addGroupMembership(groupMembership);
                break;
            case "MeetingInvite":
                MeetingInvite meetingInvite = (MeetingInvite) changedObject;

                dataStorage.meetingInvites().addMeetingInvite(meetingInvite);
                break;
            case "MeetingAdmin":
                MeetingAdmin meetingAdmin = (MeetingAdmin) changedObject;

                dataStorage.meetingAdmins().addMeetingAdmin(meetingAdmin);
                break;
            default:
                break;
        }
    }

    private void updateObject(Object changedObject){
        String table = changedObject.getClass().getSimpleName();

        switch(table){
            case "User":
                User user = (User) changedObject;

                dataStorage.users().updateUser(user);
                break;
            case "Group":
                Group group = (Group) changedObject;

                dataStorage.groups().updateGroup(group);
                break;
            case "Room":
                Room room = (Room) changedObject;

                dataStorage.rooms().updateRoom(room);
                break;
            case "Meeting":
                Meeting meeting = (Meeting) changedObject;

                dataStorage.meetings().updateMeeting(meeting);
                break;
            case "ExternalUser":
                ExternalUser externalUser = (ExternalUser) changedObject;

                dataStorage.externalUsers().updateExternalUser(externalUser);
                break;
            case "GroupMembership":
                GroupMembership groupMembership = (GroupMembership) changedObject;

                dataStorage.groupMemberships().updateGroupMembership(groupMembership);
                break;
            case "MeetingInvite":
                MeetingInvite meetingInvite = (MeetingInvite) changedObject;

                dataStorage.meetingInvites().updateMeetingInvite(meetingInvite);
                break;
            case "MeetingAdmin":
                MeetingAdmin meetingAdmin = (MeetingAdmin) changedObject;

                dataStorage.meetingAdmins().updateMeetingAdmin(meetingAdmin);
                break;
            default:
                break;
        }
    }

    private void deleteObject(Object changedObject){
        String table = changedObject.getClass().getSimpleName();

        switch(table){
            case "User":
                User user = (User) changedObject;

                dataStorage.users().removeUser(user);
                break;
            case "Group":
                Group group = (Group) changedObject;

                dataStorage.groups().removeGroup(group);
                break;
            case "Room":
                Room room = (Room) changedObject;

                dataStorage.rooms().removeRoom(room);
                break;
            case "Meeting":
                Meeting meeting = (Meeting) changedObject;

                dataStorage.meetings().removeMeeting(meeting);
                break;
            case "ExternalUser":
                ExternalUser externalUser = (ExternalUser) changedObject;

                dataStorage.externalUsers().removeExternalUser(externalUser);
                break;
            case "GroupMembership":
                GroupMembership groupMembership = (GroupMembership) changedObject;

                dataStorage.groupMemberships().removeGroupMembership(groupMembership);
                break;
            case "MeetingInvite":
                MeetingInvite meetingInvite = (MeetingInvite) changedObject;

                dataStorage.meetingInvites().removeMeetingInvite(meetingInvite);
                break;
            case "MeetingAdmin":
                MeetingAdmin meetingAdmin = (MeetingAdmin) changedObject;

                dataStorage.meetingAdmins().removeMeetingAdmin(meetingAdmin);
                break;
            default:
                break;
        }
    }

    public void setDataStorage(DataStorage dataStorage){
        this.dataStorage = dataStorage;
    }

    public DataStorage getDataStorage(){
        return dataStorage;
    }
}
