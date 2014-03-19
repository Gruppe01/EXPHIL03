package persistence;

import persistence.data.*;

public class DataStorage {
    private Users users;
    private Groups groups;
    private Rooms rooms;
    private Meetings meetings;
    private ExternalUsers externalUsers;
    private GroupMemberships groupMemberships;
    private MeetingInvites meetingInvites;
    private MeetingAdmins meetingAdmins;

    public DataStorage(Users users, Groups groups, Rooms rooms, Meetings meetings, ExternalUsers externalUsers, GroupMemberships groupMemberships, MeetingInvites meetingInvites, MeetingAdmins meetingAdmins) {
        this.users = users;
        this.groups = groups;
        this.rooms = rooms;
        this.meetings = meetings;
        this.externalUsers = externalUsers;
        this.groupMemberships = groupMemberships;
        this.meetingInvites = meetingInvites;
        this.meetingAdmins = meetingAdmins;
    }

    public DataStorage() {
        this.users = new Users();
        this.groups = new Groups();
        this.rooms = new Rooms();
        this.meetings = new Meetings();
        this.externalUsers = new ExternalUsers();
        this.groupMemberships = new GroupMemberships();
        this.meetingInvites = new MeetingInvites();
        this.meetingAdmins = new MeetingAdmins();
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    public Meetings getMeetings() {
        return meetings;
    }

    public void setMeetings(Meetings meetings) {
        this.meetings = meetings;
    }

    public ExternalUsers getExternalUsers() {
        return externalUsers;
    }

    public void setExternalUsers(ExternalUsers externalUsers) {
        this.externalUsers = externalUsers;
    }

    public GroupMemberships getGroupMemberships() {
        return groupMemberships;
    }

    public void setGroupMemberships(GroupMemberships groupMemberships) {
        this.groupMemberships = groupMemberships;
    }

    public MeetingInvites getMeetingInvites() {
        return meetingInvites;
    }

    public void setMeetingInvites(MeetingInvites meetingInvites) {
        this.meetingInvites = meetingInvites;
    }

    public MeetingAdmins getMeetingAdmins() {
        return meetingAdmins;
    }

    public void setMeetingAdmins(MeetingAdmins meetingAdmins) {
        this.meetingAdmins = meetingAdmins;
    }
}
