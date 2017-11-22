package com.ale.crud.common.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alewu
 * @date 2017/11/12 19:18
 * @description 谷歌响应体
 */
@Data
public class CorrectResponse {

    /** 响应内容 **/
    Map<String, Object> data = new HashMap<>();

    //链式操作返回信息
    public CorrectResponse put(String key, Object value){
        this.getData().put(key, value);
        return this;
    }

    public static CorrectResponse success() {
        CorrectResponse response = new CorrectResponse();
        return response;
    }


}
