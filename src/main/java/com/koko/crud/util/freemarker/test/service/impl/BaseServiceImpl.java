package com.koko.crud.util.freemarker.test.service.impl;

import com.koko.crud.util.freemarker.test.dao.BaseDAO;
import com.koko.crud.util.freemarker.test.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author alewu
 * @date 2017-11-16
 * @description 基础服务层
 */
@Service
public class BaseServiceImpl<T> implements BaseService<T>{
    @Autowired
    private BaseDAO baseDAO;

    @Override
    public int insertOne(T t) {
        return baseDAO.insertOne(t);
    }

    @Override
    public int deleteOne(String id) {
    return baseDAO.deleteOne(id);
    }

    @Override
    public int updateOne(T t) {
        return baseDAO.updateOne(t);
    }

    @Override
    public T getOne(Object obj) {
        return (T) baseDAO.getOne(obj);
    }


}

