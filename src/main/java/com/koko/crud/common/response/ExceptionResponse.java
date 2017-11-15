package com.koko.crud.common.response;
/**
 * @author alewu
 * @date 2017/10/24 11:07
 * @description 异常响应类
 */
public class ExceptionResponse {
    /*状态码*/
    private Integer code;
    /*消息*/
    private String message;

    public ExceptionResponse(){
        super();
    }

    public ExceptionResponse(Integer code, String message){
        this.message = message;  
        this.code = code;  
    }  
      
    public static ExceptionResponse create(Integer code, String message){  
        return new ExceptionResponse(code, message);  
    }  
      
    public Integer getCode() {  
        return code;  
    }

    public String getMessage() {  
        return message;  
    }  
      
}
