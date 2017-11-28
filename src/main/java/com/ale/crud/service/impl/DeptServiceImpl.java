package com.ale.crud.service.impl;

import com.ale.crud.bean.Dept;
import com.ale.crud.dao.DeptDAO;
import com.ale.crud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService{

    private DeptDAO deptDAO;
    @Autowired
    public void setDeptDAO(DeptDAO deptDAO) {
        this.deptDAO = deptDAO;
    }
}
