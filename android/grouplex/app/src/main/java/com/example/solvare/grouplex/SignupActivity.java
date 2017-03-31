package com.example.solvare.grouplex;


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

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et3, et4, et5, et6;
    private Button buttonRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);
        et6 = (EditText) findViewById(R.id.editText6);
        buttonRegister=(Button)findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(this);
    }
    public void registerUser(){
        String full_name= null;
        String email = null;
        String password= null;

        if (et3.getText().toString().length() == 0) {
            et3.setError("Name cannot be Blank");
        }
        else{
            full_name=et3.getText().toString().trim();
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(et4.getText().toString()).matches()) {
            et4.setError("Invalid Email");
        }else {
            email=et4.getText().toString().trim();//final ??
        }
        if (et5.getText().toString().length() < 6) {
            et5.setError("Password should be atleast 6 characters");
        }else if (!et6.getText().toString().equals(et5.getText().toString()))
        {
            et6.setError("Passwords didn't match !");
        }else{
            password=et5.getText().toString().trim();
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