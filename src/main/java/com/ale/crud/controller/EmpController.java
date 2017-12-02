package com.ale.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ale.crud.bean.Emp;
import com.ale.crud.common.page.PageBean;
import com.ale.crud.common.page.PageParam;
import com.ale.crud.common.response.Msg;
import com.ale.crud.common.response.Response;
import com.ale.crud.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EmpController {

    @Autowired
    private EmpService empService;


    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn){
        //引入PageHelper分页查询
        PageHelper.startPage(pn,5);
        //startPage紧跟的查询就是一个分页查询
        List<Emp> empList = empService.listEmps();
        //使用PageInfo包装查询后的结果，只需将pageInfo交给页面就行
        //封装了详细的页面信息，包括我们查询出来的数据，传入连续显示的页数
        PageInfo pageInfo = new PageInfo(empList, 6);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 查询员工（分页查询）
     * @param PageParam 分页参数
     * @return 响应
     */
    @GetMapping("/emps1")
    @ResponseBody
    public Response getEmpsWithJson(PageParam PageParam){
        PageParam.setOffset(1);
        PageParam.setLimit(6);
        PageBean<Emp> pageBean = empService.listEmps(PageParam);
        if (pageBean == null) {
            return new Response().failure();
        }
        Response response = new Response().success(pageBean);
        return response;
    }


    /**
     * 查询员工（分页查询）
     * @param pn
     * @return
     */
    @RequestMapping("/emps2")
    public String listEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model){
        //引入PageHelper分页查询
        PageHelper.startPage(pn,5);

        //startPage紧跟的查询就是一个分页查询
        List<Emp> empList = empService.listEmps();

        //使用PageInfo包装查询后的结果，只需将pageInfo交给页面就行
        //封装了详细的页面信息，包括我们查询出来的数据，传入连续显示的页数

        PageInfo pageInfo = new PageInfo(empList, 6);
        model.addAttribute("pageInfo", pageInfo);

        return "list";
    }

}
