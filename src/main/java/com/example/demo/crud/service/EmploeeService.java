package com.example.demo.crud.service;

import com.example.demo.crud.entities.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmploeeService {

    int insert(Employee emp);

    int delete(Long empId);

    int update(Employee emp);

    Employee query(Long empId);
}
