package com.example.solvare.grouplex.menu.account;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.example.solvare.grouplex.startup.RequestHandler;
import com.example.solvare.grouplex.startup.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.solvare.grouplex.startup.SharedPrefManager.KEY_ID;
import static com.example.solvare.grouplex.startup.SharedPrefManager.KEY_USERNAME;
import static com.example.solvare.grouplex.startup.SharedPrefManager.SHARED_PREF_NAME;

public class ChangeNameActivity extends AppCompatActivity {

    private TextInputLayout inputLayoutName;
    private EditText new_name;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);

        final SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String old_name = sharedPreferences.getString(KEY_USERNAME, null);

        new_name = (EditText) findViewById(R.id.editText_change_name);
        new_name.setText(old_name);
        new_name.setSelection(new_name.getText().length());

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_change_name);

        button = (Button) findViewById(R.id.button_change_name);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeName(sharedPreferences);
            }
        });
    }

    public void changeName(final SharedPreferences sharedPreferences) {

        if (!validateName()) {
            return;
        }

        final String user_id = sharedPreferences.getString(KEY_ID, null);
        final String finalFull_name = new_name.getText().toString().trim();

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Updating...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_CHANGE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("error").equalsIgnoreCase("false")) {
                                SharedPrefManager.getInstance(getApplicationContext()).changeName(finalFull_name);
                            }
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
                params.put("new_uname", finalFull_name);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private boolean validateName() {
        if (new_name.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError("Enter your full name");
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

}
