package com.example.demo.crud.service;

import com.example.demo.crud.entities.Employee;

public interface EmploeeService {

    int insert(Employee emp);

    int delete( Long empId);

    int update(Employee emp);

    Employee query(Long empId);
}
