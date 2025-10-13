package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class StudentProfileActivity extends AppCompatActivity {
    private EditText etStudentID, etStudentName, etStudentEmail, etStudentContactNumber;
    private ImageView ivStudentImg;
    private Button btnCancel, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        etStudentID = findViewById(R.id.etStudentID);
        etStudentName = findViewById(R.id.etStudentName);
        etStudentEmail = findViewById(R.id.etStudentEmail);
        etStudentContactNumber = findViewById(R.id.etStudentContactNumber);
        ivStudentImg = findViewById(R.id.ivStudentImg);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });
    }

    private void saveProfile() {
        String studentId = etStudentID.getText().toString().trim();
        String studentName = etStudentName.getText().toString().trim();
        String studentEmail = etStudentEmail.getText().toString().trim();
        String phoneNumber = etStudentContactNumber.getText().toString().trim();

        var verifier = Verifier.getInstance();
        if (!verifier.verifyStudentId(this, studentId) ||
                !verifier.verifyName(this, studentName) ||
                !verifier.verifyEmail(this, studentEmail) ||
                !verifier.verifyPhone(this, phoneNumber)) {
            return;
        }

        System.out.println("Student ID    : " + studentId);
        System.out.println("Student Name  : " + studentName);
        System.out.println("Student Email : " + studentEmail);
        System.out.println("Student Phone : " + phoneNumber);
    }
}
