package persistence;

import persistence.data.*;

import java.io.Serializable;

public class DataStorage implements Serializable{
    private GroupMemberships groupMemberships;
    private MeetingInvites meetingInvites;
    private MeetingAdmins meetingAdmins;
    private Users users;
    private Groups groups;
    private Rooms rooms;
    private Meetings meetings;
    private ExternalUsers externalUsers;

    public DataStorage(Users users, Groups groups, Rooms rooms, Meetings meetings, ExternalUsers externalUsers, GroupMemberships groupMemberships, MeetingInvites meetingInvites, MeetingAdmins meetingAdmins) {
        this.groupMemberships = groupMemberships;
        this.meetingInvites = meetingInvites;
        this.meetingAdmins = meetingAdmins;
        this.users = users;
        this.groups = groups;
        this.rooms = rooms;
        this.meetings = meetings;
        this.externalUsers = externalUsers;
    }

    public DataStorage() {
        this.groupMemberships = new GroupMemberships();
        this.meetingInvites = new MeetingInvites();
        this.meetingAdmins = new MeetingAdmins();
        this.users = new Users();
        this.groups = new Groups();
        this.rooms = new Rooms();
        this.meetings = new Meetings();
        this.externalUsers = new ExternalUsers();
    }

    public Users users() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Groups groups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public Rooms rooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    public Meetings meetings() {
        return meetings;
    }

    public void setMeetings(Meetings meetings) {
        this.meetings = meetings;
    }

    public ExternalUsers externalUsers() {
        return externalUsers;
    }

    public void setExternalUsers(ExternalUsers externalUsers) {
        this.externalUsers = externalUsers;
    }

    public GroupMemberships groupMemberships() {
        return groupMemberships;
    }

    public void setGroupMemberships(GroupMemberships groupMemberships) {
        this.groupMemberships = groupMemberships;
    }

    public MeetingInvites meetingInvites() {
        return meetingInvites;
    }

    public void setMeetingInvites(MeetingInvites meetingInvites) {
        this.meetingInvites = meetingInvites;
    }

    public MeetingAdmins meetingAdmins() {
        return meetingAdmins;
    }

    public void setMeetingAdmins(MeetingAdmins meetingAdmins) {
        this.meetingAdmins = meetingAdmins;
    }
}
