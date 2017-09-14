package com.example.solvare.grouplex.startup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            if (SharedPrefManager.getInstance(this).isVerified()) {
                startActivity(new Intent(this, MyGroupsActivity.class));
            } else {
                startActivity(new Intent(this, OtpEmailActivity.class));
            }
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
        return;
    }
}
