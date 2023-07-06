package com.balestech.b3scrap.entity.stock_site;

import com.balestech.b3scrap.entity.stock.Stock;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

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
