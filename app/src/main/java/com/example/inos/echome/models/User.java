package com.example.inos.echome.models;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public class User {
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public User(String u, String p) {
        email = u;
        password = p;
        firstName = "";
        lastName = "";
    }

    public User(String u, String p, String f, String l) {
        email = u;
        password = p;
        firstName = f;
        lastName = l;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
