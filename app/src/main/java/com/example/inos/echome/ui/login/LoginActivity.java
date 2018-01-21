package com.example.inos.echome.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inos.echome.R;
import com.example.inos.echome.presenters.login.ILoginPresenter;
import com.example.inos.echome.presenters.login.LoginPresenter;
import com.example.inos.echome.ui.question_feed.QuestionFeedActivity;

import mehdi.sakout.fancybuttons.FancyButton;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    FancyButton trueLogin;

    ILoginPresenter presenter;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);

        trueLogin = (FancyButton) findViewById(R.id.login_btn);

        trueLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText passEdit = (EditText) findViewById(R.id.password_et);
                EditText usrEdit = (EditText) findViewById(R.id.username_et);
                username = usrEdit.getText().toString();
                presenter.checkLoginWith(username, passEdit.getText().toString());
        }
    });



    }

    @Override
    public void updateLoginStatus(boolean isLoginSuccessful) {
        // TODO: send user to next activity if LOGIN SUCCESS

        if(isLoginSuccessful) {
            //direct to home page
            //QuestionFeedActivity
            Intent i = new Intent(LoginActivity.this, QuestionFeedActivity.class);
            i.putExtra("username", username);
            startActivity(i);
            this.finish();
        } else {
            Toast.makeText(this, "Invalid User/Password combination", Toast.LENGTH_SHORT).show();
        }

    }
}
