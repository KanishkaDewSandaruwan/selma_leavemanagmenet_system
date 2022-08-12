package com.example.selma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    Context context;

    private static final int VERSION = 1;
    private static final String DB_NAME = "selma";

//create employee table
    private  static final String EMPLOYEE_TABLE_NAME = "employee";

    private static final String EMPID = "emp_id";
    private static final String EMPNUMBER = "emp_number";
    private static final String NAME = "name";
    private static final String NIC = "nic";
    private static final String ADDRESS = "address";
    private static final String EMAIL = "email";
    private static final String PHONE = "phonenumber";

//leave table

    private  static final String LEAVE_TABLE_NAME = "leave";

    private static final String LEAVEID = "leave_id";
    private static final String LEAVE_START_DATE = "leave_start_date";
    private static final String LEAVE_END_DATE = "leave_end_date";
    private static final String LEAVE_EMPID = "empid";
    private static final String ACCEPT = "accept";

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String employee_table_query = "CREATE TABLE "+EMPLOYEE_TABLE_NAME+"("
                +EMPID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +EMPNUMBER+" TEXT, "
                +NAME+" TEXT, "
                +NIC+" TEXT, "
                +ADDRESS+" TEXT, "
                +EMAIL+" TEXT, "
                +PHONE+" TEXT );";
        db.execSQL(employee_table_query);

        //Create Semester Table

        String Semester_table_query = "CREATE TABLE "+LEAVE_TABLE_NAME+"("
                +LEAVEID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +LEAVE_EMPID+" TEXT, "
                +ACCEPT+" TEXT, "
                +LEAVE_START_DATE+" DATETIME, "
                +LEAVE_END_DATE+" DATETIME);";

        db.execSQL(Semester_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addEmployee(Employee employee){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues  = new ContentValues();

        contentValues.put(EMPNUMBER,employee.getEmp_number());
        contentValues.put(NAME,employee.getName());
        contentValues.put(NIC,employee.getNic());
        contentValues.put(ADDRESS,employee.getAddress());
        contentValues.put(EMAIL,employee.getEmail());
        contentValues.put(PHONE,employee.getPhonenumber());

        db.insert(EMPLOYEE_TABLE_NAME,null,contentValues);
    }

    public void addLeave(Leave leave){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues  = new ContentValues();

        contentValues.put(LEAVE_START_DATE,leave.getLeave_start_date());
        contentValues.put(LEAVE_END_DATE,leave.getLeave_end_date());
        contentValues.put(LEAVE_EMPID,leave.getEmpid());
        contentValues.put(ACCEPT,"Pending");

        db.insert(LEAVE_TABLE_NAME,null,contentValues);
    }

    public List<Employee> getEmployee(){

        List<Employee> employeeList = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();
        String get_semester_query = "SELECT * FROM "+EMPLOYEE_TABLE_NAME;
        Cursor cursor = db.rawQuery(get_semester_query,null);

        if (cursor.moveToFirst()){
            do {
                Employee employee = new Employee();

                employee.setEmp_id(cursor.getInt(0));
                employee.setEmp_number(cursor.getString(1));
                employee.setName(cursor.getString(2));
                employee.setNic(cursor.getString(3));
                employee.setAddress(cursor.getString(4));
                employee.setEmail(cursor.getString(5));
                employee.setPhonenumber(cursor.getInt(6));

                employeeList.add(employee);

            }while (cursor.moveToNext());
        }
        return employeeList;
    }

    public List<Leave> getAllLeave(){

        List<Leave> leaveList = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();
        String get_semester_query = "SELECT * FROM "+LEAVE_TABLE_NAME;
        Cursor cursor = db.rawQuery(get_semester_query,null);

        if (cursor.moveToFirst()){
            do {
                Leave leave = new Leave();

                leave.setLeave_id(cursor.getInt(0));
                leave.setEmpid(cursor.getString(1));
                leave.setAccept(cursor.getString(2));
                leave.setLeave_start_date(cursor.getString(3));
                leave.setLeave_end_date(cursor.getString(4));

                leaveList.add(leave);

            }while (cursor.moveToNext());
        }
        return leaveList;
    }

    public void deleteEmployee(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(EMPLOYEE_TABLE_NAME,EMPID+" = ? ",new String[]{String.valueOf(id)});
    }

    public List<Leave> getLeaveByID(int position){


        List<Leave> leaveList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(LEAVE_TABLE_NAME,new String[]{LEAVEID, LEAVE_EMPID,ACCEPT, LEAVE_START_DATE, LEAVE_END_DATE},LEAVE_EMPID + " = ?",new String[]{String.valueOf(position)},null,null,LEAVEID+" DESC");


        if (cursor.moveToFirst()){
            do {
                Leave leave = new Leave();
                leave.setLeave_id(cursor.getInt(0));
                leave.setEmpid(cursor.getString(1));
                leave.setAccept(cursor.getString(2));
                leave.setLeave_start_date(cursor.getString(3));
                leave.setLeave_end_date(cursor.getString(4));


                leaveList.add(leave);
            }while (cursor.moveToNext());
        }
        return leaveList;
    }

    public void editEmployee(Employee employee){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if(!employee.getName().isEmpty()){
            contentValues.put(NAME,employee.getName());
        }

        if(!employee.getNic().isEmpty()){
            contentValues.put(NIC,employee.getNic());
        }

        if(!employee.getAddress().isEmpty()){
            contentValues.put(ADDRESS,employee.getAddress());
        }

        if(!employee.getEmail().isEmpty()){
            contentValues.put(EMAIL,employee.getEmail());
        }
        if(String.valueOf(employee.getPhonenumber()).isEmpty()){
            contentValues.put(PHONE,employee.getPhonenumber());
        }

        db.update(EMPLOYEE_TABLE_NAME,contentValues,EMPID+" = ? ",new String[]{String.valueOf(employee.getEmp_id())});
    }

    public boolean login(String emp_number, String nic){

        boolean result;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM "+EMPLOYEE_TABLE_NAME+" WHERE emp_number = ? AND nic = ?",new String[]{emp_number,nic});

        int count = 0;
        count = cursor.getCount();

        if (count > 0){
            result = true;
        }else{
            result = false;
        }

        return result;
    }
}
