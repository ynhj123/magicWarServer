package com.ynhj.magic_war.model.entity.msg;

import com.google.protobuf.InvalidProtocolBufferException;
import com.ynhj.magic_war.model.entity.msg.protobuf.ChatRoomMsgOuterClass;
import com.ynhj.magic_war.model.entity.msg.protobuf.HitMsgOuterClass;

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
        ChatRoomMsgOuterClass.ChatRoomMsg.Builder builder = ChatRoomMsgOuterClass.ChatRoomMsg.newBuilder();
        builder.setFromId("x");
        ChatRoomMsgOuterClass.ChatRoomMsg build = builder.setContent("好好学习天天向上").build();
        byte[] bytes = build.toByteArray();
        byte[] bytes1 = new byte[bytes.length+1];
        bytes1[bytes.length] = (byte) 255;
        ChatRoomMsgOuterClass.ChatRoomMsg chatRoomMsg=null;
        try {
            chatRoomMsg = ChatRoomMsgOuterClass.ChatRoomMsg.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        System.out.println(chatRoomMsg.getContent());

    }

}
