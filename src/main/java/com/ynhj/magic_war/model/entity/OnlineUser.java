package com.ynhj.magic_war.model.entity;

/**
 * @date: 2020-11-19
 * @author: yangniuhaojiang
 * @title: OnlineUser
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class OnlineUser {
    private String id;
    private String username;
    private String nickname;
    private String token;
    private String sessionId;
    private String roomId;

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getRoomId
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * @param roomId the String to set
     * @author: yangniuhaojiang
     * @title: setRoomId
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getId
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the String to set
     * @author: yangniuhaojiang
     * @title: setId
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setId(String id) {
        this.id = id;
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
     * @title: getSessionId
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the String to set
     * @author: yangniuhaojiang
     * @title: setSessionId
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


}
