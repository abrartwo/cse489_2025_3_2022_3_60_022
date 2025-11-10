package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText etCourseCode, etDate;
    private ListView lvStudents;

    private List<Attendence> attendances;
    private CustomAdapter customAdapter;
    private KeyValueDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCourseCode = findViewById(R.id.etCourseCode);
        etDate = findViewById(R.id.etDate);
        lvStudents = findViewById(R.id.lvStudents);

        attendances = new ArrayList<>();
        customAdapter = new CustomAdapter(this, attendances);
        lvStudents.setAdapter(customAdapter);

        findViewById(R.id.btnExit).setOnClickListener((v) -> finish());

        findViewById(R.id.btnAddStudent).setOnClickListener((v) ->
                startActivity(new Intent(MainActivity.this, StudentProfileActivity.class))
        );

    }

    @Override
    protected void onStart() {
        super.onStart();

        loadStudentsFromLocalDB();
    }

    private void loadStudentsFromLocalDB() {
        attendances.clear();
        String q = "SELECT * FROM key_value_pairs WHERE 'STD_%'";
        Cursor c = db.execute(q);
        if (c == null) {
            db.close();
        }
        while (c.moveToNext()) {
            String key = c.getString(0);
            String value = c.getString(1);

            String[] stdInfo = value.split(";");
            String sid = stdInfo[0];
            String name = stdInfo[1];

            Attendence att = new Attendence(sid, name, false);
            attendances.add(att);
        }
        customAdapter.notifyDataSetChanged();
        db.close();
    }
}
