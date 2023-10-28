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
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.Locator;

import java.io.IOException;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
@NoArgsConstructor
public class WebScrapStockIndicatorTradingView {

    @Autowired
    private WebDriverBalestech driver;

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

    protected Document getDocumentPage(String url, By locator, String text) {
        log.info("Scrapping: " + url);
        String html = isNull(locator) ? driver.run(url) : isNull(text) ? driver.runWait(url, locator) : driver.runWaitContent(url, locator, text);
        return Jsoup.parse(html);
    }

    private String getTarget(StockEnumTradingView stock) throws IOException {
        String url = TradingViewUtil.URL_BASE.concat(TradingViewUtil.URL_TARGET).replace(":STOCK_CODE", stock.toString());
        Document documentPage = getDocumentPage(url, null, null);
        Elements element = documentPage.select("span[class=\"highlight-maJ2WnzA highlight-Cimg1AIh price-qWcO4bp9\"]");
        return element.text().replace("R$", "").trim();
    }

    private String getPrice(StockEnumTradingView stock) throws IOException {
        String url = TradingViewUtil.URL_BASE.concat(TradingViewUtil.URL_PRICE).replace(":STOCK_CODE", stock.toString());
        By locator = By.xpath("//span[contains(@class, 'last-JWoJqCpY') and contains(@class, 'js-symbol-last')]");
        Document documentPage = getDocumentPage(url, locator, null);
        Elements element = documentPage.select("span[class=\"last-JWoJqCpY js-symbol-last\"]");
        return element.text().replace("R$", "").trim();
    }

    private String getVolume(StockEnumTradingView stock){
        return null;
    }
}
