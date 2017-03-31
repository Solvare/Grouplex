package com.example.solvare.grouplex;

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

public class AboutActivity extends AppCompatActivity {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // 1. pass context and data to the custom adapter
        com.example.solvare.grouplex.custom.AboutAdapter adapter = new com.example.solvare.grouplex.custom.AboutAdapter(this, generateData());

        // 2. Get ListView from activity_main.xml
        ListView listView = (ListView) findViewById(R.id.listview_about);

        // 3. setListAdapter
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                if(position == 4) {
                    Intent intent = new Intent(AboutActivity.this, OpenSourceActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
    /*private ArrayList<com.example.solvare.grouplex.custom.Items> generateData(){
        ArrayList<com.example.solvare.grouplex.custom.Items> items = new ArrayList<>();
        items.add(new com.example.solvare.grouplex.custom.Items("Aim","To facilitate Official Group Chats."));
        items.add(new com.example.solvare.grouplex.custom.Items("Licence","Open Source"));
        items.add(new com.example.solvare.grouplex.custom.Items("Developers", "Rishabh Ahuja\nRajat Saxena"));
        items.add(new com.example.solvare.grouplex.custom.Items("Contact Us", "rishabhahuja279@gmail.com\nrajat24saxena@gmail.com"));
        items.add(new com.example.solvare.grouplex.custom.Items("Open Source Credits", "We thank every open-source developer for their contribution to the community."));

        return items;
    }*/
}
