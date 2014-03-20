package model;

import persistence.mysql.MySQLQuery;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {
    private final int groupID;
    private int superGroup;

    public Group(int groupID, int superGroup) throws IllegalArgumentException {
        this.groupID = groupID;
        this.superGroup = superGroup;
    }

    public Group(int superGroup) throws IllegalArgumentException {
        this.groupID = new MySQLQuery().getNextID("Group");
        this.superGroup = superGroup;
    }

    public Group() throws IllegalArgumentException {
        this.groupID = new MySQLQuery().getNextID("Group");
        this.superGroup = -1;
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
}
