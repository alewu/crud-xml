package com.ale.crud.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class Emp {
    private Integer empId;

    private Integer deptId;

    private String empName;

    private Integer age;

    private String gender;

    private String email;

    private Dept dept;

    private Date gmtCreate;

    private Date gmtModified;

    public Emp(Integer empId, Integer deptId, String empName, Integer age, String gender, String email) {
        this.empId = empId;
        this.deptId = deptId;
        this.empName = empName;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }
}