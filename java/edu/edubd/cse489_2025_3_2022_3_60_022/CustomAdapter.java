package edu.edubd.cse489_2025_3_2022_3_60_022;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends ArrayAdapter<Attendence> {
    Context context;
    List<Attendence> arrayList;
    LayoutInflater inflater;

    public CustomAdapter(Context context, List<Attendence> arrayList) {
        super(context, R.layout.row_attendence, arrayList);
        this.context = context;
        this.arrayList = arrayList;

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

        Attendence att = this.arrayList.get(position);
        tvSid.setText(att.getSid());
        tvName.setText(att.getName());
        rbPresent.setChecked(att.isPresent());

        return rowView;
    }
}
