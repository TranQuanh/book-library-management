package Model;

import java.util.Scanner;

public abstract class User {
    private int ID;
    private String name;
    private String email;
    private String userName;
    private String password;
    private String phoneNumber;
    private String location;

    //type: 0 ==> Client
    //      1 ==> Admin
    //      2 ==> DeletedClientAccount
    //      3 ==> DeletedAdminAccount
    public User() {}
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public abstract void showList(Database database, Scanner s);
}
