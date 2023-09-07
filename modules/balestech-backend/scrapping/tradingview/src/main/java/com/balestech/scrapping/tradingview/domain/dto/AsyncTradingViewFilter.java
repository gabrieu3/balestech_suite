package com.balestech.scrapping.tradingview.domain.dto;

import com.balestech.commom.domain.indicator.IndicatorEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsyncTradingViewFilter {
    StockEnumTradingView stock;
    List<IndicatorEnum> indicatorList;
}
