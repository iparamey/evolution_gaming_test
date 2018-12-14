package com.ss.basepage;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasePage {

    public Selenium selenium;

    public BasePage(Selenium selenium) {
        PageFactory.initElements(selenium.driver, this);
        this.selenium = selenium;
    }

    @FindBy(id = "a_fav_sel")
    public WebElement getAdToFavoritesLink;

    @FindBy(id = "alert_ok")
    public WebElement getAlertOkButton;

    @FindBy(xpath = "//a[@title='Memo']")
    WebElement getMemoLink;

    @FindBy(xpath = "//span[@id='mnu_fav_id']")
    WebElement getMemoCounter;

    @FindBy(id = "sel_cnt_obj")
    WebElement getSelectedCounter;

    @FindBy(xpath = "//a[@title='Search announcements']")
    WebElement getSearchLink;

    public WebElement getElementByXpath(String xpath) {
        return selenium.getElementByXpath(xpath);
    }

    public List<WebElement> getElementsByXpath(String xpath) {
        return selenium.getElementsByXpath(xpath);
    }

    public List<WebElement> getElementsByCss(String css) {return selenium.getElementsByCss(css); }

    public void open(String url) {
        selenium.open(url);
    }

    public void scrollToElement(WebElement element) {
        selenium.scrollTo(element);
    }

    private int getCounter(String str) {
        String counter = str.replaceAll("[(]", "").replaceAll("[)]", "").replace(" ", "");
        return Integer.parseInt(counter);
    }

    public void openFavoritesPage() {
        getMemoLink.click();
    }

    public void openSearchPage() {
        getSearchLink.click();
    }

    public int getMemoCounter() {
        return getCounter(getMemoCounter.getText());
    }

    public int addSelectedAdsToFavorites() {
        scrollToElement(getAdToFavoritesLink);
        int result = getCounter(getSelectedCounter.getText());
        getAdToFavoritesLink.click();
        getAlertOkButton.click();
        return result;
    }

}
