package com.ynhj.magic_war.common.net;


import com.alibaba.fastjson.JSON;
import com.ynhj.magic_war.model.entity.msg.MsgBase;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * create by 尼恩 @ 疯狂创客圈
 * <p>
 * 编码器
 */


public class MsgEncoder extends MessageToByteEncoder<MsgBase> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MsgBase msg, ByteBuf out) throws Exception {
        byte[] headBytes = msg.getProtoName().getBytes();
        byte[] bodyBytes = JSON.toJSONString(msg).getBytes();
        int msgLength = headBytes.length + bodyBytes.length + 2;
        out.writeByte(msgLength % 256);
        out.writeByte(msgLength / 256);
        int headLength = headBytes.length;
        out.writeByte(headLength % 256);
        out.writeByte(headLength / 256);
        //out.writeShort();
        out.writeBytes(headBytes);
        out.writeBytes(bodyBytes);
    }
}
