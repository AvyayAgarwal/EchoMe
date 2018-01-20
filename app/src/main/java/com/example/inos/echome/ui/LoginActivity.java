package com.example.inos.echome.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inos.echome.R;
import com.example.inos.echome.presenters.ILoginPresenter;
import com.example.inos.echome.presenters.LoginPresenter;
import com.example.inos.echome.ui.question_feed.QuestionFeedActivity;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    Button mStubLoginBtn;
    Button trueLogin;

    ILoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mStubLoginBtn = (Button) findViewById(R.id.stub_login_btn);
        trueLogin = (Button) findViewById(R.id.login_btn);
        presenter = new LoginPresenter(this);


        mStubLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, QuestionFeedActivity.class));
            }
        });

        trueLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText passEdit = (EditText) findViewById(R.id.password);
                EditText usrEdit = (EditText) findViewById(R.id.emailID);
                String _emailTxt = usrEdit.getText().toString();
                String _passwordTxt = passEdit.getText().toString();
                presenter.checkLoginWith(_emailTxt, _passwordTxt);
                //Add id from text box in XML whenever user input area is created
            //startActivity(new Intent(LoginActivity.this, QuestionFeedActivity.class));
        }
    });



    }

    @Override
    public void updateLoginStatus(boolean isLoginSuccessful) {
        // TODO: send user to next activity if LOGIN SUCCESS

        if(isLoginSuccessful) {
            //direct to home page
            //QuestionFeedActivity
            startActivity(new Intent(LoginActivity.this, QuestionFeedActivity.class));
        } else {
            Toast.makeText(this, "Invalid Email/Password combination", Toast.LENGTH_SHORT).show();
        }

    }
}
