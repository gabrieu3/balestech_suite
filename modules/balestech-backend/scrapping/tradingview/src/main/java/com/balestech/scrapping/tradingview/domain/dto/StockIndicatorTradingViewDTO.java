package com.balestech.scrapping.tradingview.domain.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockIndicatorTradingViewDTO {
    private StockEnumTradingView stock;
    private String target;
    private String price;
    private String volume;
}
