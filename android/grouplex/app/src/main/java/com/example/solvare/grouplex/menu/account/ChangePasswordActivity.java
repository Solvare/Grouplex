package com.example.solvare.grouplex.menu.account;

import android.content.Context;
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
import com.example.solvare.grouplex.startup.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.solvare.grouplex.startup.SharedPrefManager.KEY_ID;
import static com.example.solvare.grouplex.startup.SharedPrefManager.KEY_USERNAME;
import static com.example.solvare.grouplex.startup.SharedPrefManager.SHARED_PREF_NAME;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Button button = (Button) findViewById(R.id.button_change_pass);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    public void changePassword() {

        final SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String user_id = sharedPreferences.getString(KEY_ID, null);

        EditText cur_pass = (EditText) findViewById(R.id.editText_cur_pass);
        EditText new_pass = (EditText) findViewById(R.id.editText_new_pass);
        EditText conf_pass = (EditText) findViewById(R.id.editText_conf_pass);

        if (cur_pass.getText().toString().length() == 0) {
            cur_pass.setError("Current Password cannot be Blank");
        }

        else if (new_pass.getText().toString().length() < 6) {
            new_pass.setError("New Password should be atleast 6 characters");
        }

        else if(!conf_pass.getText().toString().equals(new_pass.getText().toString())){
            conf_pass.setError("Passwords didn't match !");
        }

        final String final_cur_pass=cur_pass.getText().toString();
        final String final_new_pass=new_pass.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_CHANGE_PASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
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
                params.put("cur_upass", final_cur_pass);
                params.put("new_upass", final_new_pass);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
