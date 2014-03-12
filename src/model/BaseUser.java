package model;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class BaseUser {
    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9_.-]*$";
    public static final String NAME_PATTERN = "^[a-zA-Z- ]*$";
    public static final String PHONE_PATTERN = "^\\+?[\\(?\\+?(\\d{2})\\)?[ -]?(\\d{0,})[- ]?(\\d{0,})[- ]?(\\d{0,})]{8,16}$";

    private String name;
    private String email;
    private String phonenumber;

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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) throws IllegalArgumentException {
        if(!phonenumber.matches(PHONE_PATTERN)) throw new IllegalArgumentException("Invalid phone number.");

        this.phonenumber = phonenumber;
    }

    public String toString() {
        return getName();
    }
}
