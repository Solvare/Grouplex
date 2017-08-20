package com.example.solvare.grouplex.startup;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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

    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword, inputLayoutConfPassword;
    private EditText signup_fullName, signup_email, signup_password, signup_confPassword;
    private Button buttonRegister;
    public static final String SIGNUP_SUCCESS = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_signup_fullName);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_signup_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_signup_password);
        inputLayoutConfPassword = (TextInputLayout) findViewById(R.id.input_layout_signup_confPassword);

        signup_fullName = (EditText) findViewById(R.id.editText_signup_fullName);
        signup_email = (EditText) findViewById(R.id.editText_signup_email);
        signup_password = (EditText) findViewById(R.id.editText_signup_password);
        signup_confPassword = (EditText) findViewById(R.id.editText_signup_confPassword);

        buttonRegister=(Button)findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(this);

        signup_fullName.addTextChangedListener(new MyTextWatcher(signup_fullName));
        signup_email.addTextChangedListener(new MyTextWatcher(signup_email));
        signup_password.addTextChangedListener(new MyTextWatcher(signup_password));
        signup_confPassword.addTextChangedListener(new MyTextWatcher(signup_confPassword));

    }

    public void registerUser(){

        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        if (!validateConfPassword()) {
            return;
        }

        final String finalFull_name = signup_fullName.getText().toString().trim();
        final String finalEmail = signup_email.getText().toString().trim();
        final String finalPassword = signup_password.getText().toString();

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Signing-Up...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();
                        try {
                            String finalresponse_mssg;
                            JSONObject jsonObject = new JSONObject(response);
                            finalresponse_mssg = jsonObject.getString("error");
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if (finalresponse_mssg.equalsIgnoreCase(SIGNUP_SUCCESS)) {
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(jsonObject.getString("email")
                                        , jsonObject.getString("full_name"), jsonObject.getString("user_id")
                                );
                                Intent intent = new Intent(SignupActivity.this, MyGroupsActivity.class);
                                startActivity(intent);
                                setResult(RESULT_OK, null);
                                finish();
                            }
                        }
                        catch (JSONException e) {
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

    private boolean validateName() {
        if (signup_fullName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError("Enter your full name");
            requestFocus(signup_fullName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = signup_email.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError("Enter valid email address");
            requestFocus(signup_email);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (signup_password.getText().toString().length() < 6) {
            inputLayoutPassword.setError("Password should be atleast 6 characters");
            requestFocus(signup_password);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateConfPassword() {
        if (!signup_confPassword.getText().toString().equals(signup_password.getText().toString())) {
            inputLayoutConfPassword.setError("Passwords didn't match !");
            requestFocus(signup_confPassword);
            return false;
        } else {
            inputLayoutConfPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.editText_signup_fullName:
                    validateName();
                    break;
                case R.id.editText_signup_email:
                    validateEmail();
                    break;
                case R.id.editText_signup_password:
                    validatePassword();
                    break;
                case R.id.editText_signup_confPassword:
                    validateConfPassword();
                    break;
            }
        }
    }

}