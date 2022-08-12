package com.example.selma;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddLeave extends AppCompatActivity {

    final Calendar myCalendar= Calendar.getInstance();
    final Calendar myCalendar1= Calendar.getInstance();
    EditText startDate, endDates;
    Button btnRequestLeave;
    DBHandler dbHandler;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_leave);

        final Intent intent = getIntent();
        final String EMP_ID = intent.getStringExtra("EMPID");

        context = this;

        dbHandler = new DBHandler(context);

        startDate = findViewById(R.id.editStartDate);
        endDates = findViewById(R.id.editEndDate);
        btnRequestLeave = findViewById(R.id.btnRequestLeave);

        DatePickerDialog.OnDateSetListener endDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                myCalendar1.set(Calendar.YEAR, i);
                myCalendar1.set(Calendar.MONTH,i1);
                myCalendar1.set(Calendar.DAY_OF_MONTH,i2);
                updateLabel2();
            }
        };

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                myCalendar.set(Calendar.YEAR, i);
                myCalendar.set(Calendar.MONTH,i1);
                myCalendar.set(Calendar.DAY_OF_MONTH,i2);
                updateLabel();
            }
        };

        endDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddLeave.this,endDate,myCalendar1.get(Calendar.YEAR),myCalendar1.get(Calendar.MONTH),myCalendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddLeave.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnRequestLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getStartDate = startDate.getText().toString();
                String getEndDate = endDates.getText().toString();

                Leave leave = new Leave(getStartDate, getEndDate, EMP_ID);
                dbHandler.addLeave(leave);
                Toast.makeText(context, "Leave Request Sent Success! Wait until Approve Leave Request", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, EmployeeMain.class));
            }
        });
    }

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        startDate.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void updateLabel2(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        endDates.setText(dateFormat.format(myCalendar1.getTime()));
    }
}