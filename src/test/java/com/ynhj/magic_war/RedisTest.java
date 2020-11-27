package com.ynhj.magic_war;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @date: 2020-11-11
 * @author: yangniuhaojiang
 * @title: RedisTest
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class RedisTest extends MagicWarApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;
    @Test
    void save() throws InterruptedException {
        redisTemplate.opsForValue().set("key","value",2,TimeUnit.SECONDS);
        System.out.println(redisTemplate.hasKey("key"));
        System.out.println(redisTemplate.opsForValue().get("key"));
        Thread.sleep(4*1000);
        System.out.println(redisTemplate.hasKey("key"));
        System.out.println(redisTemplate.opsForValue().get("key"));

    }
}
