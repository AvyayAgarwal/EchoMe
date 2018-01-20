package com.example.inos.echome.presenters;

import com.example.inos.echome.ui.ILoginView;

/**
 * Created by vinitsoni on 2018-01-19.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView mLoginView;
    private ILoginNetwork mLoginNetwork;

    public LoginPresenter(ILoginView l) {
        mLoginView = l;
        mLoginNetwork = new LoginNetwork(this);
    }

    @Override
    public void checkLoginWith(String username, String password) {
        // TODO: check network layer if creds correct)
            // mLoginView.updateLoginStatus(username == mLoginView.username && password == mLoginView.password? True : False)
            mLoginNetwork.checkUserWith(username, password);
        }

    }

    @Override
    void updateLoginStatus(boolean isSuccess) {

    }


}
