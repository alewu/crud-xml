package com.ale.crud.service.impl;

import com.ale.crud.common.page.PageBean;
import com.github.pagehelper.PageHelper;
import com.ale.crud.bean.Dept;
import com.ale.crud.common.page.PageParams;
import com.ale.crud.dao.DeptMapper;
import com.ale.crud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService{
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public PageBean<Dept> listDept(PageParams pageParams) {
        // TODO
        Integer offset = pageParams.getOffset() == null ? 0 : pageParams.getOffset();
        Integer limit = pageParams.getLimit() == null ? 20 : pageParams.getLimit();
        PageHelper.startPage(offset, limit);
        List<Dept> list = deptMapper.listDept();
        return new PageBean<>(list);
    }

    @Override
    public void addDepts(List<Dept> depts) {
        for (Dept dept : depts) {
            deptMapper.addDept(dept);
        }
    }

    @Override
    public void addDept(Dept dept) {
        deptMapper.addDept(dept);
    }

    @Override
    public Dept getDept(Long deptId) {
        return deptMapper.selectByPrimaryKey(deptId);
    }

    @Override
    public int deleteDept(Long deptId) {
        return deptMapper.deleteByPrimaryKey(deptId);
    }

    @Override
    public int updateDept(Dept dept) {
        return deptMapper.updateByPrimaryKey(dept);
    }

    @Override
    public int deleteDepts(List<Long> deptIds) {
        // TODO

        return 0;
    }
}
