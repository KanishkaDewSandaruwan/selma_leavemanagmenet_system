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

public class EmployeeLeaveDetails extends AppCompatActivity {


    DBHandler dbHandler;
    Context context;
    LeaveHistoryArrayAdapter leaveHistoryArrayAdapter;
    List<Leave> leaveList;
    ListView employeeDetailslistview;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_leave_details);

        final Intent intent = getIntent();
        final String EMP_ID = intent.getStringExtra("EMP_ID");
        int id = Integer.parseInt(EMP_ID);

        back = findViewById(R.id.btnBackEmployeeLeaveDetails);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), EmployeeList.class);
                startActivity(intent1);
            }
        });

        context = this;
        dbHandler = new DBHandler(context);
        leaveList = new ArrayList<>();
        leaveList = dbHandler.getLeaveByID(id);

        employeeDetailslistview = findViewById(R.id.listViewLeaveDetails);
        leaveHistoryArrayAdapter = new LeaveHistoryArrayAdapter(context,R.layout.one_leave_history,leaveList);
        employeeDetailslistview.setAdapter(leaveHistoryArrayAdapter);

        employeeDetailslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final Leave leave = leaveList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Leave Manage");

                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteLeave(leave.getLeave_id());
                        Intent intent1 = new Intent(getApplicationContext(), EmployeeLeaveDetails.class);
                        intent1.putExtra("EMP_ID", EMP_ID);
                        startActivity(intent1);
                        Toast.makeText(context, "Leave Delete Success!", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setPositiveButton("Cancel Request", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dbHandler.updateCancelRequest(leave.getLeave_id());
                        Toast.makeText(context, "Leave Request Cancel Success!", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(getApplicationContext(), EmployeeLeaveDetails.class);
                        intent1.putExtra("EMP_ID", EMP_ID);
                        startActivity(intent1);
                    }
                });

                builder.show();
            }
        });
    }
}