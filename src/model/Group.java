package model;

import persistence.mysql.MySQLQuery;

import java.util.ArrayList;

public class Group {
    private int groupID;
    private Group superGroup;
    private ArrayList<User> members;

    public Group(Group superGroup, ArrayList<User> members){
        groupID = new MySQLQuery().getNextID("Group");
        this.superGroup = superGroup;
        this.members = members;
    }

    public Group(){
        groupID = new MySQLQuery().getNextID("Group");
        this.superGroup = null;
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

    public void addMember(User member) {
        members.add(member);
    }

    public void deleteMember(User member) {
        members.remove(member);
    }
}
