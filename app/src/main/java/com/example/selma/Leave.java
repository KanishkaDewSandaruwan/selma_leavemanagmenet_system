package com.example.selma;

public class Leave {

    private int leave_id;
    private String leave_start_date;
    private String leave_end_date;
    private String empid;
    private String accept;

    public Leave() {
    }

    public Leave(int leave_id, String leave_start_date, String leave_end_date, String empid, String accept) {
        this.leave_id = leave_id;
        this.leave_start_date = leave_start_date;
        this.leave_end_date = leave_end_date;
        this.empid = empid;
        this.accept = accept;
    }

    public Leave(String leave_start_date, String leave_end_date, String empid) {
        this.leave_start_date = leave_start_date;
        this.leave_end_date = leave_end_date;
        this.empid = empid;
    }

    public int getLeave_id() {
        return leave_id;
    }

    public void setLeave_id(int leave_id) {
        this.leave_id = leave_id;
    }

    public String getLeave_start_date() {
        return leave_start_date;
    }

    public void setLeave_start_date(String leave_start_date) {
        this.leave_start_date = leave_start_date;
    }

    public String getLeave_end_date() {
        return leave_end_date;
    }

    public void setLeave_end_date(String leave_end_date) {
        this.leave_end_date = leave_end_date;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }
}
