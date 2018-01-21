package com.example.inos.echome.network.question_feed;

import com.example.inos.echome.models.QuestionAnswer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public interface QuestionFeedService {


    @GET("echo@dev//question_instance/generate_random")
    Call<QuestionAnswer> getQuestion(@Query("user_email") String userEmail);


}
