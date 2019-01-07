package com.ale.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class User {
    private String userId;
    private String username;
    private String password;
    private Integer age;
    private String sex;
    private Date gmtCreate;
    private Date gmtModified;


    public User(String username,String password){
        this.username = username;
        this.password = password;
    }
}
