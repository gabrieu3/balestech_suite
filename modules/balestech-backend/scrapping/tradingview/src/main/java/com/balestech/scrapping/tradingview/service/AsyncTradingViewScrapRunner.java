package com.balestech.scrapping.tradingview.service;

import com.balestech.scrapping.tradingview.integration.KafkaProducerFeignIntegration;
import com.balestech.scrapping.tradingview.domain.dto.AsyncTradingViewFilter;
import com.balestech.scrapping.tradingview.domain.dto.StockIndicatorTradingViewDTO;
import com.balestech.scrapping.tradingview.util.TradingViewUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class AsyncTradingViewScrapRunner {

    @Autowired
    private AsyncTradingViewScrap asyncTradingViewScrap;

    @Autowired
    private KafkaProducerFeignIntegration kafkaProducer;

    @Autowired
    private ObjectMapper objectMapper;

    public void executeMultipleTasksAndWait(List<AsyncTradingViewFilter> asyncTradingViewFilterList) {
        StockIndicatorTradingViewDTO response;
        List<Future<StockIndicatorTradingViewDTO>> futures = new ArrayList<>();
        asyncTradingViewFilterList.forEach(filter -> futures.add(asyncTradingViewScrap.executeTask(filter)));
        for (Future<StockIndicatorTradingViewDTO> future: futures) {
            try {
                response = future.get();
                kafkaProducer.sendMessage(TradingViewUtil.TOPIC_KAFKA_TRADINGVIEW,  objectMapper.writeValueAsString(response));
            } catch (InterruptedException | ExecutionException | JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

}
