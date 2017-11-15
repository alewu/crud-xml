package com.koko.crud.util.freemarker.test.controller;

import com.koko.crud.util.freemarker.test.entity.People;
import com.koko.crud.util.freemarker.test.common.bean.page.PageBean;
import com.koko.crud.util.freemarker.test.common.bean.page.PageParams;
import com.koko.crud.util.freemarker.test.common.bean.Response;
import com.koko.crud.util.freemarker.test.constants.RestURIConstants;
import com.koko.crud.util.freemarker.test.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
* @author alewu
* @date 2017-11-04
* @description People 控制器
*/
@RestController
@RequestMapping(RestURIConstants.APP_PREFIX)
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    /**
     * @author alewu
     * @date 2017-11-04
     * @description 增加单个People
     */
    @PostMapping(RestURIConstants.PEOPLES)
    public Response insertOne(People people){
        peopleService.insertOne(people);
        return Response.success();
    }
    /**
     * @author alewu
     * @date 2017-11-04
     * @description 删除单个People
     */
    @DeleteMapping(RestURIConstants.PEOPLE_ID)
    public Response deleteOne(@PathVariable String peopleId){
        peopleService.deleteOne(peopleId);
        return Response.success();
    }

    /**
     * @author alewu
     * @date 2017-11-04
     * @description 更新单个People
     */
    @PutMapping(RestURIConstants.PEOPLE_ID)
    public Response updateOne(@PathVariable String peopleId,People people){
        int m = peopleService.updateOne(people);
        return Response.success();
    }

    /**
     * @author alewu
     * @date 2017-11-04
     * @description 查询单个People
     */
    @GetMapping(RestURIConstants.PEOPLE_ID)
    public Response getOne(@PathVariable String peopleId){
        People people = peopleService.getOne(peopleId);
        return Response.success().put("people", people);
    }

    /**
     * @author alewu
     * @date 2017-11-04
     * @description 分页查询People
     */
    @GetMapping(RestURIConstants.PEOPLES)
    public Response listPeople(PageParams pageParams){
        PageBean<People> pageBean = peopleService.listPeople(pageParams);
        return Response.success().put("pageBean", pageBean);
    }
}


