package com.ynhj.magic_war.conf;

import com.ynhj.magic_war.common.entity.Result;
import com.ynhj.magic_war.common.exception.BaseException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;




/**
 * @date: 2020-11-13
 * @author: yangniuhaojiang
 * @title: ControllerAdviceConf
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@ControllerAdvice
public class ControllerAdviceConf {
    @ResponseBody
    @ExceptionHandler(value = InternalAuthenticationServiceException.class)
    public Result myExceptionHandler(InternalAuthenticationServiceException myex){

        //发生异常进行日志记录，写入数据库或者其他处理，此处省略

        return Result.fail(myex.getMessage());
    }
    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param myex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BaseException.class)
    public Result myExceptionHandler(BaseException myex){

        //发生异常进行日志记录，写入数据库或者其他处理，此处省略

        return Result.fail(myex.getErrorType());
    }

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public Result myExceptionHandler(RuntimeException ex){
        ex.printStackTrace();
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return Result.fail(ex.getMessage());
    }
}
