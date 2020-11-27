package com.ynhj.magic_war;

import com.ynhj.magic_war.Netty.NettyServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @date: 2020-11-11
 * @author: yangniuhaojiang
 * @title: KafkaTest
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class KafkaTest extends MagicWarApplicationTests {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Test
    void sendMsg(){
        System.err.println("topic1:test1");
        kafkaTemplate.send("topic1", "topic1:test");
        System.err.println("topic1:test2");
        new NettyServer().run();
    }


}
