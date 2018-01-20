package com.example.inos.echome.presenters.question_feed;

import com.example.inos.echome.network.User.question_feed.IQuestionFeedNetwork;
import com.example.inos.echome.network.User.question_feed.QuestionFeedNetwork;
import com.example.inos.echome.ui.ILoginView;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public class QuestionFeedPresenter implements IQuestionFeedPresenter {

    ILoginView view;
    IQuestionFeedNetwork mQuestionFeedNetwork;

    public QuestionFeedPresenter(ILoginView view) {
        this.view = view;
        this.mQuestionFeedNetwork = new QuestionFeedNetwork(this);
    }

    @Override
    public void getQuestions() {
        // TODO: get questions from network
    }
}
