package com.koko.crud.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author alewu
 * @date 2017/11/12 19:21
 * @description
 */
@Data
public class ErrorResponse {

    private Error error;

    public static ErrorResponse create(Integer code, String message){
        ErrorResponse errorResponse = new ErrorResponse();
        Error error = new Error(code,message);
        errorResponse.setError(error);
        return errorResponse;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static  class Error{
        /*状态码*/
        private Integer code;
        /*消息*/
        private String message;

    }
}
