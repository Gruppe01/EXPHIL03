package model;

import persistence.mysql.MySQLQuery;

public class Group {
    private int groupID;
    private Group superGroup;

    public Group(Group superGroup){
        groupID = new MySQLQuery().getNextID("Group");
        this.superGroup = superGroup;
    }

    public Group(){
        groupID = new MySQLQuery().getNextID("Group");
        this.superGroup = null;
    }

    public int getGruopID() {
        return groupID;
    }

    public Group getSuperGroup() {
        return superGroup;
    }

    public void setSuperGroup(Group superGroup) {
        this.superGroup = superGroup;
    }
}
