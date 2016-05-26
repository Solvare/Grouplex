package com.example.solvare.grouplex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyGroupsActivity extends AppCompatActivity {

    private ArrayAdapter<String> groupsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_groups);

        String[] groupsNames = {
                "CSE-2016",
                "ECE-2016",
                "MECH-2016",
                "LAW-2016",
                "CIVIL-2016",
                "MBA-2016",
                "ISR-2016",
                "CSE-2012",
                "ECE-2012",
                "MECH-2012",
                "LAW-2012",
                "CIVIL-2012",
                "MBA-2012",
                "ISR-2012"
        };

        List<String> yourGroups = new ArrayList<String>(Arrays.asList(groupsNames));

        groupsAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.groups,R.id.group_name,yourGroups);

        ListView listview = (ListView) findViewById(R.id.listview_1);
        listview.setAdapter(groupsAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String forecast = groupsAdapter.getItem(position);
                Intent intent = new Intent(MyGroupsActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.about:
                aboutGrouplex();
                return true;
            case R.id.join:
                joinGroup();
                return true;
            case R.id.create:
                createGroup();
                return true;
            case R.id.help:
                helpUser();
                return true;
            case R.id.settings:
                settings();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void aboutGrouplex() {
        Intent intent = new Intent(MyGroupsActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    private void joinGroup() {
        Intent intent = new Intent(MyGroupsActivity.this, JoinGroupActivity.class);
        startActivity(intent);
    }

    private void createGroup() {
        Intent intent = new Intent(MyGroupsActivity.this, CreateGroupActivity.class);
        startActivity(intent);
    }

    private void helpUser() {
        Intent intent = new Intent(MyGroupsActivity.this, HelpActivity.class);
        startActivity(intent);
    }

    private void settings() {
        Intent intent = new Intent(MyGroupsActivity.this, UserSettingsActivity.class);
        startActivity(intent);
    }

}
