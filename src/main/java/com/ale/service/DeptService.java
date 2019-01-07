package com.ale.service;


import com.ale.common.page.PageBean;
import com.ale.common.page.PageParam;
import com.ale.entity.Dept;

public interface DeptService extends BaseService<Dept>{

    PageBean<Dept> listDept(PageParam pageParam);


}
