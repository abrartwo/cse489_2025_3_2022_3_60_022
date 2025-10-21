package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText etUserIdLogin, etPasswordLogin;
    CheckBox cbRememberUserLogin, cbRememberPasswordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserIdLogin = findViewById(R.id.etUserIdLogin);
        etPasswordLogin = findViewById(R.id.etPasswordLogin);
        cbRememberUserLogin = findViewById(R.id.cbRememberUserLogin);
        cbRememberPasswordLogin = findViewById(R.id.cbRememberPasswordLogin);

        findViewById(R.id.btnHaveAcc).setOnClickListener((v) -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });
        findViewById(R.id.btnExitLogin).setOnClickListener((v) -> {
            finish();
        });
        findViewById(R.id.btnLoginGo).setOnClickListener((v) -> {
            String userId = etUserIdLogin.getText().toString().trim();
            String password = etPasswordLogin.getText().toString().trim();
            boolean isRememberUser = cbRememberUserLogin.isChecked();
            boolean isRememberPassword = cbRememberPasswordLogin.isChecked();

            Validator.ThenDoEvent event = (info) -> Toast.makeText(LoginActivity.this, info, Toast.LENGTH_LONG).show();

            if (!Validator.validateUserId(event, userId) || !Validator.validatePassword(event, password)) {
                return;
            }

            // TODO: Save login details;
        });

    }

}