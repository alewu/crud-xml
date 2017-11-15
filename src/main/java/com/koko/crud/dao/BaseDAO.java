package com.koko.crud.dao;

public interface BaseDAO<T> {

    int insertOne(T t);

    int updateOne(T t);

    T getOne(Object obj);

    int deleteOne(Object obj);
}
