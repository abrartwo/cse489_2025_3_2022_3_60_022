package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.edubd.cse489_2025_3_2022_3_60_022.helpers.AccountCredentialManager;
import edu.edubd.cse489_2025_3_2022_3_60_022.helpers.Validator;

public class LoginActivity extends AppCompatActivity {
    EditText etUserIdLogin, etPasswordLogin;
    CheckBox cbRememberUserLogin, cbRememberPasswordLogin;
    String userId, password;
    boolean rememberUser;

    private AccountCredentialManager acm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        acm = AccountCredentialManager.openSharedPreferences(this);

        Intent i = getIntent();
        etUserIdLogin = findViewById(R.id.etUserIdLogin);
        etPasswordLogin = findViewById(R.id.etPasswordLogin);
        cbRememberUserLogin = findViewById(R.id.cbRememberUserLogin);
        cbRememberPasswordLogin = findViewById(R.id.cbRememberPasswordLogin);

        if (i != null) {
            userId = i.getStringExtra("USER_ID");
            password = i.getStringExtra("PASSWORD");
            rememberUser = i.getBooleanExtra("REMEMBER_USER", false);
            if (rememberUser) {
                etUserIdLogin.setText(userId);
                cbRememberUserLogin.setChecked(rememberUser);
            }
        }

        findViewById(R.id.btnHaveAcc).setOnClickListener((v) -> moveToMainActivity());
        findViewById(R.id.btnExitLogin).setOnClickListener((v) -> finish());
        findViewById(R.id.btnLoginGo).setOnClickListener((v) -> processLogin());
    }

    private void moveToMainActivity() {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        finishAffinity();
    }

    private void processLogin() {
        String userId = etUserIdLogin.getText().toString().trim();
        String password = etPasswordLogin.getText().toString().trim();
        boolean isRememberUser = cbRememberUserLogin.isChecked();
        boolean isRememberPassword = cbRememberPasswordLogin.isChecked();

        Validator.ThenDoEvent event = (info) -> Toast.makeText(LoginActivity.this, info, Toast.LENGTH_LONG).show();

        if (!Validator.validateUserId(event, userId) || !Validator.validatePassword(event, password)) {
            return;
        }
        if (!this.userId.equals(userId) || !this.password.equals(password)) {
            event.callback("Invalid username or password");
            return;
        }

        acm.newEditor()
                .updateUserId(userId)
                .updatePassword(password)
                .updateRememberUser(isRememberUser)
                .updateRememberPassword(isRememberPassword).apply();
        finishAffinity();
    }
}