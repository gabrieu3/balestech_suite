package com.balestech.scrapping.tradingview.service;

import com.balestech.commom.domain.indicator.IndicatorEnum;
import com.balestech.commom.util.EnumUtils;
import com.balestech.scrapping.tradingview.domain.dto.AsyncTradingViewFilter;
import com.balestech.scrapping.tradingview.domain.dto.StockEnumTradingView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DataFlowTradingView {

    @Autowired
    private AsyncTradingViewScrapRunner asyncScrapTradingViewScrapRunner;

    public void run() {
        log.info("Scrapping TradingView");
        asyncScrapTradingViewScrapRunner.executeMultipleTasksAndWait(getAsyncTradingViewFilterList());
    }

    private List<AsyncTradingViewFilter> getAsyncTradingViewFilterList(){
        List<IndicatorEnum> listIndicatorEnum = new ArrayList<>();
        listIndicatorEnum.add(IndicatorEnum.PRICE);

        List<AsyncTradingViewFilter> asyncTradingViewFilterList = new ArrayList<>();

        for(StockEnumTradingView stockEnum: EnumUtils.toList(StockEnumTradingView.class)){
            asyncTradingViewFilterList.add(AsyncTradingViewFilter.builder().stock(stockEnum).indicatorList(listIndicatorEnum).build());
        }

        return asyncTradingViewFilterList;
    }
}
