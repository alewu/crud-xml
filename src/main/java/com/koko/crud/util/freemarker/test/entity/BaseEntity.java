package com.koko.crud.util.freemarker.test.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    private Date gmtCreate;

    private Date gmtModified;
}
