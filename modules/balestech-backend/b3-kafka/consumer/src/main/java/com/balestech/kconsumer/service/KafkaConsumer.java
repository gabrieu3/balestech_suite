package com.balestech.kconsumer.service;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "balestech")
    public void consumeMessage(String message) {
        System.out.println("Received message: " + message);
        // Faça o processamento necessário com a mensagem recebida
    }
}