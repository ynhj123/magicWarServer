package com.ynhj.magic_war.model.entity.msg;

import com.ynhj.magic_war.model.entity.PlayerInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2020-11-30
 * @author: yangniuhaojiang
 * @title: LoadFinshMsg
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class LoadFinishMsg extends MsgBase {
    private String code;
    private String msg;
    private List<PlayerInfo> players = new ArrayList<>();

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

    /**
     * @return the Player>
     * @author: yangniuhaojiang
     * @title: getPlayers
     * @description: update_version: update_date: update_author: update_note:
     */
    public List<PlayerInfo> getPlayers() {
        return players;
    }

    /**
     * @param players the Player> to set
     * @author: yangniuhaojiang
     * @title: setPlayers
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setPlayers(List<PlayerInfo> players) {
        this.players = players;
    }
}
