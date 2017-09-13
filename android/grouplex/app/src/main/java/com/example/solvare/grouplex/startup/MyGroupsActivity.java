package com.example.solvare.grouplex.startup;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.solvare.grouplex.R;
import com.example.solvare.grouplex.constant.Urls;
import com.example.solvare.grouplex.custom.MyGroups;
import com.example.solvare.grouplex.custom.MyGroupsAdapter;
import com.example.solvare.grouplex.menu.CreateGroupActivity;
import com.example.solvare.grouplex.menu.JoinGroupActivity;
import com.example.solvare.grouplex.menu.about.AboutActivity;
import com.example.solvare.grouplex.menu.account.UserAccountActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyGroupsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public ArrayList<String> ids= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_groups);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
            return;
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setTitle("  Grouplex  ");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);



        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this); // (Context context, int spanCount)
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);
        recyclerView.setItemAnimator(new

                DefaultItemAnimator()

        );
        setUpRecyclerView();

    }

    private void setUpRecyclerView() {


        Urls url = new Urls(this);
        StringRequest stringRequest = new StringRequest(url.URL_GROUPS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            parseData(response);
                            //adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Stopping swipe refresh

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }



    MyGroups groups = new MyGroups();

    private void parseData(String jsonStr) throws JSONException {

        ArrayList<MyGroups> dataList = new ArrayList<>();
        //ArrayList<String> id = new ArrayList<>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> levels = new ArrayList<String>();
        ArrayList<String> num_members = new ArrayList<String>();
        JSONObject jsonObj = new JSONObject(jsonStr);
        //Log.d("rishabh",jsonStr);
        if(jsonObj.getString("error").equalsIgnoreCase("true"))
        {
            Resources res = getResources();
            if(jsonObj.getInt("errorId")==res.getInteger(R.integer.no_associated_groups))
            {
                setContentView(R.layout.activity_no_groups);
                Button button_join = (Button) findViewById(R.id.button_dir_join_group);
                Button button_create = (Button) findViewById(R.id.button_dir_create_group);
                button_join.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        joinGroup();
                    }
                });
                button_create.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        createGroup();
                    }
                });
                return;
            }
        }

        else
        {
            JSONArray jsonarray = jsonObj.getJSONArray("groups");
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                ids.add(jsonobject.getString("id"));
                names.add(jsonobject.getString("name"));
                levels.add(jsonobject.getString("level"));
                num_members.add(jsonobject.getString("members"));
            }
            groups.setIds(ids);
            groups.set_groupnames(names);
            groups.setLevel(levels);
            groups.setNumMembers(num_members);

            for (int i = 0; i < groups.get_groupnames().size(); i++) {

                groups.s_set_groupnames(groups.get_groupnames().get(i));
                groups.s_setLevel(groups.getLevel().get(i));
                groups.s_setNumMembers(groups.getNumMembers().get(i));
                groups.setId(groups.getIds().get(i));
                dataList.add(groups);
            }
            //Log.d("Elements", ids.get(0));
            adapter = new MyGroupsAdapter(this, getData(dataList));
            recyclerView.setAdapter(adapter);
        }
    }

    public ArrayList<MyGroups> getData(ArrayList<MyGroups> list) {
        ArrayList<MyGroups> dataList = new ArrayList<>();
        dataList = list;
        return dataList;
    }

    private void logout() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout ?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        SharedPrefManager.getInstance(getApplicationContext()).logout();

                        //Starting login activity
                        Intent intent = new Intent(MyGroupsActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.join:
                joinGroup();
                return true;
            case R.id.create:
                createGroup();
                return true;
            case R.id.account:
                account();
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

    private void account() {
        Intent intent = new Intent(MyGroupsActivity.this, UserAccountActivity.class);
        startActivity(intent);
    }

    private void about() {
        Intent intent = new Intent(MyGroupsActivity.this, AboutActivity.class);
        startActivity(intent);
    }

}
