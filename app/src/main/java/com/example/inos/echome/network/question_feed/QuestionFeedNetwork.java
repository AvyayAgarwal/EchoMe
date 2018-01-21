package com.example.inos.echome.network.question_feed;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.inos.echome.models.QuestionAnswer;
import com.example.inos.echome.models.QuestionStat;
import com.example.inos.echome.presenters.question_feed.IQuestionFeedPresenter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import okhttp3.ResponseBody;
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
    public Map<String, Integer> getQuestionStats(String username, int key) {

//        Type myObjectListType = new TypeToken<Map<String, Integer>>(){}.getType();
//        gsonBuilder.registerTypeAdapter(myObjectListType, new MyObjectListDeserializer());
        Retrofit r = new Retrofit.Builder()
                .baseUrl("https://azhng.lib.id/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<ResponseBody> getStats = qService.getQuestionResults(username, key);

        try {
            String respString = getStats.execute().body().string();
            Type type = new TypeToken<Map<String, Integer>>(){}.getType();
            Map<String, Integer> myMap = new Gson().fromJson(respString, type);
            return myMap;
        } catch (IOException e) {

        }



        return new Map<String, Integer>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object o) {
                return false;
            }

            @Override
            public boolean containsValue(Object o) {
                return false;
            }

            @Override
            public Integer get(Object o) {
                return null;
            }

            @Override
            public Integer put(String s, Integer integer) {
                return null;
            }

            @Override
            public Integer remove(Object o) {
                return null;
            }

            @Override
            public void putAll(@NonNull Map<? extends String, ? extends Integer> map) {

            }

            @Override
            public void clear() {

            }

            @NonNull
            @Override
            public Set<String> keySet() {
                return null;
            }

            @NonNull
            @Override
            public Collection<Integer> values() {
                return null;
            }

            @NonNull
            @Override
            public Set<Entry<String, Integer>> entrySet() {
                return null;
            }
        };


//        getStats.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.d("VINIT", "Response received");
//                Log.d("VINIT", response.body().toString()); // TODO: send back to the UI
//                if (response.isSuccessful()) {
//                    String respString = "";
//                    try {
//                        respString = response.body().string();
//                    } catch (IOException e) {
//                        Log.d("VINIT", "Had IO exception");
//                    }
//                    Log.d("VINIT", respString);
//                    Type type = new TypeToken<Map<String, Integer>>(){}.getType();
//                    Map<String, Integer> myMap = new Gson().fromJson(respString, type);
//
//                    ResponseBody answers = response.body();
//                    Log.d("LOOPING", " NOW");
//                    for (Map.Entry<String, Integer> en : myMap.entrySet()) {
//                        Log.d("VINIT", en.getKey()+" --> "+en.getValue());
//                    }
//
//                    Log.d("DONE", "LOOP");
//                }
//                Log.d("IF UN", response.raw().toString());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d("VINITERR", t.getMessage());
//            }
//        });
    }


}
