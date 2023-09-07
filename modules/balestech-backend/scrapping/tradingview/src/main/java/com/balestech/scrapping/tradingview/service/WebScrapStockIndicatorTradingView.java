package com.balestech.scrapping.tradingview.service;


import com.balestech.commom.domain.indicator.IndicatorEnum;
import com.balestech.commom.domain.stock.StockEnum;
import com.balestech.scrapping.tradingview.domain.dto.StockEnumTradingView;
import com.balestech.scrapping.tradingview.domain.dto.StockIndicatorTradingViewDTO;
import com.balestech.scrapping.tradingview.util.TradingViewUtil;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@NoArgsConstructor
public class WebScrapStockIndicatorTradingView {

    public StockIndicatorTradingViewDTO scrap(StockEnumTradingView stock, List<IndicatorEnum> indicatorList) {
        StockIndicatorTradingViewDTO scrapIndicator = new StockIndicatorTradingViewDTO();

        try {
            String target = null;
            String price = null;
            String volume = null;

            for (IndicatorEnum indicator : indicatorList) {
                switch (indicator) {
                    case PRICE:
                        price = getPrice(stock);
                        break;
                    case TARGET:
                        target = getTarget(stock);
                        break;
                    case VOLUME:
                        volume = getVolume(stock);
                        break;
                }
            }

            scrapIndicator = StockIndicatorTradingViewDTO.builder()
                    .stock(stock)
                    .target(target)
                    .price(price)
                    .volume(volume)
                    .build();
        } catch (Exception e) {
            log.error("Falha no scrap da ação: " + stock.toString() + ". Erro: " + e.getMessage());
        }
        return scrapIndicator;
    }

    private Document getDocumentPage(String url) throws IOException {
        log.info("Scrapping: " + url);
        return Jsoup.connect(url).get();
    }

    private String getTarget(StockEnumTradingView stock) throws IOException {
        String url = TradingViewUtil.URL_BASE.concat(TradingViewUtil.URL_TARGET).replace(":STOCK_CODE", stock.toString());
        Document documentPage = getDocumentPage(url);
        Elements element = documentPage.select("span[class=\"highlight-maJ2WnzA highlight-Cimg1AIh price-qWcO4bp9\"]");
        return element.text().replace("R$", "").trim();
    }

    private String getPrice(StockEnumTradingView stock) throws IOException {
        String url = TradingViewUtil.URL_BASE.concat(TradingViewUtil.URL_PRICE).replace(":STOCK_CODE", stock.toString());
        Document documentPage = getDocumentPage(url);
        Elements element = documentPage.select("span[class=\"last-JWoJqCpY js-symbol-last\"]");
        return element.text().replace("R$", "").trim();
    }

    private String getVolume(StockEnumTradingView stock){
        return null;
    }
}
