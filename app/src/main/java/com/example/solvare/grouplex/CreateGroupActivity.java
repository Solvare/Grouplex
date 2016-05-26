package com.example.solvare.grouplex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
    }

    public void myGroups(View view)
    {
        EditText et8;
        et8 = (EditText) findViewById(R.id.editText8);
        if(et8.getText().toString().length()==0)
        {
            et8.setError("Group Name cannot be Blank");
            return;
        }
        else
        {
            Intent intent = new Intent(CreateGroupActivity.this, MyGroupsActivity.class);
            startActivity(intent);
        }
    }
}
