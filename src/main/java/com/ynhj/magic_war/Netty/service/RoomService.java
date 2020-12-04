package com.ynhj.magic_war.Netty.service;

import com.ynhj.magic_war.Netty.ServerSession;
import com.ynhj.magic_war.model.entity.OnlineUser;
import com.ynhj.magic_war.model.entity.msg.*;

import java.util.List;

/**
 * @date: 2020-11-30
 * @author: yangniuhaojiang
 * @title: RoomService
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public interface RoomService {
    void pageRoomInfos(RoomListMsg msg, ServerSession session);

    void addRoomInfo(CreateRoomMsg msg, ServerSession session);

    void Chat(ChatRoomMsg msg, ServerSession session);

    void getRoomInfo(GetRoomInfoMsg msg, ServerSession session);

    void enterRoom(EnterRoomMsg msg, ServerSession session);

    void leaveRoom(LeaveRoomMsg msg, ServerSession session);

    void ready(ServerSession session);

    void Unready(ServerSession session);

    void KickUser(KickRoomMsg msg, ServerSession session);

    void broadcast(List<String> userIds, MsgBase msgBase);

    void broadcast(String roomId, MsgBase msgBase);

    boolean isOwn(OnlineUser user);

    boolean isAllReady(String roomId);

    int getDegree(OnlineUser user);

    int getCount(String roomId);

    String getRoomIdBy(String uid);
}
