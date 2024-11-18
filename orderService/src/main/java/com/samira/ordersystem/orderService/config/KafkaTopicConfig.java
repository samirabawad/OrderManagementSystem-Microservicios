package com.samira.ordersystem.orderService.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
//config del topic
public class KafkaTopicConfig {

    //genera topic al iniciar la app
    @Bean
    public NewTopic generateTopic(){
        //crea propiedades del topic.
        Map<String, String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG,TopicConfig.CLEANUP_POLICY_DELETE);
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");

        return TopicBuilder.name("order-created")
                .partitions(2)
                .replicas(1) //luego tirarlo a valor 2
                .configs(configurations) //asigna las props creadas
                .build();

    }
}
