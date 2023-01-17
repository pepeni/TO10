package com.example.TO9;

import java.util.ArrayList;
import java.util.List;

public class EmployeeTable {
    private static EmployeeTable instance = null;
    List<Employee> employees;

    private EmployeeTable() {
        employees = new ArrayList<>();
    }

    public static EmployeeTable getInstance() {
        if (instance == null) {
            instance = new EmployeeTable();
        }
        return instance;
    }

    public void add_employee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> get_employees(){
        return employees;
    }
}

