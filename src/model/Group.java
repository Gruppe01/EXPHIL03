package model;

import persistence.mysql.MySQLQuery;

import java.util.ArrayList;

public class Group {
    private final int groupID;
    private Group superGroup;
    private ArrayList<User> members;

    public Group(Group superGroup, ArrayList<User> members) throws IllegalArgumentException {
        groupID = new MySQLQuery().getNextID("Group");
        this.superGroup = superGroup;
        this.members = members;
    }

    public Group(Group superGroup) throws IllegalArgumentException {
        groupID = new MySQLQuery().getNextID("Group");
        this.superGroup = superGroup;
        this.members = new ArrayList<>();
    }

    public Group(ArrayList<User> members) throws IllegalArgumentException {
        groupID = new MySQLQuery().getNextID("Group");
        this.superGroup = null;
        this.members = members;
    }

    public Group() throws IllegalArgumentException {
        groupID = new MySQLQuery().getNextID("Group");
        this.superGroup = null;
        this.members = new ArrayList<>();
    }

    public int getGroupID() {
        return groupID;
    }

    public Group getSuperGroup() {
        return superGroup;
    }

    public void setSuperGroup(Group superGroup) {
        this.superGroup = superGroup;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members){
        this.members = members;
    }

    public void addMember(User member) throws IllegalArgumentException{
        if(!members.contains(member)) members.add(member);
        else throw new IllegalArgumentException("User already member of the group.");
    }

    public void deleteMember(User member) throws IllegalArgumentException {
        if(members.contains(member)) members.remove(member);
        else throw new IllegalArgumentException("User is not in group group.");
    }
}
