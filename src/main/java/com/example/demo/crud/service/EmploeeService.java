package com.example.demo.crud.service;

import com.example.demo.crud.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmploeeService {

    Long insert(Employee emp);

    int delete(Long empId);

    int update(Employee emp);

    Employee query(Long empId);

    List<Employee> list();
}
