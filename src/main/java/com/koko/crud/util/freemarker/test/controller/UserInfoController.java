package com.koko.crud.util.freemarker.test.controller;

import com.koko.crud.util.freemarker.test.entity.UserInfo;
import com.koko.crud.util.freemarker.test.common.bean.page.PageBean;
import com.koko.crud.util.freemarker.test.common.bean.page.PageParams;
import com.koko.crud.util.freemarker.test.common.bean.Response;
import com.koko.crud.util.freemarker.test.constants.RestURIConstants;
import com.koko.crud.util.freemarker.test.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
* @author alewu
* @date 2017-11-04
* @description UserInfo 控制器
*/
@RestController
@RequestMapping(RestURIConstants.APP_PREFIX)
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * @author alewu
     * @date 2017-11-04
     * @description 增加单个UserInfo
     */
    @PostMapping(RestURIConstants.USERINFOS)
    public Response insertOne(UserInfo userInfo){
        userInfoService.insertOne(userInfo);
        return Response.success();
    }
    /**
     * @author alewu
     * @date 2017-11-04
     * @description 删除单个UserInfo
     */
    @DeleteMapping(RestURIConstants.USERINFO_ID)
    public Response deleteOne(@PathVariable String userInfoId){
        userInfoService.deleteOne(userInfoId);
        return Response.success();
    }

    /**
     * @author alewu
     * @date 2017-11-04
     * @description 更新单个UserInfo
     */
    @PutMapping(RestURIConstants.USERINFO_ID)
    public Response updateOne(@PathVariable String userInfoId,UserInfo userInfo){
        int m = userInfoService.updateOne(userInfo);
        return Response.success();
    }

    /**
     * @author alewu
     * @date 2017-11-04
     * @description 查询单个UserInfo
     */
    @GetMapping(RestURIConstants.USERINFO_ID)
    public Response getOne(@PathVariable String userInfoId){
        UserInfo userInfo = userInfoService.getOne(userInfoId);
        return Response.success().put("userInfo", userInfo);
    }

    /**
     * @author alewu
     * @date 2017-11-04
     * @description 分页查询UserInfo
     */
    @GetMapping(RestURIConstants.USERINFOS)
    public Response listUserInfo(PageParams pageParams){
        PageBean<UserInfo> pageBean = userInfoService.listUserInfo(pageParams);
        return Response.success().put("pageBean", pageBean);
    }
}


