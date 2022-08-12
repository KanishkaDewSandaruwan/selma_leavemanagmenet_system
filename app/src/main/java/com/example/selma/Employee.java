package com.example.selma;

public class Employee {
    private int emp_id;
    private String name;
    private String emp_number;
    private String address;
    private String nic;
    private String email;
    private int phonenumber;

    public Employee() {
    }

    public Employee(String emp_number, String name, String nic, String address, String email, int phonenumber) {
        this.name = name;
        this.emp_number = emp_number;
        this.address = address;
        this.nic = nic;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public Employee(int emp_id, String name, String nic, String address,  String email, int phonenumber) {
        this.emp_id = emp_id;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public Employee(int emp_id, String name, String emp_number, String address, String nic, String email, int phonenumber) {
        this.emp_id = emp_id;
        this.name = name;
        this.emp_number = emp_number;
        this.address = address;
        this.nic = nic;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmp_number() {
        return emp_number;
    }

    public void setEmp_number(String emp_number) {
        this.emp_number = emp_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }
}
