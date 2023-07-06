package com.balestech.b3web.entity.vstock_indicator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "v_stock_indicator_data_compile")
public class StockIndicatorDataCompile {

    @Transient
    private UUID id;
    @Id
    private String name;
    private String company;
    private BigDecimal price;
    @Column(name="target_tradingview")
    private BigDecimal targetTrading;
    @Column(name="target_btgpactual")
    private BigDecimal targetBTG;
    @Column(name="target_wsj")
    private BigDecimal targetWsj;
    @Column(name="target_xp")
    private BigDecimal targetXp;
    private BigDecimal volume;

    public UUID getId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        return id;
    }
}
