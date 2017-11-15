package com.koko.crud.util.freemarker.test.dao;

import com.koko.crud.util.freemarker.test.entity.UserPeople;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
* @author alewu
* @date 2017-11-04
* @description UserPeopleDAO持久层映射接口
*/
@Repository
public interface UserPeopleDAO extends BaseDAO<UserPeople> {

    List<UserPeople> listUserPeople();

}