package com.example.solvare.grouplex.menu.account;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
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
import com.example.solvare.grouplex.startup.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.solvare.grouplex.startup.SharedPrefManager.KEY_ID;
import static com.example.solvare.grouplex.startup.SharedPrefManager.SHARED_PREF_NAME;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText cur_pass, new_pass, conf_pass;
    private TextInputLayout inputLayoutCur, inputLayoutNew, inputLayoutConf;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        cur_pass = (EditText) findViewById(R.id.editText_cur_pass);
        new_pass = (EditText) findViewById(R.id.editText_new_pass);
        conf_pass = (EditText) findViewById(R.id.editText_conf_pass);

        inputLayoutCur = (TextInputLayout) findViewById(R.id.input_layout_cur_pass);
        inputLayoutNew = (TextInputLayout) findViewById(R.id.input_layout_new_pass);
        inputLayoutConf = (TextInputLayout) findViewById(R.id.input_layout_conf_pass);

        button = (Button) findViewById(R.id.button_change_pass);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changePassword();
            }
        });

        cur_pass.addTextChangedListener(new MyTextWatcher(cur_pass));
        new_pass.addTextChangedListener(new MyTextWatcher(new_pass));
        conf_pass.addTextChangedListener(new MyTextWatcher(conf_pass));
    }

    public void changePassword() {

        if (!validateCurPassword()) {
            return;
        }

        if (!validateNewPassword()) {
            return;
        }

        if (!validateConfPassword()) {
            return;
        }

        final SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String user_id = sharedPreferences.getString(KEY_ID, null);

        final String final_cur_pass = cur_pass.getText().toString();
        final String final_new_pass = new_pass.getText().toString();

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Updating...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_CHANGE_PASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();
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

    private boolean validateCurPassword() {
        if (cur_pass.getText().toString().isEmpty()) {
            inputLayoutCur.setError("Enter your current password");
            requestFocus(cur_pass);
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
                case R.id.editText_cur_pass:
                    validateCurPassword();
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
