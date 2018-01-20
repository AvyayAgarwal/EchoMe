package com.example.inos.echome.network.question_feed;

import com.example.inos.echome.models.QuestionAnswer;
import com.example.inos.echome.presenters.question_feed.IQuestionFeedPresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public class QuestionFeedNetwork implements IQuestionFeedNetwork {

    IQuestionFeedPresenter mQuestionFeedPresenter;

    public QuestionFeedNetwork(IQuestionFeedPresenter qfp) {
        mQuestionFeedPresenter = qfp;
    }

    @Override
    public void getQuestion(String userEmail, int qbaseKey) {
        // TODO: get questions from server
        Retrofit r = new Retrofit.Builder()
                .baseUrl("https://azhng.lib.id/echo@dev/")
                .build();

        QuestionFeedService qService = r.create(QuestionFeedService.class);

        Call<ArrayList<QuestionAnswer>> qaCall = qService.getQuestion(userEmail, qbaseKey); // TODO: add params
        qaCall.enqueue(new Callback<ArrayList<QuestionAnswer>>() {
            @Override
            public void onResponse(Call<ArrayList<QuestionAnswer>> call, Response<ArrayList<QuestionAnswer>> response) {
                // TODO: parse the response

            }

            @Override
            public void onFailure(Call<ArrayList<QuestionAnswer>> call, Throwable t) {

            }
        });

    }
}
