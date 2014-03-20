package persistence.data;

import model.User;

import java.io.Serializable;
import java.util.*;

public class Users implements Serializable {
    private ArrayList<User> users;

    public Users(ArrayList<User> users){
        this.users = users;
    }

    public Users(){
        this.users = new ArrayList<>();
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public User getUserByUsername(String userName){
        for(User user : users){
            if(userName.equals(user.getUsername())) return user;
        }

        return null;
    }

    public int getUserIndexByUsername(String username){
        for(User user : users){
            if(username.equals(user.getUsername())) return users.indexOf(user);
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
        int i = getUserIndexByUsername(user.getUsername());

        users.remove(i);
        users.add(i, user);
    }

    public boolean checkLogin(String username, String password){
        User user = getUserByUsername(username);

        if(user == null) throw new IllegalArgumentException("User not found");

        return user.getPassword().equals(password);
    }

    public ArrayList<String> getAllUsers(){
        ArrayList<String> usersReturn = new ArrayList<>();

        for(User user : users){
            usersReturn.add(user.toString());
        }

        return usersReturn;
    }
}
