package com.example.inos.echome.models;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public class QuestionAnswer {
    private String mQuestion;
    private String mAnswer;


    public QuestionAnswer(String q, String a) {
        this.mQuestion = q;
        this.mAnswer = a;
    }


    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }
}
