package persistence;

import model.*;

import java.util.*;

public class DataHandler {
    protected DataStorage dataStorage;

    public DataHandler(DataStorage dataStorage){
        this.dataStorage = dataStorage;
    }

    public DataHandler(){
        this.dataStorage = new DataStorage();
    }

    public void receiveChanges(ArrayList<Object> changedObjects, String type){
        switch (type){
            case "insert":
                insertObject(changedObjects);
                break;
            case "update":
                updateObject(changedObjects);
                break;
            case "delete":
                deleteObject(changedObjects);
                break;
            default:
                break;
        }
    }

    public void insertObject(ArrayList<Object> changedObjects){
        for(Object changedObject : changedObjects){
            String table = changedObject.getClass().getSimpleName();

            switch(table){
                case "User":
                    User user = (User) changedObject;

                    dataStorage.getUsers().addUser(user);
                    break;
                case "Group":
                    Group group = (Group) changedObject;

                    dataStorage.getGroups().addGroup(group);
                    break;
                case "Room":
                    Room room = (Room) changedObject;

                    dataStorage.getRooms().addRoom(room);
                    break;
                case "Meeting":
                    Meeting meeting = (Meeting) changedObject;

                    dataStorage.getMeetings().addMeeting(meeting);
                    break;
                case "ExternalUser":
                    ExternalUser externalUser = (ExternalUser) changedObject;

                    dataStorage.getExternalUsers().addExternalUser(externalUser);
                    break;
                case "GroupMembership":
                    GroupMembership groupMembership = (GroupMembership) changedObject;

                    dataStorage.getGroupMemberships().addGroupMembership(groupMembership);
                    break;
                case "MeetingInvite":
                    MeetingInvite meetingInvite = (MeetingInvite) changedObject;

                    dataStorage.getMeetingInvites().addMeetingInvite(meetingInvite);
                    break;
                case "MeetingAdmin":
                    MeetingAdmin meetingAdmin = (MeetingAdmin) changedObject;

                    dataStorage.getMeetingAdmins().addMeetingAdmin(meetingAdmin);
                    break;
                default:
                    break;
            }
        }
    }

    public void updateObject(ArrayList<Object> changedObjects){
        for(Object changedObject : changedObjects){
            String table = changedObject.getClass().getSimpleName();

            switch(table){
                case "User":
                    User user = (User) changedObject;

                    dataStorage.getUsers().updateUser(user);
                    break;
                case "Group":
                    Group group = (Group) changedObject;

                    dataStorage.getGroups().updateGroup(group);
                    break;
                case "Room":
                    Room room = (Room) changedObject;

                    dataStorage.getRooms().updateRoom(room);
                    break;
                case "Meeting":
                    Meeting meeting = (Meeting) changedObject;

                    dataStorage.getMeetings().updateMeeting(meeting);
                    break;
                case "ExternalUser":
                    ExternalUser externalUser = (ExternalUser) changedObject;

                    dataStorage.getExternalUsers().updateExternalUser(externalUser);
                    break;
                case "GroupMembership":
                    GroupMembership groupMembership = (GroupMembership) changedObject;

                    dataStorage.getGroupMemberships().updateGroupMembership(groupMembership);
                    break;
                case "MeetingInvite":
                    MeetingInvite meetingInvite = (MeetingInvite) changedObject;

                    dataStorage.getMeetingInvites().updateMeetingInvite(meetingInvite);
                    break;
                case "MeetingAdmin":
                    MeetingAdmin meetingAdmin = (MeetingAdmin) changedObject;

                    dataStorage.getMeetingAdmins().updateMeetingAdmin(meetingAdmin);
                    break;
                default:
                    break;
            }
        }
    }

    public void deleteObject(ArrayList<Object> changedObjects){
        for(Object changedObject : changedObjects){
            String table = changedObject.getClass().getSimpleName();

            switch(table){
                case "User":
                    User user = (User) changedObject;

                    dataStorage.getUsers().removeUser(user);
                    break;
                case "Group":
                    Group group = (Group) changedObject;

                    dataStorage.getGroups().removeGroup(group);
                    break;
                case "Room":
                    Room room = (Room) changedObject;

                    dataStorage.getRooms().removeRoom(room);
                    break;
                case "Meeting":
                    Meeting meeting = (Meeting) changedObject;

                    dataStorage.getMeetings().removeMeeting(meeting);
                    break;
                case "ExternalUser":
                    ExternalUser externalUser = (ExternalUser) changedObject;

                    dataStorage.getExternalUsers().removeExternalUser(externalUser);
                    break;
                case "GroupMembership":
                    GroupMembership groupMembership = (GroupMembership) changedObject;

                    dataStorage.getGroupMemberships().removeGroupMembership(groupMembership);
                    break;
                case "MeetingInvite":
                    MeetingInvite meetingInvite = (MeetingInvite) changedObject;

                    dataStorage.getMeetingInvites().removeMeetingInvite(meetingInvite);
                    break;
                case "MeetingAdmin":
                    MeetingAdmin meetingAdmin = (MeetingAdmin) changedObject;

                    dataStorage.getMeetingAdmins().removeMeetingAdmin(meetingAdmin);
                    break;
                default:
                    break;
            }
        }
    }

    public void setDataStorage(DataStorage dataStorage){
        this.dataStorage = dataStorage;
    }

    public DataStorage getDataStorage(){
        return dataStorage;
    }
}
