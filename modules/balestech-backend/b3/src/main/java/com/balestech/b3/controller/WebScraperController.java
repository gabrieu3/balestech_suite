package com.balestech.b3.controller;

import com.balestech.b3.web.WebScraper;
import com.balestech.b3.web.enum_web.SiteScraperEnum;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scrap")
public class WebScraperController {

    @Autowired
    private WebScraper webScraper;

    @GetMapping("/{site}")
    public String scrap(@PathVariable("site") String site){
        webScraper.scrap(SiteScraperEnum.valueOf(site));
        return "OK";
    }
}
