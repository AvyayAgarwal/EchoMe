package com.example.inos.echome.models;

/**
 * Created by vinitsoni on 2018-01-20.
 */

public class QuestionAnswer {
    private String question;
    private String mAnswer;
    private int key;
    private String[] answers;
    private String uuid;
    private String crush_email;
    private String user_email;
    private String final_answer;
    



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
