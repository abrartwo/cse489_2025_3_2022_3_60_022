package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;


public class CustomAdapter extends ArrayAdapter<Attendance> {
    Context context;
    List<Attendance> arrayList;
    LayoutInflater inflater;
    private final KeyValueDB db;

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

        return rowView;
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
