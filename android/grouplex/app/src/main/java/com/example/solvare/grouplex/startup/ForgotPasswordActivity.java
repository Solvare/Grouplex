package com.example.solvare.grouplex.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.solvare.grouplex.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final Button button = (Button) findViewById(R.id.button_mail);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMail();
            }
        });

    }

    public void sendMail()
    {
        EditText fp_email;
        fp_email = (EditText) findViewById(R.id.editText_fp_email);
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(fp_email.getText().toString()).matches())
        {
            fp_email.setError("Invalid Email");
        }
        else
        {
            Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
