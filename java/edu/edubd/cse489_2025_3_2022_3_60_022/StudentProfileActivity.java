package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentProfileActivity extends AppCompatActivity {

    private EditText etStudentID, etStudentName, etStudentEmail, etStudentContactNumber;
    private ImageView ivStudentImg;

    private KeyValueDB stdDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        etStudentID = findViewById(R.id.etStudentID);
        etStudentName = findViewById(R.id.etStudentName);
        etStudentEmail = findViewById(R.id.etStudentEmail);
        etStudentContactNumber = findViewById(R.id.etStudentContactNumber);
        ivStudentImg = findViewById(R.id.ivStudentImg);

        stdDb = new KeyValueDB(this);

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

        if (!std.validateStudentIfNotThenDo((info) -> {
            Toast.makeText(StudentProfileActivity.this, info, Toast.LENGTH_SHORT).show();
        })) {
            return;
        }

        String key = "STD_" + std.getId();
        String value = std.toString();

        if (stdDb.insertKeyValue(key, value)) {
            Toast.makeText(this, "New student profile has been saved.", Toast.LENGTH_SHORT).show();
        } else if (stdDb.updateValueByKey(key, value)) {
            Toast.makeText(this, "Student profile has been updated.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            return;
        }
        finish();
    }

}
