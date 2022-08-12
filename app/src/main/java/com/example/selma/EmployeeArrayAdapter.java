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

public class EmployeeArrayAdapter extends ArrayAdapter {
    Context context;
    int resource;
    List<Employee> employeelist;

    public EmployeeArrayAdapter(@NonNull Context context, int resource, List<Employee> employeelist) {
        super(context, resource, employeelist);

        this.context = context;
        this.resource = resource;
        this.employeelist = employeelist;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);


        TextView name =row.findViewById(R.id.textName);
        TextView empnumber = row.findViewById(R.id.textEMPNumber);
        TextView nic = row.findViewById(R.id.textNicnumber);
        TextView address = row.findViewById(R.id.textAddress);
        TextView email = row.findViewById(R.id.textEmail);
        TextView phone = row.findViewById(R.id.textPhone);

        Employee employee = employeelist.get(position);

        name.setText(employee.getName());
        empnumber.setText(employee.getEmp_number());
        nic.setText(employee.getNic());
        address.setText(employee.getAddress());
        email.setText(employee.getEmail());
        phone.setText(String.valueOf(employee.getPhonenumber()));

        return row;
    }
}
