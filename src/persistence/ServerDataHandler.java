package persistence;

import model.*;
import persistence.mysql.MySQLQuery;

import java.util.*;

public class ServerDataHandler extends DataHandler {
    private MySQLQuery mySQLQuery;

    public ServerDataHandler(){
        mySQLQuery = new MySQLQuery();
        dataStorage = new DataStorage();
    }

    public void editDatabase(ArrayList<Object> changedObjects, String type){
        switch(type){
            case "insert":
                insert(changedObjects);
                break;
            case "update":
                update(changedObjects);
                break;
            case "delete":
                delete(changedObjects);
                break;
            default:
                break;
        }
    }

    public void insert(ArrayList<Object> changedObjects){
        ArrayList<String> fields;
        ArrayList<String> values;

        for(Object changedObject : changedObjects){
            String table = changedObject.getClass().getSimpleName();

            switch(table){
                case "User":
                   User user = (User) changedObject;

                    fields = new ArrayList<>(Arrays.asList("username", "password", "name", "email", "phonenumber"));
                    values = new ArrayList<>(Arrays.asList(user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getPhonenumber()));

                    mySQLQuery.insert(table, fields, values);
                    break;
                case "Group":
                    Group group = (Group) changedObject;

                    fields = new ArrayList<>(Arrays.asList("groupID", "supergroup"));
                    values = new ArrayList<>(Arrays.asList(String.valueOf(group.getGroupID()), String.valueOf(group.getSuperGroup().getGroupID())));

                    mySQLQuery.insert(table, fields, values);
                    break;
                case "Room":
                    Room room = (Room) changedObject;

                    fields = new ArrayList<>(Arrays.asList("roomnumber", "capacity"));
                    values = new ArrayList<>(Arrays.asList(String.valueOf(room.getRoomNumber()), String.valueOf(room.getCapacity())));

                    mySQLQuery.insert(table, fields, values);
                    break;
                case "Meeting":
                    Meeting meeting = (Meeting) changedObject;

                    String creator = meeting.getAdmins().get(0).getUsername();

                    fields = new ArrayList<>(Arrays.asList("meetingID", "startTime", "endTime", "description", "place", "room", "creator"));
                    values = new ArrayList<>(Arrays.asList(String.valueOf(meeting.getMeetingID()), meeting.getStarttime(), meeting.getEndtime(), meeting.getDescription(), meeting.getPlace(), String.valueOf(meeting.getRoom().getRoomNumber()), creator));

                    mySQLQuery.insert(table, fields, values);
                    mySQLQuery.insert("MeetingAdmin", null, new ArrayList<>(Arrays.asList(String.valueOf(meeting.getMeetingID()), creator)));
                    mySQLQuery.insert("MeetingInvite", null, new ArrayList<>(Arrays.asList(String.valueOf(meeting.getMeetingID()), creator, "true")));
                    break;
                case "ExternalUser":
                    ExternalUser externalUser = (ExternalUser) changedObject;

                    fields = new ArrayList<>(Arrays.asList("email", "meetingID", "name"));
                    values = new ArrayList<>(Arrays.asList(externalUser.getEmail(), String.valueOf(externalUser.getMeetingID()), externalUser.getName()));

                    mySQLQuery.insert(table, fields, values);
                    break;
                case "GroupMembership":
                    GroupMembership groupMembership = (GroupMembership) changedObject;

                    fields = new ArrayList<>(Arrays.asList("username", "groupID"));
                    values = new ArrayList<>(Arrays.asList(groupMembership.getUsername(), String.valueOf(groupMembership.getGroupID())));

                    mySQLQuery.insert(table, fields, values);
                    break;
                case "MeetingInvite":
                    MeetingInvite meetingInvite = (MeetingInvite) changedObject;

                    fields = new ArrayList<>(Arrays.asList("username", "meetingID"));
                    values = new ArrayList<>(Arrays.asList(meetingInvite.getUsername(), String.valueOf(meetingInvite.getMeetingID())));

                    mySQLQuery.insert(table, fields, values);
                    break;
                case "MeetingAdmin":
                    MeetingAdmin meetingAdmin = (MeetingAdmin) changedObject;

                    fields = new ArrayList<>(Arrays.asList("username", "meetingID"));
                    values = new ArrayList<>(Arrays.asList(meetingAdmin.getUsername(), String.valueOf(meetingAdmin.getMeetingID())));

                    mySQLQuery.insert(table, fields, values);
                    break;
                default:
                    break;
            }
        }
    }

    public void update(ArrayList<Object> changedObjects){
        ArrayList<String> fields;
        ArrayList<String> values;
        HashMap<String, String> map;

        for(Object changedObject : changedObjects){
            String table = changedObject.getClass().getSimpleName();

            switch(table){
                case "User":
                    User user = (User) changedObject;

                    map = new HashMap<>(); map.put("username", user.getUsername());
                    fields = new ArrayList<>(Arrays.asList("username", "password", "name", "email", "phonenumber"));
                    values = new ArrayList<>(Arrays.asList(user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getPhonenumber()));

                    mySQLQuery.update(table, fields, values, map, null);
                    break;
                case "Group":
                    Group group = (Group) changedObject;

                    map = new HashMap<>(); map.put("groupID", String.valueOf(group.getGroupID()));
                    fields = new ArrayList<>(Arrays.asList("groupID", "supergroup"));
                    values = new ArrayList<>(Arrays.asList(String.valueOf(group.getGroupID()), String.valueOf(group.getSuperGroup().getGroupID())));

                    mySQLQuery.update(table, fields, values, map, null);
                    break;
                case "Room":
                    Room room = (Room) changedObject;

                    map = new HashMap<>(); map.put("roomnumber", String.valueOf(room.getRoomNumber()));
                    fields = new ArrayList<>(Arrays.asList("roomnumber", "capacity"));
                    values = new ArrayList<>(Arrays.asList(String.valueOf(room.getRoomNumber()), String.valueOf(room.getCapacity())));

                    mySQLQuery.update(table, fields, values, map, null);
                    break;
                case "Meeting":
                    Meeting meeting = (Meeting) changedObject;

                    String creator = meeting.getAdmins().get(0).getUsername();

                    map = new HashMap<>(); map.put("meetingID", String.valueOf(meeting.getMeetingID()));
                    fields = new ArrayList<>(Arrays.asList("meetingID", "startTime", "endTime", "description", "place", "room", "creator"));
                    values = new ArrayList<>(Arrays.asList(String.valueOf(meeting.getMeetingID()), meeting.getStarttime(), meeting.getEndtime(), meeting.getDescription(), meeting.getPlace(), String.valueOf(meeting.getRoom().getRoomNumber()), creator));

                    mySQLQuery.update(table, fields, values, map, null);
                    break;
                case "ExternalUser":
                    ExternalUser externalUser = (ExternalUser) changedObject;

                    map = new HashMap<>(); map.put("email", String.valueOf(externalUser.getEmail())); map.put("meetingID", String.valueOf(externalUser.getMeetingID()));
                    fields = new ArrayList<>(Arrays.asList("email", "meetingID", "name"));
                    values = new ArrayList<>(Arrays.asList(externalUser.getEmail(), String.valueOf(externalUser.getMeetingID()), externalUser.getName()));

                    mySQLQuery.update(table, fields, values, map, null);
                    break;
                case "GroupMembership":
                    GroupMembership groupMembership = (GroupMembership) changedObject;

                    map = new HashMap<>(); map.put("username", String.valueOf(groupMembership.getUsername())); map.put("groupID", String.valueOf(groupMembership.getGroupID()));
                    fields = new ArrayList<>(Arrays.asList("username", "groupID"));
                    values = new ArrayList<>(Arrays.asList(groupMembership.getUsername(), String.valueOf(groupMembership.getGroupID())));

                    mySQLQuery.update(table, fields, values, map, null);
                    break;
                case "MeetingInvite":
                    MeetingInvite meetingInvite = (MeetingInvite) changedObject;

                    map = new HashMap<>(); map.put("meetingID", String.valueOf(meetingInvite.getMeetingID())); map.put("username", String.valueOf(meetingInvite.getUsername()));
                    fields = new ArrayList<>(Arrays.asList("username", "meetingID"));
                    values = new ArrayList<>(Arrays.asList(meetingInvite.getUsername(), String.valueOf(meetingInvite.getMeetingID())));

                    mySQLQuery.update(table, fields, values, map, null);
                    break;
                case "MeetingAdmin":
                    MeetingAdmin meetingAdmin = (MeetingAdmin) changedObject;

                    map = new HashMap<>(); map.put("meetingID", String.valueOf(meetingAdmin.getMeetingID())); map.put("username", String.valueOf(meetingAdmin.getUsername()));
                    fields = new ArrayList<>(Arrays.asList("username", "meetingID"));
                    values = new ArrayList<>(Arrays.asList(meetingAdmin.getUsername(), String.valueOf(meetingAdmin.getMeetingID())));

                    mySQLQuery.update(table, fields, values, map, null);
                    break;
                default:
                    break;
            }
        }
    }

    public void delete(ArrayList<Object> changedObjects){
        ArrayList<String> fields;
        ArrayList<String> values;
        HashMap<String, String> map;

        for(Object changedObject : changedObjects){
            String table = changedObject.getClass().getSimpleName();

            switch(table){
                case "User":
                    User user = (User) changedObject;

                    map = new HashMap<>(); map.put("username", user.getUsername());

                    mySQLQuery.delete(table, map);
                    break;
                case "Group":
                    Group group = (Group) changedObject;

                    map = new HashMap<>(); map.put("groupID", String.valueOf(group.getGroupID()));

                    mySQLQuery.delete(table, map);
                    break;
                case "Room":
                    Room room = (Room) changedObject;

                    map = new HashMap<>(); map.put("roomnumber", String.valueOf(room.getRoomNumber()));

                    mySQLQuery.delete(table, map);
                    break;
                case "Meeting":
                    Meeting meeting = (Meeting) changedObject;

                    map = new HashMap<>(); map.put("meetingID", String.valueOf(meeting.getMeetingID()));

                    mySQLQuery.delete(table, map);
                    break;
                case "ExternalUser":
                    ExternalUser externalUser = (ExternalUser) changedObject;

                    map = new HashMap<>(); map.put("email", String.valueOf(externalUser.getEmail())); map.put("meetingID", String.valueOf(externalUser.getMeetingID()));

                    mySQLQuery.delete(table, map);
                    break;
                case "GroupMembership":
                    GroupMembership groupMembership = (GroupMembership) changedObject;

                    map = new HashMap<>(); map.put("username", String.valueOf(groupMembership.getUsername())); map.put("groupID", String.valueOf(groupMembership.getGroupID()));

                    mySQLQuery.delete(table, map);
                    break;
                case "MeetingInvite":
                    MeetingInvite meetingInvite = (MeetingInvite) changedObject;

                    map = new HashMap<>(); map.put("meetingID", String.valueOf(meetingInvite.getMeetingID())); map.put("username", String.valueOf(meetingInvite.getUsername()));
                    mySQLQuery.delete(table, map);
                    break;
                case "MeetingAdmin":
                    MeetingAdmin meetingAdmin = (MeetingAdmin) changedObject;

                    map = new HashMap<>(); map.put("meetingID", String.valueOf(meetingAdmin.getMeetingID())); map.put("username", String.valueOf(meetingAdmin.getUsername()));

                    mySQLQuery.delete(table, map);
                    break;
                default:
                    break;
            }
        }
    }

    //TODO: Dette blir et helvete!
    public void populate(){

    }
}
