package com.ss.basepage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Selenium {

    protected WebDriver driver;

    public Selenium(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public List<WebElement> getElementsByXpath(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    public List<WebElement> getElementsByCss(String css) {
        return driver.findElements(By.cssSelector(css));
    }

    public void scrollTo(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();" ,element);
    }

    public void open(String url) {
        driver.get(url);
    }

}
