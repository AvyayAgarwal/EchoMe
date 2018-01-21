package com.example.inos.echome.models;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public class QuestionAnswer {
    public String question;
    public String mAnswer;
    public int key;
    public String[] answers;
    public String uuid;
    public String crush_email;
    public String user_email;
    public String final_answer;




    public QuestionAnswer(String q, String a) {
        this.question = q;
        this.mAnswer = a;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String mQuestion) {
        this.question = mQuestion;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }
}
