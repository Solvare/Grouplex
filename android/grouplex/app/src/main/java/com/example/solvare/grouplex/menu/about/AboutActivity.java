package com.example.solvare.grouplex.menu.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.solvare.grouplex.R;
import com.example.solvare.grouplex.custom.About;
import com.example.solvare.grouplex.custom.AboutAdapter;
import com.example.solvare.grouplex.custom.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity {
    private List<About> aboutList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AboutAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_about);

        mAdapter = new AboutAdapter(aboutList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                About about = aboutList.get(position);
                if(about.getHeading() == "Open Source Credits")
                {
                    openSource();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));

        prepareAboutData();
    }

    private void prepareAboutData() {
        About about = new About("Aim", "To facilitate Official Group Chats.");
        aboutList.add(about);

        about = new About("Licence", "Open Source");
        aboutList.add(about);

        about = new About("Developers", "Rishabh Ahuja\nRajat Saxena");
        aboutList.add(about);

        about = new About("Contact Us", "rishabhahuja279@gmail.com\nrajat24saxena@gmail.com");
        aboutList.add(about);

        about = new About("Version", "1.8");
        aboutList.add(about);

        about = new About("Open Source Credits", "Tap Here !!");
        aboutList.add(about);


        mAdapter.notifyDataSetChanged();
    }

    public void openSource()
    {
        Intent intent = new Intent(AboutActivity.this, OpenSourceActivity.class);
        startActivity(intent);
    }
}