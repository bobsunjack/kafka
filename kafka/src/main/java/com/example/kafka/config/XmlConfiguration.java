package com.example.kafka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath*:applicationContext.xml"})
public class XmlConfiguration {
}
