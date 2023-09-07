package com.balestech.commom.domain.indicator;

public enum IndicatorEnum {
    PRICE("Preço da ação"),
    VOLUME("Volume de negociação"),
    TARGET("Preço alvo da ação");

    private String companyName;

    private IndicatorEnum(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
}
