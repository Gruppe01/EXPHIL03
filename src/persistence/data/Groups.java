package persistence.data;

import model.Group;

import java.util.ArrayList;

//Group, GroupMembership
public class Groups {
    private ArrayList<Group> groups;

    public Groups(ArrayList<Group> groups){
        populate(groups);
    }

    public Groups(){
        this.groups = new ArrayList<>();
    }

    public ArrayList<Group> getGroups(){
        return groups;
    }

    public Group getGroupByID(int groupID){
        for(Group group : groups){
            if(groupID == group.getGroupID()) return group;
        }

        return null;
    }

    public int getGroupIndex(int groupID){
        for(Group group : groups){
            if(groupID == group.getGroupID()) return groups.indexOf(group);
        }

        return -1;
    }

    public void addGroup(Group group){
        groups.add(group);
    }

    public void removeGroup(Group group){
        groups.remove(group);
    }

    public void updateGroup(Group group){
        int i = getGroupIndex(group.getGroupID());

        groups.remove(i);
        groups.add(i, group);
    }

    public void populate(ArrayList<Group> groups){
        for(Group group : groups){
            groups.add(group);
        }
    }
}
