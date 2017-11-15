package com.koko.crud.util.freemarker.test.controller;

import com.koko.crud.util.freemarker.test.entity.UserPeople;
import com.koko.crud.util.freemarker.test.common.bean.page.PageBean;
import com.koko.crud.util.freemarker.test.common.bean.page.PageParams;
import com.koko.crud.util.freemarker.test.common.bean.Response;
import com.koko.crud.util.freemarker.test.constants.RestURIConstants;
import com.koko.crud.util.freemarker.test.service.UserPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
* @author alewu
* @date 2017-11-04
* @description UserPeople 控制器
*/
@RestController
@RequestMapping(RestURIConstants.APP_PREFIX)
public class UserPeopleController {
    @Autowired
    private UserPeopleService userPeopleService;

    /**
     * @author alewu
     * @date 2017-11-04
     * @description 增加单个UserPeople
     */
    @PostMapping(RestURIConstants.USERPEOPLES)
    public Response insertOne(UserPeople userPeople){
        userPeopleService.insertOne(userPeople);
        return Response.success();
    }
}


