package com.ale.crud.service.impl;

import com.ale.crud.dao.BaseDAO;
import com.ale.crud.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl<T> implements BaseService<T> {
    private BaseDAO<T> baseDAO;
    public void setBaseDAO(BaseDAO<T> baseDao) {
        this.baseDAO = baseDao;
    }

    @Override
    public int insertOne(T t) {
        return baseDAO.insertOne(t);
    }

    @Override
    public int updateOne(T t) {
        return baseDAO.updateOne(t);
    }

    @Override
    public T getOne(Object obj) {
        return baseDAO.getOne(obj);
    }

    @Override
    public int deleteOne(Object obj) {
        return baseDAO.deleteOne(obj);
    }
}
