package com.example.inos.echome.network.question_feed;

import java.util.Map;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public interface IQuestionFeedNetwork {
    void getQuestion(String userEmail);

    void updateAnswer(String ans, String uuid);

    Map<String, Integer> getQuestionStats(String username, int key);
}
