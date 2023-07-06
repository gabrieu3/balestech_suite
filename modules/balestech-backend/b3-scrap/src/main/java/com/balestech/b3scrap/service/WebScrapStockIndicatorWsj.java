package com.balestech.b3scrap.service;

import com.balestech.b3scrap.entity.indicator.Indicator;
import com.balestech.b3scrap.entity.indicator.IndicatorEnum;
import com.balestech.b3scrap.entity.stock.Stock;
import com.balestech.b3scrap.entity.stock_indicator.StockIndicator;
import com.balestech.commom.util.B3Util;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
public class WebScrapStockIndicatorWsj implements SiteScraper {

    private final String URL_BASE = "https://www.wsj.com";

    private final String URL_ACOES = "/market-data/quotes/BR/:STOCK_CODE/research-ratings";

    private List<StockIndicator> stockIndicatorList;

    private Stock stock;

    private List<Indicator> indicatorList;

    private void setAttributes(Stock stock, List<Indicator> indicatorList){
        this.stock = stock;
        this.indicatorList = indicatorList;
        this.stockIndicatorList = new ArrayList<>();
    }

    @Override
    public List<StockIndicator> scrap(Stock stock, List<Indicator> indicatorList){
        try {
            StockIndicator stockIndicator;
            setAttributes(stock, indicatorList);
            Document documentPage = getDocumentStockPage();

            for(Indicator indicator: indicatorList ){
                stockIndicator = getIndicator(indicator, documentPage);
                if(!isNull(stockIndicator.getStock()))
                    this.stockIndicatorList.add(stockIndicator);
            }
        }catch(Exception e){
            log.error("Falha no scrap da ação: " + stock.getName() + ". Erro: "+e.getMessage());
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
        Element stockPriceTable = documentPage.selectFirst("div[class=\"cr_data rr_stockprice module\"]");
        Elements stockPriceElements = stockPriceTable.select("span[class=\"data_data\"]");
        String stringValue = stockPriceElements.get(3).text().trim();
        String stringValueOrig = stringValue;
        if(stringValue.contains("R$"))
            stringValue = stringValue.replace("R$","").trim();
        else
            stringValue = stringValue.replace("$","").trim();

        BigDecimal numberValue = B3Util.formatBigDecimal(stringValue,'.',',');
        return StockIndicator.builder().
                stock(this.stock).
                indicator(indicator).
                valueNumber(numberValue).
                valueString(stringValueOrig).
                dateTimeCreate(LocalDateTime.now()).
                localInfo(URL_BASE).build();
    }

}
