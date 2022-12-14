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

        historylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final Leave leave = leaveList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Leave Manage");

                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteLeave(leave.getLeave_id());
                        Intent intent1 = new Intent(getApplicationContext(), EmployeeLeaveHistory.class);
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
                        Intent intent1 = new Intent(getApplicationContext(), EmployeeLeaveHistory.class);
                        intent1.putExtra("EMP_ID", EMP_ID);
                        startActivity(intent1);
                    }
                });

                builder.show();
            }
        });

    }
}