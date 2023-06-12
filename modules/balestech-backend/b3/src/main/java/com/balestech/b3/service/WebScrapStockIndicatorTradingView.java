package com.balestech.b3.service;

import com.balestech.b3.entity.indicator.Indicator;
import com.balestech.b3.entity.indicator.IndicatorEnum;
import com.balestech.b3.entity.indicator.IndicatorRepository;
import com.balestech.b3.entity.stock.Stock;
import com.balestech.b3.entity.stock_indicator.StockIndicator;
import com.balestech.b3.entity.stock_indicator.StockIndicatorRepository;
import com.balestech.b3.util.B3Util;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;


@Slf4j
@Service
@NoArgsConstructor
public class WebScrapStockIndicatorTradingView {

    private final String URL_BASE = "https://br.tradingview.com";

    private final String URL_ACOES = "/symbols/BMFBOVESPA-:STOCK_CODE/forecast/";

    private List<StockIndicator> stockIndicatorList;

    private Stock stock;

    private List<Indicator> indicatorList;

    private void setAttributes(Stock stock, List<Indicator> indicatorList){
        this.stock = stock;
        this.indicatorList = indicatorList;
        this.stockIndicatorList = new ArrayList<>();
    }

    public List<StockIndicator> scrap(Stock stock, List<Indicator> indicatorList){
        StockIndicator stockIndicator;
        setAttributes(stock, indicatorList);
        Document documentPage = getDocumentStockPage();

        for(Indicator indicator: indicatorList ){
            stockIndicator = getIndicator(indicator, documentPage);
            if(!isNull(stockIndicator.getStock()))
                this.stockIndicatorList.add(stockIndicator);
        }
        return stockIndicatorList;
    }

    private Document getDocumentStockPage(){
        String url = URL_BASE.concat(URL_ACOES);
        url = url.replace(":STOCK_CODE", this.stock.getName());
        Document doc;

        try {
            doc = Jsoup.connect(url).get();
            log.info("Scrapping: "+url);
        } catch (IOException e) {
            log.error("Não foi possível acessar a URL: "+url);
            throw new RuntimeException(e);
        }
        return doc;
    }

    private StockIndicator getIndicator(Indicator indicator, Document documentPage){
        IndicatorEnum indicatorEnum = IndicatorEnum.valueOf(indicator.getName());
        StockIndicator stockIndicator = StockIndicator.builder().build();
        switch (indicatorEnum){
            case PRICE:
                break;
            case VOLUME:
                break;
            case TARGET:
                stockIndicator = target(documentPage, indicator);
                break;
        }
        return stockIndicator;
    }

    private StockIndicator target(Document documentPage, Indicator indicator) {
        Elements stockPrice;
        stockPrice = documentPage.select("span[class=\"highlight-maJ2WnzA highlight-Cimg1AIh price-qWcO4bp9\"]");
        String stringValue = stockPrice.text().replace("R$","").trim();
        BigDecimal numberValue = B3Util.formatBigDecimal(stringValue,'.',',');
        return StockIndicator.builder().
                stock(this.stock).
                indicator(indicator).
                valueNumber(numberValue).
                valueString(stringValue).
                dateTimeCreate(LocalDateTime.now()).
                localInfo(URL_BASE).build();
    }

}
