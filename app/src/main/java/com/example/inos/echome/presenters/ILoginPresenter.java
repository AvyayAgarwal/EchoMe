package com.example.inos.echome.presenters;

/**
 * Created by avyayagarwal on 2018-01-20.
 */

public interface ILoginPresenter {

    // USED BY VIEW
    void checkLoginWith(String username, String password);


    // USED BY MODEL/NETWORK
    void updateLoginStatus(boolean isSuccess);
}
