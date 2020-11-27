package com.ynhj.magic_war.model.entity;

/**
 * @date: 2020-11-23
 * @author: yangniuhaojiang
 * @title: PlayerRoom
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class PlayerRoom {
    private String uid;
    private String username;
    private String nickname;
    private int degree; //0房主
    private int score;
    private int roomStatus; // 0待1//准备
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
     * @title: getDegree
     * @description: update_version: update_date: update_author: update_note:
     */
    public int getDegree() {
        return degree;
    }

    /**
     * @param degree the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setDegree
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setDegree(int degree) {
        this.degree = degree;
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
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getRoomStatus
     * @description: update_version: update_date: update_author: update_note:
     */
    public int getRoomStatus() {
        return roomStatus;
    }

    /**
     * @param roomStatus the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setRoomStatus
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setRoomStatus(int roomStatus) {
        this.roomStatus = roomStatus;
    }


}
