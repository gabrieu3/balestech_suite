package com.balestech.b3.entity.stock_site;

import com.balestech.b3.entity.indicator.Indicator;
import com.balestech.b3.entity.stock.Stock;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
public class StockSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Stock stock;
    private String site;
    private String path;

}
