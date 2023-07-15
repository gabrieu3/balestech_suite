package com.balestech.b3scrap.service.ScrapStockIndicator;

import com.balestech.b3scrap.entity.indicator.Indicator;
import com.balestech.b3scrap.entity.stock.Stock;
import com.balestech.b3scrap.entity.stock_indicator.StockIndicator;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
public abstract class  WebScrapStockIndicatorAbstract implements WebScrapStockIndicator{

    @Override
    public List<StockIndicator> scrap(Stock stock, List<Indicator> indicatorList){
        List<StockIndicator> stockIndicatorList = new ArrayList<>();
        try {
            StockIndicator stockIndicator;
            Document documentPage = getDocumentStockPage(stock);
            for(Indicator indicator: indicatorList ){
                stockIndicator = getIndicator(indicator, stock, documentPage);
                if(!isNull(stockIndicator.getStock()))
                    stockIndicatorList.add(stockIndicator);
            }
        }catch(Exception e){
            log.error("Falha no scrap da ação: " + stock.getName() + ". Erro: "+e.getMessage());
        }
        return stockIndicatorList;
    }

    protected abstract StockIndicator getIndicator(Indicator indicator, Stock stock,  Document documentPage);

    protected abstract Document getDocumentStockPage(Stock stock);
}
