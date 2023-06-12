package com.balestech.b3.controller;

import com.balestech.b3.web.WebScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scrap")
public class WebScraperController {

    @Autowired
    private WebScraper webScraper;

    @GetMapping
    public String scrap(){
        webScraper.scrap();
        return "OK";
    }
}
