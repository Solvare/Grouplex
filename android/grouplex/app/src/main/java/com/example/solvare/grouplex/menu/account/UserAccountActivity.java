package com.example.solvare.grouplex.menu.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.solvare.grouplex.R;

public class UserAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

    }

    public void changeName(View v) {
        Intent intent = new Intent(UserAccountActivity.this, ChangeNameActivity.class);
        startActivity(intent);
    }

    public void changePassword(View v) {
        Intent intent = new Intent(UserAccountActivity.this, ChangePasswordActivity.class);
        startActivity(intent);
    }

}
