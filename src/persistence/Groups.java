package persistence;

import model.Group;
import model.User;

import java.util.ArrayList;

//Group, GroupMembership
public class Groups {
    private ArrayList<GroupObject> groupObjects;

    class GroupObject{
        private Group group;
        private Group superGroup;
        private ArrayList<User> groupMembers;

        public GroupObject(Group group, Group superGroup, ArrayList<User> groupMembers){
            this.group = group;
            this.superGroup = superGroup;
            this.groupMembers = groupMembers;
        }

        public int getGroupID(){
            return group.getGruopID();
        }
    }
}
