package com.ynhj.magic_war.Netty.service;

import com.ynhj.magic_war.Netty.ServerSession;
import com.ynhj.magic_war.Netty.SessionMap;
import com.ynhj.magic_war.common.entity.Result;
import com.ynhj.magic_war.common.exception.SystemErrorType;
import com.ynhj.magic_war.model.Role;
import com.ynhj.magic_war.model.entity.OnlineUser;
import com.ynhj.magic_war.model.entity.PlayerRoom;
import com.ynhj.magic_war.model.entity.RoomInfo;
import com.ynhj.magic_war.model.entity.msg.*;
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
import java.util.stream.Stream;

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

    public void pageRoomInfos(RoomListMsg msg, ServerSession session) {
        OnlineUser user = session.getUser();
        if (null == session || !session.isLogin()) {
            msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
            msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());
        } else if (msg.getCurPage() < 0 || (((msg.getCurPage() - 1) != 0 )&&((msg.getCurPage() - 1) * msg.getPageSize() >= roomMap.size()))) {
            msg.setCode(SystemErrorType.BAD_REQUEST_ERROR.getCode());
            msg.setMsg("请求越界");
        } else if (!StringUtils.isEmpty(user.getRoomId())){
            RoomInfo roomInfo = roomMap.get(user.getRoomId());
            if (roomInfo == null){
                user.setRoomId("");
            }else {
                Optional<PlayerRoom> first = roomInfo.getPlayers().stream().filter(playerRoom -> playerRoom.getUid().equals(user.getId())).findFirst();
                if (!first.isPresent()){
                    user.setRoomId("");
                }else {
                    msg.setCode(SystemErrorType.REPEAT_ERROR.getCode());
                    msg.setMsg(roomInfo.getId());
                }
            }
        } else {
            page(msg);
        }

    }

    private void page(RoomListMsg msg) {
        List<RoomInfo> rooms = roomMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .sorted()
                .skip(msg.getPageSize() * (msg.getCurPage() - 1)).limit(msg.getPageSize())
                .collect(Collectors.toList());
        msg.setRooms(rooms);
        msg.setSize(roomMap.size());
        msg.setCode(Result.SUCCESSFUL_CODE);
        msg.setMsg(Result.SUCCESSFUL_MESG);
    }

    public void addRoomInfo(CreateRoomMsg msg, ServerSession session) {
        if (null == session || !session.isLogin()) {
            msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
            msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());
        } else {
            OnlineUser user = session.getUser();
            Role role = roleService.get(user.getId());
            RoomInfo roomInfo = new RoomInfo();
            roomInfo.setMaxCount(8);
            List<PlayerRoom> players = roomInfo.getPlayers();
            roomInfo.setCount(players.size() + 1);
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
            roomMap.put(roomInfo.getId(), roomInfo);
            msg.setRoomId(roomInfo.getId());
            msg.setCode(Result.SUCCESSFUL_CODE);
            msg.setMsg(Result.SUCCESSFUL_MESG);
        }

    }

    public void Chat(ChatRoomMsg msg, ServerSession session) {
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
                broadcast(userIds, msg);
            }
        }
    }

    public void getRoomInfo(GetRoomInfoMsg msg, ServerSession session) {
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
            msg.setPlayers(roomMap.get(roomId).getPlayers());
        }
    }

    public void enterRoom(EnterRoomMsg msg, ServerSession session) {
        if (null == session || !session.isLogin()) {
            msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
            msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());
        } else {
            String roomId = msg.getRoomId();
            if (!roomMap.containsKey(roomId)){
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
                PlayerRoom playerRoom = new PlayerRoom();
                playerRoom.setDegree(players.size());
                playerRoom.setNickname(user.getNickname());
                playerRoom.setRoomStatus(0);
                playerRoom.setUsername(user.getUsername());
                playerRoom.setUid(user.getId());
                roomInfo.setCount(players.size());
                playerRoom.setScore(roleService.get(user.getId()).getScore());
                players.add(playerRoom);
                user.setRoomId(roomId);
                msg.setCode(Result.SUCCESSFUL_CODE);
                msg.setMsg(Result.SUCCESSFUL_MESG);
                List<String> userIds = players.stream()
                        .map(playerRoom1 -> playerRoom1.getUid())
                        .filter(playerRoom1 -> !playerRoom1.equals(user.getId()))
                        .collect(Collectors.toList());
                GetRoomInfoMsg getRoomInfoMsg = new GetRoomInfoMsg();
                getRoomInfoMsg.setCode(Result.SUCCESSFUL_CODE);
                getRoomInfoMsg.setMsg(Result.SUCCESSFUL_MESG);
                getRoomInfoMsg.setPlayers(players);
                broadcast(userIds, getRoomInfoMsg);
            }
        }
    }

    public void leaveRoom(LeaveRoomMsg msg, ServerSession session) {
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
                        GetRoomInfoMsg getRoomInfoMsg = new GetRoomInfoMsg();
                        getRoomInfoMsg.setCode(Result.SUCCESSFUL_CODE);
                        getRoomInfoMsg.setMsg(Result.SUCCESSFUL_MESG);
                        getRoomInfoMsg.setPlayers(players);
                        List<String> userIds = players.stream()
                                .map(playerRoom1 -> playerRoom1.getUid())
                                .filter(playerRoom2 -> !playerRoom2.equals(user.getId()))
                                .collect(Collectors.toList());
                        broadcast(userIds, getRoomInfoMsg);
                    }

                    msg.setCode(Result.SUCCESSFUL_CODE);
                    msg.setMsg(Result.SUCCESSFUL_MESG);
                }
            }
            session.writeAndFlush(msg);
        }
    }

    public void KickUser(KickRoomMsg msg, ServerSession session) {
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
                            GetRoomInfoMsg getRoomInfoMsg = new GetRoomInfoMsg();
                            getRoomInfoMsg.setCode(Result.SUCCESSFUL_CODE);
                            getRoomInfoMsg.setMsg(Result.SUCCESSFUL_MESG);
                            getRoomInfoMsg.setPlayers(players);
                            List<String> userIds = players.stream()
                                    .map(playerRoom1 -> playerRoom1.getUid())
                                    .filter(playerRoom2 -> !playerRoom2.equals(uid))
                                    .collect(Collectors.toList());
                            broadcast(userIds, getRoomInfoMsg);
                            //单播
                            List<ServerSession> sessionsBy = SessionMap.inst().getSessionsBy(uid);
                            if (sessionsBy.size() > 0) {
                                ServerSession serverSession = sessionsBy.get(0);
                                serverSession.getUser().setRoomId("");
                                LeaveRoomMsg leaveRoomMsg = new LeaveRoomMsg();
                                leaveRoomMsg.setCode(Result.SUCCESSFUL_CODE);
                                leaveRoomMsg.setMsg("你被踢出房间！");
                                serverSession.writeAndFlush(leaveRoomMsg);
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
                        GetRoomInfoMsg getRoomInfoMsg = new GetRoomInfoMsg();
                        getRoomInfoMsg.setCode(Result.SUCCESSFUL_CODE);
                        getRoomInfoMsg.setMsg(Result.SUCCESSFUL_MESG);
                        List<String> userIds = players.stream().map(playerRoom1 -> playerRoom1.getUid()).collect(Collectors.toList());
                        getRoomInfoMsg.setPlayers(players);
                        broadcast(userIds, getRoomInfoMsg);
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
                        GetRoomInfoMsg getRoomInfoMsg = new GetRoomInfoMsg();
                        getRoomInfoMsg.setCode(Result.SUCCESSFUL_CODE);
                        getRoomInfoMsg.setMsg(Result.SUCCESSFUL_MESG);
                        List<String> userIds = players.stream().map(playerRoom1 -> playerRoom1.getUid()).collect(Collectors.toList());
                        getRoomInfoMsg.setPlayers(players);
                        broadcast(userIds, getRoomInfoMsg);
                    });
        }
    }


    public void broadcast(List<String> userIds, MsgBase msgBase) {
        SessionMap.inst().getSessionsBy(userIds).stream().
                forEach(serverSession -> serverSession.writeAndFlush(msgBase));

    }

    @Override
    public void broadcast(String roomId, MsgBase msgBase) {
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
        if (first.isPresent()){
            return first.get().getId();
        }
        return null;
    }
}
