package com.example.solvare.grouplex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatActivity extends AppCompatActivity {


    private EditText editText1;
    private ArrayAdapter<String> messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        editText1 = (EditText) findViewById(R.id.editText_chat);
        editText1.requestFocus();

        String[] message = {
                "Hello !!\nHow are you doing ??",
                "The next semester starts in July.\nWhen are you coming to university ??",
                "Lets complete the project at the earliest",
                "Ok",
                "Naveen Sir is the CEO of Astrea.",
                "If you work hard, you are bound to get succeed !!",
                "Good Night...."
        };

        List<String> yourMessages = new ArrayList<String>(Arrays.asList(message));

        messageAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.item_chat,R.id.message,yourMessages);

        ListView listview = (ListView) findViewById(R.id.listview_2);
        listview.setAdapter(messageAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.group_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.members:
                groupMembers();
                return true;
            case R.id.settings:
                groupSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void groupMembers() {
        Intent intent = new Intent(ChatActivity.this, GroupMembersActivity.class);
        startActivity(intent);
    }

    private void groupSettings() {
        Intent intent = new Intent(ChatActivity.this, GroupSettingsActivity.class);
        startActivity(intent);
    }
}
