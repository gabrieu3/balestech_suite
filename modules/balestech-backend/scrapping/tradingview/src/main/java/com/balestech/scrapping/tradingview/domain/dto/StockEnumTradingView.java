package com.balestech.scrapping.tradingview.domain.dto;

public enum StockEnumTradingView {
    CIEL3("CIELO"),
    MGLU3("Magazine Luiza"),
    MGLU4("Magazine Luiza");

    private String companyName;

    private StockEnumTradingView(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
}
