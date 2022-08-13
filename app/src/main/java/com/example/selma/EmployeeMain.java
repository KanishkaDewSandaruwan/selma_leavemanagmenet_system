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
    List<Employee> employeeList;
    Employee employee;
    TextView btnMyProfile;

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
        employee = new Employee();

        leaveList = new ArrayList<>();
        employeeList = new ArrayList<>();

        Button btnAddNewLeave, leaveHistory;
        btnAddNewLeave = findViewById(R.id.btnAddNewLeave);
        leaveHistory = findViewById(R.id.btnLeaveHistory);

        name = findViewById(R.id.txtName);
        startDate = findViewById(R.id.txtLeaveDate);
        EndDate = findViewById(R.id.txtLeaveEndDate);
        Status = findViewById(R.id.txtLeaveStatus);

        btnMyProfile = findViewById(R.id.txtbtnProfile);

        btnMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), Profile.class);
                intent1.putExtra("EMPID", EMP_ID);
                startActivity(intent1);
            }
        });

        employeeList = dbHandler.getEmployeeByID(id);
        if(!employeeList.isEmpty()){
            employee = employeeList.get(0);
            name.setText("Hello! " + employee.getName());
        }

        leaveList = dbHandler.getLeaveByID(id);
        if(!leaveList.isEmpty()){

            leave = leaveList.get(0);
            if(!leave.getAccept().isEmpty()){
                startDate.setText("Start Date : "+ leave.getLeave_start_date());
                EndDate.setText("End Date : " + leave.getLeave_end_date());
                Status.setText("Request Status : " + leave.getAccept());
            }
        }else{
            name.setText("Your Leave List is Empty");
            startDate.setText("");
            EndDate.setText("");
            Status.setText("Welcome to SELMA Levae System");
        }



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