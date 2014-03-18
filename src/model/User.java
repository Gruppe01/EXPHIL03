package model;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class User {
    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9_.-]*$";
    public static final String NAME_PATTERN = "^[a-zA-Z- ]*$";
    public static final String PHONE_PATTERN = "^\\+?[\\(?\\+?(\\d{2})\\)?[ -]?(\\d{0,})[- ]?(\\d{0,})[- ]?(\\d{0,})]{8,16}$";

    private String username;
    private String password;
    private String email;
    private String phonenumber;
    private String name;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        try {
            InternetAddress verifyEmail = new InternetAddress(email);
            verifyEmail.validate();

            this.email = email;
        } catch (AddressException ex) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) throws IllegalArgumentException {
        if(!phonenumber.matches(PHONE_PATTERN)) throw new IllegalArgumentException("Invalid phone number.");

        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if(!name.matches(NAME_PATTERN)) throw new IllegalArgumentException("Invalid name.");

        this.name = name;
    }

    public String toString() {
        return getName();
    }
}
