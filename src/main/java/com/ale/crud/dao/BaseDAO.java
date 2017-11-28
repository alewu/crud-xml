package com.ale.crud.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface BaseDAO<T> {

    int insertOne(T t);

    int updateOne(T t);

    T getOne(Object obj);

    int deleteOne(Object obj);
}
