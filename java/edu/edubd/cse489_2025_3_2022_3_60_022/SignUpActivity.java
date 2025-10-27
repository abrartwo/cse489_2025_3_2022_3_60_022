package edu.edubd.cse489_2025_3_2022_3_60_022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    EditText etUserIdSignUp, etEmailSignUp, etPasswordSignUp, etRetypePasswordSignUp;
    CheckBox cbRememberUserSignUp, cbRememberPasswordSignUp;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("signup_info", MODE_PRIVATE);
        String storedUserid = sp.getString("USER_ID", "");
        if (!storedUserid.equals("")) {
            boolean storedPasswordRemember = sp.getBoolean("REMEMBER_PASSWORD", false);
            if (storedPasswordRemember) {
                moveToMainActivity();
            } else {
                String storedPassword = sp.getString("PASSWORD", "");
                boolean storedUserRemember = sp.getBoolean("REMEMBER_USER", false);
                moveToLoginActivity(storedUserid, storedPassword, storedUserRemember);
            }
            finishAffinity();
            return;
        }

        setContentView(R.layout.activity_sign_up);

        etUserIdSignUp = findViewById(R.id.etUserIdSignUp);
        etEmailSignUp = findViewById(R.id.etEmailSignUp);
        etPasswordSignUp = findViewById(R.id.etPasswordSignUp);
        etRetypePasswordSignUp = findViewById(R.id.etRetypePasswordSignUp);
        cbRememberUserSignUp = findViewById(R.id.cbRememberUserSignUp);
        cbRememberPasswordSignUp = findViewById(R.id.cbRememberPasswordSignUp);

        findViewById(R.id.btnHaveAcc).setOnClickListener(v -> {
            moveToLoginActivity("", "", false);
        });
        findViewById(R.id.btnExitSignUp).setOnClickListener(v -> {
            finish();
        });
        findViewById(R.id.btnSignUpGo).setOnClickListener(v -> {
            processSignUp();
        });
    }

    private void processSignUp() {
        String userId = etUserIdSignUp.getText().toString().trim();
        String email = etEmailSignUp.getText().toString().trim();
        String password = etPasswordSignUp.getText().toString().trim();
        String RetypePassword = etRetypePasswordSignUp.getText().toString().trim();
        boolean isRememberUser = cbRememberUserSignUp.isChecked();
        boolean isRememberPassword = cbRememberPasswordSignUp.isChecked();

        Validator.ThenDoEvent event = (info) -> Toast.makeText(SignUpActivity.this, info, Toast.LENGTH_LONG).show();

        if (!Validator.validateUserId(event, userId) || !Validator.validateEmail(event, email) || !Validator.validatePassword(event, password)) {
            return;
        }
        if (!password.equals(RetypePassword)) {
            Toast.makeText(SignUpActivity.this, "Password did not matched", Toast.LENGTH_LONG).show();
            return;
        }

        SharedPreferences.Editor ed = sp.edit();
        ed.putString("USER_ID", userId);
        ed.putString("PASSWORD", password);
        ed.putBoolean("REMEMBER_USER", isRememberUser);
        ed.putBoolean("REMEMBER_PASSWORD", isRememberPassword);
        ed.apply();

        moveToMainActivity();
    }

    private void moveToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void moveToLoginActivity(String userid, String password, boolean rememberUser) {
        Intent i = new Intent(this, LoginActivity.class);
        i.putExtra("USER_ID", userid);
        i.putExtra("PASSWORD", password);
        i.putExtra("REMEMBER_USER", rememberUser);
        startActivity(i);
    }
}