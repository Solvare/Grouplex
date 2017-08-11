package com.example.solvare.grouplex.startup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.solvare.grouplex.startup.SharedPrefManager;
import com.example.solvare.grouplex.R;

import static com.example.solvare.grouplex.startup.LoginActivity.LOGGEDIN_SHARED_PREF;
import static com.example.solvare.grouplex.startup.LoginActivity.SHARED_PREF_NAME;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{

    //public static final String SHARED_PREF_NAME = "myloginapp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        finish();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,MyGroupsActivity.class));
            return;
        }

        Button buttonLogin = (Button)findViewById(R.id.button_chooseLogin);
        buttonLogin.setOnClickListener(this);

        Button buttonSignup = (Button)findViewById(R.id.button_chooseSignup);
        buttonSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_chooseLogin:
                login();
                break;

            case R.id.button_chooseSignup:
                signup();
                break;
        }

    }

    public void login()
    {
        Intent intent = new Intent(FirstActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void signup()
    {
        Intent intent = new Intent(FirstActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}
