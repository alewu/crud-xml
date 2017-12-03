package com.ale.crud.service.impl;

import com.ale.crud.bean.Dept;
import com.ale.crud.common.page.PageBean;
import com.ale.crud.common.page.PageParam;
import com.ale.crud.dao.DeptDAO;
import com.ale.crud.service.DeptService;
import com.ale.crud.util.common.PageUtil;
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
