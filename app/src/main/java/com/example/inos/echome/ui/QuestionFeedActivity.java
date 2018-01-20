package com.example.inos.echome.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.inos.echome.R;
import com.example.inos.echome.models.QuestionAnswer;

import java.util.ArrayList;

public class QuestionFeedActivity extends AppCompatActivity {

    private RecyclerView mQaRecView;
    private QAAdapter mAdapter;
    private ArrayList<QuestionAnswer> qaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_feed);

        qaList = new ArrayList<>(0);
        qaList.add(new QuestionAnswer("How old am I?", "10 years old"));
        qaList.add(new QuestionAnswer("How beautiful is this person", "Very very Beautiful"));


        mQaRecView = (RecyclerView) findViewById(R.id.qa_recview);
        mAdapter = new QAAdapter(qaList);
        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false);
        mQaRecView.setAdapter(mAdapter);
        mQaRecView.setLayoutManager(llm);





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
            holder.answerTextView.setText(qaList.get(position).getAnswer());
        }

        @Override
        public int getItemCount() {
            return qaList.size();
        }



        // -------------------------- View Holder --------------------------
        public class CustomViewHolder extends RecyclerView.ViewHolder {
            public TextView questionTextView;
            public TextView answerTextView;

            public CustomViewHolder(View itemView) {
                super(itemView);
                questionTextView = (TextView) itemView.findViewById(R.id.question_tv);
                answerTextView = (TextView) itemView.findViewById(R.id.answer_tv);

            }
        }
        // -----------------------------------------------------------------
    }
}
