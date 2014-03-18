package persistence;

import model.User;

import java.util.ArrayList;

//User
public class Users {
    private ArrayList<User> users;

    public Users(ArrayList<User> users){
        populate(users);
    }

    public Users(){
        this.users = new ArrayList<>();
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public User getUserByUserName(String userName){
        for(User user : users){
            if(userName.equals(user.getUsername())) return user;
        }

        return null;
    }

    public int getUserIndex(String userName){
        for(User user : users){
            if(userName.equals(user.getUsername())) return users.indexOf(user);
        }

        return -1;
    }

    public void addUser(User user){
        users.add(user);
    }

    public void removeUser(User user){
        users.remove(user);
    }

    public void updateUser(User user){
        int i = getUserIndex(user.getUsername());

        users.remove(i);
        users.add(i, user);
    }

    public void populate(ArrayList<User> users){
        for(User user : users){
            users.add(user);
        }
    }
}
