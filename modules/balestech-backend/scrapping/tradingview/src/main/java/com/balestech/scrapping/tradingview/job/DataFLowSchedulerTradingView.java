package com.balestech.scrapping.tradingview.job;

import com.balestech.scrapping.tradingview.service.DataFlowTradingView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataFLowSchedulerTradingView {

    @Autowired
    private DataFlowTradingView dataFlowTradingView;

    /**
     * 1 - Segundos
     * 2 - Minutos
     * 3 - Horas
     * 4 - Dia do Mês
     * 5 - Indica qual o Mês do ano
     * 6 - Representa o dia da Semana
     *
     *  O Asterisco representa:
     *  A interrogação representa:
     *  todos os dias às 23 horas (meio-dia)
     */
    @Scheduled(cron = "${spring.task.scheduling.cron:30 * * * * ?}")
    public void execute() {
        dataFlowTradingView.run();
    }

}
