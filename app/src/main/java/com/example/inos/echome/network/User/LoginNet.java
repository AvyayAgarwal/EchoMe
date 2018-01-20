package com.example.inos.echome.network.User;

import com.example.inos.echome.presenters.ILoginPresenter;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public class LoginNet implements ILoginNetwork {

    ILoginPresenter presenter;

    public LoginNet(ILoginPresenter p) {
        this.presenter = p;
    }

    @Override
    public void checkUserWith(String username, String password) {
        // TODO: check the username
    }
}
