package com.koko.crud.test;


import com.koko.crud.bean.Dept;
import com.koko.crud.bean.Emp;
import com.koko.crud.dao.DeptMapper;
import com.koko.crud.dao.EmpMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * //推荐spring的项目使用spring的单元测试
 * 1、导入spring Test 模块
 * 2、@ContextConfiguration 指定spring配置文件的位置
 * 3、直接Autowired要使用的组件即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext*.xml"})
public class MapperTest {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    SqlSession sqlSession;

    @Test
    public void testCRUD() {
        //1.创建springIoC容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.从spring中获取Mapper
        DeptMapper deptBean = applicationContext.getBean(DeptMapper.class);
        System.out.println(deptBean);
        //1.插入部门

        deptMapper.insertSelective(new Dept(null,"开发部门"));
        deptMapper.insertSelective(new Dept(null,"测试部门"));

        //2.插入员工
        empMapper.insertSelective(new Emp(null, 1, "jack", 12,"M",  "wyweiwei@163.com"));


        //3.批量插入员工，可使用执行批量操作的sqlSession
        for (int i = 0; i < 100; i++) {
            empMapper.insertSelective(new Emp(null, 2,"jack", 12,"M", i + "kkkooo@163.com"));
        }
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);

        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            empMapper.insertSelective(new Emp(null, 1,"jack", 12,"M", uid + "@163.com"));
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("用时为：" + (endTime - startTime));


    }
}
