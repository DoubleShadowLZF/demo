package com.example.demo.crud.dao.impl;

import com.example.demo.crud.dao.DepartmentDao;
import com.example.demo.crud.dao.EmployeeDao;
import com.example.demo.crud.entities.Department;
import com.example.demo.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository("employeeMemoryDao")
public class EmployeeMemoryDao implements EmployeeDao {

	private static Map<Long, Employee> employees = null;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	static{
		employees = new HashMap<Long, Employee>();

		employees.put(1001L, new Employee(1001L, "E-AA", "aa@163.com", Employee.FEMALE, new Department(101, "D-AA")));
		employees.put(1002L, new Employee(1002L, "E-BB", "bb@163.com", Employee.FEMALE, new Department(102, "D-BB")));
		employees.put(1003L, new Employee(1003L, "E-CC", "cc@163.com", Employee.MALE, new Department(103, "D-CC")));
		employees.put(1004L, new Employee(1004L, "E-DD", "dd@163.com", Employee.FEMALE, new Department(104, "D-DD")));
		employees.put(1005L, new Employee(1005L, "E-EE", "ee@163.com", Employee.MALE, new Department(105, "D-EE")));
	}
	
	private static Long initId = 1006L;

	@Override
	public Long insert(Employee employee){
		if(employee.getId() == null){
			employee.setId(initId++);
		}
		
		employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
		employees.put(employee.getId(), employee);
		return employee.getId();
	}

	@Override
	public void insertBatch(List<Employee> emps){
		for (int i = 0; i < emps.size(); i++) {
			Employee emp = new Employee();
			employees.put(emp.getId(),emps.get(i));
		}
	}
	
	public Collection<Employee> getAll(){
		return employees.values();
	}

	@Override
	public int delete(Long id){
		employees.remove(id);
		return 1;
	}

	@Override
	public int update(Employee emp){
		employees.put(emp.getId(),emp);
		return 1 ;
	}

	@Override
	public Employee query(Long id){
		return employees.get(id);
	}

	@Override
	public List<Employee> list() {
		return null;
	}

}

