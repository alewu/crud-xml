package com.ale.crud.service.impl;

import com.ale.crud.bean.User;
import com.ale.crud.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public int insertOne(User user) {
        return 0;
    }

    @Override
    public int updateOne(User user) {
        return 0;
    }

    @Override
    public User getOne(Object obj) {
        return null;
    }

    @Override
    public int deleteOne(Object obj) {
        return 0;
    }
}
