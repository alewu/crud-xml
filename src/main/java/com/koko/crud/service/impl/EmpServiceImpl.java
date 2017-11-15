package com.koko.crud.service.impl;

import com.github.pagehelper.PageHelper;
import com.koko.crud.bean.Emp;
import com.koko.crud.common.page.PageBean;
import com.koko.crud.common.page.PageParams;
import com.koko.crud.dao.EmpMapper;
import com.koko.crud.service.EmpService;
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
