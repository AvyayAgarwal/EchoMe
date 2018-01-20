package com.example.inos.echome.presenters.question_feed;

import android.support.v7.widget.RecyclerView;

import com.example.inos.echome.models.QuestionAnswer;
import com.example.inos.echome.ui.question_feed.QuestionFeedActivity;

import java.util.ArrayList;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public interface IQuestionFeedPresenter {
    // USED BY VIEW
    void getQuestions();
    QuestionAnswer getQAAt(int position);
    int getQAListSize();
    void setQAListAdapter(QuestionFeedActivity.QAAdapter adapter);
    void setInitialList();

    // USED BY MODEL
    void answered(String answer); // TODO: Change to get the valid params
    void notifyQuestionReceived(QuestionAnswer qa);
}
