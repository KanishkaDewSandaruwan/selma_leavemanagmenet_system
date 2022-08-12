package com.example.selma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LeaveHistoryArrayAdapter extends ArrayAdapter {

    Context context;
    int resource;
    List<Leave> leavelist;

    public LeaveHistoryArrayAdapter(@NonNull Context context, int resource, List<Leave> leavelist) {
        super(context, resource, leavelist);

        this.context = context;
        this.resource = resource;
        this.leavelist = leavelist;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);


        TextView startDate =row.findViewById(R.id.txtStartDateOne);
        TextView endDate = row.findViewById(R.id.txtEndDateOne);
        TextView status = row.findViewById(R.id.txtAcceptOne);


        Leave leave = leavelist.get(position);

        startDate.setText(leave.getLeave_start_date());
        endDate.setText(leave.getLeave_end_date());
        status.setText(leave.getAccept());


        return row;
    }
}
