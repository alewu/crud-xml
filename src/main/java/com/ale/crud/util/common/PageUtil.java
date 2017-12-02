package com.ale.crud.util.common;

import com.ale.crud.common.page.PageBean;
import com.ale.crud.common.page.PageParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageUtil {

//    public <T, E> PageBean<E> paging(PageParam pageParam, T t) {
//        startPage(pageParam);
//
//        List<E> list = userCollectionDao.queryUserCollectionArticleList(articleParam);
//
//        return pageBean;
//    }

    public static void startPage(PageParam pageParam){
        Integer  pageNum = pageParam.getOffset() == null ? 1 : pageParam.getOffset();
        Integer pageSize = pageParam.getLimit() == null ? 20 : pageParam.getLimit();
        PageHelper.startPage(pageNum, pageSize);
    }

    public static  <E> PageBean<E> getPageBean(List<E> list ){
        PageBean<E> pageBean = new PageBean<>();
        PageInfo<E> pageInfo = new PageInfo<>(list);
        pageBean.setRecordCount(pageInfo.getTotal());
        pageBean.setPageCount(pageInfo.getPages());
        pageBean.setList(list);
        return pageBean;
    }

}
