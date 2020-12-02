package com.ynhj.magic_war.common.net;

import com.alibaba.fastjson.JSON;
import com.ynhj.magic_war.model.entity.msg.MsgBase;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
/**
 * create by 尼恩 @ 疯狂创客圈
 **/


/**
 * create by 尼恩 @ 疯狂创客圈
 * <p>
 * 解码器
 */


public class MsgDecoder extends ByteToMessageDecoder {

    private final static String MSG_URL = "com.ynhj.magic_war.model.entity.msg.";
    private final Logger log = LogManager.getLogger(getClass());

    @Override
    protected void decode(ChannelHandlerContext ctx,
                          ByteBuf in,
                          List<Object> out) throws Exception {
        // 判断包头长度
        //int LENGTH = in.readableBytes();
        if (in.readableBytes() < 4) {// 不够包头
            return;
        }

        //记录当前ByteBuf的读指针位置，以便下面取报文长度字节
        //pos是一个完整报文的开始位置，报文整体会在ByteBuf中移动，类似内存管理，所以基于字节的判断报文长度等等，都是基于pos，否则可以在byteBuf.readBytes（）之后加，byteBuf.discardReadBytes();整理ByteBuf，使pos回到0开始位置
        /*byte[] bytes = in.array();
        System.out.println(pos);
        int msgLen = ((byteBuf.getByte(pos +1))<<8) | (byteBuf.getByte(pos));
        System.out.println(byteBuf.getByte(pos +1));
        System.out.println(byteBuf.getByte(pos));
        System.out.println(msgLen);
        //收到的报文长度不足一个完整的报文，继续接收
        if(byteBuf.readableBytes()<msgLen){
            return;
        }

        byte[] msgContent = new byte[msgLen+2];
        byteBuf.readBytes(msgContent);
        //提出完整报文(readBytes读到msg中)，放到list给下一个Handler处理
        if(msgLen>0){
            list.add(msgContent);
        }*/
        // 标记一下当前的readIndex的位置
        in.markReaderIndex();

        byte a = in.readByte();
        byte b = in.readByte();
        //读取消息长度
        int length = (0xFF & b) << 8 | (0xFF & a);
        byte c = in.readByte();
        byte d = in.readByte();
        //读取版本
        int headLength = (0xFF & d) << 8 | (0xFF & c);
        // 长度如果小于0
        if (length < 0) {// 非法数据，关闭连接
            ctx.close();
        }

        if (length > in.readableBytes() + 2) {// 读到的消息体长度如果小于传送过来的消息长度
            // 重置读取位置 //防止半包
            in.resetReaderIndex();
            return;
        }
        int bodyLength = length - headLength - 2;

        byte[] headBytes = new byte[headLength];
        if (bodyLength < 0) {
            log.error(bodyLength);

        }
        byte[] bodyBytes = new byte[bodyLength];
        in.readBytes(headBytes, 0, headLength);
        in.readBytes(bodyBytes, 0, bodyLength);
       /* if (in.hasArray()) {
            //堆缓冲
            ByteBuf slice = in.slice();
            array = slice.array();
        } else {
            //直接缓冲
            array = new byte[length];
            in.readBytes(array, 0, length);
        }*/

//        if(in.refCnt()>0)
//        {
////            log.debug("释放临时缓冲");
//            in.release();
//        }
        String className = new String(headBytes);
        MsgBase msgBase = JSON.parseObject(bodyBytes, Class.forName(MSG_URL + className));


        if (msgBase != null) {
            // 获取业务消息
            out.add(msgBase);
        }

    }
}
