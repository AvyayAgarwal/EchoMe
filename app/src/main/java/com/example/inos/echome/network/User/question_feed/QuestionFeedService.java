package com.example.inos.echome.network.User.question_feed;

import com.example.inos.echome.models.QuestionAnswer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public interface QuestionFeedService {


    @GET("questions")
    Call<ArrayList<QuestionAnswer>> getQuestions();
}
