package com.example.solvare.grouplex.startup;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.solvare.grouplex.R;

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

    public void sendMail()
    {
        if (!validateEmail()) {
            return;
        }

        final String finalEmail = fp_email.getText().toString().trim();
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
