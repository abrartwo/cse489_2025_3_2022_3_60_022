package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

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

        Intent i = getIntent();
        if (i != null) {
            if (i.hasExtra("STD")) {
                Student std = (Student) i.getSerializableExtra("STD");
                etStudentID.setText(std.getId());
                etStudentName.setText(std.getName());
                etStudentEmail.setText(std.getEmail());
                etStudentContactNumber.setText(std.getPhoneNumber());
            }
        }

        stdDb = new KeyValueDB(this);

        findViewById(R.id.btnCancel).setOnClickListener(v -> finish());
        findViewById(R.id.btnSave).setOnClickListener(v -> saveProfile());
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

        String key = "std_" + std.getId();
        String value = std.toString();

        if (stdDb.insertKeyValue(key, value)) {
            Toast.makeText(this, "New student profile has been saved.", Toast.LENGTH_SHORT).show();
        } else if (stdDb.updateValueByKey(key, value)) {
            Toast.makeText(this, "Student profile has been updated.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Something went wrong ", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] keys = {"action", "sid", "sem", "key", "value"};
        String[] values = {"backup", "2022-3-60-022", "2025-3", key, value};
        httpRequest(keys, values);
        finish();
    }

    @SuppressLint("StaticFieldLeak")
    private void httpRequest(final String[] keys, final String[] values) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for (int i = 0; i < keys.length; i++) {
                    params.add(new BasicNameValuePair(keys[i], values[i]));
                }
                String url = "https://www.muthosoft.com/univ/cse489/key_value.php";
                try {
                    RemoteAccess ar = new RemoteAccess();
                    String data = ar.makeHttpRequest(url, "POST", params);
                    return data;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(String data) {
                if (data != null) {
                    Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

}
