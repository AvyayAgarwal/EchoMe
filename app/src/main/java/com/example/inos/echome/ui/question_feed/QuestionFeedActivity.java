package com.example.inos.echome.ui.question_feed;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inos.echome.R;
import com.example.inos.echome.models.QuestionAnswer;
import com.example.inos.echome.presenters.question_feed.IQuestionFeedPresenter;
import com.example.inos.echome.presenters.question_feed.QuestionFeedPresenter;
import com.example.inos.echome.ui.login.LoginActivity;
import com.example.inos.echome.ui.suggestion.SuggestionActivity;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

public class QuestionFeedActivity extends AppCompatActivity implements IQuestionFeedView {

    private String username;

    private RecyclerView mQaRecView;
    private FancyButton nextBtn;

    private FancyButton suggestionBtn;

    private QAAdapter mAdapter;
    private ArrayList<QuestionAnswer> qaList;

    private IQuestionFeedPresenter mQuestionFeedPresenter;

    private GraphView mResultsGraph;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_feed);
        this.username = "test1" ;// savedInstanceState.getString("username", "test1");
        mQuestionFeedPresenter = new QuestionFeedPresenter(this, username); // TODO: DO NOT REMOVE NOTE: MUST BE INIT BEFORE REC VIEW

        nextBtn = (FancyButton) findViewById(R.id.next_btn);
        suggestionBtn = (FancyButton) findViewById(R.id.qf_suggestion);

        SnapHelper snapHelper = new LinearSnapHelper();
        mQaRecView = (RecyclerView) findViewById(R.id.qa_recview);
        mAdapter = new QAAdapter(mQuestionFeedPresenter);
        final LinearLayoutManager llm = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false);

        mQuestionFeedPresenter.setQAListAdapter(mAdapter);
        mQuestionFeedPresenter.setInitialList();

        final RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(getBaseContext()) {
            @Override protected int getHorizontalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };

        mQaRecView.setAdapter(mAdapter);
        mQaRecView.setLayoutManager(llm);
        snapHelper.attachToRecyclerView(mQaRecView);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smoothScroller.setTargetPosition(llm.findFirstVisibleItemPosition()+1);
                llm.startSmoothScroll(smoothScroller);
            }
        });


        suggestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(QuestionFeedActivity.this, SuggestionActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void getQuestions() {
        // TODO: get the presenter to get the questions
    }


    // ------------------------------------ Recycler View ----------------------------------------------
    public class QAAdapter extends RecyclerView.Adapter<QuestionFeedActivity.CustomViewHolder> {
        private IQuestionFeedPresenter mQuestionFeedPresenter;

        QAAdapter(IQuestionFeedPresenter questionFeedPresenter) {
            mQuestionFeedPresenter = questionFeedPresenter;
        }

        @Override
        public QuestionFeedActivity.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.question_answer_layout, parent, false);

            return new QuestionFeedActivity.CustomViewHolder(itemView, 0);
        }

        @Override
        public void onBindViewHolder(QuestionFeedActivity.CustomViewHolder holder, int position) {
            holder.questionTextView.setText(mQuestionFeedPresenter.getQAAt(position).getQuestion());
            holder.addAnswerOptions(mQuestionFeedPresenter.getQAAt(position).answers);
            holder.questionNumber = position;

        }

        @Override
        public int getItemCount() {
            return mQuestionFeedPresenter.getQAListSize();
        }
    }
    // ------------------------------------------------------------------------------------

    // -------------------------- View Holder --------------------------
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView questionTextView;
        public RadioGroup ansListRg;
        public EasyFlipView questionCardFlipView;
        public int questionNumber;


        public CustomViewHolder(View itemView, int qNum) {
            super(itemView);
            this.questionNumber = qNum;
            ansListRg = (RadioGroup) itemView.findViewById(R.id.ans_list_rg);
            questionTextView = (TextView) itemView.findViewById(R.id.question_tv);
            questionCardFlipView = (EasyFlipView) itemView.findViewById(R.id.question_card_fv);
            final View qaCard = itemView.findViewById(R.id.qa_card_layout);
            final View graphCard = itemView.findViewById(R.id.graph_layout);
            graphCard.setVisibility(View.INVISIBLE);
            qaCard.setVisibility(View.VISIBLE);
            ansListRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                    mQuestionFeedPresenter.answered(((RadioButton)findViewById(i)).getText().toString(), questionNumber);
                    mQuestionFeedPresenter.getQuestionStats(questionNumber);
                    questionCardFlipView.flipTheView();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            graphCard.setVisibility(View.VISIBLE);
                            qaCard.setVisibility(View.INVISIBLE);
                            mResultsGraph = (GraphView) findViewById(R.id.results_gv);
                            // TODO: get all datapoints for given question about me
                            BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[]{
                                    new DataPoint(0, 0),
                                    new DataPoint(1, 1),
                                    new DataPoint(2, 2),
                                    new DataPoint(3, 3),
                                    new DataPoint(4, 4),
                                    new DataPoint(5, 5),
                            });
                            mResultsGraph.addSeries(series);
                            series.setSpacing(50);
                        }
                    }, 1000);
                }
            });
        }

        public void addAnswerOptions(String[] ansList) {
            ansListRg.removeAllViews();
            for (int i = 0; i < ansList.length; i++) {
                RadioButton rb = new RadioButton(getBaseContext());
                boolean isChecked = false;
                if (ansListRg.getChildAt(i) != null) {
                    isChecked = ((RadioButton)ansListRg.getChildAt(i)).isChecked();
                    ansListRg.removeViewAt(i);

                }
                rb.setChecked(isChecked);
                rb.setText(ansList[i]);
                this.ansListRg.addView(rb);
            }

        }

    }
    // -----------------------------------------------------------------

}
