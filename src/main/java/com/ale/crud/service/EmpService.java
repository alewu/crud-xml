package com.ale.crud.service;

import com.ale.crud.common.page.PageBean;
import com.ale.crud.bean.Emp;
import com.ale.crud.common.page.PageParam;

import java.util.List;

public interface EmpService {
    List<Emp> listEmps();

    PageBean<Emp> listEmps(PageParam PageParam);

}
