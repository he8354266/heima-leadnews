package com.heima.common.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/818:16
 */
public class TestKafkaListener implements KafkaListener<String,String> {
    @Override
    public String topic() {
        return "topic.test";
    }

    @Override
    public void onMessage(ConsumerRecord data, Consumer consumer) {
        System.out.println("===========receive test message:" + data);
    }

//    @Override
//    public void onMessage(Object o) {
//
//    }
}
