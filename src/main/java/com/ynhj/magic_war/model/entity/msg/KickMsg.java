package com.ynhj.magic_war.model.entity.msg;

/**
 * @date: 2020-11-19
 * @author: yangniuhaojiang
 * @title: KickMsg
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class KickMsg extends MsgBase {
    private int reason;

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getReason
     * @description: update_version: update_date: update_author: update_note:
     */
    public int getReason() {
        return reason;
    }

    /**
     * @param reason the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setReason
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setReason(int reason) {
        this.reason = reason;
    }
}
