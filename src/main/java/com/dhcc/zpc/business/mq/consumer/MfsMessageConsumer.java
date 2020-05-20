package com.dhcc.zpc.business.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Description TODO
 * @Author 赵朋超
 * @Date 2020/5/19 22:36
 * @Version 1.0
 */
@Slf4j
@Service
@RocketMQMessageListener(
        topic = "${risk.mq.instance.mfsMessage.topicName}",
        selectorExpression = "RISK-XML",
        consumerGroup = "risk-message-${risk.mq.instance.mfsMessage.topicName}-RISK-XML"
)
public class MfsMessageConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void onMessage(String message) {
        System.out.println("消费者消费消息：" + message);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setInstanceName(UUID.randomUUID().toString());
    }
}
