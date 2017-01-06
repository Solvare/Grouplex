package com.example.solvare.grouplex;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
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

        groupsAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.item_groups,R.id.group_name,yourGroups);

        ListView listview = (ListView) findViewById(R.id.listview_1);
        listview.setAdapter(groupsAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MyGroupsActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
    }
    /*public void onResume() {
        logout();
    }*/
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //exitByBackKey();
            logout();
            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(LoginActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(LoginActivity.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(LoginActivity.EMAIL_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(MyGroupsActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

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
            case R.id.join:
                joinGroup();
                return true;
            case R.id.create:
                createGroup();
                return true;
            case R.id.settings:
                settings();
                return true;
            case R.id.about:
                about();
                return true;
            case R.id.menuLogout:
                logout();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void joinGroup() {
        Intent intent = new Intent(MyGroupsActivity.this, JoinGroupActivity.class);
        startActivity(intent);
    }

    private void createGroup() {
        Intent intent = new Intent(MyGroupsActivity.this, CreateGroupActivity.class);
        startActivity(intent);
    }

    private void settings() {
        Intent intent = new Intent(MyGroupsActivity.this, UserSettingsActivity.class);
        startActivity(intent);
    }

    private void about() {
        Intent intent = new Intent(MyGroupsActivity.this, AboutActivity.class);
        startActivity(intent);
    }

}
