package com.koko.crud.util.freemarker.test.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
/**
 * @author alewu
 * @date 2017-11-04
 * @@description UserInfo 数据库表对应的实体类
*/
@Data
@SuppressWarnings("serial")
public class UserInfo extends BaseEntity implements Serializable {
    /** 主键id **/
    private String id;
    /** 昵称 **/
    private String nickName;
    /** 邮箱 **/
    private String email;
    /** 手机号 **/
    private String phone;


}
