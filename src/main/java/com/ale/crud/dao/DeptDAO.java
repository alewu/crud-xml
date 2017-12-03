package com.ale.crud.dao;

import com.ale.crud.bean.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptDAO extends BaseDAO<Dept> {

    List<Dept> listDept();
}