package com.ynhj.magic_war.Netty.service;

import com.ynhj.magic_war.Netty.ServerSession;
import com.ynhj.magic_war.model.entity.msg.*;

/**
 * @date: 2020-11-30
 * @author: yangniuhaojiang
 * @title: BattleService
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public interface BattleService {
    void start(StartMsg msg, ServerSession session);

    void load(LoadFinishMsg msg, ServerSession session);

    void syncPlayer(SyncPlayerMsg msg, ServerSession session);

    void leave(LeaveBattleMsg msg, ServerSession session);

    void syncSkill(SkillMsg msg, ServerSession session);

    void syncHit(HitMsg msg, ServerSession session);
}
