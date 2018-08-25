package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jdbcTest")
public class JdbcController {
/*

    @Autowired
    private EmploeeService emploeeService;

    @PostMapping
    public Object insert(@RequestBody Employee emp){
        if(emp.getLastName() == null){
            return "FAIL";
        }
        int insert = emploeeService.insert(emp);
        return insert == 1 ? "SUCCESS" : "FAIL" ;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long empId){
        int idDelete = emploeeService.delete(empId);
        return idDelete == 1 ? "SUCCESS" : "FAIL";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long empId, @RequestBody Employee emp){
        int idUpdate = emploeeService.update(emp);
        return idUpdate == 1 ? "SUCCESS" : "FAIL";
    }

    @GetMapping("/{id}")
    public String query(@PathVariable("id")Long empId){
        return emploeeService.query(empId).toString();
    }
*/


    @Autowired
    private JdbcTemplate jdbcTemplate;


}
