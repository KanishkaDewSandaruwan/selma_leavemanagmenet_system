package com.example.selma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin;
        EditText userName, password;
        DBHandler dbHandler;
        Context context = this;

        dbHandler = new DBHandler(context);

        btnLogin = findViewById(R.id.btnLogin);
        userName = findViewById(R.id.editUserName);
        password = findViewById(R.id.editPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  try {
                      boolean count;
                      String getEMPID = userName.getText().toString();
                      String getNIC = password.getText().toString();

                      if (dbHandler.login(getEMPID, getNIC)){
                          Toast t = Toast.makeText(context,"Login Success!",Toast.LENGTH_SHORT);
                          t.show();
                          Intent intent = new Intent(getApplicationContext(),EmployeeMain.class);
                          intent.putExtra("EMPID", getEMPID);

                          startActivity(intent);

                      }else if(getEMPID.equals("admin") && getNIC.equals("12345")) {
                          Toast t = Toast.makeText(context,"Login Success!",Toast.LENGTH_SHORT);
                          t.show();
                          startActivity(new Intent(LoginActivity.this, StaffMain.class));
                      }else{
                          Toast t = Toast.makeText(context,"Employee Number or NIC is Wrong",Toast.LENGTH_SHORT);
                          t.show();
                          userName.setText("");
                          password.setText("");
                      }
                  }catch (Exception exception){
                      Toast t = Toast.makeText(context,"Login Error",Toast.LENGTH_SHORT);
                      t.show();
                  }
                }

        });
    }
}