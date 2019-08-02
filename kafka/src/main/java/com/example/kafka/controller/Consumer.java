package com.example.kafka.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
   /* @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(@Payload String message) throws Exception {
        logger.info(String.format("$$ -> Consumed Message -> %s",message));
       *//* if (1 == 1) {
            throw new Exception("1");
        }*//*
        //  ack.acknowledge();
    }*/

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(@Payload String message, Acknowledgment ack){
        logger.info(String.format("$$ -> Consumed Message -> %s",message));
      //  ack.acknowledge();
    }



}
