package com.balestech.b3scrap.service.job;


import com.balestech.b3scrap.service.WebScraper;
import com.balestech.b3scrap.web.enum_web.SiteScraperEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WebScraperScheduler {

    @Autowired
    private WebScraper webScraper;

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
    @Scheduled(cron = "${spring.task.scheduling.cron:0 0 23 * * ?}")
    public void execute() {
        webScraper.scrap(SiteScraperEnum.ALL);
    }
}
