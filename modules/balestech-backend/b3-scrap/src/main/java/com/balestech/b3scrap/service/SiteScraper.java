package com.balestech.b3scrap.service;

import com.balestech.b3scrap.entity.indicator.Indicator;
import com.balestech.b3scrap.entity.stock.Stock;
import com.balestech.b3scrap.entity.stock_indicator.StockIndicator;

import java.util.List;

public interface SiteScraper {
    List<StockIndicator> scrap(Stock stock, List<Indicator> indicatorList);

}

