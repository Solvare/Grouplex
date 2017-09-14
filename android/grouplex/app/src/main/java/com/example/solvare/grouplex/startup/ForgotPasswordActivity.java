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

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText fp_email;
    private TextInputLayout inputLayoutFpEmail;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        inputLayoutFpEmail = (TextInputLayout) findViewById(R.id.input_layout_fp_email);
        fp_email = (EditText) findViewById(R.id.editText_fp_email);
        fp_email.addTextChangedListener(new MyTextWatcher(fp_email));

        button = (Button) findViewById(R.id.button_mail);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMail();
            }
        });

    }

    public void sendMail() {
        if (!validateEmail()) {
            return;
        }

        final String finalEmail = fp_email.getText().toString().trim();

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sending OTP...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_OTP_SEND,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();
                        try {
                            String finalresponse_mssg;
                            JSONObject jsonObject = new JSONObject(response);
                            finalresponse_mssg = jsonObject.getString("error");

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if (finalresponse_mssg.equalsIgnoreCase("false")) {
                                final String uid = jsonObject.getString("user_id");
                                Intent intent = new Intent(ForgotPasswordActivity.this, OtpPasswordActivity.class);
                                intent.putExtra("user_id", uid);
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
                params.put("email", finalEmail);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private boolean validateEmail() {
        String email = fp_email.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutFpEmail.setError("Enter valid email address");
            requestFocus(fp_email);
            return false;
        } else {
            inputLayoutFpEmail.setErrorEnabled(false);
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
                case R.id.editText_fp_email:
                    validateEmail();
                    break;
            }
        }
    }
}
