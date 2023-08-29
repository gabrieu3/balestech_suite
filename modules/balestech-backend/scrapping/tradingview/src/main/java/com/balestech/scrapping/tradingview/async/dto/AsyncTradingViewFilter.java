package com.balestech.scrapping.tradingview.async.dto;

import com.balestech.commom.domain.indicator.IndicatorEnum;
import com.balestech.commom.domain.stock.StockEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsyncTradingViewFilter {
    StockEnum stock;
    List<IndicatorEnum> indicatorList;
}
