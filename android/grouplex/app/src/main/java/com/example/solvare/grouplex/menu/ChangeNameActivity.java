package com.example.solvare.grouplex.menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.solvare.grouplex.R;
import com.example.solvare.grouplex.constant.Urls;
import com.example.solvare.grouplex.startup.ForgotPasswordActivity;
import com.example.solvare.grouplex.startup.LoginActivity;
import com.example.solvare.grouplex.startup.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.solvare.grouplex.startup.SharedPrefManager.KEY_ID;
import static com.example.solvare.grouplex.startup.SharedPrefManager.KEY_USERNAME;
import static com.example.solvare.grouplex.startup.SharedPrefManager.SHARED_PREF_NAME;

public class ChangeNameActivity extends AppCompatActivity {

    private EditText new_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String old_user_name = sharedPreferences.getString(KEY_USERNAME, null);
        //String old_user_name = "ede";
        new_name = (EditText) findViewById(R.id.editText_change_name);
        new_name.setText(old_user_name);
        final Button button = (Button) findViewById(R.id.button_change_name);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeName();
            }
        });
    }

    public void changeName() {

        final SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String user_id = sharedPreferences.getString(KEY_ID, null);

        String full_name = null;

        if (new_name.getText().toString().length() == 0) {
            new_name.setError("Name cannot be Blank");
        } else {
            full_name = new_name.getText().toString().trim();
        }
        final String finalFull_name = full_name;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_CHANGE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.getString("error")=="false")
                            {
                                //Getting editor
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString(KEY_USERNAME, finalFull_name);
                                editor.commit();
                            }
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", user_id);
                params.put("new_uname", finalFull_name);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
