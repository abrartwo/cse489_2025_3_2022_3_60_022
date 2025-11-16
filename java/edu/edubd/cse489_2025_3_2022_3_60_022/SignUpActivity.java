package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.edubd.cse489_2025_3_2022_3_60_022.helpers.AccountCredentialManager;
import edu.edubd.cse489_2025_3_2022_3_60_022.helpers.Validator;

public class SignUpActivity extends AppCompatActivity {
    EditText etUserIdSignUp, etEmailSignUp, etPasswordSignUp, etRetypePasswordSignUp;
    CheckBox cbRememberUserSignUp, cbRememberPasswordSignUp;

    private AccountCredentialManager acm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        acm = AccountCredentialManager.openSharedPreferences(this);

        if (acm.isUserExists()) {
            if (acm.isRememberPassword()) {
                moveToMainActivity();
            } else {
                String storedUserid = acm.getUserId();
                String storedPassword = acm.getPassword();
                boolean storedUserRemember = acm.isRememberUser();
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

        findViewById(R.id.btnHaveAcc).setOnClickListener(v ->
            moveToLoginActivity("", "", false)
        );
        findViewById(R.id.btnExitSignUp).setOnClickListener(v ->
            finish()
        );
        findViewById(R.id.btnSignUpGo).setOnClickListener(v ->
            processSignUp()
        );
    }

    private void processSignUp() {
        String userId = etUserIdSignUp.getText().toString().trim();
        String email = etEmailSignUp.getText().toString().trim();
        String password = etPasswordSignUp.getText().toString().trim();
        String RetypePassword = etRetypePasswordSignUp.getText().toString().trim();
        boolean isRememberUser = cbRememberUserSignUp.isChecked();
        boolean isRememberPassword = cbRememberPasswordSignUp.isChecked();

        Validator.ThenDoEvent event = (info) -> Toast.makeText(SignUpActivity.this, info, Toast.LENGTH_LONG).show();

        if (!Validator.validateUserId(event, userId) ||
            !Validator.validateEmail(event, email) ||
            !Validator.validatePassword(event, password)) {
            return;
        }
        if (!password.equals(RetypePassword)) {
            Toast.makeText(SignUpActivity.this, "Password did not matched", Toast.LENGTH_LONG).show();
            return;
        }

        acm.newEditor()
            .updateUserId(userId)
            .updatePassword(password)
            .updateRememberUser(isRememberUser)
            .updateRememberPassword(isRememberPassword)
            .apply();

        moveToMainActivity();
    }

    private void moveToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }

    private void moveToLoginActivity(String userid, String password, boolean rememberUser) {
        startActivity(
            new Intent(this, LoginActivity.class)
                .putExtra("USER_ID", userid)
                .putExtra("PASSWORD", password)
                .putExtra("REMEMBER_USER", rememberUser)
        );
    }
}