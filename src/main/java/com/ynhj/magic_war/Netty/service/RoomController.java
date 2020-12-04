package com.ynhj.magic_war.Netty.service;

import com.ynhj.magic_war.Netty.BusinessServiceImpl;
import com.ynhj.magic_war.Netty.ServerSession;
import com.ynhj.magic_war.common.entity.Result;
import com.ynhj.magic_war.common.exception.SystemErrorType;
import com.ynhj.magic_war.model.entity.msg.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @date: 2020-11-23
 * @author: yangniuhaojiang
 * @title: RoomBusiness
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Component
public class RoomController implements CommandLineRunner {
    Logger log = LogManager.getLogger(getClass());
    @Autowired
    BusinessServiceImpl businessService;
    @Autowired
    RoomService roomService;

    @Override
    public void run(String... args) throws Exception {
        businessService.setListeners(RoomListMsg.class.getSimpleName(), (ctx, msgBase) -> {
            RoomListMsg msg = (RoomListMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            log.error("getRoomList");
            roomService.pageRoomInfos(msg, session);
            if (msg.getCode().equals(SystemErrorType.REPEAT_ERROR.getCode())){
                EnterRoomMsg enterRoomMsg = new EnterRoomMsg();
                enterRoomMsg.setRoomId(msg.getMsg());
                enterRoomMsg.setCode(Result.SUCCESSFUL_CODE);
                enterRoomMsg.setMsg(Result.SUCCESSFUL_MESG);
                session.writeAndFlush(enterRoomMsg);
            }else {
                session.writeAndFlush(msg);

            }
        }).setListeners(CreateRoomMsg.class.getSimpleName(), (ctx, msgBase) -> {
            CreateRoomMsg msg = (CreateRoomMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            roomService.addRoomInfo(msg, session);
            session.writeAndFlush(msg);
        }).setListeners(ChatRoomMsg.class.getSimpleName(), (ctx, msgBase) -> {
            ChatRoomMsg msg = (ChatRoomMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            roomService.Chat(msg, session);

        }).setListeners(GetRoomInfoMsg.class.getSimpleName(), (ctx, msgBase) -> {
            GetRoomInfoMsg msg = (GetRoomInfoMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            roomService.getRoomInfo(msg, session);
            session.writeAndFlush(msg);
        }).setListeners(EnterRoomMsg.class.getSimpleName(), ((ctx, msgBase) -> {
            EnterRoomMsg msg = (EnterRoomMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            roomService.enterRoom(msg, session);
            session.writeAndFlush(msg);
        })).setListeners(LeaveRoomMsg.class.getSimpleName(), (ctx, msgBase) -> {
            //广播离开游戏
            LeaveRoomMsg msg = (LeaveRoomMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            roomService.leaveRoom(msg, session);
        }).setListeners(ReadyStartMsg.class.getSimpleName(), (ctx, msgBase) -> {
            //广播准备
            ReadyStartMsg msg = (ReadyStartMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            roomService.ready(session);

        }).setListeners(UnreadyStartMsg.class.getSimpleName(), (ctx, msgBase) -> {
            //广播取消准备
            UnreadyStartMsg msg = (UnreadyStartMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            roomService.Unready(session);
        }).setListeners(KickRoomMsg.class.getSimpleName(), ((ctx, msgBase) -> {
            KickRoomMsg msg = (KickRoomMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            roomService.KickUser(msg, session);

        }))
        ;

    }


}
