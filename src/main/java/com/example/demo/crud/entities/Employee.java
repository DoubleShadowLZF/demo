package com.example.demo.crud.entities;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Double
 */
@Data
public class Employee implements Serializable {

    public static final Short MALE = 0;
    public static final Short FEMALE = 1;

	private Long id;
    private String lastName;

    private String email;
    /**1 male, 0 female*/
    private Short gender;
    private Department department;
    private Date birth;
    private Date entryTime;

    {
        //Excel 填充数据时 ,对象必须初始化
        department = new Department();
        /*lastName = UUID.randomUUID().toString();
        email = "1009083442@qq.com";
        gender = FEMALE;
        department = new Department(0,"Docker");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1995,7,1);
        birth = new Timestamp(calendar.getTimeInMillis());
        entryTime = new Timestamp(System.currentTimeMillis());*/
    }

    public Employee(){}

    public Employee(Long id, String lastName, String email, Short gender, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
    }

}
