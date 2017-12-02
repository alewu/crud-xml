package com.ale.crud.service.impl;

import com.ale.crud.bean.Emp;
import com.ale.crud.common.page.PageBean;
import com.ale.crud.common.page.PageParam;
import com.ale.crud.dao.EmpMapper;
import com.ale.crud.service.EmpService;
import com.ale.crud.util.common.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService{
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Emp> listEmps() {
        return empMapper.getAll();
    }

    @Override
    public PageBean<Emp> listEmps(PageParam pageParam) {
        PageUtil.startPage(pageParam);
        List<Emp> list = empMapper.getAll();
        return PageUtil.getPageBean(list);
    }
}
