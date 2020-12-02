package com.ynhj.magic_war.Netty;

import com.ynhj.magic_war.common.exception.InvalidFrameException;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * create by 尼恩 @ 疯狂创客圈
 **/

@ChannelHandler.Sharable
@Service
public class ServerExceptionHandler extends ChannelInboundHandlerAdapter {
    private final Logger log = LogManager.getLogger(getClass());
     @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // ..

        if (cause instanceof InvalidFrameException) {
            log.error(cause.getMessage());
            ServerSession.closeSession(ctx);
        } else {

            //捕捉异常信息
            cause.printStackTrace();
            log.error(cause.getMessage());
            ctx.close();
        }
    }

    /**
     * 通道 Read 读取 Complete 完成
     * 做刷新操作 ctx.flush()
     */
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx)
            throws Exception {
        ServerSession.closeSession(ctx);
    }


}