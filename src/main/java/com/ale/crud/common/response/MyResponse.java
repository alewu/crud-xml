package com.ale.crud.common.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alewu
 * @date 2017/11/12 13:22
 * @description 统一响应结构
 */
@Data
public class MyResponse {

    /**
     * 状态码
     **/
    private Integer code;

    /**
     * 响应内容
     **/
    Map<String, Object> data = new HashMap<>();

    public MyResponse() {
    }

    //链式操作返回信息
    public MyResponse put(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    public static MyResponse ok() {
        MyResponse myResponse = new MyResponse();
        myResponse.setCode(200);
        return myResponse;
    }

    public static MyResponse failed() {
        MyResponse myResponse = new MyResponse();
        myResponse.setCode(400);
        return myResponse;
    }


}
