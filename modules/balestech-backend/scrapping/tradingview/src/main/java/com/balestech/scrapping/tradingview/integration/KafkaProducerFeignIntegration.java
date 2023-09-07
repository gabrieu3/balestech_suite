package com.balestech.scrapping.tradingview.integration;

import com.balestech.scrapping.tradingview.configuration.FeignConfigurationTradingView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "kafka-producer", url = "${integration.kafka-producer.host:localhost:8100/b3/api/v1}", configuration = FeignConfigurationTradingView.class)
public interface KafkaProducerFeignIntegration {
    @RequestMapping(method = RequestMethod.POST, value = "producer")
    void sendMessage(@RequestParam String topic, @RequestBody String message);
}

