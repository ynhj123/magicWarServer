package com.ynhj.magic_war.Netty;

import com.ynhj.magic_war.model.entity.msg.MsgBase;
import com.ynhj.magic_war.common.net.cocurrent.FutureTaskScheduler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @date: 2020-11-19
 * @author: yangniuhaojiang
 * @title: BussinessHandle
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@ChannelHandler.Sharable
@Component
public class BusinessHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    BusinessServiceImpl businessService;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (null == msg || !(msg instanceof MsgBase)) {
            super.channelRead(ctx, msg);
            return;
        }

        //异步处理IM消息转发的逻辑
       /* FutureTaskScheduler.add(() ->
        {
            businessService.Handler(ctx, (MsgBase) msg);
        });*/
        businessService.Handler(ctx, (MsgBase) msg);
        super.channelRead(ctx, msg);
    }
}
