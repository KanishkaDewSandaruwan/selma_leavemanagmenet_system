package com.example.selma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmployeeEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit);

        final Intent intent = getIntent();
        final int emp_id = intent.getIntExtra("EMP_ID",0);
        Context context = this;
        EditText name, address, email, phone, nic;
        Button btnUpdate;

        DBHandler dbHandler;

        dbHandler = new DBHandler(context);

        btnUpdate = findViewById(R.id.btnUpdateEmployee);

        name = findViewById(R.id.editUpdateName);
        address = findViewById(R.id.editUpdateAddress);
        email = findViewById(R.id.editUpdateEmail);
        phone = findViewById(R.id.editUpdatePhone);
        nic = findViewById(R.id.editUpdateNic);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getName = name.getText().toString();
                String getAddress = address.getText().toString();
                String getEmail = email.getText().toString();
                String getPhone = phone.getText().toString();
                String getNic = nic.getText().toString();



                int getConvPhone = Integer.parseInt(getPhone);

                Employee employee = new Employee(emp_id, getName, getNic, getAddress, getEmail, getConvPhone);

                dbHandler.editEmployee(employee);
                startActivity(new Intent(context, EmployeeList.class));
                Toast.makeText(context, "Edit Success!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}