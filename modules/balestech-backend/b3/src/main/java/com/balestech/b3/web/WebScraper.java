package com.balestech.b3.web;

import com.balestech.b3.entity.indicator.Indicator;
import com.balestech.b3.entity.indicator.IndicatorRepository;
import com.balestech.b3.entity.stock.Stock;
import com.balestech.b3.entity.stock.StockRepository;
import com.balestech.b3.entity.stock_indicator.StockIndicator;
import com.balestech.b3.entity.stock_indicator.StockIndicatorRepository;
import com.balestech.b3.service.WebScrapStockIndicatorInvestidor10;
import com.balestech.b3.service.WebScrapStockIndicatorTradingView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class WebScraper {

    @Autowired
    private WebScrapStockIndicatorInvestidor10 investidor10;

    @Autowired
    private WebScrapStockIndicatorTradingView tradingView;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Autowired
    private StockIndicatorRepository stockIndicatorRepository;

    public void scrap(){
        List<StockIndicator> stockIndicatorList = new ArrayList<>();

        List<Stock> stockList = stockRepository.findAll();
        List<Indicator> indicatorList = indicatorRepository.findAll();

        for(Stock stock: stockList){
            try {
                stockIndicatorList.addAll(investidor10.scrap(stock, indicatorList));
                stockIndicatorList.addAll(tradingView.scrap(stock, indicatorList));
            }catch(Exception e){
                log.error("Falha no scrap da ação: " + stock.getName() + ". Erro: "+e.getMessage());
            }
        }
        stockIndicatorRepository.saveAll(stockIndicatorList);

    }

}
