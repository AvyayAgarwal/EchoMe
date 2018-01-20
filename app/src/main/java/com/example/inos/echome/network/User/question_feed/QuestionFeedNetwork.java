package com.example.inos.echome.network.User.question_feed;

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
    public void getQuestions() {
        // TODO: get questions from server
        Retrofit r = new Retrofit.Builder()
                .baseUrl("<BASE URL GOES HERE>")
                .build();

        QuestionFeedService qService = r.create(QuestionFeedService.class);

        Call<ArrayList<QuestionAnswer>> qaCall = qService.getQuestions();
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
