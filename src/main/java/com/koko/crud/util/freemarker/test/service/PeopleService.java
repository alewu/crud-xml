package com.koko.crud.util.freemarker.test.service;

import com.koko.crud.util.freemarker.test.entity.People;
import com.koko.crud.util.freemarker.test.common.bean.page.PageBean;
import com.koko.crud.util.freemarker.test.common.bean.page.PageParams;

/**
 * @author alewu
 * @date 2017-11-04
 * @description People服务层接口
 */
public interface PeopleService extends BaseService<People> {

    PageBean<People> listPeople(PageParams pageParams);

}