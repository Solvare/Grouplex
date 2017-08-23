package com.example.solvare.grouplex.startup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OtpPasswordActivity extends AppCompatActivity {

    private EditText cur_otp, new_pass, conf_pass;
    private TextInputLayout inputLayoutCur, inputLayoutNew, inputLayoutConf;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_password);

        cur_otp = (EditText) findViewById(R.id.editText_cur_otp);
        new_pass = (EditText) findViewById(R.id.editText_new_pass);
        conf_pass = (EditText) findViewById(R.id.editText_conf_pass);

        inputLayoutCur = (TextInputLayout) findViewById(R.id.input_layout_cur_otp);
        inputLayoutNew = (TextInputLayout) findViewById(R.id.input_layout_new_pass);
        inputLayoutConf = (TextInputLayout) findViewById(R.id.input_layout_conf_pass);

        button = (Button) findViewById(R.id.button_change_pass);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changePassword();
            }
        });

        cur_otp.addTextChangedListener(new OtpPasswordActivity.MyTextWatcher(cur_otp));
        new_pass.addTextChangedListener(new OtpPasswordActivity.MyTextWatcher(new_pass));
        conf_pass.addTextChangedListener(new OtpPasswordActivity.MyTextWatcher(conf_pass));
    }

    public void changePassword() {

        if (!validateCurOtp()) {
            return;
        }

        if (!validateNewPassword()) {
            return;
        }

        if (!validateConfPassword()) {
            return;
        }

        final String final_cur_otp=cur_otp.getText().toString();
        final String final_new_pass=new_pass.getText().toString();

        Bundle extras = getIntent().getExtras();
        final String user_id = extras.getString("user_id");
        
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Changing...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_OTP_PASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if(jsonObject.getString("error").equalsIgnoreCase("false")){
                                Intent intent = new Intent(OtpPasswordActivity.this,LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }

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
                params.put("uid", user_id);
                params.put("otp", final_cur_otp);
                params.put("new_pass", final_new_pass);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private boolean validateCurOtp() {
        if (cur_otp.getText().toString().isEmpty()) {
            inputLayoutCur.setError("Enter OTP sent to your email");
            requestFocus(cur_otp);
            return false;
        } else {
            inputLayoutCur.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateNewPassword() {
        if (new_pass.getText().toString().length() < 6) {
            inputLayoutNew.setError("Password should be atleast 6 characters");
            requestFocus(new_pass);
            return false;
        } else {
            inputLayoutNew.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateConfPassword() {
        if (!conf_pass.getText().toString().equals(new_pass.getText().toString())) {
            inputLayoutConf.setError("Passwords didn't match !");
            requestFocus(conf_pass);
            return false;
        } else {
            inputLayoutConf.setErrorEnabled(false);
        }

        return true;
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
                case R.id.editText_cur_otp:
                    validateCurOtp();
                    break;
                case R.id.editText_new_pass:
                    validateNewPassword();
                    break;
                case R.id.editText_conf_pass:
                    validateConfPassword();
                    break;
            }
        }
    }
    
}

