package com.example.solvare.grouplex.menu;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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
import com.example.solvare.grouplex.startup.MyGroupsActivity;
import com.example.solvare.grouplex.startup.RequestHandler;

import static com.example.solvare.grouplex.startup.SharedPrefManager.KEY_ID;
import static com.example.solvare.grouplex.startup.SharedPrefManager.SHARED_PREF_NAME;

public class CreateGroupActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonCreateGroup;
    private EditText group_name;
    private TextInputLayout inputLayoutGroupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        group_name = (EditText) findViewById(R.id.editText_group_name);
        inputLayoutGroupName = (TextInputLayout) findViewById(R.id.input_layout_create_group);

        buttonCreateGroup=(Button)findViewById(R.id.button_create_group);
        buttonCreateGroup.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MyGroupsActivity.class));
    }
    public void createGroup()
    {
        if (!validateName()) {
            return;
        }

        final String finalGroup_name = group_name.getText().toString().trim();

        SharedPreferences sharedPreferences =getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String user_id=sharedPreferences.getString(KEY_ID,null);

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Creating...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_CREATE_GROUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();
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
                params.put("group_name", finalGroup_name);
                params.put("user_id", user_id);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        //Intent intent = new Intent(CreateGroupActivity.this, MyGroupsActivity.class);
        //startActivity(intent);
        }

    @Override
    public void onClick(View v) {
        if(v==buttonCreateGroup){
            createGroup();
            startActivity(new Intent(this, MyGroupsActivity.class));
        }
    }

    private boolean validateName() {
        if (group_name.getText().toString().trim().isEmpty()) {
            inputLayoutGroupName.setError("Enter group name");
            return false;
        } else {
            inputLayoutGroupName.setErrorEnabled(false);
        }

        return true;
    }
}
