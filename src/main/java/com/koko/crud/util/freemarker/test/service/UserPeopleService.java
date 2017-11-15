package com.koko.crud.util.freemarker.test.service;

import com.koko.crud.util.freemarker.test.entity.UserPeople;
import com.koko.crud.util.freemarker.test.common.bean.page.PageBean;
import com.koko.crud.util.freemarker.test.common.bean.page.PageParams;

/**
 * @author alewu
 * @date 2017-11-04
 * @description UserPeople服务层接口
 */
public interface UserPeopleService extends BaseService<UserPeople> {

    PageBean<UserPeople> listUserPeople(PageParams pageParams);

}