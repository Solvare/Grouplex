package com.example.solvare.grouplex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.solvare.grouplex.SharedPrefManager;

import static com.example.solvare.grouplex.LoginActivity.LOGGEDIN_SHARED_PREF;
import static com.example.solvare.grouplex.LoginActivity.SHARED_PREF_NAME;

public class FirstActivity extends AppCompatActivity {

    //public static final String SHARED_PREF_NAME = "myloginapp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        boolean hasLoggedIn=sharedPreferences.getBoolean(LOGGEDIN_SHARED_PREF,false);
        if(hasLoggedIn){
            finish();
            startActivity(new Intent(this,MyGroupsActivity.class));
            return;
        }
    }

    public void login(View view)
    {
        Intent intent = new Intent(FirstActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void signup(View view)
    {
        Intent intent = new Intent(FirstActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}
