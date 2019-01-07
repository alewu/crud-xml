package com.ale.dao;

import com.ale.entity.Emp;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmpMapper {
    int deleteByPrimaryKey(Integer empId);

    int insert(Emp record);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(Integer empId);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);

    List<Emp> getAll();
}