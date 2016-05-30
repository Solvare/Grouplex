package com.example.solvare.grouplex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
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
