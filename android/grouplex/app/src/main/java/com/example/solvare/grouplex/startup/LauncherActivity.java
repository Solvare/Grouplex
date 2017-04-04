package com.example.solvare.grouplex.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finish();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            startActivity(new Intent(this,MyGroupsActivity.class));
        }
        else {
            startActivity(new Intent(this,LoginActivity.class));
        }
        return;
    }
}
