package com.example.inos.echome.network.suggestion;

import com.example.inos.echome.models.QuestionAnswer;
import com.example.inos.echome.models.User;
import com.example.inos.echome.models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Azhng on 2018-01-21.
 */

public interface SuggestionService {


    @GET("echo@dev/users/all")
    Call<List<User>> getUsers();


}
