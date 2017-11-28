package com.ale.crud.controller;

import com.ale.crud.bean.User;
import com.ale.crud.common.response.Response;
import com.ale.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/id")
    public Response getUser(String id){
        User user = userService.getOne(id);
        return new Response().success(user);
    }
}
