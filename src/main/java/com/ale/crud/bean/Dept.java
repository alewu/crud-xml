package com.ale.crud.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class Dept {

    private Long deptId;

    private String deptName;
    @JsonIgnore // 序列化时，忽略此字段
    private Date gmtCreate;
    @JsonIgnore
    private Date gmtModified;


    public Dept(Long deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }
}