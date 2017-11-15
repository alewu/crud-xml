package com.koko.crud.util.freemarker.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.koko.crud.util.freemarker.test.common.bean.page.PageBean;
import com.koko.crud.util.freemarker.test.common.bean.page.PageParams;
import com.koko.crud.util.freemarker.test.entity.People;
import com.koko.crud.util.freemarker.test.dao.PeopleDAO;
import com.koko.crud.util.freemarker.test.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author alewu
 * @date 2017-11-04
 * @description peopleService 接口业务逻辑实现类
 */
@Service("peopleService")
public class PeopleServiceImpl extends BaseServiceImpl<People> implements PeopleService{
    @Autowired
    private PeopleDAO peopleDAO;

    @Override
    public PageBean<People> listPeople(PageParams pageParams) {
        // TODO
        Integer offset = pageParams.getOffset() == null ? 0 : pageParams.getOffset();
        Integer limit = pageParams.getLimit() == null ? 20 : pageParams.getLimit();
        PageHelper.startPage(offset, limit);
        List<People> list = peopleDAO.listPeople();
        return new PageBean<>(list);
    }

}