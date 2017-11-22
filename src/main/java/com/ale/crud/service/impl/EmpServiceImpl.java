package com.ale.crud.service.impl;

import com.ale.crud.common.page.PageBean;
import com.ale.crud.dao.EmpMapper;
import com.github.pagehelper.PageHelper;
import com.ale.crud.bean.Emp;
import com.ale.crud.common.page.PageParams;
import com.ale.crud.service.EmpService;
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
    public PageBean<Emp> listEmps(PageParams pageParams) {
        PageHelper.startPage(pageParams.getOffset(), pageParams.getLimit());
        List<Emp> list = empMapper.getAll();
        PageBean<Emp> pageBean = new PageBean<>(list);
        pageBean.setRecordCount(list.size());
        return pageBean;
    }
}
