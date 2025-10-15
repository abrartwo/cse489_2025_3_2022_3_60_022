package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentProfileActivity extends AppCompatActivity {

    private EditText etStudentID, etStudentName, etStudentEmail, etStudentContactNumber;
    private ImageView ivStudentImg;
    // private Button btnCancel, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        etStudentID = findViewById(R.id.etStudentID);
        etStudentName = findViewById(R.id.etStudentName);
        etStudentEmail = findViewById(R.id.etStudentEmail);
        etStudentContactNumber = findViewById(R.id.etStudentContactNumber);
        ivStudentImg = findViewById(R.id.ivStudentImg);

        findViewById(R.id.btnCancel).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });
    }

    private void saveProfile() {
        Student std = new StudentBuilder()
                .addId(etStudentID.getText().toString().trim())
                .addName(etStudentName.getText().toString().trim())
                .addEmail(etStudentEmail.getText().toString().trim())
                .addPhone(etStudentContactNumber.getText().toString().trim())
                .build();

        var isValid = std.validateAllIfNotThenDo(new Validator.ThenDoEvent() {
            @Override
            public void callback(String info) {
                Toast.makeText(StudentProfileActivity.this, info, Toast.LENGTH_SHORT).show();
            }
        });
        if (!isValid) {
            return;
        }

        var b = new Bundle();
        b.putSerializable("EXTRA_STD", std);

        Intent i = new Intent();
    }

}
