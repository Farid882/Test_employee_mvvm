package com.example.testemployeemvvm.screens.employees;

import com.example.testemployeemvvm.pojo.Employee;

import java.util.List;

public interface EmployeeListView {
    public void loadData(List<Employee> employees);
    public void showError();
}
