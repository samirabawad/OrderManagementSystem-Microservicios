package com.samira.ordersystem.orderService.config;

import com.samira.ordersystem.orderService.model.Order;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
//config del cliente: KafkaTemplate para enviar mensajes y ProducerFactory para producir el mensaje
public class KafkaProviderConfig {

    //recupera nuestro servidor de application.properties
    @Value("${spring.kafka.bootstrapServers}")
    private String bootstrapServers;

    //crea propiedades del mensaje a producir
    public Map<String, Object> producerConfig(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return properties;
    }


    //produce el mensaje con las props.
    @Bean
    public ProducerFactory<String, Order> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    //recibe el producer y envia el mensaje
    @Bean
    public KafkaTemplate<String, Order> kafkaTemplate(ProducerFactory<String,Order> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
