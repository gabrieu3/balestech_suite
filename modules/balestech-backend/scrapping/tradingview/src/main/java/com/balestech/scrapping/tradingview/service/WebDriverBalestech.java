package com.balestech.scrapping.tradingview.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class WebDriverBalestech {

    @Autowired
    private WebDriver driver;

    public String run(String url) {
        driver.get(url);
        String html = driver.getPageSource();
        return html;
    }

    public String runWait(String url, By locator) {
        driver.get(url);
        new WebDriverWait(driver, Duration.ofSeconds (10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

        String html = driver.getPageSource();
        return html;
    }

    public String runWaitContent(String url, By locator, String text) {
        driver.get(url);
        new WebDriverWait(driver, Duration.ofSeconds (10))
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));

        String html = driver.getPageSource();
        return html;
    }
}
