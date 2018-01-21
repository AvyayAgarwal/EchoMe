package com.example.inos.echome.presenters.question_feed;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inos.echome.R;
import com.example.inos.echome.models.QuestionAnswer;
import com.example.inos.echome.network.question_feed.IQuestionFeedNetwork;
import com.example.inos.echome.network.question_feed.QuestionFeedNetwork;
import com.example.inos.echome.ui.question_feed.IQuestionFeedView;
import com.example.inos.echome.ui.question_feed.QuestionFeedActivity;

import java.util.ArrayList;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public class QuestionFeedPresenter implements IQuestionFeedPresenter {

    IQuestionFeedView mQuestionFeedView;
    IQuestionFeedNetwork mQuestionFeedNetwork;
    QuestionFeedActivity.QAAdapter mListAdapter;

    ArrayList<QuestionAnswer> mQAList;

    public QuestionFeedPresenter(IQuestionFeedView view) {
        this.mQuestionFeedView = view;
        this.mQuestionFeedNetwork = new QuestionFeedNetwork(this);
        mQAList = new ArrayList<>(0);
    }

    @Override
    public void setInitialList() {
        // TODO: fetch a list from network layer
        notifyQuestionReceived(new QuestionAnswer("What is your name?", "Vinit Soni"));
        notifyQuestionReceived(new QuestionAnswer("What happened?", "Holy shit"));
        notifyQuestionReceived(new QuestionAnswer("On a scale of 1 to 10, how friendly is this person? On a scale of 1 to 10, how friendly is this person?", "adsad"));
        notifyQuestionReceived(new QuestionAnswer("checkssdsadsa", "Holy shit again"));
        for (int i = 0; i < 5; i++) {
            mQuestionFeedNetwork.getQuestion("vinso"); // TODO: change to dynamically set username
        }
    }

    @Override
    public void getQuestions() {
        // TODO: get questions from network
    }

    @Override
    public QuestionAnswer getQAAt(int position) {
        return mQAList.get(position);
    }

    @Override
    public int getQAListSize() {
        return this.mQAList.size();
    }

    @Override
    public void answered(String answer) {
        Log.d("Vinit", "Answered with "+answer);
    }

    @Override
    public void notifyQuestionReceived(QuestionAnswer qa) {
        this.mQAList.add(qa);
        this.mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void setQAListAdapter(QuestionFeedActivity.QAAdapter adapter) {
        this.mListAdapter = adapter;
    }
}
