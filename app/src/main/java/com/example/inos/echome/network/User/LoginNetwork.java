package com.example.inos.echome.network.User;

import com.example.inos.echome.presenters.ILoginPresenter;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public class LoginNetwork implements ILoginNetwork {

    ILoginPresenter mLoginPresenter;

    public LoginNetwork(ILoginPresenter p) {
        this.mLoginPresenter = p;
    }

    @Override
    public void checkUserWith(String username, String password) {
        // TODO: check the username
        boolean isSuccess = true;
        mLoginPresenter.updateLoginStatus(isSuccess);
    }
}
