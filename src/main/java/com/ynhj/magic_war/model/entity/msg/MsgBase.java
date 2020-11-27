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


    public MsgBase() {
        protoName = getClass().getSimpleName();
    }

    /**
     * @return the String
     * @author: yangniuhaojiang
     * @title: getProtoName
     * @description: update_version: update_date: update_author: update_note:
     */
    public String getProtoName() {
        return protoName;
    }

    @Override
    public boolean equals(Object obj) {
        MsgBase msg = (MsgBase) obj;
        return protoName.equals(msg.getProtoName());
    }
}
