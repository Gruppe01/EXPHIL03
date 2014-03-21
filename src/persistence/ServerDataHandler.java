package persistence;

import model.*;
import persistence.data.*;
import persistence.mysql.MySQLQuery;

import java.util.*;

public class ServerDataHandler extends DataHandler {
    private MySQLQuery mySQLQuery;

    public ServerDataHandler(){
        mySQLQuery = new MySQLQuery();
        dataStorage = getDataStorageFromDatabase();
    }

    @SuppressWarnings("unchecked")
    public void editDatabase(Object changedObject, String type){
        if(changedObject.getClass().getSimpleName().equals("ArrayList")) edit((ArrayList<Object>) changedObject, type);
        else edit(changedObject, type);
    }

    public void edit(ArrayList<Object> changedObjects, String type){
        switch(type){
            case "insert":
                for(Object changedObject : changedObjects){
                    insert(changedObject);
                }
                break;
            case "update":
                for(Object changedObject : changedObjects){
                    update(changedObject);
                }
                break;
            case "delete":
                for(Object changedObject : changedObjects){
                    delete(changedObject);
                }
                break;
            default:
                break;
        }
    }

    public void edit(Object changedObject, String type){
        switch(type){
            case "insert":
                insert(changedObject);
                break;
            case "update":
                update(changedObject);
                break;
            case "delete":
                delete(changedObject);
                break;
            default:
                break;
        }
    }

    public void insert(Object changedObject){
        ArrayList<String> fields;
        ArrayList<String> values;

        String table = changedObject.getClass().getSimpleName();

        switch(table){
            case "User":
               User user = (User) changedObject;

                fields = new ArrayList<>(Arrays.asList("username", "password", "name", "email", "phonenumber"));
                values = new ArrayList<>(Arrays.asList(user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getPhoneNumber()));

                mySQLQuery.insert(table, fields, values);
                break;
            case "Group":
                Group group = (Group) changedObject;

                fields = new ArrayList<>(Arrays.asList("groupID", "supergroup"));
                values = new ArrayList<>(Arrays.asList(String.valueOf(group.getGroupID()), String.valueOf(group.getSuperGroup())));

                mySQLQuery.insert(table, fields, values);
                break;
            case "Room":
                Room room = (Room) changedObject;

                fields = new ArrayList<>(Arrays.asList("roomNumber", "capacity"));
                values = new ArrayList<>(Arrays.asList(String.valueOf(room.getRoomNumber()), String.valueOf(room.getCapacity())));

                mySQLQuery.insert(table, fields, values);
                break;
            case "Meeting":
                Meeting meeting = (Meeting) changedObject;

                fields = new ArrayList<>(Arrays.asList("meetingID", "startTime", "endTime", "description", "place", "room", "minCapacity", "creator", "lastUpdated"));
                values = new ArrayList<>(Arrays.asList(String.valueOf(meeting.getMeetingID()), meeting.getStarttime(), meeting.getEndtime(), meeting.getDescription(), meeting.getPlace(), String.valueOf(meeting.getRoom()), String.valueOf(meeting.getMinCapacity()), meeting.getCreator(), meeting.getLastUpdated()));

                mySQLQuery.insert(table, fields, values);
                mySQLQuery.insert("MeetingAdmin", new ArrayList<>(Arrays.asList("meetingID", "username")), new ArrayList<>(Arrays.asList(String.valueOf(meeting.getMeetingID()), meeting.getCreator())));
                mySQLQuery.insert("MeetingInvite", new ArrayList<>(Arrays.asList("meetingID", "username", "coming")), new ArrayList<>(Arrays.asList(String.valueOf(meeting.getMeetingID()), meeting.getCreator(), "1")));
                break;
            case "ExternalUser":
                ExternalUser externalUser = (ExternalUser) changedObject;

                fields = new ArrayList<>(Arrays.asList("email", "meetingID", "name", "phonenumber"));
                values = new ArrayList<>(Arrays.asList(externalUser.getEmail(), String.valueOf(externalUser.getMeetingID()), externalUser.getName(), externalUser.getPhoneNumber()));

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

                fields = new ArrayList<>(Arrays.asList("username", "meetingID", "coming", "alarm", "lastSeen"));
                values = new ArrayList<>(Arrays.asList(meetingInvite.getUsername(), String.valueOf(meetingInvite.getMeetingID()), String.valueOf(meetingInvite.isComing()), meetingInvite.getAlarm(), meetingInvite.getLastSeen()));

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

    public void update(Object changedObject){
        ArrayList<String> fields;
        ArrayList<String> values;
        HashMap<String, String> map;

        String table = changedObject.getClass().getSimpleName();

        switch(table){
            case "User":
                User user = (User) changedObject;

                map = new HashMap<>(); map.put("username", user.getUsername());
                fields = new ArrayList<>(Arrays.asList("username", "password", "name", "email", "phonenumber"));
                values = new ArrayList<>(Arrays.asList(user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getPhoneNumber()));

                mySQLQuery.update(table, fields, values, map, null);
                break;
            case "Group":
                Group group = (Group) changedObject;

                map = new HashMap<>(); map.put("groupID", String.valueOf(group.getGroupID()));
                fields = new ArrayList<>(Arrays.asList("groupID", "supergroup"));
                values = new ArrayList<>(Arrays.asList(String.valueOf(group.getGroupID()), String.valueOf(group.getSuperGroup())));

                mySQLQuery.update(table, fields, values, map, null);
                break;
            case "Room":
                Room room = (Room) changedObject;

                map = new HashMap<>(); map.put("roomNumber", String.valueOf(room.getRoomNumber()));
                fields = new ArrayList<>(Arrays.asList("roomNumber", "capacity"));
                values = new ArrayList<>(Arrays.asList(String.valueOf(room.getRoomNumber()), String.valueOf(room.getCapacity())));

                mySQLQuery.update(table, fields, values, map, null);
                break;
            case "Meeting":
                Meeting meeting = (Meeting) changedObject;

                map = new HashMap<>(); map.put("meetingID", String.valueOf(meeting.getMeetingID()));
                fields = new ArrayList<>(Arrays.asList("meetingID", "startTime", "endTime", "description", "place", "room", "minCapacity", "creator", "lastUpdated"));
                values = new ArrayList<>(Arrays.asList(String.valueOf(meeting.getMeetingID()), meeting.getStarttime(), meeting.getEndtime(), meeting.getDescription(), meeting.getPlace(), String.valueOf(meeting.getRoom()), String.valueOf(meeting.getMinCapacity()), meeting.getCreator(), meeting.getLastUpdated()));

                mySQLQuery.update(table, fields, values, map, null);
                break;
            case "ExternalUser":
                ExternalUser externalUser = (ExternalUser) changedObject;

                map = new HashMap<>(); map.put("email", String.valueOf(externalUser.getEmail())); map.put("meetingID", String.valueOf(externalUser.getMeetingID()));
                fields = new ArrayList<>(Arrays.asList("email", "meetingID", "name", "phonenumber"));
                values = new ArrayList<>(Arrays.asList(externalUser.getEmail(), String.valueOf(externalUser.getMeetingID()), externalUser.getName(), externalUser.getPhoneNumber()));

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
                fields = new ArrayList<>(Arrays.asList("username", "meetingID", "coming", "alarm", "lastSeen"));
                values = new ArrayList<>(Arrays.asList(meetingInvite.getUsername(), String.valueOf(meetingInvite.getMeetingID()), String.valueOf(meetingInvite.isComingAsTinyInt()), meetingInvite.getAlarm(), meetingInvite.getLastSeen()));

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

    public void delete(Object changedObject){
        HashMap<String, String> map;

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

                map = new HashMap<>(); map.put("roomNumber", String.valueOf(room.getRoomNumber()));

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

    public DataStorage getDataStorageFromDatabase(){
        Users users = new Users(getAllUsersFromDatabase());
        Groups groups = new Groups(getAllGroupsFromDatabase());
        Rooms rooms = new Rooms(getAllRoomsFromDatabase());
        Meetings meetings = new Meetings(getAllMeetingsFromDatabase());
        ExternalUsers externalUsers = new ExternalUsers(getAllExternalUsersFromDatabase());
        GroupMemberships groupMemberships = new GroupMemberships(getAllGroupMembershipsFromDatabase());
        MeetingInvites meetingInvites = new MeetingInvites(getAllMeetingInvitesFromDatabase());
        MeetingAdmins meetingAdmins = new MeetingAdmins(getAllMeetingAdminsFromDatabase());

        System.out.println("Number of users: " + users.getUsers().size());
        System.out.println("Number of groups: " + groups.getGroups().size());
        System.out.println("Number of rooms: " + rooms.getRooms().size());
        System.out.println("Number of meetings: " + meetings.getMeetings().size());
        System.out.println("Number of external users: " + externalUsers.getExternalUsers().size());
        System.out.println("Number of group memberships: " + groupMemberships.getGroupMemberships().size());
        System.out.println("Number of meeting invites: " + meetingInvites.getMeetingInvites().size());
        System.out.println("Number of meeting admins: " + meetingAdmins.getMeetingAdmins().size());
        System.out.println();

        return new DataStorage(users, groups, rooms, meetings, externalUsers, groupMemberships, meetingInvites, meetingAdmins);
    }

    public ArrayList<User> getAllUsersFromDatabase(){
        ArrayList<User> users = new ArrayList<>();
        ArrayList<HashMap<String, String>> users_raw = mySQLQuery.getAllRows("User");

        for(HashMap<String, String> user_raw : users_raw){
            String username = user_raw.get("username");
            String password = user_raw.get("password");
            String name = user_raw.get("name");
            String email = user_raw.get("email");
            String phonenumber = user_raw.get("phonenumber");

            users.add(new User(username, password, name, email, phonenumber));
        }

        return users;
    }

    public ArrayList<Group> getAllGroupsFromDatabase(){
        ArrayList<Group> groups = new ArrayList<>();
        ArrayList<HashMap<String, String>> groups_raw = mySQLQuery.getAllRows("Group");

        for(HashMap<String, String> group_raw : groups_raw){
            int groupID = Integer.parseInt(group_raw.get("groupID"));
            int supergroup = group_raw.get("supergroup") == null ? -1 : Integer.parseInt(group_raw.get("supergroup"));

            groups.add(new Group(groupID, supergroup));
        }

        return groups;
    }

    public ArrayList<Room> getAllRoomsFromDatabase(){
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<HashMap<String, String>> rooms_raw = mySQLQuery.getAllRows("Room");

        for(HashMap<String, String> room_raw : rooms_raw){
            int roomNumber = Integer.parseInt(room_raw.get("roomNumber"));
            int capacity = Integer.parseInt(room_raw.get("capacity"));

            rooms.add(new Room(roomNumber, capacity));
        }

        return rooms;
    }

    public ArrayList<Meeting> getAllMeetingsFromDatabase(){
        ArrayList<Meeting> meetings = new ArrayList<>();
        ArrayList<HashMap<String, String>> meetings_raw = mySQLQuery.getAllRows("Meeting");

        for(HashMap<String, String> meeting_raw : meetings_raw){
            int meetingID = Integer.parseInt(meeting_raw.get("meetingID"));
            String startTime = meeting_raw.get("startTime").substring(0, meeting_raw.get("startTime").length()-2).replace(' ', 'T');
            String endTime = meeting_raw.get("endTime").substring(0, meeting_raw.get("endTime").length()-2).replace(' ', 'T');
            String description = meeting_raw.get("description") == null ? "" : meeting_raw.get("description");
            String place = meeting_raw.get("place") == null ? "" : meeting_raw.get("place");
            int room = meeting_raw.get("room") == null ? -1 : Integer.parseInt(meeting_raw.get("room"));
            int minCapacity = meeting_raw.get("minCapacity") == null ? -1 : Integer.parseInt(meeting_raw.get("minCapacity"));
            String creator = meeting_raw.get("creator");
            String lastUpdated = meeting_raw.get("lastUpdated") == null ? null : meeting_raw.get("lastUpdated").substring(0, meeting_raw.get("lastUpdated").length()-2).replace(' ', 'T');

            meetings.add(new Meeting(meetingID, startTime, endTime, description, place, room, minCapacity, creator, lastUpdated));
        }

        return meetings;
    }

    public ArrayList<ExternalUser> getAllExternalUsersFromDatabase(){
        ArrayList<ExternalUser> externalUsers = new ArrayList<>();
        ArrayList<HashMap<String, String>> externalUsers_raw = mySQLQuery.getAllRows("ExternalUser");

        for(HashMap<String, String> externalUser_raw : externalUsers_raw){
            String email = externalUser_raw.get("email");
            int meetingID = Integer.parseInt(externalUser_raw.get("meetingID"));
            String name = externalUser_raw.get("name") == null ? "" : externalUser_raw.get("name");
            String phonenumber = externalUser_raw.get("phonenumber") == null ? "" : externalUser_raw.get("phonenumber");

            externalUsers.add(new ExternalUser(email, meetingID, name, phonenumber));
        }

        return externalUsers;
    }

    public ArrayList<GroupMembership> getAllGroupMembershipsFromDatabase(){
        ArrayList<GroupMembership> groupMemberships = new ArrayList<>();
        ArrayList<HashMap<String, String>> groupMemberships_raw = mySQLQuery.getAllRows("GroupMembership");

        for(HashMap<String, String> groupMembership_raw : groupMemberships_raw){
            String username = groupMembership_raw.get("username");
            int groupID = Integer.parseInt(groupMembership_raw.get("groupID"));

            groupMemberships.add(new GroupMembership(username, groupID));
        }

        return groupMemberships;
    }

    public ArrayList<MeetingInvite> getAllMeetingInvitesFromDatabase(){
        ArrayList<MeetingInvite> meetingInvites = new ArrayList<>();
        ArrayList<HashMap<String, String>> meetingInvites_raw = mySQLQuery.getAllRows("MeetingInvite");

        for(HashMap<String, String> meetingInvite_raw : meetingInvites_raw){
            int meetingID = Integer.parseInt(meetingInvite_raw.get("meetingID"));
            String username = meetingInvite_raw.get("username");

            meetingInvites.add(new MeetingInvite(meetingID, username));
        }

        return meetingInvites;
    }

    public ArrayList<MeetingAdmin> getAllMeetingAdminsFromDatabase(){
        ArrayList<MeetingAdmin> meetingAdmins = new ArrayList<>();
        ArrayList<HashMap<String, String>> meetingAdmins_raw = mySQLQuery.getAllRows("MeetingAdmin");

        for(HashMap<String, String> meetingAdmin_raw : meetingAdmins_raw){
            int meetingID = Integer.parseInt(meetingAdmin_raw.get("meetingID"));
            String username = meetingAdmin_raw.get("username");

            meetingAdmins.add(new MeetingAdmin(meetingID, username));
        }

        return meetingAdmins;
    }
}
