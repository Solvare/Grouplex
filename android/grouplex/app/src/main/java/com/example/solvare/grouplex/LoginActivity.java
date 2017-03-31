package com.example.solvare.grouplex;

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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et1,et2;
    private Button buttonlogin;
    private Boolean loggedin=false;
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String LOGIN_SUCCESS = "false";
    public static final String SHARED_PREF_NAME = "myloginapp";
    public static final String EMAIL_SHARED_PREF = "email";
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        boolean hasLoggedIn=sharedPreferences.getBoolean(LOGGEDIN_SHARED_PREF,false);
        if(hasLoggedIn){
            finish();
            startActivity(new Intent(this,MyGroupsActivity.class));
            return;
        }
        et1=(EditText)findViewById(R.id.editText1);
        et2=(EditText)findViewById(R.id.editText2);
        buttonlogin=(Button)findViewById(R.id.buttonlogin);
        buttonlogin.setOnClickListener(this);
    }
    public void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        loggedin=sharedPreferences.getBoolean(LOGGEDIN_SHARED_PREF,false);
        if(loggedin){
            Intent intent = new Intent(LoginActivity.this,MyGroupsActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public void onClick(View v) {
        if(v==buttonlogin){
            login();
        }
    }
    private void login(){
        final String response_mssg = null;
        final String email= et1.getText().toString().trim();
        final String password = et2.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String finalresponse_mssg= response_mssg;
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            finalresponse_mssg =jsonObject.getString("error");
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            if(finalresponse_mssg.equalsIgnoreCase(LOGIN_SUCCESS)){
                                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean(LOGGEDIN_SHARED_PREF,true);
                                editor.putString(EMAIL_SHARED_PREF,email);
                                editor.commit();
                                Intent intent = new Intent(LoginActivity.this,MyGroupsActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
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
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void forgotPassword(View view)
    {
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }


}
