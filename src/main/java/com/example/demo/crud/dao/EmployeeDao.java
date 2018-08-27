package com.example.demo.crud.dao;

import com.example.demo.crud.entities.Employee;

import java.util.List;

public interface EmployeeDao {

    Long insert(Employee emp);

    void insertBatch(List<Employee> emps);

    int delete(Long empId);

    int update(Employee emp);

    Employee query(Long empId);

    List<Employee> list();
}
