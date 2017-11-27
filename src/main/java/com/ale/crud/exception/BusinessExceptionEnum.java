package com.ale.crud.exception;

public enum BusinessExceptionEnum implements ExceptionEnum {
    UserNotExist(404, "用户不存在");

    private final Integer code;
    private final String message;

    BusinessExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
