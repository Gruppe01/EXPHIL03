package persistence.data;

import model.GroupMembership;

import java.io.Serializable;
import java.util.ArrayList;

public class GroupMemberships implements Serializable {
    private ArrayList<GroupMembership> groupMemberships;

    public GroupMemberships(ArrayList<GroupMembership> groupMemberships){
        this.groupMemberships = groupMemberships;
    }

    public GroupMemberships(){
        this.groupMemberships = new ArrayList<>();
    }

    public ArrayList<GroupMembership> getGroupMemberships(){
        return groupMemberships;
    }

    public GroupMembership getGroupMembershipByUsernameAndGroupID(int groupMembershipID, String username){
        for(GroupMembership groupMembership : groupMemberships){
            if(groupMembershipID == groupMembership.getGroupID() && username.equals(groupMembership.getUsername())) return groupMembership;
        }

        return null;
    }

    public int getGroupMembershipIndexByUsernameAndGroupID(int groupMembershipID, String username){
        for(GroupMembership groupMembership : groupMemberships){
            if(groupMembershipID == groupMembership.getGroupID() && username.equals(groupMembership.getUsername())) return groupMemberships.indexOf(groupMembership);
        }

        return -1;
    }

    public void addGroupMembership(GroupMembership groupMembership){
        groupMemberships.add(groupMembership);
    }

    public void removeGroupMembership(GroupMembership groupMembership){
        groupMemberships.remove(groupMembership);
    }

    public void updateGroupMembership(GroupMembership groupMembership){
        int i = getGroupMembershipIndexByUsernameAndGroupID(groupMembership.getGroupID(), groupMembership.getUsername());

        groupMemberships.remove(i);
        groupMemberships.add(i, groupMembership);
    }

    public ArrayList<GroupMembership> getGroupMembershipsByGroupID(int groupID){
        ArrayList<GroupMembership> groupMembershipsReturn = new ArrayList<>();

        for(GroupMembership groupMembership : groupMemberships){
            if(groupMembership.getGroupID() == groupID) groupMembershipsReturn.add(groupMembership);
        }

        return groupMembershipsReturn;
    }

    public ArrayList<String> getGroupsMembersByGroupID(int groupID){
        ArrayList<String> groupMembers = new ArrayList<>();
        ArrayList<GroupMembership> groupMemberships = getGroupMembershipsByGroupID(groupID);

        for(GroupMembership groupMembership : groupMemberships){
            groupMembers.add(groupMembership.getUsername());
        }

        return groupMembers;
    }

    public void setGroupMembers(int groupID, ArrayList<String> users){
        for(String username : users){
            addGroupMember(groupID, username);
        }
    }

    public void addGroupMember(int groupID, String username) throws IllegalArgumentException{
        if(getGroupMembershipByUsernameAndGroupID(groupID, username) != null) throw new IllegalArgumentException("User " + username + " already member of the group.");
        else groupMemberships.add(new GroupMembership(username, groupID));
    }

    public void deleteGroupMember(int groupID, String username) throws IllegalArgumentException {
        if(getGroupMembershipByUsernameAndGroupID(groupID, username) == null) throw new IllegalArgumentException("User " + username + "not member of the group.");
        else groupMemberships.add(new GroupMembership(username, groupID));
    }
}
