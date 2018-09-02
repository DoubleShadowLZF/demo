package com.example.demo.crud.dao.impl;

import com.example.demo.crud.dao.EmployeeDao;
import com.example.demo.crud.entities.Department;
import com.example.demo.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository("employeeDbDao")
public class EmployeeDbDao implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long insert(Employee emp) {
        String sql  = "INSERT INTO `demo`.`employee` (" +
                "  `last_name`,`email`,`gender`, `department`,`birth`,`entry_time`)" +
                "VALUES(?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if("Transactional".equals(emp.getLastName())){
            throw new RuntimeException("测试Transactional的使用方式....");
        }
        try {
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(
                        Connection connection) throws SQLException {
                    //拼装插入数据库的sql
                    PreparedStatement ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setString(1,emp.getLastName());
                    ps.setString(2,emp.getEmail());
                    ps.setInt(3,emp.getGender());
                    ps.setInt(4,emp.getDepartment().getId());
//                    ps.setTimestamp(5,emp.getBirth());
//                    ps.setTimestamp(6,emp.getEntryTime());
                    ps.setDate(5,emp.getBirth());
                    ps.setDate(6,emp.getEntryTime());
                    return ps;
                }
            }, keyHolder);
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
        return keyHolder.getKey().longValue();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<Employee> emps){
        for (Employee emp : emps){
            insert(emp);
        }
    }

    @Override
    public int delete(Long empId) {
        return jdbcTemplate.update("DELETE FROM `demo`.`employee` WHERE id = ?",empId);
    }

    @Override
    public int update(Employee emp) {
        return jdbcTemplate.update("UPDATE employee SET last_name=?,email=?,gender=?," +
                        "department=?,birth=?,entry_time=?",
                emp.getLastName(),emp.getEmail(),emp.getGender(),
                emp.getDepartment().getId(),emp.getBirth(),emp.getEntryTime());
    }

    @Override
    public Employee query(Long empId) {
        Object[] args = new Object[]{empId};
        return jdbcTemplate.queryForObject("select id,last_name,email,gender,department,birth,entry_time from employee where id = ?",
                args, new EmployeeRowMapper());
    }

    @Override
    public List<Employee> list() {
        return jdbcTemplate.query("select id,last_name,email,gender,department,birth,entry_time from employee",new EmployeeRowMapper());
    }
}

class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee emp = new Employee();
        emp.setId(rs.getLong(1));
        emp.setLastName(rs.getString(2));
        emp.setEmail(rs.getString(3));
        emp.setGender(rs.getShort(4));
        emp.setDepartment(new Department(rs.getInt(5),""));
//        emp.setBirth(rs.getTimestamp(6));
//        emp.setEntryTime(rs.getTimestamp(7));
        emp.setBirth(rs.getDate(6));
        emp.setEntryTime(rs.getDate(7));
        return emp;
    }
}
