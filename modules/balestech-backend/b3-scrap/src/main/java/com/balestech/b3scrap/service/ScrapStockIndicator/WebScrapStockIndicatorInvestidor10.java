package com.balestech.b3scrap.service.ScrapStockIndicator;

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
@NoArgsConstructor
@Service
public class WebScrapStockIndicatorInvestidor10 implements WebScrapStockIndicator {

    private final String URL_BASE = "https://investidor10.com.br";

    private final String URL_ACOES = "/acoes/:STOCK_CODE";

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
                stockIndicator = price(documentPage, indicator);
                break;
            case VOLUME:
                stockIndicator = volume(documentPage, indicator);
                break;
            case TARGET:
                break;
        }
        return stockIndicator;
    }

    private StockIndicator price(Document documentPage, Indicator indicator){
        Elements stockPrice = documentPage.select("div[class=\"_card cotacao\"]");
        String stringValue = stockPrice.select("span.value").text().replace("R$","").trim();
        BigDecimal numberValue = B3Util.formatBigDecimal(stringValue,',','.');
        return StockIndicator.builder().
                stock(this.stock).
                indicator(indicator).
                valueNumber(numberValue).
                valueString(stringValue).
                dateTimeCreate(LocalDateTime.now()).
                localInfo(URL_BASE).build();
    }

    private StockIndicator volume(Document documentPage, Indicator indicator){
        Element elementLiquidez = documentPage.selectFirst("span.title:containsOwn(Liquidez Média Diária)");
        String stringValue = "0,00";
        if(!isNull(elementLiquidez)) {
            Element elementLiquidezValue = elementLiquidez.parent().selectFirst("div[class=\"detail-value\"]");
            if(!isNull(elementLiquidezValue))
                stringValue = elementLiquidezValue.text().replace("R$", "").trim();
        }
        BigDecimal numberValue = B3Util.formatBigDecimal(stringValue,',','.');
        return StockIndicator.builder().
                stock(this.stock).
                indicator(indicator).
                valueString(stringValue).
                valueNumber(numberValue).
                dateTimeCreate(LocalDateTime.now()).
                localInfo(URL_BASE).build();
    }



}
