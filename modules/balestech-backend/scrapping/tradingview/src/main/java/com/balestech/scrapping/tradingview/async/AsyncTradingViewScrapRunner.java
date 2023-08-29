package com.balestech.scrapping.tradingview.async;

import com.balestech.scrapping.tradingview.async.dto.AsyncTradingViewFilter;
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

    public void executeMultipleTasksAndWait(List<AsyncTradingViewFilter> asyncTradingViewFilterList) {
        List<Future<String>> futures = new ArrayList<>();
        asyncTradingViewFilterList.forEach(filter -> futures.add(asyncTradingViewScrap.executeTask(filter)));
        for (Future future: futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
