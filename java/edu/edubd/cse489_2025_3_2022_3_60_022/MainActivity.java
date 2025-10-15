package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etCourseCode, etDate;
    private Button btnExit, btnAddStudent;
    private ListView lvStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCourseCode = findViewById(R.id.etCourseCode);
        etDate = findViewById(R.id.etDate);
        btnExit = findViewById(R.id.btnExit);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        lvStudents = findViewById(R.id.lvStudents);

        btnExit.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            }
        );

        btnAddStudent.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(
                        new Intent(
                            MainActivity.this,
                            StudentProfileActivity.class
                        )
                    );
                }
            }
        );
    }

    public boolean verifyCourseCode(Context c, String courseCode) {
        if (courseCode.length() != 6 && courseCode.length() != 7) {
            Toast.makeText(
                    c,
                    "Course code must be 6 or 7 char long",
                    Toast.LENGTH_LONG
            ).show();
            return false;
        }
        return true;
    }

    public boolean verifyDate(Context c, String date) {
        // TODO: NOT IMPLEMENTED ;
        return true;
    }
}
