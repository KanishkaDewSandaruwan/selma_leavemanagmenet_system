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

public class EmployeeList extends AppCompatActivity {

    DBHandler dbHandler;
    Context context;
    EmployeeArrayAdapter employeeArrayAdapter;
    List<Employee> employeeList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        Button btnBackEmployyeList;

        btnBackEmployyeList = findViewById(R.id.btnBackEmployeeList);



       context = this;
       dbHandler = new DBHandler(context);
       employeeList = new ArrayList<>();
       employeeList = dbHandler.getEmployee();

        listView = findViewById(R.id.employeeListView);

       employeeArrayAdapter = new EmployeeArrayAdapter(context, R.layout.one_employee, employeeList);
       listView.setAdapter(employeeArrayAdapter);

       btnBackEmployyeList.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(EmployeeList.this, StaffMain.class));
           }
       });

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               final Employee employee = employeeList.get(position);
               AlertDialog.Builder builder = new AlertDialog.Builder(context);
               builder.setTitle("Employee Details");

               builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Intent intent = new Intent(context, EmployeeEdit.class);
                       intent.putExtra("EMP_ID",employee.getEmp_id());
                       startActivity(intent);
                   }
               });

               builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteEmployee(employee.getEmp_id());
                        startActivity(new Intent(context, EmployeeList.class));
                       Toast.makeText(context, "Employee Delete Success!", Toast.LENGTH_SHORT).show();
                   }
               });

               builder.setPositiveButton("Leave Details", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                   }
               });

               builder.show();
           }
       });
    }
}