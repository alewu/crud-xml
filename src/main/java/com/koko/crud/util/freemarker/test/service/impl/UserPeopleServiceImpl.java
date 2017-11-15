package com.koko.crud.util.freemarker.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.koko.crud.util.freemarker.test.common.bean.page.PageBean;
import com.koko.crud.util.freemarker.test.common.bean.page.PageParams;
import com.koko.crud.util.freemarker.test.entity.UserPeople;
import com.koko.crud.util.freemarker.test.dao.UserPeopleDAO;
import com.koko.crud.util.freemarker.test.service.UserPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author alewu
 * @date 2017-11-04
 * @description userPeopleService 接口业务逻辑实现类
 */
@Service("userPeopleService")
public class UserPeopleServiceImpl extends BaseServiceImpl<UserPeople> implements UserPeopleService{
    @Autowired
    private UserPeopleDAO userPeopleDAO;

    @Override
    public PageBean<UserPeople> listUserPeople(PageParams pageParams) {
        // TODO
        Integer offset = pageParams.getOffset() == null ? 0 : pageParams.getOffset();
        Integer limit = pageParams.getLimit() == null ? 20 : pageParams.getLimit();
        PageHelper.startPage(offset, limit);
        List<UserPeople> list = userPeopleDAO.listUserPeople();
        return new PageBean<>(list);
    }

}