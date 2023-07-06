package com.balestech.b3scrap.controller;

import com.balestech.b3scrap.service.WebScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activate")
public class WebScraperAcoesActivate {

    @Autowired
    private WebScraper webScraper;

    @GetMapping
    public String scrap(){
        webScraper.scrapActivate();
        return "OK";
    }

    @GetMapping("liquibase")
    public String generate(){
        webScraper.scrapActivateLiquibase();
        return "OK";
    }
}
