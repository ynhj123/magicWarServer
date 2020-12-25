package com.ynhj.magic_war.Netty.service;

import com.ynhj.magic_war.Netty.ServerSession;
import com.ynhj.magic_war.model.entity.OnlineUser;
import com.ynhj.magic_war.model.entity.msg.protobuf.*;

import java.util.List;

/**
 * @date: 2020-11-30
 * @author: yangniuhaojiang
 * @title: RoomService
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public interface RoomService {
    void pageRoomInfos(RoomListMsgOuterClass.RoomListMsg msg, ServerSession session);

    void addRoomInfo(CreateRoomMsgOuterClass.CreateRoomMsg msg, ServerSession session);

    void Chat(ChatRoomMsgOuterClass.ChatRoomMsg msg, ServerSession session);

    void getRoomInfo(RoomListMsgOuterClass.GetRoomInfoMsg msg, ServerSession session);

    void enterRoom(EnterRoomMsgOuterClass.EnterRoomMsg msg, ServerSession session);

    void leaveRoom(LeaveRoomMsgOuterClass.LeaveRoomMsg msg, ServerSession session);

    void ready(ServerSession session);

    void Unready(ServerSession session);

    void KickUser(KickRoomMsgOuterClass.KickRoomMsg msg, ServerSession session);

    void broadcast(List<String> userIds, com.google.protobuf.GeneratedMessageV3 msgBase);

    void broadcast(String roomId, com.google.protobuf.GeneratedMessageV3 msgBase);

    boolean isOwn(OnlineUser user);

    boolean isAllReady(String roomId);

    int getDegree(OnlineUser user);

    int getCount(String roomId);

    String getRoomIdBy(String uid);

    void initRoomPlayer(String roomId);

    void start(String roomId);

    void end(String roomId);
}
