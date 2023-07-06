package com.balestech.b3scrap.service;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebDriverBalestech {

    @Autowired
    private WebDriver driver;

    public String run(String url) {
        driver.get(url);
        String html = driver.getPageSource();
        return html;
    }
}
