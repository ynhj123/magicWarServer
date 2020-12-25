package com.ynhj.magic_war.Netty.service;

import com.ynhj.magic_war.Netty.ServerSession;
import com.ynhj.magic_war.Netty.SessionMap;
import com.ynhj.magic_war.common.entity.Result;
import com.ynhj.magic_war.common.exception.SystemErrorType;
import com.ynhj.magic_war.model.Role;
import com.ynhj.magic_war.model.entity.OnlineUser;
import com.ynhj.magic_war.model.entity.PlayerRoom;
import com.ynhj.magic_war.model.entity.RoomInfo;
import com.ynhj.magic_war.model.entity.msg.protobuf.*;
import com.ynhj.magic_war.service.RoleService;
import com.ynhj.magic_war.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @date: 2020-11-23
 * @author: yangniuhaojiang
 * @title: RoomManger
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Service
public class RoomServiceImpl implements RoomService {
    Logger log = LogManager.getLogger(getClass());
    static Map<String, RoomInfo> roomMap = new HashMap<>(1024);
    @Autowired
    RoleService roleService;

    public void pageRoomInfos(RoomListMsgOuterClass.RoomListMsg base, ServerSession session) {
        OnlineUser user = session.getUser();
        RoomListMsgOuterClass.RoomListMsg.Builder msg = base.toBuilder();
        if (null == session || !session.isLogin()) {
            msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
            msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());
        } else if (msg.getCurPage() < 0 || (((msg.getCurPage() - 1) != 0) && ((msg.getCurPage() - 1) * msg.getPageSize() >= roomMap.size()))) {
            msg.setCode(SystemErrorType.BAD_REQUEST_ERROR.getCode());
            msg.setMsg("请求越界");
        } else if (!StringUtils.isEmpty(user.getRoomId())) {
            RoomInfo roomInfo = roomMap.get(user.getRoomId());
            if (roomInfo == null) {
                user.setRoomId("");
            } else {
                Optional<PlayerRoom> first = roomInfo.getPlayers().stream().filter(playerRoom -> playerRoom.getUid().equals(user.getId())).findFirst();
                if (!first.isPresent()) {
                    user.setRoomId("");
                } else {
                    msg.setCode(SystemErrorType.REPEAT_ERROR.getCode());
                    msg.setMsg(roomInfo.getId());
                }
            }
        } else {
            page(msg);
        }
        if (msg.getCode().equals(SystemErrorType.REPEAT_ERROR.getCode())){
            EnterRoomMsgOuterClass.EnterRoomMsg.Builder enterRoomMsg = EnterRoomMsgOuterClass.EnterRoomMsg.newBuilder();
            enterRoomMsg.setRoomId(msg.getMsg());
            enterRoomMsg.setCode(Result.SUCCESSFUL_CODE);
            enterRoomMsg.setMsg(Result.SUCCESSFUL_MESG);
            session.writeAndFlush(enterRoomMsg.build());
        }else {
            session.writeAndFlush(msg.build());

        }

    }

    private void page(RoomListMsgOuterClass.RoomListMsg.Builder msg) {
        List<RoomInfo> rooms = roomMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .sorted()
                .skip(msg.getPageSize() * (msg.getCurPage() - 1)).limit(msg.getPageSize())
                .collect(Collectors.toList());
        rooms.forEach(roomInfo -> msg.addRooms(roomInfo.toMsg()));
        msg.setSize(roomMap.size());
        msg.setCode(Result.SUCCESSFUL_CODE);
        msg.setMsg(Result.SUCCESSFUL_MESG);
    }

    public void addRoomInfo(CreateRoomMsgOuterClass.CreateRoomMsg base, ServerSession session) {
        CreateRoomMsgOuterClass.CreateRoomMsg.Builder msg = base.toBuilder();
        if (null == session || !session.isLogin()) {
            msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
            msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());
        } else {
            OnlineUser user = session.getUser();
            Role role = roleService.get(user.getId());
            RoomInfo roomInfo = new RoomInfo();
            roomInfo.setMaxCount(8);
            List<PlayerRoom> players = roomInfo.getPlayers();
            roomInfo.setStatus(0);
            roomInfo.setId(UUIDUtils.getRoomId());
            user.setRoomId(roomInfo.getId());
            PlayerRoom playerRoom = new PlayerRoom();
            playerRoom.setDegree(players.size());
            playerRoom.setNickname(user.getNickname());
            playerRoom.setRoomStatus(0);
            playerRoom.setScore(role.getScore());
            playerRoom.setUsername(user.getUsername());
            playerRoom.setUid(user.getId());
            players.add(playerRoom);
            roomInfo.setCount(players.size());
            roomMap.put(roomInfo.getId(), roomInfo);
            msg.setRoomId(roomInfo.getId());
            msg.setCode(Result.SUCCESSFUL_CODE);
            msg.setMsg(Result.SUCCESSFUL_MESG);
        }
        session.writeAndFlush(msg.build());
    }

    public void Chat(ChatRoomMsgOuterClass.ChatRoomMsg base, ServerSession session) {
        ChatRoomMsgOuterClass.ChatRoomMsg.Builder msg = base.toBuilder();
        OnlineUser user = session.getUser();
        String roomId = user.getRoomId();
        if (null == session || !session.isLogin()) {
            /*msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
            msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());*/
            log.error("用户未登录");
        } else if (StringUtils.isEmpty(roomId)) {
           /* msg.setCode(SystemErrorType.SYSTEM_ERROR.getCode());
            msg.setMsg("用户不在房间内！");*/
            log.error("用户不在房间内");
        } else if (StringUtils.isEmpty(msg.getContent())) {
            log.error("消息内容不能为空");
        } else {
            RoomInfo roomInfo = roomMap.get(roomId);
            long count = roomInfo.getPlayers().stream().filter(playerRoom -> playerRoom.getUid().equals(user.getId())).count();
            if (0L == count) {
               /* msg.setCode(SystemErrorType.SYSTEM_ERROR.getCode());
                msg.setMsg("用户不在房间内！");*/
                log.error("用户不在房间内");
            } else {
                List<String> userIds = roomInfo.getPlayers().stream().map(playerRoom -> playerRoom.getUid()).collect(Collectors.toList());
                msg.setFromId(user.getId());
                broadcast(userIds, msg.build());
            }
        }
    }

    public void getRoomInfo(RoomListMsgOuterClass.GetRoomInfoMsg base, ServerSession session) {
        RoomListMsgOuterClass.GetRoomInfoMsg.Builder msg = base.toBuilder();
        OnlineUser user = session.getUser();
        String roomId = user.getRoomId();
        if (null == session || !session.isLogin()) {
            msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
            msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());

        } else if (StringUtils.isEmpty(roomId)) {
            msg.setCode(SystemErrorType.SYSTEM_ERROR.getCode());
            msg.setMsg("用户不在房间内！");
        } else {
            msg.setCode(Result.SUCCESSFUL_CODE);
            msg.setMsg(Result.SUCCESSFUL_MESG);
            roomMap.get(roomId).getPlayers().forEach(playerRoom -> msg.addPlayers(playerRoom.toMsg()));
        }
        session.writeAndFlush(msg.build());

    }

    public void enterRoom(EnterRoomMsgOuterClass.EnterRoomMsg base, ServerSession session) {
        EnterRoomMsgOuterClass.EnterRoomMsg.Builder msg = base.toBuilder();

        if (null == session || !session.isLogin()) {
            msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
            msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());
        } else {
            String roomId = msg.getRoomId();
            if (!roomMap.containsKey(roomId)) {
                msg.setCode(SystemErrorType.NOTFOUND_ERROR.getCode());
                msg.setMsg(SystemErrorType.NOTFOUND_ERROR.getMesg());
                return;
            }
            RoomInfo roomInfo = roomMap.get(roomId);
            List<PlayerRoom> players = roomInfo.getPlayers();
            if (roomInfo == null) {
                msg.setCode(SystemErrorType.NOTFOUND_ERROR.getCode());
                msg.setMsg(SystemErrorType.NOTFOUND_ERROR.getMesg());
            } else if (players.size() >= roomInfo.getMaxCount()) {
                msg.setCode(SystemErrorType.SYSTEM_ERROR.getCode());
                msg.setMsg("房间人数已满！");
            } else {
                OnlineUser user = session.getUser();
                Optional<PlayerRoom> first = players.stream().filter(playerRoom -> playerRoom.getUid().equals(user.getId())).findFirst();
                PlayerRoom playerRoom;
                if (first.isPresent()) {
                    playerRoom = first.get();
                } else {
                    playerRoom = new PlayerRoom();
                    playerRoom.setDegree(players.size());
                    playerRoom.setNickname(user.getNickname());
                    playerRoom.setRoomStatus(0);
                    playerRoom.setUsername(user.getUsername());
                    playerRoom.setUid(user.getId());
                    playerRoom.setScore(roleService.get(user.getId()).getScore());
                    players.add(playerRoom);
                    user.setRoomId(roomId);
                    roomInfo.setCount(players.size());

                }

                msg.setCode(Result.SUCCESSFUL_CODE);
                msg.setMsg(Result.SUCCESSFUL_MESG);
                List<String> userIds = players.stream()
                        .map(playerRoom1 -> playerRoom1.getUid())
                        .filter(playerRoom1 -> !playerRoom1.equals(user.getId()))
                        .collect(Collectors.toList());
                RoomListMsgOuterClass.GetRoomInfoMsg.Builder getRoomInfoMsg = RoomListMsgOuterClass.GetRoomInfoMsg.newBuilder();
                getRoomInfoMsg.setCode(Result.SUCCESSFUL_CODE);
                getRoomInfoMsg.setMsg(Result.SUCCESSFUL_MESG);
                players.forEach(playerRoom1 -> getRoomInfoMsg.addPlayers(playerRoom1.toMsg()));
                broadcast(userIds, getRoomInfoMsg.build());
            }
        }
        session.writeAndFlush(msg.build());

    }

    public void leaveRoom(LeaveRoomMsgOuterClass.LeaveRoomMsg base, ServerSession session) {
        LeaveRoomMsgOuterClass.LeaveRoomMsg.Builder msg = base.toBuilder();
        if (null == session || !session.isLogin()) {
            msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
            msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());
        } else {
            OnlineUser user = session.getUser();
            if (StringUtils.isEmpty(user.getRoomId())) {
                msg.setCode(SystemErrorType.SYSTEM_ERROR.getCode());
                msg.setMsg("已经离开房间");
            } else {
                String roomId = user.getRoomId();
                RoomInfo roomInfo = roomMap.get(roomId);
                List<PlayerRoom> players = roomInfo.getPlayers();
                Optional<PlayerRoom> playerRoomOptional = players.stream().filter(playerRoom -> playerRoom.getUid().equals(user.getId())).findFirst();
                if (!playerRoomOptional.isPresent()) {
                    msg.setCode(SystemErrorType.SYSTEM_ERROR.getCode());
                    msg.setMsg("已经离开房间");
                } else {
                    PlayerRoom playerRoom = playerRoomOptional.get();
                    players.remove(playerRoom);
                    user.setRoomId("");
                    if (players.size() == 0) {
                        //删除房间
                        if (roomMap.containsKey(roomId)) {
                            roomMap.remove(roomId);
                            UUIDUtils.removeRoomId(roomId);
                        }
                    } else {
                        for (int i = 0; i < players.size(); i++) {
                            players.get(i).setDegree(i);
                        }
                        //广播离开
                        roomInfo.setCount(players.size());
                        RoomListMsgOuterClass.GetRoomInfoMsg.Builder getRoomInfoMsg = RoomListMsgOuterClass.GetRoomInfoMsg.newBuilder();
                        getRoomInfoMsg.setCode(Result.SUCCESSFUL_CODE);
                        getRoomInfoMsg.setMsg(Result.SUCCESSFUL_MESG);
                        players.forEach(playerRoom1 -> getRoomInfoMsg.addPlayers(playerRoom1.toMsg()));
                        List<String> userIds = players.stream()
                                .map(playerRoom1 -> playerRoom1.getUid())
                                .filter(playerRoom2 -> !playerRoom2.equals(user.getId()))
                                .collect(Collectors.toList());
                        broadcast(userIds, getRoomInfoMsg.build());
                    }

                    msg.setCode(Result.SUCCESSFUL_CODE);
                    msg.setMsg(Result.SUCCESSFUL_MESG);
                }
            }
            session.writeAndFlush(msg.build());
        }
    }

    public void KickUser(KickRoomMsgOuterClass.KickRoomMsg base, ServerSession session) {
        OnlineUser user = session.getUser();
        KickRoomMsgOuterClass.KickRoomMsg.Builder msg = base.toBuilder();
        String roomId = user.getRoomId();

        if (null == session || !session.isLogin()) {
           /* msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
            msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());*/
            log.error("未登录");
        } else if (StringUtils.isEmpty(roomId)) {
            log.error("用户不在房间内");
            /*msg.setCode(SystemErrorType.SYSTEM_ERROR.getCode());
            msg.setMsg("用户不在房间内！");*/
        } else {
            RoomInfo roomInfo = roomMap.get(roomId);
            List<PlayerRoom> players = roomInfo.getPlayers();
            if (!players.get(0).getUid().equals(user.getId())) {
                log.error("用户不是房主！");
            } else {
                String uid = msg.getUid();
                players.stream()
                        .filter(playerRoom -> playerRoom.getUid().equals(uid)).findFirst()
                        .ifPresent(playerRoom -> {
                            players.remove(playerRoom);
                            //广播
                            RoomListMsgOuterClass.GetRoomInfoMsg.Builder getRoomInfoMsg = RoomListMsgOuterClass.GetRoomInfoMsg.newBuilder();
                            getRoomInfoMsg.setCode(Result.SUCCESSFUL_CODE);
                            getRoomInfoMsg.setMsg(Result.SUCCESSFUL_MESG);
                            players.forEach(playerRoom1 -> getRoomInfoMsg.addPlayers(playerRoom1.toMsg()));
                            List<String> userIds = players.stream()
                                    .map(playerRoom1 -> playerRoom1.getUid())
                                    .filter(playerRoom2 -> !playerRoom2.equals(uid))
                                    .collect(Collectors.toList());
                            broadcast(userIds, getRoomInfoMsg.build());
                            //单播
                            List<ServerSession> sessionsBy = SessionMap.inst().getSessionsBy(uid);
                            if (sessionsBy.size() > 0) {
                                ServerSession serverSession = sessionsBy.get(0);
                                serverSession.getUser().setRoomId("");
                                LeaveRoomMsgOuterClass.LeaveRoomMsg.Builder leaveRoomMsg = LeaveRoomMsgOuterClass.LeaveRoomMsg.newBuilder();
                                leaveRoomMsg.setCode(Result.SUCCESSFUL_CODE);
                                leaveRoomMsg.setMsg("你被踢出房间！");
                                serverSession.writeAndFlush(leaveRoomMsg.build());
                            }


                        });
            }

        }
    }

    public void Unready(ServerSession session) {
        OnlineUser user = session.getUser();
        String roomId = user.getRoomId();
        if (null == session || !session.isLogin()) {
           /* msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
            msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());*/
            log.error("未登录");
        } else if (StringUtils.isEmpty(roomId)) {
            log.error("用户不在房间内");
            /*msg.setCode(SystemErrorType.SYSTEM_ERROR.getCode());
            msg.setMsg("用户不在房间内！");*/
        } else {
            RoomInfo roomInfo = roomMap.get(user.getRoomId());
            List<PlayerRoom> players = roomInfo.getPlayers();
            players.stream()
                    .filter(playerRoom -> playerRoom.getUid().equals(user.getId()))
                    .findFirst()
                    .ifPresent(playerRoom -> {
                        playerRoom.setRoomStatus(0);
                        RoomListMsgOuterClass.GetRoomInfoMsg.Builder getRoomInfoMsg = RoomListMsgOuterClass.GetRoomInfoMsg.newBuilder();
                        getRoomInfoMsg.setCode(Result.SUCCESSFUL_CODE);
                        getRoomInfoMsg.setMsg(Result.SUCCESSFUL_MESG);
                        List<String> userIds = players.stream().map(playerRoom1 -> playerRoom1.getUid()).collect(Collectors.toList());
                        players.forEach(playerRoom1 -> getRoomInfoMsg.addPlayers(playerRoom1.toMsg()));
                        broadcast(userIds, getRoomInfoMsg.build());
                    });
        }
    }

    public void ready(ServerSession session) {
        OnlineUser user = session.getUser();
        String roomId = user.getRoomId();
        if (null == session || !session.isLogin()) {
           /* msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
            msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());*/
            log.error("未登录");
        } else if (StringUtils.isEmpty(roomId)) {
            log.error("用户不在房间内");
            /*msg.setCode(SystemErrorType.SYSTEM_ERROR.getCode());
            msg.setMsg("用户不在房间内！");*/
        } else {
            RoomInfo roomInfo = roomMap.get(user.getRoomId());
            List<PlayerRoom> players = roomInfo.getPlayers();
            players.stream()
                    .filter(playerRoom -> playerRoom.getUid().equals(user.getId()))
                    .findFirst()
                    .ifPresent(playerRoom -> {
                        playerRoom.setRoomStatus(1);
                        RoomListMsgOuterClass.GetRoomInfoMsg.Builder getRoomInfoMsg = RoomListMsgOuterClass.GetRoomInfoMsg.newBuilder();
                        getRoomInfoMsg.setCode(Result.SUCCESSFUL_CODE);
                        getRoomInfoMsg.setMsg(Result.SUCCESSFUL_MESG);
                        List<String> userIds = players.stream().map(playerRoom1 -> playerRoom1.getUid()).collect(Collectors.toList());
                        players.forEach(playerRoom1 -> getRoomInfoMsg.addPlayers(playerRoom1.toMsg()));
                        broadcast(userIds, getRoomInfoMsg.build());
                    });
        }
    }


    public void broadcast(List<String> userIds, com.google.protobuf.GeneratedMessageV3 msgBase) {
        SessionMap.inst().getSessionsBy(userIds).stream().
                forEach(serverSession -> serverSession.writeAndFlush(msgBase));

    }

    @Override
    public void broadcast(String roomId, com.google.protobuf.GeneratedMessageV3 msgBase) {
        RoomInfo roomInfo = roomMap.get(roomId);
        List<String> userIds = roomInfo.getPlayers().stream().map(playerRoom -> playerRoom.getUid()).collect(Collectors.toList());
        broadcast(userIds, msgBase);
    }

    @Override
    public boolean isOwn(OnlineUser user) {
        RoomInfo roomInfo = roomMap.get(user.getRoomId());
        boolean isOwn = roomInfo.getPlayers().get(0).getUid().equals(user.getId());
        return isOwn;
    }

    @Override
    public boolean isAllReady(String roomId) {
        RoomInfo roomInfo = roomMap.get(roomId);
        List<PlayerRoom> players = roomInfo.getPlayers();

        for (int i = 1; i < players.size(); i++) {
            PlayerRoom playerRoom = players.get(i);
            if (playerRoom.getRoomStatus() == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getDegree(OnlineUser user) {
        RoomInfo roomInfo = roomMap.get(user.getRoomId());
        List<PlayerRoom> players = roomInfo.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getUid().equals(user.getId())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getCount(String roomId) {
        RoomInfo roomInfo = roomMap.get(roomId);
        return roomInfo.getPlayers().size();
    }

    @Override
    public String getRoomIdBy(String uid) {
        Optional<RoomInfo> first = roomMap.values().stream()
                .filter(roomInfo -> roomInfo.getPlayers().stream().filter(playerRoom -> playerRoom.getUid().equals(uid)).count() > 0)
                .findFirst();
        if (first.isPresent()) {
            return first.get().getId();
        }
        return null;
    }

    @Override
    public void initRoomPlayer(String roomId) {
        RoomInfo roomInfo = roomMap.get(roomId);
        List<PlayerRoom> players = roomInfo.getPlayers();
        players.forEach(playerRoom -> playerRoom.setRoomStatus(0));
    }

    @Override
    public void start(String roomId) {
        RoomInfo roomInfo = roomMap.get(roomId);
        roomInfo.setStatus(1);
    }

    @Override
    public void end(String roomId) {
        RoomInfo roomInfo = roomMap.get(roomId);
        roomInfo.setStatus(0);
    }
}
