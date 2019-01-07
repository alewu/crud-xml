package com.ale.common.response;

import lombok.Data;

/**
 * @author alewu
 * @date 2017/10/24 8:48
 * @description 交互类：统一响应结构
 */
@Data
public class ResponseResult<T> {

    private boolean success;
    private String message;
    private T data;
    /* 不提供直接设置errorCode的接口，只能通过setErrorInfo方法设置错误信息 */
    private String errorCode;
    public ResponseResult() {}



}