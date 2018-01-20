package com.example.inos.echome.network.User;

import com.example.inos.echome.models.User;
import com.example.inos.echome.presenters.ILoginPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("<BASE URL GOES HERE>")
                .build();

        UserService userService = retrofit.create(UserService.class);
        Call<User> user = userService.getUser(username);
        user.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // TODO: parse response
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
