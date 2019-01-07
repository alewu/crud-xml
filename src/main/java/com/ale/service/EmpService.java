package com.ale.service;

import com.ale.common.page.PageBean;
import com.ale.common.page.PageParam;
import com.ale.entity.Emp;

import java.util.List;

public interface EmpService {
    List<Emp> listEmps();

    PageBean<Emp> listEmps(PageParam PageParam);

}
