package com.example.inos.echome.ui.question_feed;

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

import com.example.inos.echome.R;
import com.example.inos.echome.models.QuestionAnswer;
import com.example.inos.echome.presenters.question_feed.IQuestionFeedPresenter;
import com.example.inos.echome.presenters.question_feed.QuestionFeedPresenter;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

public class QuestionFeedActivity extends AppCompatActivity implements IQuestionFeedView {

    private RecyclerView mQaRecView;
    private FancyButton nextBtn;

    private QAAdapter mAdapter;
    private ArrayList<QuestionAnswer> qaList;

    private IQuestionFeedPresenter mQuestionFeedPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_feed);

        mQuestionFeedPresenter = new QuestionFeedPresenter(this); // TODO: DO NOT REMOVE NOTE: MUST BE INIT BEFORE REC VIEW

        nextBtn = (FancyButton) findViewById(R.id.next_btn);
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

            return new QuestionFeedActivity.CustomViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(QuestionFeedActivity.CustomViewHolder holder, int position) {
            holder.questionTextView.setText(mQuestionFeedPresenter.getQAAt(position).getQuestion());
            // TODO: change to for loop
            ArrayList<String> ansList = new ArrayList<>(0);
            ansList.add(mQuestionFeedPresenter.getQAAt(position).getAnswer());
            holder.addAnswerOption(ansList);

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


        public CustomViewHolder(View itemView) {
            super(itemView);
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
                    mQuestionFeedPresenter.answered(((RadioButton)findViewById(i)).getText().toString());
                    questionCardFlipView.flipTheView();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            graphCard.setVisibility(View.VISIBLE);
                            qaCard.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);
                }
            });
        }

        public void addAnswerOption(ArrayList<String> ansList) {
            for (int i = 0; i < ansList.size(); i++) {
                RadioButton rb = new RadioButton(getBaseContext());
                rb.setText(ansList.get(i));
                this.ansListRg.addView(rb);
            }

        }

    }
    // -----------------------------------------------------------------

}
