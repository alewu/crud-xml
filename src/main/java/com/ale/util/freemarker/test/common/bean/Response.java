package com.ale.util.freemarker.test.common.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alewu
 * @date 2017/11/12 13:22
 * @description 统一响应结构
 */
@Data
public class Response {

    /**
     * 元数据
     **/
    private Meta meta;

    /**
     * 响应内容
     **/
    Map<String, Object> data = new HashMap<>();

    //链式操作返回信息
    public Response put(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    public static Response success() {
        Response response = new Response();
        Meta meta = new Meta(true, 200);
        response.setMeta(meta);
        return response;
    }

    public static Response failure(String message) {
        Response response = new Response();
        Meta meta = new Meta(false, message);
        response.setMeta(meta);
        return response;
    }

    public static Response failure(Integer code, String message) {
        Response response = new Response();
        Meta meta = new Meta(false, code, message);
        response.setMeta(meta);
        return response;
    }

    /**
     * Title: 请求元数据
     *
     * @author rico
     * @created 2017年7月4日 下午5:08:12
     */
    private static class Meta {

        private boolean success;
        private Integer code;
        private String message;

        public Meta(boolean success, Integer code) {
            this.success = success;
            this.code = code;
        }

        public Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public Meta(boolean success, Integer code, String message) {
            this.success = success;
            this.code = code;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }


}
