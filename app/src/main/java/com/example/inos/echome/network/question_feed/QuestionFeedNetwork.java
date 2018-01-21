package com.example.inos.echome.network.question_feed;

import android.util.Log;

import com.example.inos.echome.models.QuestionAnswer;
import com.example.inos.echome.presenters.question_feed.IQuestionFeedPresenter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public class QuestionFeedNetwork implements IQuestionFeedNetwork {

    IQuestionFeedPresenter mQuestionFeedPresenter;

    public QuestionFeedNetwork(IQuestionFeedPresenter qfp) {
        mQuestionFeedPresenter = qfp;
    }

    @Override
    public void getQuestion(String userEmail) {
        // TODO: get questions from server

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        Retrofit r = new Retrofit.Builder()
                .baseUrl("https://azhng.lib.id/echo@dev")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        QuestionFeedService qService = r.create(QuestionFeedService.class);

        Call<QuestionAnswer> qaCall = qService.getQuestion(userEmail); // TODO: add params
        qaCall.enqueue(new Callback<QuestionAnswer>() {
            @Override
            public void onResponse(Call<QuestionAnswer> call, Response<QuestionAnswer> response) {
                // TODO: parse the response
                if (response.isSuccessful()) {
                    Log.d("VINIT", "Successful Response...");
                    QuestionAnswer qa = response.body();
                }

            }

            @Override
            public void onFailure(Call<QuestionAnswer> call, Throwable t) {

            }
        });

    }
}
