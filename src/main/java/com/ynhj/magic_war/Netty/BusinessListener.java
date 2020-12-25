package com.ynhj.magic_war.Netty;

import com.google.protobuf.InvalidProtocolBufferException;
import com.ynhj.magic_war.model.entity.msg.MsgBase;
import io.netty.channel.ChannelHandlerContext;

/**
 * @date: 2020-11-19
 * @author: yangniuhaojiang
 * @title: BussinessService
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */

public interface BusinessListener {
    void handler(ChannelHandlerContext ctx, MsgBase msgBase) throws InvalidProtocolBufferException;
}
