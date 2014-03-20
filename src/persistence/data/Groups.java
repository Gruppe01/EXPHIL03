package persistence.data;

import model.Group;

import java.io.Serializable;
import java.util.ArrayList;

public class Groups implements Serializable {
    private ArrayList<Group> groups;

    public Groups(ArrayList<Group> groups){
        this.groups = groups;
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

    public int getGroupIndexByGroupID(int groupID){
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
        int i = getGroupIndexByGroupID(group.getGroupID());

        groups.remove(i);
        groups.add(i, group);
    }
}
