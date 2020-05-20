package com.dhcc.zpc.business.mq.producer;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yuanshaobo
 * @date: 2019/8/22 9:47
 * @since v1.1
 */
@Service
public class MqProducerTemplate {
    @Value("${risk.mq.instance.mfsMessage.topicName}")
    private String mfsMessageTopic;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendMfsMessage(String tags , String message){
        rocketMQTemplate.syncSend(mfsMessageTopic + ":" + tags, message);
    }
}
