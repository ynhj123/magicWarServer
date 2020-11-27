package com.ynhj.magic_war.model.entity.msg;

/**
 * @date: 2020-11-23
 * @author: yangniuhaojiang
 * @title: LevelRoomMsg
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class LeaveRoomMsg extends MsgBase {
    private String code;
    private String msg;

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getCode
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the $field.typeName to set
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
     * @title: getMsg
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the String to set
     * @author: yangniuhaojiang
     * @title: setMsg
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
