package com.koko.crud.util.freemarker.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.koko.crud.util.freemarker.test.common.bean.page.PageBean;
import com.koko.crud.util.freemarker.test.common.bean.page.PageParams;
import com.koko.crud.util.freemarker.test.entity.UserInfo;
import com.koko.crud.util.freemarker.test.dao.UserInfoDAO;
import com.koko.crud.util.freemarker.test.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author alewu
 * @date 2017-11-04
 * @description userInfoService 接口业务逻辑实现类
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService{
    @Autowired
    private UserInfoDAO userInfoDAO;

    @Override
    public PageBean<UserInfo> listUserInfo(PageParams pageParams) {
        // TODO
        Integer offset = pageParams.getOffset() == null ? 0 : pageParams.getOffset();
        Integer limit = pageParams.getLimit() == null ? 20 : pageParams.getLimit();
        PageHelper.startPage(offset, limit);
        List<UserInfo> list = userInfoDAO.listUserInfo();
        return new PageBean<>(list);
    }

}