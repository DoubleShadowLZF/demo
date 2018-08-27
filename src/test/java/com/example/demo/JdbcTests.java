package com.example.demo;

import com.example.demo.crud.dao.EmployeeDao;
import com.example.demo.crud.entities.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.example.demo")
public class JdbcTests {
    @Autowired
    private EmployeeDao employeeDbDao;

    @Test
    public void testTransactional(){
        List<Employee> emps = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            Employee emp = new Employee();
            emp.setLastName(i + "_"+UUID.randomUUID().toString().substring(0,5));
            emps.add(emp);
        }
        Employee employee = new Employee();
//        employee.setLastName("Transactional");
        emps.add(5,employee);
        emps.add(10,employee);
        employeeDbDao.insertBatch(emps);

    }
}
