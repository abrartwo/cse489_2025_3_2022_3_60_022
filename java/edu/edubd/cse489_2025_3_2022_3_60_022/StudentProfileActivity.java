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

        btnCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );

        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveProfile();
                    }
                }
        );
    }

    private void saveProfile() {
        String studentId = etStudentID.getText().toString().trim();
        String studentName = etStudentName.getText().toString().trim();
        String studentEmail = etStudentEmail.getText().toString().trim();
        String phoneNumber = etStudentContactNumber.getText().toString().trim();

        if (!verifyStudentId(this, studentId) ||
                !verifyName(this, studentName) ||
                !verifyEmail(this, studentEmail) ||
                !verifyPhone(this, phoneNumber)) {
            return;
        }

        System.out.println("Student ID    : " + studentId);
        System.out.println("Student Name  : " + studentName);
        System.out.println("Student Email : " + studentEmail);
        System.out.println("Student Phone : " + phoneNumber);
    }

    public boolean verifyStudentId(Context c, String studentId) {
        if (studentId.isEmpty()) {
            Toast.makeText(c, "Student ID is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        var isValid = true;
        String[] tokens = studentId.split("-");

        if (studentId.length() < 13) {
            isValid = false;
        } else if (tokens.length != 4) {
            isValid = false;
        } else {
            for (var tok : tokens) {
                try {
                    Integer.parseInt(tok);
                } catch (NumberFormatException nfe) {
                    isValid = false;
                }
            }
        }

        if (!isValid) {
            Toast.makeText(c, "Invalid Student ID", Toast.LENGTH_LONG).show();
        }
        return isValid;
    }

    public boolean verifyName(Context c, String studentName) {
        if (studentName.isEmpty()) {
            Toast.makeText(c, "Student name is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (studentName.length() < 2) {
            Toast.makeText(c, "Name is too short. Born again and change your name.", Toast.LENGTH_LONG).show();
            return false;
        }
        if (studentName.matches(".*\\d.*")) {
            Toast.makeText(c, "You have a number on your name??????.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean verifyEmail(Context c, String email) {
        if (email.isEmpty()) {
            Toast.makeText(c, "Student Email is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        var isValid = true;
        String[] tokens = email.split("@");

        if (email.length() < 5) {
            isValid = false;
        } else if (tokens.length != 2) {
            isValid = false;
        } else if (tokens[1].split("\\.").length != 2) {
            isValid = false;
        }

        if (!isValid) {
            Toast.makeText(c, "Not an email", Toast.LENGTH_LONG).show();
        }
        return isValid;
    }

    public boolean verifyPhone(Context c, String phone) {
        if (phone.isEmpty()) {
            Toast.makeText(c, "Phone number is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phone.length() < 10 || phone.length() > 20) {
            Toast.makeText(
                    c,
                    "Phone must be under 10 to 20 char long",
                    Toast.LENGTH_LONG
            ).show();
            return false;
        } else if (phone.matches(".*[a-zA-Z].*")) {
            Toast.makeText(
                    c,
                    "Phone can not contain alphabetic character.",
                    Toast.LENGTH_LONG
            ).show();
            return false;
        }
        return true;
    }
}
