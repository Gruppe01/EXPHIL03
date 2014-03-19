package model;

import java.io.Serializable;

public class GroupMembership implements Serializable {
    private String username;
    private int groupID;

    public GroupMembership(String username, int groupID) {
        this.username = username;
        this.groupID = groupID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
}
