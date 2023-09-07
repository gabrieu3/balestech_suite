package com.balestech.scrapping.tradingview.domain.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockIndicatorTradingViewKafkaMessage {

    String target;
}
