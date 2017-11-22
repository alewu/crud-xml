package com.ale.crud.dao;

import com.ale.crud.bean.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DeptMapper {
    int deleteByPrimaryKey(Long deptId);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Long deptId);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    List<Dept> listDept();

    void addDept(Dept dept);
}