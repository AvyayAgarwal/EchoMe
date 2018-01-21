package com.example.inos.echome.models;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public class User {
    private String username;
    private String password;

    public User(String u, String p) {
        username = u;
        password = p;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
