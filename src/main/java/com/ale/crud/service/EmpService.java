package com.ale.crud.service;

import com.ale.crud.common.page.PageBean;
import com.ale.crud.bean.Emp;
import com.ale.crud.common.page.PageParams;

import java.util.List;

public interface EmpService {
    List<Emp> listEmps();

    PageBean<Emp> listEmps(PageParams pageParams);

}
