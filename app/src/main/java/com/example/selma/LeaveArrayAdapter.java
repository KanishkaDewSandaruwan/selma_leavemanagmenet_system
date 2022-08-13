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

public class LeaveArrayAdapter extends ArrayAdapter {

    Context context;
    int resource;
    List<Leave> leavelist;

    public LeaveArrayAdapter(@NonNull Context context, int resource, List<Leave> leavelist) {
        super(context, resource, leavelist);

        this.context = context;
        this.resource = resource;
        this.leavelist = leavelist;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView leaveID =row.findViewById(R.id.txtLeaveIDS);
        TextView startDate =row.findViewById(R.id.txtStartDates);
        TextView endDate = row.findViewById(R.id.txtEndDates);
        TextView status = row.findViewById(R.id.txtStatusPending);
        TextView empNumber = row.findViewById(R.id.txtEmpNumber);


        Leave leave = leavelist.get(position);

        leaveID.setText("#" + String.valueOf(leave.getLeave_id()));
        startDate.setText("Start Date : " + leave.getLeave_start_date());
        endDate.setText("End Date : " + leave.getLeave_end_date());
        empNumber.setText(leave.getEmpid());
        status.setText(leave.getAccept());


        return row;
    }
}
