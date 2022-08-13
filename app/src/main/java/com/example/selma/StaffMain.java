package com.example.selma;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StaffMain extends AppCompatActivity {

    DBHandler dbHandler;
    Context context;
    LeaveArrayAdapter leaveArrayAdapter;
    ListView leavelistView;
    Leave leave;
    List<Leave> leaveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_main);

        Button btnRegister, btnEmployeeList;


        context = this;
        leave = new Leave();
        leaveList = new ArrayList<>();
        dbHandler = new DBHandler(context);

        leaveList = dbHandler.getAllLeave();

        leavelistView = findViewById(R.id.listViewLeave);
        leaveArrayAdapter = new LeaveArrayAdapter(context, R.layout.one_leave,leaveList);
        leavelistView.setAdapter(leaveArrayAdapter);


        leavelistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Leave leave = leaveList.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Request Manage");

                builder.setNeutralButton("Employee Details", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, EmployeeProfile.class);
                        intent.putExtra("EMP_ID",leave.getEmpid());
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.updateCancelRequest(leave.getLeave_id());
                        startActivity(new Intent(context, StaffMain.class));
                        Toast.makeText(context, "Employee Request Cancel Success!", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.updateAcceptRequest(leave.getLeave_id());
                        startActivity(new Intent(context, StaffMain.class));
                        Toast.makeText(context, "Employee Request Accepted!", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });

        btnRegister = findViewById(R.id.btnRegister);
        btnEmployeeList = findViewById(R.id.btnEmployeeList);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EmployeeRegister.class);
                startActivity(intent);
            }
        });

        btnEmployeeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EmployeeList.class);
                startActivity(intent);
            }
        });
    }
}