package com.koko.crud.controller;

import com.koko.crud.common.response.Response;
import com.koko.crud.service.UserService;
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
