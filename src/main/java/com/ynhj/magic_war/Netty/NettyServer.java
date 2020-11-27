package com.ynhj.magic_war.Netty;

import com.ynhj.magic_war.common.net.MsgDecoder;
import com.ynhj.magic_war.common.net.MsgEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @date: 2020-10-20
 * @author: yangniuhaojiang
 * @title: NettyServer
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Component
public class NettyServer {
    //bussiness
    @Autowired
    ServerExceptionHandler serverExceptionHandler;
    @Autowired
    BusinessHandler businessHandler;

    // 通过nio方式来接收连接和处理连接
    private EventLoopGroup bg =
            new NioEventLoopGroup();
    private EventLoopGroup wg =
            new NioEventLoopGroup();

    // 启动引导器
    private ServerBootstrap b =
            new ServerBootstrap();


    public void run() {

        //1 设置reactor 线程
        b.group(bg, wg);
        //2 设置nio类型的channel
        b.channel(NioServerSocketChannel.class);
        //3 设置监听端口
        String ip = "192.168.1.105";
        b.localAddress(new InetSocketAddress(ip, 8888));
        //4 设置通道选项
        b.option(ChannelOption.SO_KEEPALIVE, true);
        b.option(ChannelOption.ALLOCATOR,
                PooledByteBufAllocator.DEFAULT);

        //5 装配流水线
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            //有连接到达时会创建一个channel
            protected void initChannel(SocketChannel ch) throws Exception {
                // 管理pipeline中的Handler

//                ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(15362,0,2,0,2));
//                ch.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
//                ch.pipeline().addLast(new JsonMsgDecoder());
               /* ch.pipeline().addLast(new MyDecode());

                ch.pipeline().addLast(new MyDecoderName());*/
               ch.pipeline().addLast(new MsgDecoder());
               ch.pipeline().addLast(new MsgEncoder());
               ch.pipeline().addLast(new HeartBeatServerHandler());
               ch.pipeline().addLast(businessHandler);
               ch.pipeline().addLast(serverExceptionHandler);
                //ch.pipeline().addLast(new MyEncode());

                /*ch.pipeline().addLast("deCoder",new ProtobufDecoder());
                ch.pipeline().addLast("enCoder",new ProtobufEncoder());
                ch.pipeline().addLast("heartBeat",new HeartBeatServerHandler());
                // 在流水线中添加handler来处理登录,登录后删除
                ch.pipeline().addLast("loginRequest",loginRequestHandler);
                ch.pipeline().addLast("remoteNotificationHandler",remoteNotificationHandler);
                ch.pipeline().addLast("chatRedirect",chatRedirectHandler);
                ch.pipeline().addLast("serverException",serverExceptionHandler);*/
            }
        });
        // 6 开始绑定server
        // 通过调用sync同步方法阻塞直到绑定成功

        ChannelFuture channelFuture = null;
        boolean isStart = false;
        while (!isStart) {
            try {

                channelFuture = b.bind().sync();
                System.out.println("server启动, 端口为： " +
                        channelFuture.channel().localAddress());
                isStart = true;
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        try {
            // 7 监听通道关闭事件
            // 应用程序会一直等待，直到channel关闭
            ChannelFuture closeFuture =
                    channelFuture.channel().closeFuture();
            closeFuture.sync();
        } catch (
                Exception e) {
            e.printStackTrace();
        } finally {
            // 8 优雅关闭EventLoopGroup，
            // 释放掉所有资源包括创建的线程
            wg.shutdownGracefully();
            bg.shutdownGracefully();
        }

    }



}
