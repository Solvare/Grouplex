package com.example.solvare.grouplex.menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.solvare.grouplex.R;
import com.example.solvare.grouplex.constant.Urls;
import com.example.solvare.grouplex.startup.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.solvare.grouplex.startup.SharedPrefManager.KEY_ID;
import static com.example.solvare.grouplex.startup.SharedPrefManager.SHARED_PREF_NAME;

public class JoinGroupActivity extends AppCompatActivity{
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);

        final ListView lv = (ListView)findViewById(R.id.listViewgroups);
        final ArrayList<String> arrayList = new ArrayList<>();
        final ArrayList<String> arrayListId = new ArrayList<>();
        final ArrayList<String> arrayListMem = new ArrayList<>();
        SharedPreferences sharedPreferences =getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String uid=sharedPreferences.getString(KEY_ID,null);
        final String URL_SEARCH=Urls.ROOT_URL+"/search/"+uid;
        StringRequest stringRequest = new StringRequest(URL_SEARCH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            JSONArray jsonarray = jsonObj.getJSONArray("groups");
                            for (int i = 0; i < jsonarray.length(); i++) {
                                arrayList.add(jsonarray.getJSONObject(i).getString("name"));
                                arrayListId.add(jsonarray.getJSONObject(i).getString("id"));
                                arrayListMem.add(jsonarray.getJSONObject(i).getString("members"));
                                adapter = new ArrayAdapter<>(
                                        JoinGroupActivity.this,
                                        R.layout.item_join_group, R.id.text1,
                                        arrayList);
                                lv.setAdapter(adapter);
                                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                                            long id) {
                                        //Toast.makeText(getApplicationContext(),lv.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
                                        String sel_grp = lv.getItemAtPosition(position).toString();
                                        int index = arrayList.indexOf(sel_grp);
                                        addGroup(arrayListId.get(index),uid);
                                    }
                                });
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

    void addGroup(final String gr_id, final String us_id)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_JOIN_GROUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("group_id", gr_id);
                params.put("user_id", us_id);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}
