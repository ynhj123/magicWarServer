package com.ynhj.magic_war.common.net;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * create by 尼恩 @ 疯狂创客圈
 * <p>
 * 编码器
 */

public class MsgEncoder extends MessageToByteEncoder<com.google.protobuf.GeneratedMessageV3> {



    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, com.google.protobuf.GeneratedMessageV3 msg, ByteBuf out) throws Exception {
        byte[] bodyBytes = msg.toByteArray();
        int msgLength = bodyBytes.length;
        out.writeByte(msgLength % 256);
        out.writeByte(msgLength / 256);
        int headLength = ProtobufMapper.getInt(msg.getClass().getSimpleName());
        out.writeByte(headLength % 256);
        out.writeByte(headLength / 256);
        //out.writeShort();
        out.writeBytes(bodyBytes);
    }
}
