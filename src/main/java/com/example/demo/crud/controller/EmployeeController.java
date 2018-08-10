package com.example.demo.crud.controller;

import com.example.demo.crud.dao.DepartmentDao;
import com.example.demo.crud.dao.EmployeeDao;
import com.example.demo.crud.entities.Department;
import com.example.demo.crud.entities.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Map;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/8/10
 */
@Slf4j
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String emps(Model model){
        log.debug("emps...");
        Collection<Employee> emps = employeeDao.getAll();
        log.debug("emps:"+emps);
        model.addAttribute("emps",emps);
        return "/emps/list";
    }

    @GetMapping("/emp")
    public String addEmp(Map<String,Collection<Department>> map){
        Collection<Department> depts = departmentDao.getDepartments();
        map.put("depts",depts);
        return "/emps/add";
    }

    @PostMapping("/emp")
    public String addEmpPost(Employee employee){
        /*開啓了 Thymeleaf 試圖解析器，返回的字符串，會在classpath:/templeates 目錄下查找靜態資源；
         * 如果想跳轉到“/emps”，應該使用轉發“forward:/emps”或者是重定向“redirect:/emps”
         */
//        return "/emps";
        log.debug("add employee:"+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{empId}")
    public String singleEmp(@PathVariable("empId") Integer empId , Model model){
        Employee employee = employeeDao.get(empId);
        Collection<Department> depts = departmentDao.getDepartments();
        log.debug("edit employee:"+employee);
        model.addAttribute("emp",employee);
        model.addAttribute("depts",depts);
        return "/emps/add";
    }

    /**
     * @author Double
     * @Description
     * form 表單是沒有 put 請求的，應該模擬put ， SpringBoot 在WebMvcAutoConfiguration 中默認將HiddenHttpMethodFilter 注入了Bean 容器 ，
     * 只需要在前臺傳入 <input type="hidden" name="_method" value="put"/>
     * @param
     * @return java.lang.String
     * @Data 2018/8/10
     */
    @PutMapping("/emp")
    public String putEmp(Employee employee){
        log.debug("edit employee:"+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * @author Double
     * @Description delete 請求和 put 請求一樣，應該在前臺傳入 <input type="hidden" name="_method" value="delete"/>
     * @param
     * @return java.lang.String
     * @Data 2018/8/10
     */
    @DeleteMapping("/emp/{empId}")
    public String deleteEmp(@PathVariable("empId") Integer empId ){
        employeeDao.delete(empId);
        return "redirect:/emps";
    }
}
