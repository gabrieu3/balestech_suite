package com.balestech.scrapping.tradingview.service;

import com.balestech.commom.domain.indicator.IndicatorEnum;
import com.balestech.commom.domain.stock.StockEnum;
import com.balestech.commom.util.EnumUtils;
import com.balestech.scrapping.tradingview.async.AsyncTradingViewScrapRunner;
import com.balestech.scrapping.tradingview.async.dto.AsyncTradingViewFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class WebScrapTradingView {

    @Autowired
    private AsyncTradingViewScrapRunner asyncScrapTradingViewScrapRunner;

    public void scrap() {
        log.info("Scrapping TradingView");
        asyncScrapTradingViewScrapRunner.executeMultipleTasksAndWait(getAsyncTradingViewFilterList());
    }

    private List<AsyncTradingViewFilter> getAsyncTradingViewFilterList(){
        List<IndicatorEnum> listIndicatorEnum = new ArrayList<>();
        listIndicatorEnum.add(IndicatorEnum.PRICE);

        List<AsyncTradingViewFilter> asyncTradingViewFilterList = new ArrayList<>();

        for(StockEnum stockEnum: EnumUtils.toList(StockEnum.class)){
            asyncTradingViewFilterList.add(AsyncTradingViewFilter.builder().stock(stockEnum).indicatorList(listIndicatorEnum).build());
        }

        return asyncTradingViewFilterList;
    }
}
