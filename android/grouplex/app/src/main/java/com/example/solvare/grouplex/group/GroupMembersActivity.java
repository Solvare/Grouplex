package com.example.solvare.grouplex.group;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.solvare.grouplex.R;

public class GroupMembersActivity extends AppCompatActivity {
    /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_members);

        // 1. pass context and data to the custom adapter
        com.example.solvare.grouplex.custom.GroupMemberAdapter adapter = new com.example.solvare.grouplex.custom.GroupMemberAdapter(this, generateData());

        // 2. Get ListView from activity_main.xml
        ListView listView = (ListView) findViewById(R.id.listview_group_members);

        // 3. setListAdapter
        listView.setAdapter(adapter);

    }

    /*private ArrayList<com.example.solvare.grouplex.custom.Items> generateData(){
        ArrayList<com.example.solvare.grouplex.custom.Items> items = new ArrayList<>();
        items.add(new com.example.solvare.grouplex.custom.Items("Sarthak Garg","sarthak.garg@gmail.com"));
        items.add(new com.example.solvare.grouplex.custom.Items("Varun Garg","varun.garg@gmail.com"));
        items.add(new com.example.solvare.grouplex.custom.Items("Shobhit Sharma", "shobhit.sharma@gmail.com"));
        items.add(new com.example.solvare.grouplex.custom.Items("Bhawesh Chandoal", "bhawesh.chandola@gmail.com"));
        items.add(new com.example.solvare.grouplex.custom.Items("Vasudev Yadav", "vasudev.yadav@gmail.com"));
        items.add(new com.example.solvare.grouplex.custom.Items("Arvind Kumar", "arvind.kumar@gmail.com"));
        items.add(new com.example.solvare.grouplex.custom.Items("Sachin Pandey", "sachin.pandey@gmail.com"));
        items.add(new com.example.solvare.grouplex.custom.Items("Rahul Kumar", "rahul.kumar@gmail.com"));
        items.add(new com.example.solvare.grouplex.custom.Items("Vikas Dixit", "vikas.dixit@gmail.com"));
        items.add(new com.example.solvare.grouplex.custom.Items("Vivek Bharti", "vivek.bharti@gmail.com"));
        return items;
    }*/
}
