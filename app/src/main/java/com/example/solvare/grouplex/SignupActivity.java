package com.example.solvare.grouplex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void myGroups(View view) {
        EditText et3, et4, et5, et6;
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);
        et6 = (EditText) findViewById(R.id.editText6);
        if (et3.getText().toString().length() == 0) {
            et3.setError("Name cannot be Blank");
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(et4.getText().toString()).matches()) {
            et4.setError("Invalid Email");
        }
        else if (et5.getText().toString().length() < 6) {
            et5.setError("Password should be atleast 6 characters");
        }
        else if (!et6.getText().toString().equals(et5.getText().toString()))
        {
            et6.setError("Passwords didn't match !");
        }
        else
        {
            Intent intent = new Intent(SignupActivity.this, MyGroupsActivity.class);
            startActivity(intent);
        }
    }
}