package com.balestech.b3scrap.entity.stock_indicator;

import com.balestech.b3scrap.entity.indicator.Indicator;
import com.balestech.b3scrap.entity.stock.Stock;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
public class StockIndicator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Stock stock;
    @ManyToOne(fetch = FetchType.LAZY)
    private Indicator indicator;
    private BigDecimal valueNumber;
    private String valueString;
    private LocalDateTime dateTimeCreate = LocalDateTime.now();
    private String localInfo;

}
