package com.ynhj.magic_war.Netty.service;

import com.ynhj.magic_war.Netty.BusinessServiceImpl;
import com.ynhj.magic_war.Netty.ServerSession;
import com.ynhj.magic_war.model.entity.msg.protobuf.*;
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
        businessService.setListeners(StartMsgOuterClass.StartMsg.class.getSimpleName(), (ctx, msgBase) -> {

            StartMsgOuterClass.StartMsg msg = StartMsgOuterClass.StartMsg.parseFrom(msgBase.getContent());
            ServerSession session = ServerSession.getSession(ctx);
            battleService.start(msg, session);
        }).setListeners(LeaveBattleMsgOuterClass.LeaveBattleMsg.class.getSimpleName(), (ctx, msgBase) -> {
            LeaveBattleMsgOuterClass.LeaveBattleMsg msg = LeaveBattleMsgOuterClass.LeaveBattleMsg.parseFrom(msgBase.getContent());
            ServerSession session = ServerSession.getSession(ctx);
            battleService.leave(msg, session);
        }).setListeners(HitMsgOuterClass.HitMsg.class.getSimpleName(), (ctx, msgBase) -> {
            HitMsgOuterClass.HitMsg msg = HitMsgOuterClass.HitMsg.parseFrom(msgBase.getContent());
            ServerSession session = ServerSession.getSession(ctx);
            battleService.syncHit(msg, session);
        }).setListeners(SkillMsgOuterClass.SkillMsg.class.getSimpleName(), (ctx, msgBase) -> {
            SkillMsgOuterClass.SkillMsg msg = SkillMsgOuterClass.SkillMsg.parseFrom(msgBase.getContent());
            ServerSession session = ServerSession.getSession(ctx);
            battleService.syncSkill(msg, session);
        }).setListeners(SyncPlayerMsgOuterClass.SyncPlayerMsg.class.getSimpleName(), (ctx, msgBase) -> {
            SyncPlayerMsgOuterClass.SyncPlayerMsg msg = SyncPlayerMsgOuterClass.SyncPlayerMsg.parseFrom(msgBase.getContent());
            ServerSession session = ServerSession.getSession(ctx);
            battleService.syncPlayer(msg, session);
        }).setListeners(LoadFinishMsgOuterClass.LoadFinishMsg.class.getSimpleName(), (ctx, msgBase) -> {
            LoadFinishMsgOuterClass.LoadFinishMsg msg = LoadFinishMsgOuterClass.LoadFinishMsg.parseFrom(msgBase.getContent());
            ServerSession session = ServerSession.getSession(ctx);
            battleService.load(msg, session);
        })
        ;
    }
}
