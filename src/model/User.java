package model;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.Serializable;

public class User implements Serializable {
    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9_.-]*$";
    public static final String NAME_PATTERN = "^[a-zA-Z- æøåÆØÅ]*$";
    public static final String PHONE_PATTERN = "^\\+?[\\(?\\+?(\\d{2})\\)?[ -]?(\\d{0,})[- ]?(\\d{0,})[- ]?(\\d{0,})]{8,16}$";

    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String name;

    public User(String username, String password, String name, String email, String phoneNumber) throws IllegalArgumentException{
        setUsername(username);
        setPassword(password);
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws IllegalArgumentException {
        if(!username.matches(USERNAME_PATTERN)) throw new IllegalArgumentException("Invalid username.\nCan only use alphanumeric characters, '.', '_' and '-'");

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalArgumentException {
        if(password.length() < 6 || password.length() > 32) throw new IllegalArgumentException("Password must be between 6 and 32 characters long");

        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        if(validEmail(email)) this.email = email;
        else throw new IllegalArgumentException("Invalid email");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phonenumber) throws IllegalArgumentException {
        if(!phonenumber.matches(PHONE_PATTERN)) throw new IllegalArgumentException("Invalid phone number");

        this.phoneNumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if(!name.matches(NAME_PATTERN) || name.length() < 3 || name.length() > 45) throw new IllegalArgumentException("Invalid name");

        this.name = name;
    }

    public static boolean validEmail(String email){
        try {
            InternetAddress verifyEmail = new InternetAddress(email);
            verifyEmail.validate();

            return true;
        } catch (AddressException ex) {
            return false;
        }
    }

    public String toString() {
        return getName();
    }
}
