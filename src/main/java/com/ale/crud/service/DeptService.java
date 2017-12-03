package com.ale.crud.service;


import com.ale.crud.bean.Dept;
import com.ale.crud.common.page.PageBean;
import com.ale.crud.common.page.PageParam;

public interface DeptService extends BaseService<Dept>{

    PageBean<Dept> listDept(PageParam pageParam);


}
