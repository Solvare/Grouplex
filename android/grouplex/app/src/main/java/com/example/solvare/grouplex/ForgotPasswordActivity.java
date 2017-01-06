package com.example.solvare.grouplex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void loginAgain(View view)
    {
        EditText et7;
        et7 = (EditText) findViewById(R.id.editText7);
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(et7.getText().toString()).matches())
        {
            et7.setError("Invalid Email");
        }
        else
        {
            Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
