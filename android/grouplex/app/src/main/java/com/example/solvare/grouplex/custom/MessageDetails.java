package com.example.solvare.grouplex.custom;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.solvare.grouplex.R;
import com.example.solvare.grouplex.constant.Urls;
import com.example.solvare.grouplex.startup.RequestHandler;
import com.example.solvare.grouplex.startup.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.solvare.grouplex.constant.Urls.ROOT_URL;
import static com.example.solvare.grouplex.constant.Urls.getGroupId;
import static com.example.solvare.grouplex.startup.SharedPrefManager.KEY_ID;
import static com.example.solvare.grouplex.startup.SharedPrefManager.SHARED_PREF_NAME;

public class MessageDetails extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private EditText message;
    private ImageButton post;
    public static final String LOGIN_SUCCESS = "false";

    MyGroups groups = new MyGroups();
    ArrayList<String> list=groups.getIds();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_messages);

        post=(ImageButton)findViewById(R.id.post);
        post.setOnClickListener(this);
        message=(EditText)findViewById(R.id.message);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        setUpRecyclerView();
        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this); // (Context context, int spanCount)
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);
        recyclerView.setItemAnimator(new

                DefaultItemAnimator()

        );

        //message=(TextView)findViewById(R.id.textView1);
    }
    private void setUpRecyclerView(){
        Urls url = new Urls();

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        StringRequest stringRequest = new StringRequest(url.readUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            progress.dismiss();
                            parseData(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("myTag",response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
    public void parseData(String jsonStr) throws JSONException {
        ArrayList<MyMessages> dataList = new ArrayList<>();


        JSONObject jsonObject = new JSONObject(jsonStr);
        JSONArray jsonArray = jsonObject.getJSONArray("messages");

        if(jsonObject.getString("error").equalsIgnoreCase("false")){

            for(int i=0;i<jsonArray.length();i++){
                MyMessages post = new MyMessages();
                post.setPost(jsonArray.getString(i));

                dataList.add(post);
            }

            Log.d("MYTAG",Integer.toString(dataList.size()));
            adapter = new MessagesAdapter(this, getData(dataList));
            recyclerView.setAdapter(adapter);
            recyclerView.scrollToPosition(dataList.size()-1);

        }
    }
    public ArrayList<MyMessages> getData(ArrayList<MyMessages> list) {
        ArrayList<MyMessages> dataList;
        dataList = list;
        return dataList;
    }

    @Override
    public void onClick(View view) {
        if(view==post){
            posting();
        }
    }
    public void posting(){
        Log.d("Final","rishabh");
        Urls url = new Urls();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String groupid=Integer.toString(getGroupId());
        final String userid=sharedPreferences.getString(KEY_ID, null);
        final String messages = message.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_POST_MESSAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            String finalresponse_mssg;
                            JSONObject jsonObject = new JSONObject(response);
                            finalresponse_mssg =jsonObject.getString("error");

                            if(finalresponse_mssg.equalsIgnoreCase(LOGIN_SUCCESS)){
                                adapter.notifyDataSetChanged();
                                setUpRecyclerView();
                            }else{
                                //Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                            }
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
                params.put("group_id", groupid);
                params.put("user_id", userid);
                params.put("chat",messages);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
