package com.ynhj.magic_war.Netty.service;

import com.ynhj.magic_war.utils.UUIDUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @date: 2020-11-24
 * @author: yangniuhaojiang
 * @title: InitService
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Component
public class InitService {

    @PostConstruct
    void init(){
        UUIDUtils.init();
    }
}
