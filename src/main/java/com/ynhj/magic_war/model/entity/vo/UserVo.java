package com.ynhj.magic_war.model.entity.vo;

/**
 * @date: 2020-11-24
 * @author: yangniuhaojiang
 * @title: UserInfo
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class UserVo {
    private String uid;
    private String username;
    private String nickname;
    private int score;
    private String icon;
    private Integer roleId;

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
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getScore
     * @description: update_version: update_date: update_author: update_note:
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setScore
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getIcon
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the String to set
     * @author: yangniuhaojiang
     * @title: setIcon
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getRoleId
     * @description: update_version: update_date: update_author: update_note:
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the String to set
     * @author: yangniuhaojiang
     * @title: setRoleId
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
