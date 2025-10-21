package edu.edubd.cse489_2025_3_2022_3_60_022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    EditText etUserIdSignUp, etEmailSignUp, etPasswordSignUp, etRetypePasswordSignUp;
    CheckBox cbRememberUserSignUp, cbRememberLoginSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUserIdSignUp = findViewById(R.id.etUserIdSignUp);
        etEmailSignUp = findViewById(R.id.etEmailSignUp);
        etPasswordSignUp = findViewById(R.id.etPasswordSignUp);
        etRetypePasswordSignUp = findViewById(R.id.etRetypePasswordSignUp);
        cbRememberUserSignUp = findViewById(R.id.cbRememberUserSignUp);
        cbRememberLoginSignUp = findViewById(R.id.cbRememberLoginSignUp);

        findViewById(R.id.btnHaveAcc).setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
        });
        findViewById(R.id.btnExitSignUp).setOnClickListener(v -> {
            finish();
        });
        findViewById(R.id.btnSignUpGo).setOnClickListener(v -> {
            String userId = etUserIdSignUp.getText().toString().trim();
            String email = etEmailSignUp.getText().toString().trim();
            String password = etPasswordSignUp.getText().toString().trim();
            String RetypePassword = etRetypePasswordSignUp.getText().toString().trim();
            boolean isRememberUser = cbRememberUserSignUp.isChecked();
            boolean isRememberLogin = cbRememberLoginSignUp.isChecked();

            Validator.ThenDoEvent event = (info) -> Toast.makeText(SignUpActivity.this, info, Toast.LENGTH_LONG).show();

            if (!Validator.validateUserId(event, userId) || !Validator.validateEmail(event, email) || !Validator.validatePassword(event, password)) {
                return;
            }
            if (!password.equals(RetypePassword)) {
                Toast.makeText(SignUpActivity.this, "Password did not matched", Toast.LENGTH_LONG).show();
                return;
            }

            // TODO: Save signup details;
        });
    }
}