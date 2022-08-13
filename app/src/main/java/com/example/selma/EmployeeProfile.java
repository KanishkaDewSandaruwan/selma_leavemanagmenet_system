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

public class EmployeeProfile extends AppCompatActivity {
    Button btnBacktoMain;
    TextView empid, name, nic, address, email, phone;
    DBHandler dbHandler;
    Employee employee;
    List<Employee> employeeList;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);
        btnBacktoMain = findViewById(R.id.btnBackProfileStaff);

        final Intent intent = getIntent();
        final String EMP_ID = intent.getStringExtra("EMP_ID");
        int id = Integer.parseInt(EMP_ID);

        employeeList = new ArrayList<>();
        employee = new Employee();
        context = this;
        dbHandler = new DBHandler(context);

        empid = findViewById(R.id.txtEmpIDProfileStaff);
        name = findViewById(R.id.txtNameProfileStaff);
        nic = findViewById(R.id.txtNicProfileStaff);
        address = findViewById(R.id.txtAddressProfileStaff);
        email = findViewById(R.id.txtEmailProfileStaff);
        phone = findViewById(R.id.txtPhoneProfileStaff);

        employeeList = dbHandler.getEmployeeByID(id);

        if(!employeeList.isEmpty()){
            employee = employeeList.get(0);

            empid.setText("Employee Number : #" + employee.getEmp_number());
            name.setText("Name : " + employee.getName());
            nic.setText("NIC Number : " + employee.getNic());
            address.setText("Address : " + employee.getAddress());
            email.setText("Email : " + employee.getEmail());
            phone.setText("Phone : " + String.valueOf(employee.getPhonenumber()));
        }

        btnBacktoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), StaffMain.class));
            }
        });
    }
}