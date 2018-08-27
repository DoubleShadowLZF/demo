package com.example.demo.crud.service.impl;

import com.example.demo.crud.dao.EmployeeDao;
import com.example.demo.crud.entities.Employee;
import com.example.demo.crud.service.EmploeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Double
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmploeeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmployeeDao employeeDbDao;

    @Override
    public Long insert(Employee emp) {
        return employeeDbDao.insert(emp);
    }

    @Override
    public int delete(Long empId) {
        return jdbcTemplate.update("delete from employee where id = ?",empId);
    }

    @Override
    public int update(Employee emp) {
        return employeeDbDao.update(emp);
    }

    @Override
    public Employee query(Long empId) {
        return employeeDbDao.query(empId);

        /*BeanPropertyRowMapper<Employee> mapper = new BeanPropertyRowMapper<>(Employee.class);
        Object[] args = new Object[]{empId};
        return jdbcTemplate.queryForObject("select * from employee where id = ?", args, mapper);*/
    }

    @Override
    public List<Employee> list() {
        return employeeDbDao.list();
    }
}
