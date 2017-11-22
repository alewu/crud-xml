package com.ale.crud.controller;

import com.ale.crud.common.response.Response;
import com.ale.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/")
    public Response getUser(String id){
        userService.getOne(id);
        return new Response();
    }
}
