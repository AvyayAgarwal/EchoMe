package com.example.inos.echome.models;

import java.util.List;

/**
 * Created by Azhng on 2018-01-21.
 */

public class Users {
    List<User> users;

    public Users(List<User> users) {

        this.users = users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {

        return users;
    }

}
