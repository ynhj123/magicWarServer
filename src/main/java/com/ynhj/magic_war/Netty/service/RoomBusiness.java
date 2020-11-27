package com.ynhj.magic_war.Netty.service;

import com.ynhj.magic_war.Netty.BusinessServiceImpl;
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
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @date: 2020-11-23
 * @author: yangniuhaojiang
 * @title: RoomBusiness
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Component
public class RoomBusiness implements CommandLineRunner {
    Logger log = LogManager.getLogger(getClass());
    Map<String, RoomInfo> roomMap = new HashMap<>(1024);
    @Autowired
    BusinessServiceImpl businessService;
    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        businessService.setListeners(RoomListMsg.class.getSimpleName(), (ctx, msgBase) -> {
            RoomListMsg msg = (RoomListMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            if (null == session || !session.isLogin()) {
                msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
                msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());
            } else if (msg.getCurPage() < 0 || ((msg.getCurPage() - 1) * msg.getPageSize() > roomMap.size())) {
                msg.setCode(SystemErrorType.BAD_REQUEST_ERROR.getCode());
                msg.setMsg("请求越界");
            } else {
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
            session.writeAndFlush(msg);
        }).setListeners(CreateRoomMsg.class.getSimpleName(), (ctx, msgBase) -> {
            CreateRoomMsg msg = (CreateRoomMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            if (null == session || !session.isLogin()) {
                msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
                msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());
            } else {
                RoomInfo roomInfo = new RoomInfo();
                roomInfo.setMaxCount(8);
                List<PlayerRoom> players = roomInfo.getPlayers();
                roomInfo.setCount(players.size() + 1);
                roomInfo.setStatus(0);
                roomInfo.setId(UUIDUtils.getRoomId());
                OnlineUser user = session.getUser();
                user.setRoomId(roomInfo.getId());
                Role role = roleService.get(user.getId());
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
            session.writeAndFlush(msg);
        }).setListeners(ChatRoomMsg.class.getSimpleName(), (ctx, msgBase) -> {
            ChatRoomMsg msg = (ChatRoomMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
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

        }).setListeners(GetRoomInfoMsg.class.getSimpleName(), (ctx, msgBase) -> {
            GetRoomInfoMsg msg = (GetRoomInfoMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
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
            session.writeAndFlush(msg);
        }).setListeners(EnterRoomMsg.class.getSimpleName(), ((ctx, msgBase) -> {
            EnterRoomMsg msg = (EnterRoomMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            if (null == session || !session.isLogin()) {
                msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
                msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());
            } else {
                String roomId = msg.getRoomId();
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
            session.writeAndFlush(msg);
        })).setListeners(LeaveRoomMsg.class.getSimpleName(), (ctx, msgBase) -> {
            //广播离开游戏
            LeaveRoomMsg msg = (LeaveRoomMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
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
        }).setListeners(ReadyStartMsg.class.getSimpleName(), (ctx, msgBase) -> {
            //广播准备
            ReadyStartMsg msg = (ReadyStartMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
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

        }).setListeners(UnreadyStartMsg.class.getSimpleName(), (ctx, msgBase) -> {
            //广播取消准备
            UnreadyStartMsg msg = (UnreadyStartMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
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
        }).setListeners(KickRoomMsg.class.getSimpleName(),((ctx, msgBase) -> {
            KickRoomMsg msg = (KickRoomMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
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
                if (!players.get(0).getUid().equals(user.getId())){
                    log.error("用户不是房主！");
                }else {
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
                                if (sessionsBy.size()>0){
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

        }))
        ;

    }

    private void broadcast(List<String> userIds, MsgBase msgBase) {
        SessionMap.inst().getSessionsBy(userIds).stream().
                forEach(serverSession -> serverSession.writeAndFlush(msgBase));

    }

}
