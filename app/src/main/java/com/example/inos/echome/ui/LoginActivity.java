package com.example.inos.echome.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.inos.echome.R;

public class LoginActivity extends AppCompatActivity {

    Button mStubLoginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mStubLoginBtn = (Button) findViewById(R.id.stub_login_btn);

        mStubLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, QuestionFeedActivity.class));
            }
        });
    }
}
