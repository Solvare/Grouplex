package com.example.solvare.grouplex.custom;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

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

public class MessageDetails extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_messages);
        //sendReadMessageRequest();
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
        Urls url = new Urls(this);
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        StringRequest stringRequest = new StringRequest(url.URL_READ_MESSAGE,
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
        }
        /*ArrayList<MyMessages> dataList = new ArrayList<>();
        ArrayList<String> messages = new ArrayList<>();

        MyMessages post = new MyMessages();

        JSONObject jsonObject = new JSONObject(jsonStr);
        JSONArray jsonArray = jsonObject.getJSONArray("messages");
        Log.d("MYTAG", jsonArray.toString());
        if(jsonObject.getString("error").equalsIgnoreCase("false")){

            for(int i=0;i<jsonArray.length();i++){
                messages.add(jsonArray.getString(i));
            }

            post.setMessages(messages);
            for(int i=0;i<messages.size();i++){
                post.setPost(post.getMessages().get(i));
            }
            //int size = messages.size();
            //Log.d("MYTAG3", post.getMessages().get(1));
            dataList.add(post);
            adapter = new MessagesAdapter(this, getData(dataList));
            recyclerView.setAdapter(adapter);
        }*/
    }
    public ArrayList<MyMessages> getData(ArrayList<MyMessages> list) {
        ArrayList<MyMessages> dataList;
        dataList = list;
        return dataList;
    }
}
