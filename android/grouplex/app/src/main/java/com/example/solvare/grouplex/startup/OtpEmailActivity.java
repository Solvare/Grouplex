package com.example.solvare.grouplex.startup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
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
import com.example.solvare.grouplex.custom.SendOtp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.solvare.grouplex.startup.SharedPrefManager.KEY_EMAIL;
import static com.example.solvare.grouplex.startup.SharedPrefManager.KEY_ID;
import static com.example.solvare.grouplex.startup.SharedPrefManager.SHARED_PREF_NAME;

public class OtpEmailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout inputLayoutOtp;
    private EditText otp;
    private Button button_verify, button_resend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_email);

        otp = (EditText) findViewById(R.id.editText_email_verify);
        inputLayoutOtp = (TextInputLayout) findViewById(R.id.input_layout_email_verify);

        button_verify = (Button) findViewById(R.id.button_email_verify);
        button_resend = (Button) findViewById(R.id.button_resend_otp);

        button_verify.setOnClickListener(this);
        button_resend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        if (v == button_verify) verifyEmail(sharedPreferences);
        else if (v == button_resend) {
            SendOtp.send_mail(getApplicationContext(), email);
        }
    }

    public void verifyEmail(final SharedPreferences sharedPreferences) {

        if (!validateOtp()) {
            return;
        }

        final String user_id = sharedPreferences.getString(KEY_ID, null);
        final String finalOtp = otp.getText().toString().trim();

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Verifying...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_OTP_EMAIL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if (jsonObject.getString("error").equalsIgnoreCase("false")) {
                                SharedPrefManager.getInstance(getApplicationContext()).verifyEmail(true);
                                Intent intent = new Intent(OtpEmailActivity.this, MyGroupsActivity.class);
                                startActivity(intent);
                                finish();
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
                params.put("otp", finalOtp);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private boolean validateOtp() {
        if (otp.getText().toString().trim().isEmpty()) {
            inputLayoutOtp.setError("Enter OTP sent to your email");
            return false;
        } else {
            inputLayoutOtp.setErrorEnabled(false);
        }

        return true;
    }

    public void resendOtp() {

    }
}
