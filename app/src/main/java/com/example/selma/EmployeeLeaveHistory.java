package com.example.selma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EmployeeLeaveHistory extends AppCompatActivity {

    DBHandler dbHandler;
    Context context;
    LeaveHistoryArrayAdapter leaveHistoryArrayAdapter;
    List<Leave> leaveList;
    ListView historylistview;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_leave_history);

        final Intent intent = getIntent();
        final String EMP_ID = intent.getStringExtra("EMP_ID");
        int id = Integer.parseInt(EMP_ID);

        back = findViewById(R.id.btnLeaveHistoryBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), EmployeeMain.class);
                intent1.putExtra("EMPID", EMP_ID);
                startActivity(intent1);
            }
        });

        context = this;
        dbHandler = new DBHandler(context);
        leaveList = new ArrayList<>();
        leaveList = dbHandler.getLeaveByID(id);

        historylistview = findViewById(R.id.LeaveHistorylistView);
        leaveHistoryArrayAdapter = new LeaveHistoryArrayAdapter(context,R.layout.one_leave_history,leaveList);
        historylistview.setAdapter(leaveHistoryArrayAdapter);

    }
}