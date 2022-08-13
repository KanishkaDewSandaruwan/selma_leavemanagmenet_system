package com.example.selma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.icu.number.CompactNotation;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {

    TextView empid, name, nic, address, email, phone;
    DBHandler dbHandler;
    Employee employee;
    List<Employee> employeeList;
    Context context;
    Button btnbackProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final Intent intent = getIntent();
        final String EMP_ID = intent.getStringExtra("EMPID");
        int id = Integer.parseInt(EMP_ID);

        employeeList = new ArrayList<>();
        employee = new Employee();
        context = this;
        dbHandler = new DBHandler(context);

        empid = findViewById(R.id.txtEmpIDProfile);
        name = findViewById(R.id.txtNameProfile);
        nic = findViewById(R.id.txtNicProfile);
        address = findViewById(R.id.txtAddressProfile);
        email = findViewById(R.id.txtEmailProfile);
        phone = findViewById(R.id.txtPhoneProfile);
        btnbackProfile = findViewById(R.id.btnBackProfile);


        employeeList = dbHandler.getEmployeeByID(id);

        if(!employeeList.isEmpty()){
            employee = employeeList.get(0);

            empid.setText(employee.getEmp_number());
            name.setText(employee.getName());
            nic.setText(employee.getNic());
            address.setText(employee.getAddress());
            email.setText(employee.getEmail());
            phone.setText(String.valueOf(employee.getPhonenumber()));
        }

        btnbackProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), EmployeeMain.class);
                intent1.putExtra("EMPID", EMP_ID);
                startActivity(intent1);
            }
        });

    }
}