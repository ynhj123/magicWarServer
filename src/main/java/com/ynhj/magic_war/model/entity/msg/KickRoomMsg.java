package com.ynhj.magic_war.model.entity.msg;

/**
 * @date: 2020-11-27
 * @author: yangniuhaojiang
 * @title: KickRoomMsg
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class KickRoomMsg extends MsgBase {
    private String uid;
    private String code;
    private String msg;

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getUid
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid the String to set
     * @author: yangniuhaojiang
     * @title: setUid
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setUid(String uid) {
        this.uid = uid;
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
