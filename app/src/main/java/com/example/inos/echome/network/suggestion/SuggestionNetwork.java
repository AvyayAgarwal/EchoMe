package com.example.inos.echome.network.suggestion;

import android.util.Log;

import com.example.inos.echome.models.QuestionAnswer;
import com.example.inos.echome.models.User;
import com.example.inos.echome.models.Users;
import com.example.inos.echome.presenters.suggestion.ISuggestionPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Azhng on 2018-01-21.
 */

public class SuggestionNetwork implements ISuggestionNetwork {

    ISuggestionPresenter mSuggestionPresenter;

    Retrofit r = new Retrofit.Builder()
            .baseUrl("https://azhng.lib.id/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    SuggestionService sService = r.create(SuggestionService.class);


    public SuggestionNetwork(ISuggestionPresenter mSuggestionPresenter) {
        this.mSuggestionPresenter = mSuggestionPresenter;
    }

    @Override
    public void getUsers() {


        Log.d("AZ", "BEFORE CALL");
        Call<List<User>> qaCall = sService.getUsers(); // TODO: add params
        qaCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                try {
                    Log.d("AZ", "BEFORE CALL");
                    if (response.isSuccessful()) {
                        Log.d("AZ", "Successful Response...");
                        Users users = new Users(response.body());
//                        for (User u : users.getUsers()) {
//                            Log.d("AZ", u.getPassword());
//                        }
                        mSuggestionPresenter.notifyUsersRecieved(users);
                    } else {
                        Log.d("AZ", "response NOT success");
                    }
                } catch (Exception e) {
                    Log.d("AZ", e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("Az", t.getMessage());
            }
        });

    }
}
