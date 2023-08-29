package com.balestech.scrapping.tradingview.service;


import com.balestech.commom.domain.indicator.IndicatorEnum;
import com.balestech.commom.domain.stock.StockEnum;
import com.balestech.commom.util.B3Util;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


@Slf4j
@Service
@NoArgsConstructor
public class WebScrapStockIndicatorTradingView {

    private final String URL_BASE = "https://br.tradingview.com";

    private final String URL_ACOES = "/symbols/BMFBOVESPA-:STOCK_CODE/forecast/";

    public String scrap(StockEnum stock, List<IndicatorEnum> indicatorList) {
        try {
            Document documentPage = getDocumentPage(stock);

            Elements stockPrice;
            stockPrice = documentPage.select("span[class=\"highlight-maJ2WnzA highlight-Cimg1AIh price-qWcO4bp9\"]");
            String stringValue = stockPrice.text().replace("R$", "").trim();
            BigDecimal numberValue = B3Util.formatBigDecimal(stringValue, '.', ',');

            log.info("SCRAPPING -------------------> " + stock.toString() + " <------------>  " + stringValue);

        } catch (Exception e) {
            log.error("Falha no scrap da ação: " + stock.toString() + ". Erro: " + e.getMessage());
        }
        return "Success";
    }

    private Document getDocumentPage(StockEnum stock) {
        String url = URL_BASE.concat(URL_ACOES);
        url = url.replace(":STOCK_CODE", stock.toString());
        Document doc;

        try {
            doc = Jsoup.connect(url).get();
            log.info("Scrapping: " + url);
        } catch (IOException e) {
            log.error("Não foi possível acessar a URL: " + url);
            throw new RuntimeException(e);
        }
        return doc;
    }
}
