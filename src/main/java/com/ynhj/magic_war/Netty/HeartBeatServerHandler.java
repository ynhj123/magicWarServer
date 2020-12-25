package com.ynhj.magic_war.Netty;


import com.ynhj.magic_war.model.entity.msg.MsgBase;
import com.ynhj.magic_war.model.entity.msg.MsgPing;
import com.ynhj.magic_war.model.entity.msg.MsgPong;
import com.ynhj.magic_war.common.net.cocurrent.FutureTaskScheduler;
import com.ynhj.magic_war.model.entity.msg.protobuf.MsgPongOuterClass;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * create by 尼恩 @ 疯狂创客圈
 **/

public class HeartBeatServerHandler extends IdleStateHandler {

    private static final int READ_IDLE_GAP = 120;

    public HeartBeatServerHandler() {
        super(READ_IDLE_GAP, 0, 0, TimeUnit.SECONDS);

    }

    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        //判断消息实例
        if (null == msg || !(msg instanceof MsgBase)) {
            super.channelRead(ctx, msg);
            return;
        }

        MsgBase pkg = (MsgBase)msg;
        //判断消息类型
        if (pkg.getProtoName().equals(MsgPing.class.getSimpleName()) ){
            FutureTaskScheduler.add(() -> {
                if (ctx.channel().isActive()) {
                    ctx.writeAndFlush(MsgPongOuterClass.MsgPong.getDefaultInstance());
                }
            });
        }

        super.channelRead(ctx, msg);

    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(READ_IDLE_GAP + "秒内未读到数据，关闭连接");
        ServerSession.closeSession(ctx);
    }
}