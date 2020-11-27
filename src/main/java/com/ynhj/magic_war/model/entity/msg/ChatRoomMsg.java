package com.ynhj.magic_war.model.entity.msg;

/**
 * @date: 2020-11-23
 * @author: yangniuhaojiang
 * @title: ChatRoomMsg
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class ChatRoomMsg extends MsgBase {
    String fromId;
    String content;

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getFromId
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getFromId() {
        return fromId;
    }

    /**
     * @param fromId the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setFromId
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getContent
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the String to set
     * @author: yangniuhaojiang
     * @title: setContent
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setContent(String content) {
        this.content = content;
    }


}
