package com.example.inos.echome.presenters.question_feed;

import android.util.Log;

import com.example.inos.echome.network.User.question_feed.IQuestionFeedNetwork;
import com.example.inos.echome.network.User.question_feed.QuestionFeedNetwork;
import com.example.inos.echome.ui.ILoginView;
import com.example.inos.echome.ui.question_feed.IQuestionFeedView;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public class QuestionFeedPresenter implements IQuestionFeedPresenter {

    IQuestionFeedView mQuestionFeedView;
    IQuestionFeedNetwork mQuestionFeedNetwork;

    public QuestionFeedPresenter(IQuestionFeedView view) {
        this.mQuestionFeedView = view;
        this.mQuestionFeedNetwork = new QuestionFeedNetwork(this);
    }

    @Override
    public void getQuestions() {
        // TODO: get questions from network
    }

    @Override
    public void answered(String answer) {
        Log.d("Vinit", "Answered with "+answer);
    }


}
