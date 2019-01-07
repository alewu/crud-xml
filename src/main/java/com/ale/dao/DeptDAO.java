package com.ale.dao;

import com.ale.entity.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptDAO extends BaseDAO<Dept> {

    List<Dept> listDept();
}