package com.example.selma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmployeeRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_register);

        EditText name, employeeid, address, email, phone, nic;
        Button btnRegEmp;
        final Context context = this;
        final DBHandler dbHandler;

        dbHandler = new DBHandler(context);

        btnRegEmp = findViewById(R.id.btnRegisterEmployee);

        name = findViewById(R.id.editName);
        employeeid = findViewById(R.id.editEmployeeID);
        address = findViewById(R.id.editAddress);
        email = findViewById(R.id.editEmail);
        phone = findViewById(R.id.editPhone);
        nic = findViewById(R.id.editNic);

        btnRegEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getName = name.getText().toString();
                String getEmployeeid = employeeid.getText().toString();
                String getAddress = address.getText().toString();
                String getEmail = email.getText().toString();
                String getPhone = phone.getText().toString();
                String getNIC = nic.getText().toString();

                int convPhone = Integer.parseInt(getPhone);

                Employee employee = new Employee(getEmployeeid, getName, getNIC, getAddress, getEmail, convPhone);
                dbHandler.addEmployee(employee);
                Toast t = Toast.makeText(getApplicationContext(), "Employee Register Success!", Toast.LENGTH_SHORT);
                t.show();

                Intent intent = new Intent(getApplicationContext(), StaffMain.class);
                startActivity(intent);

            }
        });


    }
}