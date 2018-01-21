package com.example.inos.echome.presenters.question_feed;

import android.provider.ContactsContract;
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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public class QuestionFeedPresenter implements IQuestionFeedPresenter {

    String username = "test1";

    IQuestionFeedView mQuestionFeedView;
    IQuestionFeedNetwork mQuestionFeedNetwork;
    QuestionFeedActivity.QAAdapter mListAdapter;

    ArrayList<QuestionAnswer> mQAList;

    public QuestionFeedPresenter(IQuestionFeedView view, String username) {
        this.mQuestionFeedView = view;
        this.mQuestionFeedNetwork = new QuestionFeedNetwork(this);
        this.username = username;
        mQAList = new ArrayList<>(0);
    }

    @Override
    public void setInitialList() {
        // TODO: fetch a list from network layer
        for (int i = 0; i < 10; i++) {
            mQuestionFeedNetwork.getQuestion(username); // TODO: change to dynamically set username
        }
    }

    @Override
    public BarGraphSeries<DataPoint> getQuestionStats(int qKey) {
        Map<String, Integer> m = this.mQuestionFeedNetwork.getQuestionStats(username, mQAList.get(qKey).key);
        DataPoint[] arr = new DataPoint[m.size()+1];
        int count = 1;
        arr[0] = new DataPoint(0, 0);
        for (Map.Entry<String, Integer> en : m.entrySet()) {
            try {
                Log.d("VINIT", en.getKey()+" --> "+en.getValue());
                arr[count] = new DataPoint(count, en.getValue());
                count++;
            } catch (Exception e) {

            }
        }
        return new BarGraphSeries<DataPoint>(arr);
    }

    @Override
    public void addMoreQuestions() {
        for (int i = 0; i < 5; i++) {
            mQuestionFeedNetwork.getQuestion(username); // TODO: change to dynamically set username
        }
    }

    @Override
    public void removeQuestion() {
        if (mQAList.size() > 0) {
            mQAList.remove(0);
            if (mQAList.size() == 0) {
                mQuestionFeedView.showEndgame();
            }
        }
        Log.d("SIZE", mQAList.size()+"");
        mListAdapter.notifyDataSetChanged();
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
    public void answered(String answer, int questionNumber) {
        Log.d("Vinit", "Answered with "+answer);
        String hyphenatedAns = answer.replaceAll(" ", "-");
        String uuid = mQAList.get(questionNumber).uuid;
        mQuestionFeedNetwork.updateAnswer(hyphenatedAns, uuid);
    }

    @Override
    public void notifyQuestionReceived(QuestionAnswer qa) {
        this.mQAList.add(qa);
        this.mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyAnsStatsReceived(Map<String, Integer> ansStats) {

    }

    @Override
    public void setQAListAdapter(QuestionFeedActivity.QAAdapter adapter) {
        this.mListAdapter = adapter;
    }



}
