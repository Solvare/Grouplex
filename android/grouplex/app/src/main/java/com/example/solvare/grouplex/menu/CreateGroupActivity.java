package com.example.solvare.grouplex.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.example.solvare.grouplex.R;
import com.example.solvare.grouplex.constant.Urls;
import com.example.solvare.grouplex.startup.RequestHandler;

public class CreateGroupActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonCreateGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        buttonCreateGroup=(Button)findViewById(R.id.button_create_group);
        buttonCreateGroup.setOnClickListener(this);
    }

    public void createGroup()
    {
        EditText group_name;
        group_name = (EditText) findViewById(R.id.group_name);
        final String groupName = group_name.getText().toString().trim();
        final String user_id = "1"; //static for now
        if(groupName.length()==0)
        {
            group_name.setError("Group Name cannot be Blank");
            return;
        }
        else
        {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_CREATE_GROUP,
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
                    params.put("group_name", groupName);
                    params.put("user_id", user_id);
                    return params;
                }
            };

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
            //Intent intent = new Intent(CreateGroupActivity.this, MyGroupsActivity.class);
            //startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        if(v==buttonCreateGroup){
            createGroup();
        }
    }
}
