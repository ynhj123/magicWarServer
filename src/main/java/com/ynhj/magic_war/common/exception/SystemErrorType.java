package com.ynhj.magic_war.common.exception;


public enum SystemErrorType implements ErrorType {

    BAD_REQUEST_ERROR("400", "当前请求无法被服务器理解！"),
    UNAUTHORIZED_ERROR("401", "当前请求需要用户验证！"),
    REPEAT_ERROR("402", "已经存在！"),
    FORBIDDEN_ERROR("403", "您没有权限访问资源！"),
    NOTFOUND_ERROR("404", "没有找到！"),
    SYSTEM_ERROR("500", "系统出现异常，请您稍后再试或联系管理员！"),
    OTHER_ERROR("999", "系统出现未知异常，请联系管理员！"),
    SYSTEM_BUSY("000001", "系统繁忙,请稍候再试"),

    GATEWAY_NOT_FOUND_SERVICE("010404", "服务未找到"),
    GATEWAY_ERROR("502", "网关异常"),
    GATEWAY_CONNECT_TIME_OUT("504", "网关超时"),

    ARGUMENT_NOT_VALID("020000", "请求参数校验不通过"),
    UPLOAD_FILE_SIZE_LIMIT("020001", "上传文件大小超过限制"),

    DUPLICATE_PRIMARY_KEY("030000","唯一键冲突");

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String mesg;

    SystemErrorType(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getCode
     * @description: update_version: update_date: update_author: update_note:
     */
    @Override
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
    @Override
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
}
