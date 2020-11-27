package com.ynhj.magic_war.Netty.service;

import com.ynhj.magic_war.Netty.BusinessServiceImpl;
import com.ynhj.magic_war.Netty.ServerSession;
import com.ynhj.magic_war.Netty.SessionMap;
import com.ynhj.magic_war.common.entity.Result;
import com.ynhj.magic_war.common.exception.SystemErrorType;
import com.ynhj.magic_war.model.entity.OnlineUser;
import com.ynhj.magic_war.model.entity.msg.EnterMsg;
import com.ynhj.magic_war.model.entity.msg.MsgPing;
import com.ynhj.magic_war.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @date: 2020-11-19
 * @author: yangniuhaojiang
 * @title: LoginBussiness
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Component
public class EnterBusiness implements CommandLineRunner {
    @Autowired
    BusinessServiceImpl businessService;

    @Override
    public void run(String... args) throws Exception {
        businessService.setListeners(EnterMsg.class.getSimpleName(), (ctx, msgBase) -> {
            EnterMsg msg = (EnterMsg) msgBase;
            ServerSession session = new ServerSession(ctx.channel());
            if (StringUtils.isEmpty(msg.getNickname()) || StringUtils.isEmpty(msg.getToken())) {
                msg.setCode(SystemErrorType.BAD_REQUEST_ERROR.getCode());
                msg.setMsg(SystemErrorType.BAD_REQUEST_ERROR.getMesg());
            } else {
                Claims claims = JwtUtil.checkJWT(msg.getToken().replace(JwtUtil.TOKEN_PREFIX, ""));
                String uid = claims.get("uid").toString();
                String username = claims.get("username").toString();
                if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(username)) {
                    msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
                    msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());
                } else {
                    OnlineUser onlineUser = new OnlineUser();
                    onlineUser.setId(uid);
                    onlineUser.setNickname(msg.getNickname());
                    onlineUser.setToken(msg.getToken());
                    onlineUser.setUsername(username);
                    boolean hasLogin = SessionMap.inst().hasLogin(onlineUser);
                    if (hasLogin) {
                        msg.setCode(SystemErrorType.REPEAT_ERROR.getCode());
                        msg.setMsg(SystemErrorType.REPEAT_ERROR.getMesg());
                    } else {
                        session.setUser(onlineUser);
                        session.bind();
                        msg.setCode(Result.SUCCESSFUL_CODE);
                        msg.setMsg(Result.SUCCESSFUL_MESG);
                    }
                }
            }
            session.writeAndFlush(msg);
        }).setListeners(MsgPing.class.getSimpleName(), (ctx, msgBase) -> {

        });

    }
}
