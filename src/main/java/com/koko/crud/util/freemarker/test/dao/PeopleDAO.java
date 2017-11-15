package com.koko.crud.util.freemarker.test.dao;

import com.koko.crud.util.freemarker.test.entity.People;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
* @author alewu
* @date 2017-11-04
* @description PeopleDAO持久层映射接口
*/
@Repository
public interface PeopleDAO extends BaseDAO<People> {

    List<People> listPeople();

}