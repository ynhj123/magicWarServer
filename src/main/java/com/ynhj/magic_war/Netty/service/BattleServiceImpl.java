package com.ynhj.magic_war.Netty.service;

import com.ynhj.magic_war.Netty.ServerSession;
import com.ynhj.magic_war.common.entity.Result;
import com.ynhj.magic_war.common.exception.SystemErrorType;
import com.ynhj.magic_war.model.Equipment;
import com.ynhj.magic_war.model.entity.OnlineUser;
import com.ynhj.magic_war.model.entity.PlayerInfo;
import com.ynhj.magic_war.model.entity.msg.*;
import com.ynhj.magic_war.service.EquipmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @date: 2020-11-30
 * @author: yangniuhaojiang
 * @title: BattleServiceImpl
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Service
public class BattleServiceImpl implements BattleService {
    static Map<String, List<PlayerInfo>> players = new HashMap<>();
    @Autowired
    RoomService roomService;
    @Autowired
    EquipmentService equipmentService;

    @Override
    public void start(StartMsg msg, ServerSession session) {
        OnlineUser user = session.getUser();
        if (null == session || !session.isLogin()) {
            msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
            msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());
        } else if (user == null || StringUtils.isEmpty(user.getRoomId())) {
            msg.setCode(SystemErrorType.SYSTEM_ERROR.getCode());
            msg.setMsg("用户不在房间内！");

        } else if (!roomService.isOwn(user)) {
            msg.setCode(SystemErrorType.SYSTEM_ERROR.getCode());
            msg.setMsg("用户不是房主");
        } else if (!roomService.isAllReady(user.getRoomId())) {
            msg.setCode(SystemErrorType.SYSTEM_ERROR.getCode());
            msg.setMsg("用户未全部准备");
        } else {
            msg.setCode(Result.SUCCESSFUL_CODE);
            msg.setMsg(Result.SUCCESSFUL_MESG);
            roomService.broadcast(user.getRoomId(), msg);
        }
    }

    @Override
    public void load(LoadFinishMsg msg, ServerSession session) {
        OnlineUser user = session.getUser();
        if (null == session || !session.isLogin()) {
            msg.setCode(SystemErrorType.UNAUTHORIZED_ERROR.getCode());
            msg.setMsg(SystemErrorType.UNAUTHORIZED_ERROR.getMesg());
            session.writeAndFlush(msg);
        } else if (user == null || StringUtils.isEmpty(user.getRoomId())) {
            msg.setCode(SystemErrorType.SYSTEM_ERROR.getCode());
            msg.setMsg("用户不在房间内！");
            session.writeAndFlush(msg);
        } else {
            Equipment equipment = equipmentService.getTotal(user.getId());
            int degree = roomService.getDegree(user);
            if (degree == -1) {
                msg.setCode(SystemErrorType.SYSTEM_ERROR.getCode());
                msg.setMsg("用户不在房间内！");
                session.writeAndFlush(msg);
            } else {
                PlayerInfo playerInfo = new PlayerInfo(user.getId(), degree, user.getNickname(), equipment);
                List<PlayerInfo> playerInfos;
                if (players.containsKey(user.getRoomId())) {
                    playerInfos = players.get(user.getRoomId());
                    Optional<PlayerInfo> first = playerInfos.stream().filter(playerInfo1 -> playerInfo1.getUid().equals(user.getId())).findFirst();
                    if (!first.isPresent()){
                        playerInfos.add(playerInfo);
                    }else {
                        first.get().init(equipment);
                    }

                } else {
                    playerInfos = new ArrayList<>();
                    players.put(user.getRoomId(), playerInfos);
                    playerInfos.add(playerInfo);
                }
                int playerCount = roomService.getCount(user.getRoomId());
                if (playerInfos.size() == playerCount) {
                    msg.setPlayers(playerInfos);
                    msg.setCode(Result.SUCCESSFUL_CODE);
                    msg.setMsg(Result.SUCCESSFUL_MESG);
                    roomService.broadcast(user.getRoomId(), msg);
                }
            }

        }
    }

    @Override
    public void syncPlayer(SyncPlayerMsg msg, ServerSession session) {
        OnlineUser user = session.getUser();
        //更新信息
        List<PlayerInfo> playerInfos = players.get(user.getRoomId());
        Optional<PlayerInfo> player = playerInfos.stream().filter(playerInfo -> playerInfo.getUid().equals(user.getId())).findFirst();
        if (player.isPresent()) {
            player.get().update(msg);

            msg.setUid(user.getId());
            //广播
            roomService.broadcast(user.getRoomId(), msg);
            if (msg.getHp() == 0) {
                Optional<PlayerInfo> first = playerInfos.stream().filter(playerInfo -> playerInfo.getUid().equals(playerInfo.getFinialHitId())).findFirst();
                first.ifPresent(playerInfo -> {
                    playerInfo.setKillNum(playerInfo.getKillNum() + 1);
                });
                long count = playerInfos.stream().filter(playerInfo -> playerInfo.getHp() > 0).count();
                if (count <= 1) {
                    EndMsg endMsg = new EndMsg();
                    endMsg.setPlayerInfos(playerInfos);
                    roomService.broadcast(user.getRoomId(), endMsg);
                }
            }

        }
    }

    @Override
    public void leave(LeaveBattleMsg msg, ServerSession session) {
        OnlineUser user = session.getUser();
        //更新信息
        List<PlayerInfo> playerInfos = players.get(user.getRoomId());
        Optional<PlayerInfo> player = playerInfos.stream().filter(playerInfo -> playerInfo.getUid().equals(user.getId())).findFirst();
        if (player.isPresent()) {
            PlayerInfo playerInfo = player.get();
            playerInfos.remove(playerInfo);
            //广播
            roomService.broadcast(user.getRoomId(), msg);
        }
    }

    @Override
    public void syncSkill(SkillMsg msg, ServerSession session) {
        OnlineUser user = session.getUser();
        //更新信息
        List<PlayerInfo> playerInfos = players.get(user.getRoomId());
        Optional<PlayerInfo> player = playerInfos.stream().filter(playerInfo -> playerInfo.getUid().equals(user.getId())).findFirst();
        if (player.isPresent()) {
            msg.setUid(user.getId());
            //广播
            roomService.broadcast(user.getRoomId(), msg);
        }

    }

    @Override
    public void syncHit(HitMsg msg, ServerSession session) {
        OnlineUser user = session.getUser();
        //更新信息
        List<PlayerInfo> playerInfos = players.get(user.getRoomId());
        Optional<PlayerInfo> playerOpt = playerInfos.stream().filter(playerInfo -> playerInfo.getUid().equals(user.getId())).findFirst();
        if (playerOpt.isPresent()) {
            PlayerInfo playerInfo = playerOpt.get();
            playerInfo.setFinialHitId(msg.getTargetId());
            msg.setUid(user.getId());
            //广播
            roomService.broadcast(user.getRoomId(), msg);
        }
    }
}
