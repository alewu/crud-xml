package com.ale.service.impl;

import com.ale.common.page.PageBean;
import com.ale.common.page.PageParam;
import com.ale.dao.DeptDAO;
import com.ale.entity.Dept;
import com.ale.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService {

    private DeptDAO deptDAO;

    @Autowired
    public void setDeptDAO(DeptDAO deptDAO) {
        this.deptDAO = deptDAO;
    }

    @Override
    public PageBean<Dept> listDept(PageParam pageParam) {
        PageUtil.startPage(pageParam);
        List<Dept> list = deptDAO.listDept();
        return PageUtil.getPageBean(list);
    }
}
