package com.example.solvare.grouplex.startup;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import com.example.solvare.grouplex.R;
import com.example.solvare.grouplex.constant.Urls;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText signup_fullName, signup_email, signup_password, signup_confPassword;
    private Button buttonRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup_fullName = (EditText) findViewById(R.id.editText_signup_fullName);
        signup_email = (EditText) findViewById(R.id.editText_signup_email);
        signup_password = (EditText) findViewById(R.id.editText_signup_password);
        signup_confPassword = (EditText) findViewById(R.id.editText_signup_confPassword);
        buttonRegister=(Button)findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(this);
    }
    public void registerUser(){
        String full_name= null;
        String email = null;
        String password= null;

        if (signup_fullName.getText().toString().length() == 0) {
            signup_fullName.setError("Name cannot be Blank");
        }
        else{
            full_name=signup_fullName.getText().toString().trim();
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(signup_email.getText().toString()).matches()) {
            signup_email.setError("Invalid Email");
        }else {
            email=signup_email.getText().toString().trim();//final ??
        }
        if (signup_password.getText().toString().length() < 6) {
            signup_password.setError("Password should be atleast 6 characters");
        }else if (!signup_confPassword.getText().toString().equals(signup_password.getText().toString()))
        {
            signup_confPassword.setError("Passwords didn't match !");
        }else{
            password=signup_password.getText().toString().trim();
        }
        final String finalPassword = password;
        final String finalEmail = email;
        final String finalFull_name = full_name;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_REGISTER,
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
                params.put("email", finalEmail);
                params.put("password", finalPassword);
                params.put("full_name", finalFull_name);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v==buttonRegister){
            registerUser();
        }
    }

}