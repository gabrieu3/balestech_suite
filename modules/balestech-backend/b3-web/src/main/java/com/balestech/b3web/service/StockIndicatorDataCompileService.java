package com.balestech.b3web.service;

import com.balestech.b3web.entity.vstock_indicator.StockIndicatorDataCompile;
import com.balestech.b3web.entity.vstock_indicator.StockIndicatorDataCompileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class StockIndicatorDataCompileService {

    @Autowired
    private StockIndicatorDataCompileRepository repository;

    public List<StockIndicatorDataCompile> get(String stockName){
        return isNull(stockName) ? repository.findAll() : repository.findByName(stockName);
    }
}
