package com.example.inos.echome.network.question_feed;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public interface IQuestionFeedNetwork {
    void getQuestion(String userEmail);

    void updateAnswer(String ans, String uuid);

    void getQuestionStats(String username, int key);
}
