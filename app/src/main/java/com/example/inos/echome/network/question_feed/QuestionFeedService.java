package com.example.inos.echome.network.question_feed;

import com.example.inos.echome.models.QuestionAnswer;
import com.example.inos.echome.models.QuestionStat;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public interface QuestionFeedService {


    @GET("echo@dev/question_instance/generate_random")
    Call<QuestionAnswer> getQuestion(@Query("user_email") String userEmail);

    @GET("echo@dev/question_instance/update_answer")
    Call<String> postUpdatedAnswer(@Query("uuid") String uuid, @Query("answer") String ans);

    @GET("echo@dev/users/qstats")
    Call<ResponseBody> getQuestionResults(@Query("email") String email_username, @Query("qbase_key") int key);


}
