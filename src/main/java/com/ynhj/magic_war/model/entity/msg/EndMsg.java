package com.ynhj.magic_war.model.entity.msg;

import com.ynhj.magic_war.model.entity.PlayerInfo;

import java.util.List;

/**
 * @date: 2020-11-23
 * @author: yangniuhaojiang
 * @title: EndMsg
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class EndMsg extends MsgBase {

    private List<PlayerInfo> playerInfos;

    /**
     * @return the PlayerInfo>
     * @author: yangniuhaojiang
     * @title: getPlayerInfos
     * @description: update_version: update_date: update_author: update_note:
     */
    public List<PlayerInfo> getPlayerInfos() {
        return playerInfos;
    }

    /**
     * @param playerInfos the PlayerInfo> to set
     * @author: yangniuhaojiang
     * @title: setPlayerInfos
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setPlayerInfos(List<PlayerInfo> playerInfos) {
        this.playerInfos = playerInfos;
    }
}
