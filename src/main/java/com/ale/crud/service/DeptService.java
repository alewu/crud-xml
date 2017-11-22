package com.ale.crud.service;


import com.ale.crud.bean.Dept;
import com.ale.crud.common.page.PageBean;
import com.ale.crud.common.page.PageParams;

import java.util.List;

public interface DeptService {
    PageBean<Dept> listDept(PageParams pageParams);

    void addDepts(List<Dept> depts);

    void addDept(Dept dept);

    Dept getDept(Long deptId);

    int deleteDept(Long deptId);

    int updateDept(Dept dept);

    int deleteDepts(List<Long> deptIds);
}
