package com.heima.media.kafka;

import com.heima.common.kafka.KafkaSender;
import com.heima.common.kafka.messages.SubmitArticleAuthMessage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/915:28
 */
public class AdminMessageSender {
    @Autowired
    private KafkaSender kafkaSender;

    public void sendMessage(SubmitArticleAuthMessage message) {
        kafkaSender.sendSubmitArticleAuthMessage(message);
    }
}
