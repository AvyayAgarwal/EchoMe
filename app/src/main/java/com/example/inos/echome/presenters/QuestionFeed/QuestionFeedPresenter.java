package com.example.inos.echome.presenters.QuestionFeed;

import com.example.inos.echome.ui.ILoginView;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public class QuestionFeedPresenter implements IQuestionFeedPresenter {

    ILoginView view;

    public QuestionFeedPresenter(ILoginView view) {
        this.view = view;
    }

    @Override
    public void getQuestions() {
        // TODO: get questions from network
    }
}
