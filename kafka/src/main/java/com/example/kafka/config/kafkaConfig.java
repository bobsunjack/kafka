package com.example.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryContext;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class kafkaConfig {

    private Logger log = LoggerFactory.getLogger(kafkaConfig.class);
    @Autowired
    private DefaultKafkaConsumerFactory consumerFactory;

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory
            () {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(1);
        factory.getContainerProperties().setPollTimeout(1000);

        log.info("init kafkaListener annotation container Factory");
        return factory;
    }


    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> consumerProps = new HashMap();
        consumerProps.putAll(consumerFactory.getConfigurationProperties());
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return consumerProps;
    }


    @Bean
    public ConsumerFactory<String, String> manualConsumerFactory() {
        Map<String, Object> configs = consumerConfigs();
        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        return new DefaultKafkaConsumerFactory<>(configs);
    }


    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
    kafkaManualAckListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(manualConsumerFactory());
        ContainerProperties props = factory.getContainerProperties();
        props.setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        props.setIdleEventInterval(100L);
        //   factory.setRecordFilterStrategy(manualFilter());
        props.setPollTimeout(1000L);
        factory.setAckDiscarded(true);
        //    factory.setRetryTemplate(new RetryTemplate());
        factory.setRecoveryCallback(new RecoveryCallback<Void>() {

            @Override
            public Void recover(RetryContext context) throws Exception {
                return null;
            }

        });

        return factory;
    }


    /**
     * 先init他们
     *
     * @return
     */
    @Bean
    public SpringKafkaConsumerServce kafkaListeners() {
        return new SpringKafkaConsumerServce();
    }
}