package com.balestech.b3web.service;

import com.balestech.b3web.model.dto.StockTargetResponse;
import com.balestech.commom.util.B3Util;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockTargetResponseService {

    public List<StockTargetResponse> calculateData(List<StockTargetResponse> listResponse){
        listResponse.forEach(stockTarget -> {
            processTargetDefinition(stockTarget);
            processLiquidez(stockTarget);
            processValuation(stockTarget);
        });
        return listResponse;
    }

    private void processTargetDefinition(StockTargetResponse stockTarget){
        List<Double> values = new ArrayList<>();
        values.add(stockTarget.getTargetXp());
        values.add(stockTarget.getTargetBTG());
        values.add(stockTarget.getTargetWsj());
        values.add(stockTarget.getTargetTrading());

        Double greater  = B3Util.greater(values);
        Double lower    = B3Util.lowerThatNotIsZero(values);
        Double price     = stockTarget.getPrice();

        if(stockTarget.getPrice() < lower){
            stockTarget.setTargetDefinition("COMPRA");
        }else if(stockTarget.getPrice() > greater){
            stockTarget.setTargetDefinition("VENDA");
        } else{
            stockTarget.setTargetDefinition("NEUTRO");
        }
    }

    private void processLiquidez(StockTargetResponse stockTarget){
        stockTarget.setLiquidez((stockTarget.getVolume() > 10000000) ? "ALTA" :
                                (stockTarget.getVolume() > 5000000)  ? "MEDIA" :
                                (stockTarget.getVolume() > 1000000)  ? "BAIXA" :
                                "AVALIAR");
    }

    private void processValuation(StockTargetResponse stockTarget){
        stockTarget.setValuation((stockTarget.getPrice() > 20) ? "BIG CAP" :
                                 (stockTarget.getPrice() > 10)  ? "ALTA" :
                                 (stockTarget.getPrice() > 5)  ? "MEDIA" :
                                 (stockTarget.getPrice() > 1)  ? "BAIXA" :
                                 "AVALIAR");
    }
}
