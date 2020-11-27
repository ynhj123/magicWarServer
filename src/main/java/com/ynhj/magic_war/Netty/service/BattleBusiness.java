package com.ynhj.magic_war.Netty.service;

import com.ynhj.magic_war.Netty.BusinessServiceImpl;
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
public class BattleBusiness implements CommandLineRunner {
    @Autowired
    BusinessServiceImpl businessService;

    @Override
    public void run(String... args) throws Exception {
        businessService.setListeners(StartMsg.class.getSimpleName(), (ctx, msgBase) -> {

        }).setListeners(EndMsg.class.getSimpleName(), (ctx, msgBase) -> {

        }).setListeners(HitMsg.class.getSimpleName(), (ctx, msgBase) -> {

        }).setListeners(SkillMsg.class.getSimpleName(), (ctx, msgBase) -> {

        }).setListeners(SynPlayerMsg.class.getSimpleName(), (ctx, msgBase) -> {

        });
    }
}
