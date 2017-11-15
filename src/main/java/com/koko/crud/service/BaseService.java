package com.koko.crud.service;

public interface BaseService<T> {

    int insertOne(T t);

    int updateOne(T t);

    T getOne(Object obj);

    int deleteOne(Object obj);
}
