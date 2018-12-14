package com.ss.basepage;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class Selenium {

    protected WebDriver driver;

    public Selenium(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public WebElement getElementByCss(String css) { return driver.findElement(By.cssSelector(css)); }

    public WebElement getElementByLinkText(String linkText) {return driver.findElement(By.linkText(linkText));}

    public List<WebElement> getElementsByXpath(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    public List<WebElement> getElementsByCss(String css) {
        return driver.findElements(By.cssSelector(css));
    }

    public void waitAndClick(WebElement element) {
        WebDriverWait waitForOne = new WebDriverWait(driver, 10);
        waitForOne.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void waitVisibilityOf(WebElement element) {
        WebDriverWait waitForUploading = new WebDriverWait(driver, 10);
        waitForUploading.until(ExpectedConditions.visibilityOf(element));
    }

    public void scrollTo(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();" ,element);
    }

    public void scrollUp() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
    }

    public void scrollDown() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight,5000)");
    }

    public void doubleClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }

    public void open(String url) {
        driver.get(url);
    }

    public String getCurrentURL() { return driver.getCurrentUrl(); }

    public Actions actions() {
        return new Actions(driver);
    }

    public Selenium rightClick(WebElement element) {
        Actions action = new Actions(driver).contextClick(element);
        action.build().perform();
        return this;
    }

    public File takeScreenshot() {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    }

}
