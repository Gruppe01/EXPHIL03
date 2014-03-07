package model;

import javax.mail.internet.*;

public class User {
    public static String USERNAME_PATTERN = "^[a-zA-Z0-9_.-]*$";
    public static String NAME_PATTERN = "^[a-zA-Z- ]*$";
    public static String PHONE_PATTERN = "^\\+{0,1}[\\(?\\+?(\\d{2})\\)?[ -]?(\\d{0,})[- ]?(\\d{0,})[- ]?(\\d{0,})]{8,16}$";

    private String username;
    private String password;
    private String name;
    private String email;
    private String cellphonenumber;

    public User(String username, String password, String name, String email, String cellphonenumber) throws IllegalArgumentException{
        setUsername(username);
        setPassword(password);
        setName(name);
        setEmail(email);
        setCellphonenumber(cellphonenumber);
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

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if(!name.matches(NAME_PATTERN)) throw new IllegalArgumentException("Invalid name.");

        this.name = name;
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

    public String getCellphonenumber() {
        return cellphonenumber;
    }

    public void setCellphonenumber(String cellphonenumber) throws IllegalArgumentException {
        if(!cellphonenumber.matches(PHONE_PATTERN)) throw new IllegalArgumentException("Invalid phone number.");

        this.cellphonenumber = cellphonenumber;
    }

    public String toString() {
        return getName();
    }
}
