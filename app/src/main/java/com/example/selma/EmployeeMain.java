package com.example.selma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMain extends AppCompatActivity {

    DBHandler dbHandler;
    Context context;
    TextView name, startDate, EndDate, Status;
    Leave leave;
    List<Leave> leaveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main);

        final Intent intent = getIntent();
        final String EMP_ID = intent.getStringExtra("EMPID");
        int id = Integer.parseInt(EMP_ID);

        context = this;
        dbHandler = new DBHandler(context);
        leave = new Leave();
        leaveList = new ArrayList<>();

        Button btnAddNewLeave, leaveHistory;
        btnAddNewLeave = findViewById(R.id.btnAddNewLeave);
        leaveHistory = findViewById(R.id.btnLeaveHistory);

        name = findViewById(R.id.txtName);
        startDate = findViewById(R.id.txtLeaveDate);
        EndDate = findViewById(R.id.txtLeaveEndDate);
        Status = findViewById(R.id.txtLeaveStatus);

        leaveList = dbHandler.getLeaveByID(id);
        leave = leaveList.get(0);

        startDate.setText(leave.getLeave_start_date());
        EndDate.setText(leave.getLeave_end_date());
        Status.setText(leave.getAccept());



        btnAddNewLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), AddLeave.class);
                intent1.putExtra("EMPID", EMP_ID);
                startActivity(intent1);


            }
        });


        leaveHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), EmployeeLeaveHistory.class);
                intent1.putExtra("EMP_ID", EMP_ID);
                startActivity(intent1);
            }
        });
    }
}