package com.koko.crud.util.freemarker.test.dao;

import com.koko.crud.util.freemarker.test.entity.UserInfo;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
* @author alewu
* @date 2017-11-04
* @description UserInfoDAO持久层映射接口
*/
@Repository
public interface UserInfoDAO extends BaseDAO<UserInfo> {

    List<UserInfo> listUserInfo();

}