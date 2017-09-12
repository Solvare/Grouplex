package com.example.solvare.grouplex.menu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.solvare.grouplex.R;
import com.example.solvare.grouplex.constant.Urls;
import com.example.solvare.grouplex.custom.JoinGroupAdapter;
import com.example.solvare.grouplex.custom.JoinGroup;
import com.example.solvare.grouplex.custom.RecyclerTouchListener;
import com.example.solvare.grouplex.menu.JoinGroupNewActivity;
import com.example.solvare.grouplex.menu.about.AboutActivity;
import com.example.solvare.grouplex.menu.CreateGroupActivity;
import com.example.solvare.grouplex.menu.JoinGroupActivity;
import com.example.solvare.grouplex.menu.account.UserAccountActivity;
import com.example.solvare.grouplex.startup.MyGroupsActivity;
import com.example.solvare.grouplex.startup.RequestHandler;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import static com.example.solvare.grouplex.startup.SharedPrefManager.KEY_ID;
import static com.example.solvare.grouplex.startup.SharedPrefManager.SHARED_PREF_NAME;

public class JoinGroupNewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group_new);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_join);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        SharedPreferences sharedPreferences =getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        String id=sharedPreferences.getString(KEY_ID,null);
        final String URL_GROUPS=Urls.ROOT_URL+"/search/"+id;
        StringRequest stringRequest = new StringRequest(URL_GROUPS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            parseData(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MyGroupsActivity.class));
    }

    JoinGroup groups = new JoinGroup();

    private void parseData(String jsonStr) throws JSONException {

        final ArrayList<JoinGroup> dataList = new ArrayList<>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> ids = new ArrayList<String>();
        ArrayList<String> members = new ArrayList<String>();
        JSONObject jsonObj = new JSONObject(jsonStr);
        JSONArray jsonarray = jsonObj.getJSONArray("groups");
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            ids.add(jsonobject.getString("id"));
            names.add(jsonobject.getString("name"));
            members.add(jsonobject.getString("members"));
        }
        groups.setNames(names);
        groups.setIds(ids);
        groups.setMembers(members);

        for (int i = 0; i < groups.getNames().size(); i++) {

            groups.setName(groups.getNames().get(i));
            groups.setId(groups.getIds().get(i));
            groups.setMember(groups.getMembers().get(i));
            dataList.add(groups);
        }

        int size = dataList.size();
        Log.d("SIZE", Integer.toString(size));
        adapter = new JoinGroupAdapter(this, getData(dataList));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                JoinGroup grp = dataList.get(position);
                Toast.makeText(getApplicationContext(), grp.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    public ArrayList<JoinGroup> getData(ArrayList<JoinGroup> list) {
        ArrayList<JoinGroup> dataList = new ArrayList<>();
        dataList = list;
        return dataList;
    }

}
