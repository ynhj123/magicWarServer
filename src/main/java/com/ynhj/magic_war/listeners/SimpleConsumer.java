package com.ynhj.magic_war.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @date: 2020-11-11
 * @author: yangniuhaojiang
 * @title: SimpleConsumer
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Component
public class SimpleConsumer {

    @KafkaListener(topics = {"topic1", "topic2"})
    public void listen1(String data) {
        System.err.println("================== xxxxxxxxxxx ====================");
        System.err.println(data);
    }
}
