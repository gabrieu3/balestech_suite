package com.balestech.scrapping.tradingview.async;

import com.balestech.scrapping.tradingview.async.dto.AsyncTradingViewFilter;
import com.balestech.scrapping.tradingview.service.WebScrapStockIndicatorTradingView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class AsyncTradingViewScrap {

    @Autowired
    private WebScrapStockIndicatorTradingView webScrapStockIndicatorTradingView;

    @Async("taskExecutor")
    public Future<String> executeTask(AsyncTradingViewFilter asyncTradingViewFilter) {
        return new AsyncResult<>(webScrapStockIndicatorTradingView.scrap(asyncTradingViewFilter.getStock(), asyncTradingViewFilter.getIndicatorList()));
    }
}
