package com.ynhj.magic_war.Netty.service;

import com.ynhj.magic_war.Netty.ServerSession;
import com.ynhj.magic_war.model.entity.msg.*;
import com.ynhj.magic_war.model.entity.msg.protobuf.*;

/**
 * @date: 2020-11-30
 * @author: yangniuhaojiang
 * @title: BattleService
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public interface BattleService {
    void start(StartMsgOuterClass.StartMsg msg, ServerSession session);

    void load(LoadFinishMsgOuterClass.LoadFinishMsg msg, ServerSession session);

    void syncPlayer(SyncPlayerMsgOuterClass.SyncPlayerMsg msg, ServerSession session);

    void leave(LeaveBattleMsgOuterClass.LeaveBattleMsg msg, ServerSession session);

    void syncSkill(SkillMsgOuterClass.SkillMsg msg, ServerSession session);

    void syncHit(HitMsgOuterClass.HitMsg msg, ServerSession session);
}
