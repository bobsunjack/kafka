package com.example.kafka.consume;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;

public class KafkaEventConsumer implements AcknowledgingMessageListener<String, String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEventConsumer.class);

   /* @Autowired
    StudentService service;*/

    /**
     * Read the topic and push the data to the DB The @KafkaListener annotation
     * provides a mechanism for simple POJO listeners
     */
    @Override
    @KafkaListener(id = "consumer", topics = "users", groupId = "group_id")
    public void onMessage(ConsumerRecord<String, String> data, Acknowledgment acknowledgment) {

        try {
            LOGGER.info("Record value is : " + data.value());
            LOGGER.info("Offset value is : " + data.offset());
            LOGGER.info("Topic is : " + data.topic());
            LOGGER.info("Partition is : " + data.partition());
            LOGGER.info("printing the calendar object:" + data.value());
          //  service.create(data.value());
            LOGGER.info("pushed the data to DB successfuly");

        } catch (Exception e) {
            LOGGER.error("Push the messaged to Error Stream : " + e);
        } finally {
            acknowledgment.acknowledge();
        }

    }
}
