package ${packageName}.dao;


/**
 * @author ${author}
 * @date ${date}
 * @description 基础接口
 */
public interface BaseDAO<T> {

    int insertOne(T t);

    int deleteOne(String id);

    int updateOne(T t);

    T getOne(Object obj);



}