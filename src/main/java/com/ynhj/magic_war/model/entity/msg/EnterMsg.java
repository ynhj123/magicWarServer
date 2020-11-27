package com.ynhj.magic_war.model.entity.msg;

/**
 * @date: 2020-11-19
 * @author: yangniuhaojiang
 * @title: EnterMsg
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class EnterMsg extends MsgBase {

    private String nickname;
    private String token;
    private String code;
    private String msg;

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getNickname
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the String to set
     * @author: yangniuhaojiang
     * @title: setNickname
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getToken
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the String to set
     * @author: yangniuhaojiang
     * @title: setToken
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setToken(String token) {
        this.token = token;
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
