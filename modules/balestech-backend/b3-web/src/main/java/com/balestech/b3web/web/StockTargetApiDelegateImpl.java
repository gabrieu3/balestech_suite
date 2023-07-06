package com.balestech.b3web.web;

import com.balestech.b3web.service.StockIndicatorDataCompileService;
import com.balestech.b3web.model.dto.StockTargetResponse;
import com.balestech.b3web.rest.StockTargetApiDelegate;
import com.balestech.b3web.service.StockTargetResponseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockTargetApiDelegateImpl implements StockTargetApiDelegate {

    @Autowired
    private StockIndicatorDataCompileService service;

    @Autowired
    private StockTargetResponseService stockTargetResponseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResponseEntity<List<StockTargetResponse>> stockTargetGet() {
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, StockTargetResponse.class);
        List<StockTargetResponse> stockTargetResponseList = stockTargetResponseService.calculateData(objectMapper.convertValue(service.get(null), listType));
        return ResponseEntity.ok(stockTargetResponseList);
    }
}
