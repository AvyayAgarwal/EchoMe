package com.example.inos.echome.presenters.question_feed;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public interface IQuestionFeedPresenter {
    // USED BY VIEW
    void getQuestions();

    // USED BY MODEL
    void answered(String answer); // TODO: Change to get the valid params
}
