package com.example.inos.echome.network.User;

import com.example.inos.echome.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public interface UserService {

    @GET("users/{username}") // TODO: add correct endpoint
    Call<User> getUser(@Path("username") String username);
}
