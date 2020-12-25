package com.ynhj.magic_war.model.entity.msg;

/**
 * @date: 2020-11-19
 * @author: yangniuhaojiang
 * @title: MegBase
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class MsgBase {
    private String protoName;
    private byte[] content;

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getProtoName
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getProtoName() {
        return protoName;
    }

    /**
     * @param protoName the String to set
     * @author: yangniuhaojiang
     * @title: setProtoName
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setProtoName(String protoName) {
        this.protoName = protoName;
    }

    /**
     * @return the byte
     * @author: yangniuhaojiang
     * @title: getContent
     * @description: update_version: update_date: update_author: update_note:
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * @param content the byte to set
     * @author: yangniuhaojiang
     * @title: setContent
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        MsgBase msg = (MsgBase) obj;
        return protoName.equals(msg.getProtoName());
    }
}
