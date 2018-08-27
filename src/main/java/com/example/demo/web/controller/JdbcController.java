package com.example.demo.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.crud.entities.Employee;
import com.example.demo.crud.service.EmploeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/jdbcTest")
@Slf4j
public class JdbcController {

    @Autowired
    private EmploeeService emploeeService;

    @PostMapping
    public ResponseEntity insert(@RequestBody Employee emp, HttpServletResponse req){
        if(emp.getLastName() == null){
            return returnJson("FAIL");
        }
        Long empId = emploeeService.insert(emp);
        if(empId <= 0 ){
            return returnJson("FAIL");
        }
        Employee employee = emploeeService.query(empId);
        return returnJson(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long empId){
        int idDelete = emploeeService.delete(empId);
        return returnJson(idDelete > 0 ? "SUCCESS":"FAIL");
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long empId, @RequestBody Employee emp){
        int idUpdate = emploeeService.update(emp);
        return  returnJson(idUpdate < 0 ? "FAIL" : emploeeService.query(empId));
    }

    @GetMapping("/{id}")
    public ResponseEntity query(@PathVariable("id")Long empId){
        return returnJson(emploeeService.query(empId));
    }

    @GetMapping("/list")
    public ResponseEntity queryList(){
        return returnJson(emploeeService.list());
    }


    @GetMapping("/test")
    public ResponseEntity test(){
log.debug("jdbc test。。。");
log.info("jdbc info。。。");

        return returnJson("hello");
    }

    /**
     * @Description  返回json 格式必須使用一个对象包装才能别解析传输到前端
     * @param [obj]
     * @return org.springframework.http.ResponseEntity
     * @Data 2018/8/26 3:30
     * @author Double
     */
    private ResponseEntity returnJson(Object obj){
        JSONObject json = new JSONObject();
        json.put("message",obj);
        return ResponseEntity.ok(json);
    }
}
