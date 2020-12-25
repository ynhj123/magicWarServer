package com.ynhj.magic_war.Netty.service;

import com.ynhj.magic_war.Netty.BusinessServiceImpl;
import com.ynhj.magic_war.Netty.ServerSession;
import com.ynhj.magic_war.model.entity.msg.protobuf.*;
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
        businessService.setListeners(RoomListMsgOuterClass.RoomListMsg.class.getSimpleName(), (ctx, msgBase) -> {
            RoomListMsgOuterClass.RoomListMsg msg = RoomListMsgOuterClass.RoomListMsg.parseFrom(msgBase.getContent());
            ServerSession session = ServerSession.getSession(ctx);
            roomService.pageRoomInfos(msg, session);

        }).setListeners(CreateRoomMsgOuterClass.CreateRoomMsg.class.getSimpleName(), (ctx, msgBase) -> {
            CreateRoomMsgOuterClass.CreateRoomMsg msg = CreateRoomMsgOuterClass.CreateRoomMsg.parseFrom(msgBase.getContent());
            ServerSession session = ServerSession.getSession(ctx);
            roomService.addRoomInfo(msg, session);

        }).setListeners(ChatRoomMsgOuterClass.ChatRoomMsg.class.getSimpleName(), (ctx, msgBase) -> {
            ChatRoomMsgOuterClass.ChatRoomMsg msg = ChatRoomMsgOuterClass.ChatRoomMsg.parseFrom(msgBase.getContent());
            ServerSession session = ServerSession.getSession(ctx);
            roomService.Chat(msg, session);
        }).setListeners(RoomListMsgOuterClass.GetRoomInfoMsg.class.getSimpleName(), (ctx, msgBase) -> {
            RoomListMsgOuterClass.GetRoomInfoMsg msg = RoomListMsgOuterClass.GetRoomInfoMsg.parseFrom(msgBase.getContent());
            ServerSession session = ServerSession.getSession(ctx);
            roomService.getRoomInfo(msg, session);
        }).setListeners(EnterRoomMsgOuterClass.EnterRoomMsg.class.getSimpleName(), ((ctx, msgBase) -> {
            EnterRoomMsgOuterClass.EnterRoomMsg msg = EnterRoomMsgOuterClass.EnterRoomMsg.parseFrom(msgBase.getContent()) ;
            ServerSession session = ServerSession.getSession(ctx);
            roomService.enterRoom(msg, session);
        })).setListeners(LeaveRoomMsgOuterClass.LeaveRoomMsg.class.getSimpleName(), (ctx, msgBase) -> {
            //广播离开游戏
            LeaveRoomMsgOuterClass.LeaveRoomMsg msg = LeaveRoomMsgOuterClass.LeaveRoomMsg.parseFrom(msgBase.getContent()) ;
            ServerSession session = ServerSession.getSession(ctx);
            roomService.leaveRoom(msg, session);
        }).setListeners(ReadyStartMsgOuterClass.ReadyStartMsg.class.getSimpleName(), (ctx, msgBase) -> {
            //广播准备
            ReadyStartMsgOuterClass.ReadyStartMsg msg = ReadyStartMsgOuterClass.ReadyStartMsg.parseFrom(msgBase.getContent());
            ServerSession session = ServerSession.getSession(ctx);
            roomService.ready(session);

        }).setListeners(UnreadyStartMsgOuterClass.UnreadyStartMsg.class.getSimpleName(), (ctx, msgBase) -> {
            //广播取消准备
            UnreadyStartMsgOuterClass.UnreadyStartMsg msg = UnreadyStartMsgOuterClass.UnreadyStartMsg.parseFrom(msgBase.getContent()) ;
            ServerSession session = ServerSession.getSession(ctx);
            roomService.Unready(session);
        }).setListeners(KickRoomMsgOuterClass.KickRoomMsg.class.getSimpleName(), ((ctx, msgBase) -> {
            KickRoomMsgOuterClass.KickRoomMsg msg = KickRoomMsgOuterClass.KickRoomMsg.parseFrom(msgBase.getContent());
            ServerSession session = ServerSession.getSession(ctx);
            roomService.KickUser(msg, session);

        }))
        ;

    }


}
