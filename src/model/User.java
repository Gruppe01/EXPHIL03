package model;

public class User extends BaseUser {
    private String username;
    private String password;

    public User(String username, String password, String name, String email, String phonenumber) throws IllegalArgumentException{
        setUsername(username);
        setPassword(password);
        setName(name);
        setEmail(email);
        setPhonenumber(phonenumber);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws IllegalArgumentException {
        if(!username.matches(USERNAME_PATTERN)) throw new IllegalArgumentException("Invalid username.\nCan only use alphanumeric characters, '.', '_' and '-'.");

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalArgumentException {
        if(password.length() < 6 || password.length() > 32) throw new IllegalArgumentException("Password must be between 6 and 32 characters long.");

        this.password = password;
    }
}
