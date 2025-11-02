package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import edu.edubd.cse489_2025_3_2022_3_60_022.helpers.Student;

public class MainActivity extends AppCompatActivity {
    private EditText etCourseCode, etDate;
    private ListView lvStudents;

    private ActivityResultLauncher<Intent> studentProfileLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCourseCode = findViewById(R.id.etCourseCode);
        etDate = findViewById(R.id.etDate);
        lvStudents = findViewById(R.id.lvStudents);

        findViewById(R.id.btnExit).setOnClickListener((v) -> finish());

        findViewById(R.id.btnAddStudent).setOnClickListener((v) -> {
            studentProfileLauncher.launch(new Intent(MainActivity.this, StudentProfileActivity.class));
        });

        studentProfileLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                (result) -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent it = result.getData();
                        if (it == null) {
                            return;
                        }
                        Bundle bundle = it.getExtras();
                        if (bundle == null) {
                            return;
                        }

                        Student std = (Student) bundle.getSerializable("EXTRA_STD");
                        // TODO: Save in a container.
                        Toast.makeText(this, "Student Saved", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
