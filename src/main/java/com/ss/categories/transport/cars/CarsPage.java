package com.ss.categories.transport.cars;

import com.ss.basepage.BasePage;
import com.ss.basepage.Selenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CarsPage extends BasePage {

    public CarsPage(Selenium selenium) {
        super(selenium);
    }

    @FindBy(xpath = "//input[@name='topt[8][min]']")
    WebElement getMinPriceInput;

    @FindBy(xpath = "//input[@name='topt[8][max]']")
    WebElement getMaxPriceInput;

    @FindBy(xpath = "//input[@value='Search']")
    WebElement getSearchButton;

    @FindBy(xpath = "//a[@title='Volkswagen, Announcements']")
    WebElement getVwAnnoncements;

    @FindBy(xpath = "//a[@title='Moskvich, Announcements']")
    WebElement getMoskvichAnnoncements;

    private void setMinPrice(int price) {
        getMinPriceInput.sendKeys(String.valueOf(price));
    }

    private void setMaxPrice(int price) {
        getMaxPriceInput.sendKeys(String.valueOf(price));
    }

    public void filterCarsByPrices(int minPrice, int maxPrice) {
        setMinPrice(minPrice);
        setMaxPrice(maxPrice);
        getSearchButton.click();
    }

    public void selectVwAnnouncements() {
        getVwAnnoncements.click();
    }


    public void selectMoskvichCategory() {
        getMoskvichAnnoncements.click();
    }
}
