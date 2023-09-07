package com.balestech.scrapping.tradingview.util;



public class TradingViewUtil {

    public TradingViewUtil(){ throw new RuntimeException("Classe static não permite ser instânciada");}

    public static final String URL_BASE = "https://br.tradingview.com";

    public static final String URL_TARGET = "/symbols/BMFBOVESPA-:STOCK_CODE/forecast/";

    public static final String URL_PRICE = "/symbols/BMFBOVESPA-:STOCK_CODE/";

    public static final String TOPIC_KAFKA_TRADINGVIEW = "trading_view";

}
