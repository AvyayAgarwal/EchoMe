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
import retrofit2.http.Path;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public class QuestionFeedNetwork implements IQuestionFeedNetwork {

    IQuestionFeedPresenter mQuestionFeedPresenter;
    private Retrofit r = new Retrofit.Builder()
            .baseUrl("https://azhng.lib.id/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private QuestionFeedService qService = r.create(QuestionFeedService.class);

    public QuestionFeedNetwork(IQuestionFeedPresenter qfp) {
        mQuestionFeedPresenter = qfp;
    }

    @Override
    public void getQuestion(String userEmail) {
        // TODO: get questions from server

        Log.d("VINIT", "BEFORE CALL");
        Call<QuestionAnswer> qaCall = qService.getQuestion(userEmail); // TODO: add params
        qaCall.enqueue(new Callback<QuestionAnswer>() {
            @Override
            public void onResponse(Call<QuestionAnswer> call, Response<QuestionAnswer> response) {
                // TODO: parse the response
                try {
                    Log.d("VINIT", "BEFORE CALL");
                    if (response.isSuccessful()) {
                        Log.d("VINIT", "Successful Response...");
                        QuestionAnswer qa = response.body();
                        mQuestionFeedPresenter.notifyQuestionReceived(qa);
                        for (int i = 0 ; i < qa.answers.length; i++) {
                            Log.d("VINIT", qa.answers[i]);
                        }

                    } else {
                        Log.d("VINIT", "response NOT success");
                    }
                } catch (Exception e) {
                    Log.d("VINIT", e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<QuestionAnswer> call, Throwable t) {
                Log.d("VINIT", t.getMessage());
            }
        });

    }

    @Override
    public void updateAnswer(String ans, String uuid) {
        Call<String> updateAns = qService.postUpdatedAnswer(uuid, ans);
        updateAns.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    Log.d("VINIT", "BEFORE CALL");
                    if (response.isSuccessful()) {
                        Log.d("VINIT", "Successful Response...");
                        String returnVal = response.body();
                    } else {
                        Log.d("VINIT", "response NOT success");
                    }
                } catch (Exception e) {
                    Log.d("VINIT", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("VINIT", "complete failure, "+t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getQuestionStats(String username, int key) {
        Call<String> getStats = qService.getQuestionResults(username, key);
        getStats.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("VINIT", "Response received");
                if (response.isSuccessful()) {
                    Log.d("VINIT", response.body().toString()); // TODO: send back to the UI
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


}
