package com.ale.crud.controller;

import com.ale.crud.bean.Dept;
import com.ale.crud.common.page.PageParams;
import com.ale.crud.common.response.Response;
import com.ale.crud.constants.RestURIConstants;
import com.ale.crud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author alewu
 * @date 2017/10/30 11:12
 * @description 部门相关
 */
@RestController
@RequestMapping(RestURIConstants.APP_PREFIX)
public class DeptController {
    @Autowired
    private DeptService deptService;
    @GetMapping(RestURIConstants.DEPT_ID)
    public Response getDept(@PathVariable Long deptId){
        //Dept dept = deptService.getDept(deptId);
       // return new Response().success(dept);
        return new Response();
    }

    /**
     * @author alewu
     * @date 2017/10/30 11:14
     * @description 分页查询部门
     */
    @GetMapping(RestURIConstants.DEPTS)
    public Response listDept(PageParams pageParams){
       // PageBean<Dept> pageBean = deptService.listDept(pageParams);
        return new Response().success();
    }

    /**
     * @author alewu
     * @date 2017/10/30 11:13
     * @description 新增单个部门
     */
    @PostMapping(RestURIConstants.DEPTS)
    public Response addDept(@RequestBody Dept dept){
        //deptService.addDept(dept);
        return new Response().success(dept);
    }

    /**
     * @author alewu
     * @date 2017/10/30 11:14
     * @description 批量新增部门
     */
//    @PostMapping(RestURIConstants.DEPTS)
//    public MyResponse addDepts(@RequestBody List<Dept> depts){
//        deptService.addDepts(depts);
//        return new MyResponse().success(depts);
//    }

    /**
     * @author alewu
     * @date 2017/10/30 11:15
     * @description 删除单个部门
     */
    @DeleteMapping(RestURIConstants.DEPT_ID)
    public Response deleteDept(@PathVariable Long deptId){
        //deptService.deleteDept(deptId);
        return new Response().success();
    }

    /**
     * @author alewu
     * @date 2017/10/30 11:15
     * @description 删除多个部门
     */
//    @DeleteMapping(RestURIConstants.DEPTS)
//    public MyResponse deleteDepts(@RequestBody List<Long> deptIds){
//        // TODO
//
//        deptService.deleteDepts(deptIds);
//        return new MyResponse().success();
//    }

    /**
     * @author alewu
     * @date 2017/10/30 11:15
     * @description 更新部门信息
     */
    @PutMapping(RestURIConstants.DEPT_ID)
    public Response updateDept(@PathVariable String deptId,Dept dept){
       // int m = deptService.updateDept(dept);
        return new Response().success();
    }
}
