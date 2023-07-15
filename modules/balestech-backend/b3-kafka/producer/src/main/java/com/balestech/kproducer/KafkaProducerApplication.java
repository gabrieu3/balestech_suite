package com.balestech.kproducer;

import com.balestech.kproducer.service.KafkaProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.balestech")
public class KafkaProducerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(KafkaProducerApplication.class, args);
		KafkaProducer producer = context.getBean(KafkaProducer.class);
		producer.sendMessage("balestech", "Hello, Kafka!");
	}

}
