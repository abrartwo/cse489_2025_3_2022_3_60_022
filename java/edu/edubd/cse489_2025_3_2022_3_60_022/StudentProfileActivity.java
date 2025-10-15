package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
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

        if (
            !verifyStudentId(this, studentId) ||
            !verifyName(this, studentName) ||
            !verifyEmail(this, studentEmail) ||
            !verifyPhone(this, phoneNumber)
        ) {
            return;
        }

        System.out.println("Student ID    : " + studentId);
        System.out.println("Student Name  : " + studentName);
        System.out.println("Student Email : " + studentEmail);
        System.out.println("Student Phone : " + phoneNumber);
    }

    public boolean verifyStudentId(Context c, String studentId) {
        // TODO: NOT IMPLEMENTED ;
        return false;
    }

    public boolean verifyName(Context c, String studentName) {
        // TODO: NOT IMPLEMENTED ;
        return false;
    }

    public boolean verifyEmail(Context c, String email) {
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}(?:\\.[A-Za-z]{2,})*$")) {
            Toast.makeText(c, "Invalid Email Address", Toast.LENGTH_LONG).show();
        }
        return isValid;
    }

    public boolean verifyPhone(Context c, String phone) {
        // TODO: NOT IMPLEMENTED ;
        return false;
    }

    public boolean verifyCourseCode(Context c, String courseCode) {
        // TODO: NOT IMPLEMENTED ;
        return false;
    }

    public boolean verifyDate(Context c, String date) {
        // TODO: NOT IMPLEMENTED ;
        return false;
    }
}
