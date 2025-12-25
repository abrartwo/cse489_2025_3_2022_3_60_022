package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends ArrayAdapter<Attendance> {
    private final KeyValueDB db;
    Context context;
    List<Attendance> arrayList;
    LayoutInflater inflater;

    public CustomAdapter(Context context, List<Attendance> arrayList) {
        super(context, R.layout.row_attendence, arrayList);
        this.context = context;
        this.arrayList = arrayList;
        db = new KeyValueDB(context);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.row_attendence, parent, false);

        TextView tvSid = rowView.findViewById(R.id.tvSidMain);
        TextView tvName = rowView.findViewById(R.id.tvSNameMain);
        RadioButton rbPresent = rowView.findViewById(R.id.rbPMain);
        RadioButton rbAbsent = rowView.findViewById(R.id.rbAMain);
        TextView tvStats = rowView.findViewById(R.id.tvStatMain);

        Attendance att = this.arrayList.get(position);
        tvSid.setText(att.getSid());
        tvName.setText(att.getName());
        rbPresent.setChecked(att.isPresent());

        tvSid.setOnClickListener(v -> selectStudent(att.getSid()));
        tvSid.setOnLongClickListener(v -> showConcentBox(att.getSid(), position));

        return rowView;
    }

    private boolean showConcentBox(String sid, int postion) {
        String key = "std_" + sid;
        new AlertDialog.Builder(context)
                .setTitle("Warning")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    removeStudent(key);
                    arrayList.remove(postion);
                    notifyDataSetChanged();
                })
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                }).create().show();
        return true;
    }

    private void removeStudent(String key) {
        KeyValueDB db = new KeyValueDB(context);
        db.deleteDataByKey(key);
        String[] keys = {"action", "sid", "semester", "key"};
        String[] values = {"remove", "2022-3-60-022", "2025-3", key};
        httpRequest(keys, values);
    }

    @SuppressLint("StaticFieldLeak")
    private void httpRequest(final String[] keys, final String[] values) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                List<NameValuePair> params = new ArrayList<>();
                for (int i = 0; i < keys.length; i++) {
                    params.add(new BasicNameValuePair(keys[i], values[i]));
                }
                String url = "https://www.muthosoft.com/univ/cse489/key_value.php";
                try {
                    RemoteAccess ra = new RemoteAccess();
                    String data = ra.makeHttpRequest(url, "POST", params);
                    return data;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(String data) {
                if (data != null) {
                    System.out.println(data);
                }
            }
        }.execute();
    }

    private void selectStudent(String studentId) {
        String key = "STD_" + studentId;
        String q = String.format(
                "SELECT * FROM %s WHERE %s = ?",
                db.TABLE_KEY_VALUE,
                db.KEY
        );
        Cursor c = db.execute(q, new String[]{"STD_" + studentId});
        if (c == null) {
            db.close();
            return;
        }
        while (c.moveToNext()) {
            String value = c.getString(1);

            Intent it = new Intent(context, StudentProfileActivity.class)
                    .putExtra("STD", Student.parse(value));
            context.startActivity(it);
            break;
        }
        c.close();
        db.close();
    }
}
