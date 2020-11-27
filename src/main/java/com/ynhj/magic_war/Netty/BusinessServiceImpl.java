package com.ynhj.magic_war.Netty;

import com.ynhj.magic_war.model.entity.msg.MsgBase;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2020-11-19
 * @author: yangniuhaojiang
 * @title: BusinessImpl
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Service
public class BusinessServiceImpl {
    private Map<String, BusinessListener> listeners = new HashMap<>();

    public BusinessServiceImpl setListeners(String name, BusinessListener listener) {
        listeners.put(name, listener);
        return this;
    }

    public void Handler(ChannelHandlerContext ctx, MsgBase msgBase) {
        listeners.get(msgBase.getProtoName()).handler(ctx, msgBase);
    }
}
