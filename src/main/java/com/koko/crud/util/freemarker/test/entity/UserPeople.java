package com.koko.crud.util.freemarker.test.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
/**
 * @author alewu
 * @date 2017-11-04
 * @@description UserPeople 数据库表对应的实体类
*/
@Data
@SuppressWarnings("serial")
public class UserPeople extends BaseEntity implements Serializable {
    /** 关联表主键 **/
    private String tbUserPeopleId;
    /** 用户id **/
    private String userId;
    /** 人们id **/
    private String peopleId;


}
