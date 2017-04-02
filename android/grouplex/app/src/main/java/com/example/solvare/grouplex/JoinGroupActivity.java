package com.example.solvare.grouplex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class JoinGroupActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);

        Button button_searchId = (Button)findViewById(R.id.button_search_id);
        button_searchId.setOnClickListener(this);

        Button button_searchName = (Button)findViewById(R.id.button_search_name);
        button_searchName.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_search_id:
                searchGroupsById();
                break;

            case R.id.button_search_name:
                searchGroupsByName();
                break;
        }

    }

    public void searchGroupsByName()
    {
        EditText group_name;
        group_name = (EditText) findViewById(R.id.editText_groupName);
        if(group_name.getText().toString().length()==0)
        {
            group_name.setError("Group Name cannot be Blank");
            return;
        }
        else
        {
            Intent intent = new Intent(JoinGroupActivity.this, MyGroupsActivity.class);
            startActivity(intent);
        }
    }

    public void searchGroupsById()
    {
        EditText group_id;
        group_id = (EditText) findViewById(R.id.editText_groupId);
        if(group_id.getText().toString().length()==0)
        {
            group_id.setError("Group Id cannot be Blank");
            return;
        }
        else
        {
            Intent intent = new Intent(JoinGroupActivity.this, MyGroupsActivity.class);
            startActivity(intent);
        }
    }

}
