package com.example.solvare.grouplex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class JoinGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);
    }

    public void searchGroupsByName(View view)
    {
        EditText et10;
        et10 = (EditText) findViewById(R.id.editText10);
        if(et10.getText().toString().length()==0)
        {
            et10.setError("Group Name cannot be Blank");
            return;
        }
        else
        {
            Intent intent = new Intent(JoinGroupActivity.this, MyGroupsActivity.class);
            startActivity(intent);
        }
    }

    public void searchGroupsById(View view)
    {
        EditText et9;
        et9 = (EditText) findViewById(R.id.editText9);
        if(et9.getText().toString().length()==0)
        {
            et9.setError("Group Id cannot be Blank");
            return;
        }
        else
        {
            Intent intent = new Intent(JoinGroupActivity.this, MyGroupsActivity.class);
            startActivity(intent);
        }
    }

}
