package com.ynhj.magic_war.model.entity.dto;

import java.io.Serializable;

/**
 * @date: 2020-11-13
 * @author: yangniuhaojiang
 * @title: RegisterRequest
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class RegisterRequest implements Serializable {
    private String username;
    private String password;
    private String nickName;

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getNickName
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName the String to set
     * @author: yangniuhaojiang
     * @title: setNickName
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getUsername
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the String to set
     * @author: yangniuhaojiang
     * @title: setUsername
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getPassword
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the String to set
     * @author: yangniuhaojiang
     * @title: setPassword
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
