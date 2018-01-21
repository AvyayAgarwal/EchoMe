package com.example.inos.echome.presenters.question_feed;

import android.support.v7.widget.RecyclerView;

import com.example.inos.echome.models.QuestionAnswer;
import com.example.inos.echome.ui.question_feed.QuestionFeedActivity;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Map;

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
    BarGraphSeries<DataPoint> getQuestionStats(int qKey);
    void addMoreQuestions();
    void removeQuestion();

    // USED BY MODEL
    void answered(String answer, int questionNumber); // TODO: Change to get the valid params
    void notifyQuestionReceived(QuestionAnswer qa);

    void notifyAnsStatsReceived(Map<String, Integer> ansStats);
}
