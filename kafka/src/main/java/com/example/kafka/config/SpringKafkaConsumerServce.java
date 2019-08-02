package com.example.kafka.config;

import com.example.kafka.controller.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public class SpringKafkaConsumerServce {
    private final Logger logger = LoggerFactory.getLogger(SpringKafkaConsumerServce.class);
    @KafkaListener(topics = "users", groupId = "group_id",containerFactory = "kafkaManualAckListenerContainerFactory")
    public void consume(@Payload String message, Acknowledgment ack){
        logger.info(String.format("$$ -> Consumed Message -> %s",message));
       //   ack.acknowledge();
    }
}
