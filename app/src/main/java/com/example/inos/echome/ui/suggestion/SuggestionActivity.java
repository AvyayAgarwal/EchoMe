package com.example.inos.echome.ui.suggestion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.inos.echome.R;
import com.example.inos.echome.models.User;
import com.example.inos.echome.models.Users;
import com.example.inos.echome.network.suggestion.SuggestionService;
import com.example.inos.echome.presenters.suggestion.ISuggestionPresenter;
import com.example.inos.echome.presenters.suggestion.SuggestionPresenter;

import java.util.ArrayList;
import java.util.List;

public class SuggestionActivity extends AppCompatActivity implements ISuggestionActivity {

    private Spinner person_1;
    private Spinner person_2;

    private EditText et_person_1;
    private EditText et_person_2;

    private Button confirm_btn;

    private ISuggestionPresenter mSuggestionPresenter;

    private List<String> users;
    private ArrayAdapter<String> userAdatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        mSuggestionPresenter = new SuggestionPresenter(this);

        person_1 = (Spinner) findViewById(R.id.s_person_1);
        person_2 = (Spinner) findViewById(R.id.s_person_2);
        et_person_1 = (EditText) findViewById(R.id.s_person_1_display);
        et_person_2 = (EditText) findViewById(R.id.s_person_2_display);
        confirm_btn = (Button) findViewById(R.id.s_confirm_btn);

        users = new ArrayList<>();
        userAdatper = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, users);
        userAdatper.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        person_1.setAdapter(userAdatper);
        person_2.setAdapter(userAdatper);

        person_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = (String) person_1.getItemAtPosition(i);
                Log.d("AZ", value);
                et_person_1.setText(value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
//                String value = (String) person_1.getItemAtPosition(0);
//                et_person_1.setText(value);
            }
        });

        person_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = (String) person_2.getItemAtPosition(i);
                Log.d("AZ", "FUK");
                et_person_2.setText(value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
//                String value = (String) person_2.getItemAtPosition(0);
//                et_person_2.setText(value);
            }
        });

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SuggestionActivity.this, "Hint Sent ;)",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    public void setUsers(Users users) {
        this.users.clear();
        for (User u : users.getUsers()) {
            this.users.add(u.getFirstName());
        }
        this.userAdatper.notifyDataSetChanged();
    }

    public void setPerson_1(Spinner person_1) {
        this.person_1 = person_1;
    }

    public void setPerson_2(Spinner person_2) {
        this.person_2 = person_2;
    }

    public void setConfirm_btn(Button confirm_btn) {
        this.confirm_btn = confirm_btn;
    }

    public void setmSuggestionPresenter(ISuggestionPresenter mSuggestionPresenter) {
        this.mSuggestionPresenter = mSuggestionPresenter;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public void setUserAdatper(ArrayAdapter<String> userAdatper) {
        this.userAdatper = userAdatper;
    }

    public Spinner getPerson_1() {
        return person_1;
    }

    public Spinner getPerson_2() {
        return person_2;
    }

    public Button getConfirm_btn() {
        return confirm_btn;
    }

    public ISuggestionPresenter getmSuggestionPresenter() {
        return mSuggestionPresenter;
    }

    public List<String> getUsers() {
        return users;
    }

    public ArrayAdapter<String> getUserAdatper() {
        return userAdatper;
    }
}
