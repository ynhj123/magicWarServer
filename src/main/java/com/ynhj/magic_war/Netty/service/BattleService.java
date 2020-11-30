package com.ynhj.magic_war.Netty.service;

import com.ynhj.magic_war.Netty.ServerSession;
import com.ynhj.magic_war.model.entity.msg.LoadFinishMsg;
import com.ynhj.magic_war.model.entity.msg.StartMsg;
import com.ynhj.magic_war.model.entity.msg.SynPlayerMsg;

/**
 * @date: 2020-11-30
 * @author: yangniuhaojiang
 * @title: BattleService
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public interface BattleService {
    void Start(StartMsg msg, ServerSession session);

    void Load(LoadFinishMsg msg, ServerSession session);

    void SynPlayer(SynPlayerMsg msg, ServerSession session);
}
