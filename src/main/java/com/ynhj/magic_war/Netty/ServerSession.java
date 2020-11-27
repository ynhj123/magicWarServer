package com.ynhj.magic_war.Netty;

import com.ynhj.magic_war.model.entity.OnlineUser;
import com.ynhj.magic_war.utils.UUIDUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.HashMap;
import java.util.Map;

/**
 * 实现服务器Socket Session会话
 */

public class ServerSession {

    Logger log = LogManager.getLogger(getClass());
    public static final AttributeKey<String> KEY_USER_ID =
            AttributeKey.valueOf("key_user_id");

    public static final AttributeKey<ServerSession> SESSION_KEY =
            AttributeKey.valueOf("SESSION_KEY");


    /**
     * 用户实现服务端会话管理的核心
     */
    //通道
    private Channel channel;
    //用户
    private OnlineUser user;

    //session唯一标示
    private final String sessionId;

    //登录状态
    private boolean isLogin = false;

    /**
     * @return the Channel
     * @author: yangniuhaojiang
     * @title: getChannel
     * @description: update_version: update_date: update_author: update_note:
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * @param channel the Channel to set
     * @author: yangniuhaojiang
     * @title: setChannel
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    /**
     * @return the ${field.typeName}
     * @author: yangniuhaojiang
     * @title: getLogin
     * @description: update_version: update_date: update_author: update_note:
     */
    public boolean isLogin() {
        return isLogin;
    }

    /**
     * @param login the $field.typeName to set
     * @author: yangniuhaojiang
     * @title: setLogin
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setLogin(boolean login) {
        isLogin = login;
    }

    /**
     * @return the Object>
     * @author: yangniuhaojiang
     * @title: getMap
     * @description: update_version: update_date: update_author: update_note:
     */
    public Map<String, Object> getMap() {
        return map;
    }

    /**
     * @param map the Object> to set
     * @author: yangniuhaojiang
     * @title: setMap
     * @description: update_version: update_date: update_author: update_note:
     */
    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    /**
     * session中存储的session 变量属性值
     */
    private Map<String, Object> map = new HashMap<String, Object>();

    public ServerSession(Channel channel) {
        this.channel = channel;
        this.sessionId = UUIDUtils.getUUID();
    }

    //反向导航
    public static ServerSession getSession(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        return channel.attr(ServerSession.SESSION_KEY).get();
    }

    //关闭连接
    public static void closeSession(ChannelHandlerContext ctx) {
        ServerSession session =
                ctx.channel().attr(ServerSession.SESSION_KEY).get();

        if (null != session && session.isValid()) {
            session.close();
            SessionMap.inst().removeSession(session.getSessionId());
        }
    }

    //和channel 通道实现双向绑定
    public ServerSession bind() {
        log.info(" ServerSession 绑定会话 " + channel.remoteAddress());
        channel.attr(ServerSession.SESSION_KEY).set(this);
        SessionMap.inst().addSession(getSessionId(), this);
        isLogin = true;
        return this;
    }

    public ServerSession unbind() {
        isLogin = false;
        SessionMap.inst().removeSession(getSessionId());
        this.close();
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }



    public synchronized void set(String key, Object value) {
        map.put(key, value);
    }


    public synchronized <T> T get(String key) {
        return (T) map.get(key);
    }


    public boolean isValid() {
        return getUser() != null ? true : false;
    }

    //写Protobuf数据帧
    public synchronized void writeAndFlush(Object pkg) {
        channel.writeAndFlush(pkg);
    }

    //关闭连接
    public synchronized void close() {
        ChannelFuture future = channel.close();
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    log.error("CHANNEL_CLOSED error ");
                }
            }
        });
    }


    public OnlineUser getUser() {
        return user;
    }

    public void setUser(OnlineUser user) {
        this.user = user;
        user.setSessionId(sessionId);
    }


}
