package com.koko.crud.util.freemarker.test.service;


/**
 * @author alewu
 * @date 2017-11-16
 * @description 基础接口
 */
public interface BaseService<T> {

    int insertOne(T t);

    int deleteOne(String id);

    int updateOne(T t);

    T getOne(Object obj);



}