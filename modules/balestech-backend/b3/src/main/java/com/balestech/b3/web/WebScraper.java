package com.balestech.b3.web;

import com.balestech.b3.entity.indicator.Indicator;
import com.balestech.b3.entity.indicator.IndicatorRepository;
import com.balestech.b3.entity.stock.Stock;
import com.balestech.b3.entity.stock.StockRepository;
import com.balestech.b3.entity.stock_indicator.StockIndicator;
import com.balestech.b3.entity.stock_indicator.StockIndicatorRepository;
import com.balestech.b3.service.WebScrapStockIndicatorBtgPactual;
import com.balestech.b3.service.WebScrapStockIndicatorInvestidor10;
import com.balestech.b3.service.WebScrapStockIndicatorTradingView;
import com.balestech.b3.web.enum_web.SiteScraperEnum;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class WebScraper {

    private final String ATIVO = "ATIVO";

    @Autowired
    private WebScrapStockIndicatorInvestidor10 investidor10;

    @Autowired
    private WebScrapStockIndicatorTradingView tradingView;

    @Autowired
    private WebScrapStockIndicatorBtgPactual btgPactual;
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Autowired
    private StockIndicatorRepository stockIndicatorRepository;

    public void scrap(SiteScraperEnum site){
        List<StockIndicator> stockIndicatorList = new ArrayList<>();

        List<Stock> stockList = stockRepository.findByStatus(ATIVO);
        List<Indicator> indicatorList = indicatorRepository.findAll();
        List <StockIndicator> listIndicator = new ArrayList<>();

        for(Stock stock: stockList){
            try {
                if(site.equals(SiteScraperEnum.INVESTIDOR10) || site.equals(SiteScraperEnum.ALL))
                    listIndicator = investidor10.scrap(stock, indicatorList);
                    if(listIndicator.size() > 0)
                        stockIndicatorList.addAll(listIndicator);
                if(site.equals(SiteScraperEnum.TRADING_VIEW) || site.equals(SiteScraperEnum.ALL))
                    listIndicator =tradingView.scrap(stock, indicatorList);
                    if(listIndicator.size() > 0)
                        stockIndicatorList.addAll(listIndicator);
                if(site.equals(SiteScraperEnum.BTG_PACTUAL) || site.equals(SiteScraperEnum.ALL))
                    listIndicator = btgPactual.scrap(stock, indicatorList);
                    if(listIndicator.size() > 0)
                        stockIndicatorList.addAll(listIndicator);
            }catch(Exception e){
                log.error("Falha no scrap da ação: " + stock.getName() + ". Erro: "+e.getMessage());
            }
        }
        stockIndicatorRepository.saveAll(stockIndicatorList);

    }

    public void scrapActivate(){
        List<Stock> stockList = stockRepository.findAll();
        for(Stock stock: stockList){
            try {
                String url = "https://www.google.com/finance/quote/" + stock.getName() + ":BVMF";
                log.info(url);
                Document doc = Jsoup.connect(url).get();
                Element element = doc.selectFirst("div[class=\"b4EnYd\"]");
                if(!isNull(element)) {
                    Boolean status = element.text().trim().contains("We couldn't find any match for your search.");
                    if(status) {
                        stock.setStatus("INATIVO");
                        stockRepository.save(stock);
                    }
                }
            }catch(Exception e){
                log.error("Falha no scrap da ação: " + stock.getName() + ". Erro: "+e.getMessage());
            }
        }
    }


    public void scrapActivateLiquibase(){
        List<Stock> stockList = stockRepository.findAll();
        for(Stock stock: stockList){
            String str = "        - insert:\n" +
                         "            tableName: stock\n" +
                         "            columns:\n" +
                         "              - column:\n" +
                         "                  name: name\n" +
                         "                  value: "+stock.getName()+"\n" +
                         "              - column:\n" +
                         "                  name: company\n" +
                         "                  value: "+stock.getCompany()+"\n" +
                         "              - column:\n" +
                         "                  name: status\n" +
                         "                  value: "+stock.getStatus()+"\n";
            System.out.println(str);
        }
    }

}
