package com.example.demo.crud.service.impl;

import com.example.demo.crud.entities.Employee;
import com.example.demo.crud.service.EmploeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


/**
 * @author Double
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmploeeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Employee emp) {
        return jdbcTemplate.update("insert empployee(last_name,email,gender,department," +
                "birth,entry_time) values(?,?,?,?,?,?)",
                emp.getLastName(),emp.getEmail(),emp.getGender(),emp.getDepartment(),
                emp.getBirth(),emp.getEntryTime());
    }

    @Override
    public int delete(Long empId) {
        return jdbcTemplate.update("delete from employee where id = ?",empId);
    }

    @Override
    public int update(Employee emp) {
        return jdbcTemplate.update("update employee set last_name=?,email=?,gender=?," +
                "department=?,birth=?,entry_time=?",
                emp.getLastName(),emp.getGender(),
                emp.getDepartment(),emp.getBirth(),emp.getEntryTime());
    }

    @Override
    public Employee query(Long empId) {
        BeanPropertyRowMapper<Employee> mapper = new BeanPropertyRowMapper<>(Employee.class);
        Object[] args = new Object[]{empId};
        return jdbcTemplate.queryForObject("select * from employee where id = ?", args, mapper);
    }
}
