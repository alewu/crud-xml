package com.koko.crud.service;

import com.koko.crud.bean.Emp;
import com.koko.crud.common.page.PageBean;
import com.koko.crud.common.page.PageParams;

import java.util.List;

public interface EmpService {
    List<Emp> listEmps();

    PageBean<Emp> listEmps(PageParams pageParams);

}
