package com.example.solvare.grouplex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void myGroups(View view)
    {
        EditText et1,et2;
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(et1.getText().toString()).matches())
        {
            et1.setError("Invalid Email");
            return;
        }
        else if(et2.getText().toString().length()==0)
        {
            et2.setError("Password cannot be Blank");
            return;
        }
        else
        {
            Intent intent = new Intent(LoginActivity.this, MyGroupsActivity.class);
            startActivity(intent);
        }
    }

    public void forgotPassword(View view)
    {
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

}
