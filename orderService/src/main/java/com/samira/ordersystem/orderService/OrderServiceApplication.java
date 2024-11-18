package com.samira.ordersystem.orderService;

import com.samira.ordersystem.orderService.model.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner init(KafkaTemplate<String, Order> kafkaTemplate) {
		return args -> {
			Order order = new Order("12345", "Laptop", 2);
			kafkaTemplate.send("order-created", order);
		};
	}
}
