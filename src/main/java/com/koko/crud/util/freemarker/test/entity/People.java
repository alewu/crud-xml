package com.koko.crud.util.freemarker.test.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
/**
 * @author alewu
 * @date 2017-11-04
 * @@description People 数据库表对应的实体类
*/
@Data
@SuppressWarnings("serial")
public class People extends BaseEntity implements Serializable {
    /** 主键id **/
    private Long id;
    /** 姓名 **/
    private String name;
    /** 性别 **/
    private String sex;
    /** 年龄 **/
    private Integer age;
    /** 品质 **/
    private Integer quality;
    /** 价格 **/
    private Double price;
    /** 摘要 **/
    private String digest;


}
