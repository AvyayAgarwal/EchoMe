package com.example.inos.echome.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.inos.echome.R;
import com.example.inos.echome.presenters.ILoginPresenter;
import com.example.inos.echome.presenters.LoginPresenter;
import com.example.inos.echome.ui.QuestionFeed.QuestionFeedActivity;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    Button mStubLoginBtn;
    Button trueLogin;

    ILoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mStubLoginBtn = (Button) findViewById(R.id.stub_login_btn);
        presenter = new LoginPresenter(this);


        mStubLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, QuestionFeedActivity.class));
            }
        });


    }

    @Override
    public void updateLoginStatus(boolean isLoginSuccessful) {
        // TODO: send user to next activity if LOGIN SUCCESS

        if(isLoginSuccessful) {

        } else {

        }

    }
}
