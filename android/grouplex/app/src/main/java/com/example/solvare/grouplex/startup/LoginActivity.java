package com.example.solvare.grouplex.startup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.solvare.grouplex.R;
import com.example.solvare.grouplex.constant.Urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    private EditText login_email, login_password;
    private Button buttonlogin;
    private TextView signup_page, forgot_password;
    private Boolean loggedin = false;
    public static final int REQUEST_EXIT = 1;
    public static final String LOGIN_SUCCESS = "false";
    public static final String SHARED_PREF_NAME = "myloginapp";
    public static final String EMAIL_SHARED_PREF = "email";
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = (EditText) findViewById(R.id.editText_login_email);
        login_password = (EditText) findViewById(R.id.editText_login_password);

        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_login_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_login_password);

        buttonlogin = (Button) findViewById(R.id.buttonlogin);
        buttonlogin.setOnClickListener(this);

        signup_page = (TextView) findViewById(R.id.textView_signup);
        signup_page.setOnClickListener(this);

        forgot_password = (TextView) findViewById(R.id.textView_forgotPassword);
        forgot_password.setOnClickListener(this);

        login_email.addTextChangedListener(new MyTextWatcher(login_email));
        login_password.addTextChangedListener(new MyTextWatcher(login_password));
    }

    /*
    public void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        loggedin=sharedPreferences.getBoolean(LOGGEDIN_SHARED_PREF,false);
        if(loggedin){
            Intent intent = new Intent(LoginActivity.this,MyGroupsActivity.class);
            startActivity(intent);
        }
    }
    */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_EXIT) {
            if (resultCode == RESULT_OK) {
                this.finish();

            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == buttonlogin) login();
        else if (v == signup_page) signup();
        else if (v == forgot_password) forgot();
    }

    public void signup() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivityForResult(intent, REQUEST_EXIT);
    }

    public void forgot() {
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    private void login() {

        boolean dev_mode = false;

        if (!dev_mode) {
            if (!validateEmail()) {
                return;
            }

            if (!validatePassword()) {
                return;
            }
        }

        final String email = login_email.getText().toString().trim();
        final String password = login_password.getText().toString();

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Logging-In...");
        progress.setCancelable(true); // disable dismiss by tapping outside of the dialog
        progress.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();
                        try {
                            String finalresponse_mssg;
                            JSONObject jsonObject = new JSONObject(response);
                            finalresponse_mssg = jsonObject.getString("error");

                            if (finalresponse_mssg.equalsIgnoreCase(LOGIN_SUCCESS)) {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(jsonObject.getString("email")
                                        , jsonObject.getString("full_name"), jsonObject.getString("user_id"), jsonObject.getBoolean("verified")
                                );
                                if (jsonObject.getBoolean("verified")) {
                                    Intent intent = new Intent(LoginActivity.this, MyGroupsActivity.class);
                                    startActivity(intent);
                                } else {
                                    Intent intent = new Intent(LoginActivity.this, OtpEmailActivity.class);
                                    startActivity(intent);
                                }
                                finish();
                            } else {
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
                        String message = null;
                        if (error instanceof NetworkError) {
                            message = "Cannot connect to Internet!\nPlease check your connection.";
                        }
                        else if (error instanceof ServerError) {
                            message = "The server could not be found!\nPlease try again after some time.";
                        } else if (error instanceof AuthFailureError) {
                            message = "Cannot connect to Internet!\nPlease check your connection.";
                        } else if (error instanceof ParseError) {
                            message = "Parsing error!";
                        } else if (error instanceof NoConnectionError) {
                            message = "Cannot connect to Internet!\nPlease check your connection.";
                        } else if (error instanceof TimeoutError) {
                            message = "Connection TimeOut!\nPlease check your internet connection.";
                        }
                        else{
                            message = error.getMessage();
                        }
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        progress.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private boolean validateEmail() {
        String email = login_email.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError("Enter valid email address");
            requestFocus(login_email);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (login_password.getText().toString().isEmpty()) {
            inputLayoutPassword.setError("Enter your password");
            requestFocus(login_password);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
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
                case R.id.editText_login_email:
                    validateEmail();
                    break;
                case R.id.editText_login_password:
                    validatePassword();
                    break;
            }
        }
    }

}
