package com.koko.crud.util.freemarker.test.service;

import com.koko.crud.util.freemarker.test.entity.UserInfo;
import com.koko.crud.util.freemarker.test.common.bean.page.PageBean;
import com.koko.crud.util.freemarker.test.common.bean.page.PageParams;

/**
 * @author alewu
 * @date 2017-11-04
 * @description UserInfo服务层接口
 */
public interface UserInfoService extends BaseService<UserInfo> {

    PageBean<UserInfo> listUserInfo(PageParams pageParams);

}