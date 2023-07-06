package com.balestech.b3scrap.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class WebScrapInvestidor10 {
    private final String URL_BASE = "https://investidor10.com.br";

    private final String URL_ACOES_PAGE = "/acoes/?page=:PAGE";

    private final String URL_ACOES = "/acoes/:STOCK_CODE";

    private final Integer QTDE_PAGES_ACOES = 16;

    private void run(){

        Document documentPage;
        Elements stockList;

        for(int page=1; page<=QTDE_PAGES_ACOES; page++) {
            documentPage = getDocumentPage(page);
            stockList = getStockList(documentPage);

            for (Element stock : stockList) {
                saveStockIndicators(stock);
            }
        }
    }

    private Document getDocumentPage(Integer page){
        String url = URL_BASE.concat(URL_ACOES);
        url = url.replace(":PAGE",page.toString());
        Document doc;

        try {
            doc = Jsoup.connect(url)
                    .cookie("only_main", "false")
                    .get();
        } catch (IOException e) {
            log.error("Não foi possível acessar a URL: "+url);
            throw new RuntimeException(e);
        }
        return doc;
    }

    private Elements getStockList(Document documentPage){
        return documentPage.select("div.actions");
    }

    private void saveStockIndicators(Element stockElement) {
        String[] title = stockElement.select("h3.actions-title").text().split("-");
        Elements stockElements = stockElement.select("span");
        String stockCode = title[title.length - 1].trim();
        System.out.println(
                stockCode + ";" +
                        nameCompany(title) + ";" +
                        stockElements.get(1).text() + ";" +
                        stockElements.get(3).text() + ";" +
                        stockElements.get(5).text() + ";" +
                        stockElements.get(7).text() + ";" +
                        alvo(stockCode) /*+ ";" +
                            volume(stockCode)*/);
    }

    private String nameCompany(String[] title){
        StringBuilder nameComp = new StringBuilder();
        if(title.length > 1) {
            for (int i = 0; i < title.length - 1; i++) {
                nameComp.append(title[i]);
            }
        }else{
            nameComp.append(title[0]);
        }
        return nameComp.toString();
    }

    private String cotacao(String stock) throws IOException {
        String url = "https://investidor10.com.br/acoes/"+stock.trim();
        Document doc = Jsoup.connect(url).get();
        Elements stockPrice = doc.select("div[class=\"_card cotacao\"]");
        return stockPrice.select("span.value").text().replace("R$","").trim();
    }

    private String alvo(String stock) {
        Elements stockPrice;
        try {
            String url = "https://br.tradingview.com/symbols/BMFBOVESPA-" + stock.trim() + "/forecast/";
            Document doc = Jsoup.connect(url).get();
            stockPrice = doc.select("span[class=\"highlight-maJ2WnzA highlight-Cimg1AIh price-qWcO4bp9\"]");
        }catch(IOException e){
            return "0,00";
        }
        return stockPrice.text().trim();
    }

    private String volume(String stock) {
        Elements stockVolume;
        try {
            String url = "https://www.b3.com.br/pt_br/market-data-e-indices/servicos-de-dados/market-data/cotacoes/?tvwidgetsymbol=BMFBOVESPA%3A" + stock.trim();
            Document doc = Jsoup.connect(url).get();
            stockVolume = doc.select("span[id=\"x-volume-pregao\"]");
        }catch(IOException e){
            return "0,00";
        }
        return stockVolume.text().trim().replace("R$","");
    }

}
