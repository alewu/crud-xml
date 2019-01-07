package com.ale.service.impl;

import com.ale.common.page.PageBean;
import com.ale.common.page.PageParam;
import com.ale.dao.EmpMapper;
import com.ale.entity.Emp;
import com.ale.service.EmpService;
import com.ale.util.common.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
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
