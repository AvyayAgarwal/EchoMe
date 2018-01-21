package com.example.inos.echome.network.login;

import android.util.Log;

import com.example.inos.echome.models.User;
import com.example.inos.echome.presenters.login.ILoginPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        if (/*username.equals("vinso") && */password.equals("echome")) {
            mLoginPresenter.updateLoginStatus(true);
        } else {
            mLoginPresenter.updateLoginStatus(false);
        }

//        // TODO: check the username
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://azhng.lib.id/echo@dev/")
//                .build();
//
//        UserService userService = retrofit.create(UserService.class);
//        Call<User> user = userService.getUser(username);
//        user.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                // TODO: parse response
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//        });
//        boolean isSuccess = true;
//        mLoginPresenter.updateLoginStatus(isSuccess);
    }
}
