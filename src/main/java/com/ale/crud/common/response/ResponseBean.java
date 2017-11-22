package com.ale.crud.common.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseBean {
    /** 响应状态码 **/
    private Integer code;
    /** 响应内容 **/
    Map<String, Object> data = new HashMap<>();
    /** 响应消息 **/
    private String message;

    //链式操作返回信息
    public ResponseBean put(String key,Object value){
        this.getData().put(key, value);
        return this;
    }

    public static ResponseBean success() {
        ResponseBean response = new ResponseBean();
        response.setCode(200);
        return response;
    }


    public static ResponseBean failure(Integer code, String message) {
        ResponseBean response = new ResponseBean();
        response.setCode(code);
        return response;
    }

}
