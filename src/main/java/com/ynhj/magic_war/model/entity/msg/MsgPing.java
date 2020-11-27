package com.ynhj.magic_war.model.entity.msg;

/**
 * @date: 2020-11-19
 * @author: yangniuhaojiang
 * @title: MsgPing
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class MsgPing extends MsgBase {

    public static void main(String[] args) {
        MsgBase msg = new MsgPing();
        MsgBase msgBase = new MsgBase();
        System.out.println(msg instanceof MsgPing);
        System.out.println(msgBase instanceof MsgPing);
    }

}
