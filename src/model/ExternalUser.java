package model;

public class ExternalUser extends BaseUser {
    public ExternalUser(String name, String email, String phonenumber){
        setName(name);
        setEmail(email);
        setPhonenumber(phonenumber);
    }
}
