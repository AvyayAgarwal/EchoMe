package com.example.inos.echome.presenters;

import com.example.inos.echome.ui.ILoginView;

/**
 * Created by vinitsoni on 2018-01-19.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView mLoginView;

    public LoginPresenter(ILoginView l) {
        mLoginView = l;
    }

    @Override
    public void checkLoginWith(String username, String password) {
        // TODO: check network layer if creds correct

    }


}
