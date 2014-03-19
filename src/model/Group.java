package model;

import persistence.mysql.MySQLQuery;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {
    private final int groupID;
    private int superGroup;
    private ArrayList<User> members;

    public Group(int groupID, int superGroup) throws IllegalArgumentException {
        this.groupID = groupID;
        this.superGroup = superGroup;
    }

    public Group(int superGroup, ArrayList<User> members) throws IllegalArgumentException {
        this.groupID = new MySQLQuery().getNextID("Group");
        this.superGroup = superGroup;
        this.members = members;
    }

    public Group(int superGroup) throws IllegalArgumentException {
        this.groupID = new MySQLQuery().getNextID("Group");
        this.superGroup = superGroup;
        this.members = new ArrayList<>();
    }

    public Group(ArrayList<User> members) throws IllegalArgumentException {
        this.groupID = new MySQLQuery().getNextID("Group");
        this.superGroup = -1;
        this.members = members;
    }

    public Group() throws IllegalArgumentException {
        this.groupID = new MySQLQuery().getNextID("Group");
        this.superGroup = -1;
        this.members = new ArrayList<>();
    }

    public int getGroupID() {
        return groupID;
    }

    public int getSuperGroup() {
        return superGroup;
    }

    public void setSuperGroup(int superGroup) {
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
