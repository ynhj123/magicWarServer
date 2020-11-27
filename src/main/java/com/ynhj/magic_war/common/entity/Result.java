package com.ynhj.magic_war.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ynhj.magic_war.common.exception.BaseException;
import com.ynhj.magic_war.common.exception.ErrorType;
import com.ynhj.magic_war.common.exception.SystemErrorType;

import java.time.Instant;
import java.time.ZonedDateTime;


public class Result<T> {

    public static final String SUCCESSFUL_CODE = "200";
    public static final String SUCCESSFUL_MESG = "处理成功";


    public Result(String code, String mesg, Instant time, T data) {
        this.code = code;
        this.mesg = mesg;
        this.time = time;
        this.data = data;
    }

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getCode
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the String to set
     * @author: yangniuhaojiang
     * @title: setCode
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getMesg
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getMesg() {
        return mesg;
    }

    /**
     * @param mesg the String to set
     * @author: yangniuhaojiang
     * @title: setMesg
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    /**
     * @return the Instant
     * @author: yangniuhaojiang
     * @title: getTime
     * @description: update_version: update_date: update_author: update_note:
     */
    public Instant getTime() {
        return time;
    }

    /**
     * @param time the Instant to set
     * @author: yangniuhaojiang
     * @title: setTime
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setTime(Instant time) {
        this.time = time;
    }

    /**
     * @return the T
     * @author: yangniuhaojiang
     * @title: getData
     * @description: update_version: update_date: update_author: update_note:
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the T to set
     * @author: yangniuhaojiang
     * @title: setData
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setData(T data) {
        this.data = data;
    }

    private String code;

    private String mesg;

    private Instant time;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Result() {
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * @param errorType
     */
    public Result(ErrorType errorType) {
        this.code = errorType.getCode();
        this.mesg = errorType.getMesg();
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * @param errorType
     * @param data
     */
    public Result(ErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }

    /**
     * 内部使用，用于构造成功的结果
     *
     * @param code
     * @param mesg
     * @param data
     */
    private Result(String code, String mesg, T data) {
        this.code = code;
        this.mesg = mesg;
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result success(Object data) {
        return new Result<>(SUCCESSFUL_CODE, SUCCESSFUL_MESG, data);
    }

    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @return Result
     */
    public static Result fail() {
        return new Result(SystemErrorType.SYSTEM_ERROR);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @param baseException
     * @return Result
     */
    public static Result fail(BaseException baseException) {
        return fail(baseException, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result fail(BaseException baseException, Object data) {
        return new Result<>(baseException.getErrorType(), data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @param data
     * @return Result
     */
    public static Result fail(ErrorType errorType, Object data) {
        return new Result<>(errorType, data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @return Result
     */
    public static Result fail(ErrorType errorType) {
        Result fail = new Result<>(errorType, null);
        return fail;
        //return Result.fail(errorType, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result fail(Object data) {
        return new Result<>(SystemErrorType.SYSTEM_ERROR, data);
    }


    /**
     * 成功code=000000
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    /**
     * 失败
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }

    public static Result fail(String code, String mesg) {
        return new Result(code, mesg, null);
    }

}
