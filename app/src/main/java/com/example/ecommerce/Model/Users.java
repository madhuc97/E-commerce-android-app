package com.example.ecommerce.Model;

public class Users {
    private String username, firstname, lastname, EmailID, phonenumber, password;

    public Users()
    {

    }

    public Users(String username, String firstname, String lastname, String emailID, String phonenumber, String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.EmailID = emailID;
        this.phonenumber = phonenumber;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {this.phonenumber = phonenumber;  }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
