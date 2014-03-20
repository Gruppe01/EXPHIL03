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
}
