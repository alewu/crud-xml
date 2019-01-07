package com.ale.util.common;

import com.ale.common.page.PageBean;
import com.ale.common.page.PageParam;
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
        Integer offset = pageParam.getOffset();
        Integer  pageNum = offset == null ? 1 : offset;
        Integer limit = pageParam.getLimit();
        Integer pageSize = limit == null ? 20 : limit;
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
