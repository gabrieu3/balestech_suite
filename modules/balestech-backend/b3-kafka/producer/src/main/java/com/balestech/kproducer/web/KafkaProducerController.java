package com.balestech.kproducer.web;

import com.balestech.kproducer.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1")
public class KafkaProducerController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping(path="producer")
    void sendMessage(@RequestParam String topic, @RequestBody String message){
        kafkaProducerService.sendMessage(topic, message);
    }
}
