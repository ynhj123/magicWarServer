package com.ynhj.magic_war.model.entity.dto;

/**
 * @date: 2020-11-13
 * @author: yangniuhaojiang
 * @title: LoginRequest
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class LoginRequest {
    private String username;
    private String password;

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
