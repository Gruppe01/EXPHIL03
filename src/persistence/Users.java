package persistence;

import model.User;

import java.util.ArrayList;

//User
public class Users {
    private ArrayList<UserObject> userObjects;

    public Users(ArrayList<UserObject> userObjects){
        this.userObjects = userObjects;
    }

    public Users(){
        this.userObjects = null;
    }

    public ArrayList<UserObject> getUserObjects(){
        return userObjects;
    }

    public void addUserObject(UserObject userObject){
        userObjects.add(userObject);
    }

    public void deleteUserObject(UserObject userObject){
        userObjects.remove(userObject);
    }

    public ArrayList<User> getUsers(){
        ArrayList<User> meetings = new ArrayList<>();

        for(UserObject userObject : userObjects){
            meetings.add(userObject.getUser());
        }

        return meetings;
    }

    class UserObject{
        private User user;

        public User getUser(){
            return user;
        }
    }
}
