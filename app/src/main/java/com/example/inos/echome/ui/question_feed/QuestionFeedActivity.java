package com.example.inos.echome.ui.question_feed;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inos.echome.R;
import com.example.inos.echome.models.QuestionAnswer;
import com.example.inos.echome.presenters.question_feed.IQuestionFeedPresenter;
import com.example.inos.echome.presenters.question_feed.QuestionFeedPresenter;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.ArrayList;

public class QuestionFeedActivity extends AppCompatActivity implements IQuestionFeedView {

    private RecyclerView mQaRecView;
    private QAAdapter mAdapter;
    private ArrayList<QuestionAnswer> qaList;

    private IQuestionFeedPresenter mQuestionFeedPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_feed);

        mQuestionFeedPresenter = new QuestionFeedPresenter(this);


        qaList = new ArrayList<>(0);
        qaList.add(new QuestionAnswer("How old am I?", "10 years old"));
        qaList.add(new QuestionAnswer("How beautiful is this person", "Very very Beautiful"));


        SnapHelper snapHelper = new LinearSnapHelper();
        mQaRecView = (RecyclerView) findViewById(R.id.qa_recview);
        snapHelper.attachToRecyclerView(mQaRecView);
        mAdapter = new QAAdapter(qaList);
        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false);
        mQaRecView.setAdapter(mAdapter);
        mQaRecView.setLayoutManager(llm);





    }

    @Override
    public void getQuestions() {
        // TODO: get the presenter to get the questions
    }


    public class QAAdapter extends RecyclerView.Adapter<QAAdapter.CustomViewHolder> {


        private ArrayList<QuestionAnswer> qaList;

        QAAdapter(ArrayList<QuestionAnswer> l) {
            this.qaList = l;
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.question_answer_layout, parent, false);

            return new CustomViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            holder.questionTextView.setText(qaList.get(position).getQuestion());
            // TODO: change to for loop
            ArrayList<String> ansList = new ArrayList<>(0);
            ansList.add(qaList.get(position).getAnswer());
            holder.addAnswerOption(ansList);

        }

        @Override
        public int getItemCount() {
            return qaList.size();
        }



        // -------------------------- View Holder --------------------------
        public class CustomViewHolder extends RecyclerView.ViewHolder {
            public TextView questionTextView;
            public ArrayList<View> answersTvList;
            public RadioGroup ansListRg;
            public EasyFlipView questionCardFlipView;


            public CustomViewHolder(View itemView) {
                super(itemView);
                ansListRg = (RadioGroup) itemView.findViewById(R.id.ans_list_rg);
                questionTextView = (TextView) itemView.findViewById(R.id.question_tv);
                questionCardFlipView = (EasyFlipView) itemView.findViewById(R.id.question_card_fv);

                ansListRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                        mQuestionFeedPresenter.answered(((RadioButton)findViewById(i)).getText().toString());
                        questionCardFlipView.flipTheView();
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
}
