package com.example.solvare.grouplex.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.solvare.grouplex.R;
import com.example.solvare.grouplex.constant.Urls;
import com.example.solvare.grouplex.custom.MyGroups;
import com.example.solvare.grouplex.custom.MyGroupsAdapter;
import com.example.solvare.grouplex.startup.MyGroupsActivity;
import com.example.solvare.grouplex.startup.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JoinGroupActivity extends AppCompatActivity{
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);

        final ArrayList<String> arrayList = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Urls.URL_SEARCH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            ListView lv = (ListView)findViewById(R.id.listViewgroups);
                            JSONArray jsonarray = jsonObj.getJSONArray("groups");
                            for (int i = 0; i < jsonarray.length(); i++) {
                                arrayList.add(jsonarray.getString(i));
                                adapter = new ArrayAdapter<>(
                                        JoinGroupActivity.this,
                                        android.R.layout.simple_list_item_1,
                                        arrayList);
                                lv.setAdapter(adapter);
                            }
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
    private void parseData(String jsonStr) throws JSONException {

        JSONObject jsonObj = new JSONObject(jsonStr);
        JSONArray jsonarray = jsonObj.getJSONArray("groups");
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

}
