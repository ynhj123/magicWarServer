package com.ynhj.magic_war.Netty;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @date: 2020-10-22
 * @author: yangniuhaojiang
 * @title: MyDecoderName
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class MyDecoderName extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("receive");
        String s = new String((byte[]) msg);
        System.out.println(s);
        Pong pong = new Pong();

//        ctx.writeAndFlush("hello fuck");
     /*   ByteBuf byteBuf = (ByteBuf) msg;
        // 判断报文长度
        int bufLen = byteBuf.readableBytes();


        System.out.println("msg:" + bufLen);*/

        //业务的报文处理
        ctx.writeAndFlush(Unpooled.wrappedBuffer(pong.decode()));

    }

    static class Pong{
        private String protoName = "MsgPong";

        /**
         * @return the String
         * @author: yangniuhaojiang
         * @title: getProtoName
         * @description: update_version: update_date: update_author: update_note:
         */
        public String getProtoName() {
            return protoName;
        }

        /**
         * @param protoName the String to set
         * @author: yangniuhaojiang
         * @title: setProtoName
         * @description: update_version: update_date: update_author: update_note:
         */
        public void setProtoName(String protoName) {
            this.protoName = protoName;
        }

        byte[] decode() {
            String body = JSON.toJSONString(this);
            byte[] bodyBytes = body.getBytes();
            int bodylen = bodyBytes.length;

            //名字bytes和长度
            byte[] nameBytes1 = this.protoName.getBytes();
            int len = nameBytes1.length;
            //申请bytes数值
            byte[] nameBytes = new byte[2 + len];
            //组装2字节的长度信息
            nameBytes[0] = (byte) (len % 256);
            nameBytes[1] = (byte) (len / 256);
            System.arraycopy(nameBytes1, 0, nameBytes, 2, nameBytes1.length);
            //组装名字bytes
            byte[] bytes = new byte[2 + bodylen + nameBytes.length];
            bytes[0] = (byte) (bytes.length % 256);
            bytes[1] = (byte) (bytes.length / 256);
            System.arraycopy(nameBytes, 0, bytes, 2, nameBytes.length);

            //组装消息体
            System.arraycopy(bodyBytes, 0, bytes, 2 + nameBytes.length, bodyBytes.length);
            return bytes;
        }

    }

}
