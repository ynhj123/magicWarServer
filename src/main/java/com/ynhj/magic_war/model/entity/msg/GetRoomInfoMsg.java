package com.ynhj.magic_war.model.entity.msg;

import com.ynhj.magic_war.model.entity.PlayerRoom;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2020-11-23
 * @author: yangniuhaojiang
 * @title: GetRoomInfoMsg
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class GetRoomInfoMsg extends MsgBase {
    private String code;
    private String msg;
    private List<PlayerRoom> players = new ArrayList<>();

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
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getMsg
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setMsg
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the PlayerRoom>
     * @author: yangniuhaojiang
     * @title: getPlayers
     * @description: update_version: update_date: update_author: update_note:
     */
    public List<PlayerRoom> getPlayers() {
        return players;
    }

    /**
     * @param players the PlayerRoom> to set
     * @author: yangniuhaojiang
     * @title: setPlayers
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setPlayers(List<PlayerRoom> players) {
        this.players = players;
    }
}
