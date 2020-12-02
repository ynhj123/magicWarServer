package com.ynhj.magic_war.Netty.service;

import com.ynhj.magic_war.Netty.BusinessServiceImpl;
import com.ynhj.magic_war.Netty.ServerSession;
import com.ynhj.magic_war.model.entity.msg.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @date: 2020-11-23
 * @author: yangniuhaojiang
 * @title: BattleBussiness
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Component
public class BattleController implements CommandLineRunner {

    @Autowired
    BusinessServiceImpl businessService;
    @Autowired
    BattleService battleService;

    @Override
    public void run(String... args) throws Exception {
        businessService.setListeners(StartMsg.class.getSimpleName(), (ctx, msgBase) -> {
            StartMsg msg = (StartMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            battleService.start(msg, session);
        }).setListeners(LeaveBattleMsg.class.getSimpleName(), (ctx, msgBase) -> {
            LeaveBattleMsg msg = (LeaveBattleMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            battleService.leave(msg, session);
        }).setListeners(HitMsg.class.getSimpleName(), (ctx, msgBase) -> {

        }).setListeners(SkillMsg.class.getSimpleName(), (ctx, msgBase) -> {
            SkillMsg msg = (SkillMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            battleService.syncSkill(msg, session);
        }).setListeners(SyncPlayerMsg.class.getSimpleName(), (ctx, msgBase) -> {
            SyncPlayerMsg msg = (SyncPlayerMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            battleService.syncPlayer(msg, session);
        }).setListeners(LoadFinishMsg.class.getSimpleName(), (ctx, msgBase) -> {
            LoadFinishMsg msg = (LoadFinishMsg) msgBase;
            ServerSession session = ServerSession.getSession(ctx);
            battleService.load(msg, session);
        })
        ;
    }
}
