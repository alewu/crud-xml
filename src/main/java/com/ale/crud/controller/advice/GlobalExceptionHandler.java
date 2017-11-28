package com.ale.crud.controller.advice;

import com.ale.crud.util.common.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;


/**
 * @author alewu
 * @date 2017/10/18 12:44
 * @description 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*日志*/
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleSQLException(HttpServletRequest request, Exception ex) {
        String message = ex.getMessage();
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    @ExceptionHandler(ClassCastException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleClassCastException(HttpServletRequest request, Exception ex) {
        String message = ex.getMessage();
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleNumberFormatException(HttpServletRequest request, Exception ex) {
        String message = ex.getMessage();
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleNullPointerException(HttpServletRequest request, Exception ex) {
        String message = ex.getMessage();
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IOException.class)
    public R handleIOException(HttpServletRequest request, Exception ex) {
        LOGGER.error(ex.getMessage());
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public R handleArrayIndexOutOfBoundsException(HttpServletRequest request, Exception ex) {
        LOGGER.error(ex.getMessage());
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoSuchAlgorithmException.class)
    public R handleNoSuchAlgorithmException(HttpServletRequest request, Exception ex) {
        LOGGER.error(ex.getMessage());
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InvalidKeySpecException.class)
    public R handleInvalidKeySpecException(HttpServletRequest request, Exception ex) {
        LOGGER.error(ex.getMessage());
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }


}

