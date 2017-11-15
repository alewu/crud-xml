//package com.koko.crud.service.impl;
//
//import com.koko.crud.dao.BaseDAO;
//import com.koko.crud.service.BaseService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class BaseServiceImpl<T> implements BaseService<T>{
//    @Autowired
//    private BaseDAO baseDAO;
//    @Override
//    public int insertOne(T t) {
//        return baseDAO.insertOne(t);
//    }
//
//    @Override
//    public int updateOne(T t) {
//        return baseDAO.updateOne(t);
//    }
//
//    @Override
//    public T getOne(Object obj) {
//        return (T) baseDAO.getOne(obj);
//    }
//
//    @Override
//    public int deleteOne(Object obj) {
//        return baseDAO.deleteOne(obj);
//    }
//}
